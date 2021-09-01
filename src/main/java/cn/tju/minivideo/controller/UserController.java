package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Paginator;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.JwtUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.UserDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.LoginRecord;
import cn.tju.minivideo.entity.Relation;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.LoginRecordService;
import cn.tju.minivideo.service.RelationService;
import cn.tju.minivideo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LoginRecordService loginRecordService;

    @Autowired
    private RelationService relationService;

    @PostMapping("register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody @Validated(ValidationGroups.Insert.class) UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            FieldError error = bindingResult.getFieldError();
            String defaultError = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ControllerException(MsgEnums.VALIDATION_ERROR.code(), defaultError);
        }
        if (userService.isExistByUsername(userDto.getUsername())) {
            throw new ControllerException(MsgEnums.USERNAME_EXIST.code(), MsgEnums.USERNAME_EXIST.desc());
        }
        User user = modelMapper.map(userDto, User.class);
        UUID uuid = UUID.randomUUID();
        user.setUserId(uuid.toString());
        user.setStatus(Constants.UserConst.StatusNormal);
        userService.insertSelective(user);
        return Results.OkWithData(user);
    }

    @PostMapping("login")
    @ApiOperation("用户登陆")
    public Result login(@RequestBody @Validated(ValidationGroups.Insert.class) UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String defaultError = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ControllerException(MsgEnums.VALIDATION_ERROR.code(), defaultError);
        }
        User user = userService.checkUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        String token = JwtUtil.createToken(user);
        loginRecordService.insertSelective(new LoginRecord(user.getUserId(), token));
        return Results.OkWithToken(token);
    }

    @PostMapping("update_profile")
    @AuthRequired
    @ApiOperation("修改用户信息")
    public Result updateUserProfile(@RequestBody @Validated({ValidationGroups.Update.class}) UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String defaultError = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ControllerException(MsgEnums.VALIDATION_ERROR.code(), defaultError);
        }
        User user = modelMapper.map(userDto, User.class);
        String userId = JwtInterceptor.getUser().getUserId();
        user.setUserId(userId);
        log.info("update user: {}", user);
        Integer sex = user.getSex();
        if (sex != null && (!sex.equals(Constants.UserConst.SexMale) && !sex.equals(Constants.UserConst.SexFemale))){
            throw new ControllerException(MsgEnums.USER_PROFILE_ERROR);
        }
        userService.updateByPrimaryKeySelective(user);
        return Results.OkWithData(user);
    }

    @PostMapping("follow")
    @ApiOperation("关注用户")
    @AuthRequired
    @Transactional
    public Result followUser(@RequestBody @Validated({ValidationGroups.UserIdForm.class}) UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String defaultError = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ControllerException(MsgEnums.VALIDATION_ERROR.code(), defaultError);
        }
        User toUser = modelMapper.map(userDto, User.class);
        String fromId = JwtInterceptor.getUser().getUserId();
        if (relationService.isExistRelation(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation)){
            throw new ControllerException(MsgEnums.RELATION_HAS_EXIST);
        }
        Relation relation = new Relation(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation);
        relationService.insertSelective(relation);
        userService.updateUserFollowNumByAction(toUser.getUserId(), UserService.FollowUserAction.followUser);
        return Results.Ok();
    }

    @PostMapping("unfollow")
    @ApiOperation("取消关注用户")
    @AuthRequired
    @Transactional
    public Result unFollowUser(@RequestBody @Validated({ValidationGroups.UserIdForm.class}) UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String defaultError = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ControllerException(MsgEnums.VALIDATION_ERROR.code(), defaultError);
        }
        User toUser = modelMapper.map(userDto, User.class);
        String fromId = JwtInterceptor.getUser().getUserId();
        if (!relationService.isExistRelation(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation)){
            throw new ControllerException(MsgEnums.RELATION_NOT_EXIST);
        }
        if (relationService.deleteRelationByFromIdAndToIdAndRelationType(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation) != 1){
            throw new ControllerException(MsgEnums.INTERNAL_ERROR);
        }
        userService.updateUserFollowNumByAction(toUser.getUserId(), UserService.FollowUserAction.unFollowUser);
        return Results.Ok();
    }

    @GetMapping("follows")
    @ApiOperation("获取关注的用户")
    @AuthRequired
    public Result getFollowUsers(@RequestParam(value = "page", defaultValue = "1") Integer page){
        User user = JwtInterceptor.getUser();
        PageInfo<Relation> pageInfo = relationService.findRelationsByFromIdAndRelationTypeWithPaginator(user.getUserId(), Constants.RelationConst.FollowRelation, page, ProjectConstant.PageSize);
        List<Relation> relations = pageInfo.getList();
        List<String> userIds = new ArrayList<>();
        relations.forEach((relation -> userIds.add(relation.getToId())));
        Paginator paginator = new Paginator(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getSize(), pageInfo.getPages(), userIds);
        return Results.OkWithData(paginator);
    }

    @GetMapping("is_follow")
    @ApiOperation("获取用户是否已经关注")
    @AuthRequired
    public Result getIsFollow(@RequestParam(value = "userId", required = false) String userId){
        if (userId == null || userId.equals("")){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        String fromId = JwtInterceptor.getUser().getUserId();
        boolean isExist = relationService.isExistRelation(fromId, userId, Constants.RelationConst.FollowRelation);
        return Results.OkWithData(isExist);
    }
}

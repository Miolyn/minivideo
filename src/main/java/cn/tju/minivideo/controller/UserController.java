package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.*;
import cn.tju.minivideo.dto.AddressDto;
import cn.tju.minivideo.dto.HistoryDto;
import cn.tju.minivideo.dto.UserDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.*;
import cn.tju.minivideo.service.*;
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

    @Autowired
    private AddressService addressService;

    @Autowired
    private HistoryService historyService;

    @PostMapping("register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody @Validated(ValidationGroups.Insert.class) UserDto userDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
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
        BindUtil.checkBindValid(bindingResult);
        User user = userService.checkUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        String token = JwtUtil.createToken(user);
        loginRecordService.insertSelective(new LoginRecord(user.getUserId(), token));
        return Results.OkWithToken(token);
    }

    @PostMapping("update_profile")
    @AuthRequired
    @ApiOperation("修改用户信息")
    public Result updateUserProfile(@RequestBody @Validated({ValidationGroups.Update.class}) UserDto userDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
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

    @GetMapping("user_info")
    @ApiOperation("获取用户信息")
    @AuthRequired(required = false)
    public Result getUserInfo(@RequestParam(value = "userId", defaultValue = "") String userId){
        if(userId.equals("")){
            User user = JwtInterceptor.getUser();
            if (user == null){
                throw new ControllerException(MsgEnums.VALIDATION_ERROR);
            }
            userId = user.getUserId();
        }
        User user = userService.findByUserId(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return Results.OkWithData(userDto);
    }


    @PostMapping("follow")
    @ApiOperation("关注用户")
    @AuthRequired
    @Transactional
    public Result followUser(@RequestBody @Validated({ValidationGroups.IdForm.class}) UserDto userDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        User toUser = modelMapper.map(userDto, User.class);
        String fromId = JwtInterceptor.getUser().getUserId();
        if (relationService.isExistRelation(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation)){
            throw new ControllerException(MsgEnums.RELATION_HAS_EXIST);
        }
        Relation relation = new Relation(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation);
        relationService.insertSelective(relation);
        userService.updateUserFollowNumByAction(toUser.getUserId(), UserService.FollowUserAction.followUser);
        userService.updateUserFansNumByAction(fromId, UserService.FollowUserAction.followUser);
        return Results.Ok();
    }

    @PostMapping("unfollow")
    @ApiOperation("取消关注用户")
    @AuthRequired
    @Transactional
    public Result unFollowUser(@RequestBody @Validated({ValidationGroups.IdForm.class}) UserDto userDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        User toUser = modelMapper.map(userDto, User.class);
        String fromId = JwtInterceptor.getUser().getUserId();
        if (!relationService.isExistRelation(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation)){
            throw new ControllerException(MsgEnums.RELATION_NOT_EXIST);
        }
        if (relationService.deleteRelationByFromIdAndToIdAndRelationTypeLogical(fromId, toUser.getUserId(), Constants.RelationConst.FollowRelation) != 1){
            throw new ControllerException(MsgEnums.INTERNAL_ERROR);
        }
        userService.updateUserFollowNumByAction(toUser.getUserId(), UserService.FollowUserAction.unFollowUser);
        userService.updateUserFansNumByAction(fromId, UserService.FollowUserAction.unFollowUser);
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
//        Paginator paginator = new Paginator(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getSize(), pageInfo.getPages(), userIds);
        return Results.OkWithData(Paginators.paginator(pageInfo, userIds));
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

    @GetMapping("fans")
    @ApiOperation("获取粉丝列表")
    @AuthRequired
    public Result getUserFans(@RequestParam(value = "page", defaultValue = "1") Integer page){
        User user = JwtInterceptor.getUser();
        PageInfo<Relation> pageInfo = relationService.findRelationsByToIdAndRelationTypeWithPaginator(user.getUserId(), Constants.RelationConst.FollowRelation, page, ProjectConstant.PageSize);
        List<String> userIds = new ArrayList<>();
        pageInfo.getList().forEach(relation -> userIds.add(relation.getFromId()));
        return Results.OkWithData(Paginators.paginator(pageInfo, userIds));
    }

    @PostMapping("add_address")
    @ApiOperation("添加地址")
    @AuthRequired
    public Result addAddress(@RequestBody @Validated(ValidationGroups.Insert.class)AddressDto addressDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        String userId = JwtInterceptor.getUser().getUserId();
        Address address = modelMapper.map(addressDto, Address.class);
        address.setUserId(userId);
        addressService.insertSelective(address);
        return Results.OkWithData(address.getAddressId());
    }

    @GetMapping("user_address")
    @ApiOperation("获取该用户所有地址，分页")
    @AuthRequired
    public Result getAddress(@RequestParam(value = "page", defaultValue = "1") Integer page){
        String userId = JwtInterceptor.getUser().getUserId();
        PageInfo<Address> pageInfo = addressService.getAddressByUserIdWithPaginator(userId, page, ProjectConstant.PageSize);
        List<AddressDto> data = new ArrayList<>();
        pageInfo.getList().forEach(address -> data.add(modelMapper.map(address, AddressDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

    // TODO: 返回id
    @GetMapping("histories")
    @ApiOperation("获取浏览历史")
    @AuthRequired
    public Result getHistories(@RequestParam(value = "itemType", defaultValue = "-1") Integer itemType,
                               @RequestParam(value = "page", defaultValue = "1") Integer page){
        if(itemType.equals(-1) || !ConstUtil.isHistoryTypeValid(itemType)){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        String userId = JwtInterceptor.getUser().getUserId();
        PageInfo<History> pageInfo = historyService.getHistoriesByItemIdAndUserIdWithPaginatorOrderByTimeDesc(itemType, userId, page, ProjectConstant.PageSize);
        List<HistoryDto> data = new ArrayList<>();
        pageInfo.getList().forEach(history -> data.add(modelMapper.map(history, HistoryDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }
}

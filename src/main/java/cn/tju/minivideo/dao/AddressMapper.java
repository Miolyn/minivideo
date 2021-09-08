package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<Address> findByUserId(@Param("userId")String userId);

    Address findByAddressId(@Param("addressId")Integer addressId);

}
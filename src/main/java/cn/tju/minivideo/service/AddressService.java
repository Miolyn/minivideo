package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Address;
import com.github.pagehelper.PageInfo;

public interface AddressService {


    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    PageInfo<Address> getAddressByUserIdWithPaginator(String userId, Integer page, Integer pageSize);

    boolean isExistByAddressId(Integer addressId);
}


package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Address;

public interface AddressService {


    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

}


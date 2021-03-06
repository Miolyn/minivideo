package cn.tju.minivideo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.Address;
import cn.tju.minivideo.dao.AddressMapper;
import cn.tju.minivideo.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public int deleteByPrimaryKey(Integer addressId) {
        return addressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public int insert(Address record) {
        return addressMapper.insert(record);
    }

    @Override
    public int insertSelective(Address record) {
        return addressMapper.insertSelective(record);
    }

    @Override
    public Address selectByPrimaryKey(Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public int updateByPrimaryKeySelective(Address record) {
        return addressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Address record) {
        return addressMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Address> getAddressByUserIdWithPaginator(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(addressMapper.findByUserId(userId));
    }

    @Override
    public boolean isExistByAddressId(Integer addressId) {

        return addressMapper.findByAddressId(addressId) != null;
    }

}


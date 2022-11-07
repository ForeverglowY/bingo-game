package com.fc.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.management.entity.Type;
import com.fc.management.service.TypeService;
import com.fc.management.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public boolean isExist(Type type) {
        String name = type.getName();
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Type one = typeMapper.selectOne(wrapper);
        return one != null;
    }

    @Override
    @Cacheable(value = "type", key = "'selectTypeList'")
    public List<Type> getList() {
        return typeMapper.selectList(null);
    }
}





package com.fc.management.mapper;

import com.fc.management.entity.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Entity com.fc.management.entity.Type
 */
public interface TypeMapper extends BaseMapper<Type> {

}





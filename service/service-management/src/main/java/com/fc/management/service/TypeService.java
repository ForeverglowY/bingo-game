package com.fc.management.service;

import com.fc.management.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TypeService extends IService<Type> {

    boolean isExist(Type type);

    List<Type> getList();
}

package com.fc.management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.management.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author V-Savior
* @description 针对表【game】的数据库操作Service
* @createDate 2022-11-03 11:52:41
*/
public interface GameService extends IService<Game> {

    String saveInfo(Game game);

    Map<String, Object> getGameFrontList(Page<Game> teacherPage);
}

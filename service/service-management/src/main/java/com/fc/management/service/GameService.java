package com.fc.management.service;

import com.fc.management.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author V-Savior
* @description 针对表【game】的数据库操作Service
* @createDate 2022-11-03 11:52:41
*/
public interface GameService extends IService<Game> {

    String saveInfo(Game game);
}

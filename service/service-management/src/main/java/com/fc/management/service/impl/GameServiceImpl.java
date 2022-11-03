package com.fc.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.management.entity.Game;
import com.fc.management.service.GameService;
import com.fc.management.mapper.GameMapper;
import org.springframework.stereotype.Service;

/**
* @author V-Savior
* @description 针对表【game】的数据库操作Service实现
* @createDate 2022-11-03 11:52:41
*/
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game>
    implements GameService{

}





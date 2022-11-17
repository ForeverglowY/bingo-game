package com.fc.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.management.entity.Game;
import com.fc.management.service.GameService;
import com.fc.management.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author V-Savior
* @description 针对表【game】的数据库操作Service实现
* @createDate 2022-11-03 11:52:41
*/
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game>
    implements GameService{

    @Autowired
    private GameMapper gameMapper;

    @Override
    public String saveInfo(Game game) {
        int insert = gameMapper.insert(game);
        if (insert < 1) {
            return null;
        } else {
            return game.getId();
        }
    }

    @Override
    public Map<String, Object> getGameFrontList(Page<Game> gamePage) {
        QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        //把分页数据封装到gamePage对象
        gameMapper.selectPage(gamePage, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("items",gamePage.getRecords());
        map.put("current",gamePage.getCurrent());
        map.put("pages", gamePage.getPages());
        map.put("size", gamePage.getSize());
        map.put("total", gamePage.getTotal());
        map.put("hasNext", gamePage.hasNext());
        map.put("hasPrevious", gamePage.hasPrevious());
        return map;
    }
}





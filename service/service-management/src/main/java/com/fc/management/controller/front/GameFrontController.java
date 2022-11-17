package com.fc.management.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.R;
import com.fc.management.entity.Game;
import com.fc.management.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Everglow
 * Created at 2022/11/14 14:51
 */
@RestController
@RequestMapping("/management/game/front")
public class GameFrontController {

    @Autowired
    private GameService gameService;


    /**
     * 分页查询游戏的方法
     */
    @PostMapping("/gameList/{page}/{limit}")
    public R teacherList(@PathVariable("page") long page,
                         @PathVariable("limit") long limit) {
        Page<Game> teacherPage = new Page<>(page, limit);
        Map<String, Object> map = gameService.getGameFrontList(teacherPage);

        //返回分页所有数据
        return R.ok().data(map);
    }
}

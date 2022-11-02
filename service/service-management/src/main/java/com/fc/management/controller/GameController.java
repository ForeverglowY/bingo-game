package com.fc.management.controller;

import com.fc.commonutils.R;
import com.fc.management.entity.Game;
import com.fc.management.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Everglow
 * Created at 2022/11/02 16:46
 */
@RestController
@RequestMapping("/management/game")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * 添加游戏
     */
    public R saveGame(@RequestBody Game game) {
        return R.ok();
    }
}

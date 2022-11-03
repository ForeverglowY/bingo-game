package com.fc.management.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.R;
import com.fc.management.entity.Game;
import com.fc.management.service.GameService;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @param current 当前页码
     * @param limit   每页条数
     * @return 查询结果 类型 list
     */
    @GetMapping("/list/{current}/{limit}")
    public R list(@PathVariable("current") Long current,
                  @PathVariable("limit") Long limit) {
        //创建 Page 对象
        Page<Game> page = new Page<>(current, limit);
        gameService.page(page, null);
        long total = page.getTotal();
        List<Game> records = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return R.ok().data(map);
    }

    /**
     * 根据 id 查询 game
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public R getGameById(@PathVariable("id") String id) {
        Game game = gameService.getById(id);
        if (game == null) {
            throw new BingoException(20001, "没有查到相关记录");
        }
        return R.ok().data("game", game);
    }

    /**
     * 添加游戏
     * @param game 类型对象
     * @return R
     */
    @PostMapping("/save")
    public R save(@RequestBody Game game) {
        boolean save = gameService.save(game);
        return save ? R.ok().message("添加成功") : R.error().message("添加失败");
    }

    /**
     * 根据 id 删除 game
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id) {
        boolean b = gameService.removeById(id);
        return b ? R.ok().message("删除成功") : R.error().message("删除失败");
    }

    /**
     * 修改 game
     * @param game 游戏
     * @return R
     */
    @PutMapping("/update")
    public R update(@RequestBody Game game) {
        boolean b = gameService.updateById(game);
        return b ? R.ok().message("修改成功") : R.error().message("修改失败");
    }
}

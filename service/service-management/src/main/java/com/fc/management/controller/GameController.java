package com.fc.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.R;
import com.fc.management.entity.Game;
import com.fc.management.entity.GameBanner;
import com.fc.management.entity.vo.GameQuery;
import com.fc.management.entity.vo.GameVo;
import com.fc.management.service.GameBannerService;
import com.fc.management.service.GameService;
import com.fc.management.service.TypeService;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Everglow
 * Created at 2022/11/02 16:46
 */
@RestController
@RequestMapping("/management/game")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private GameBannerService gameBannerService;

    /**
     * @param current 当前页码
     * @param limit   每页条数
     * @return 查询结果 类型 list
     */
    @PostMapping("/list/{current}/{limit}")
    public R list(@PathVariable("current") Long current,
                  @PathVariable("limit") Long limit,
                  @RequestBody(required = false) GameQuery gameQuery) {
        //创建 Page 对象
        Page<Game> page = new Page<>(current, limit);
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        String name;
        BigDecimal minPrice;
        BigDecimal maxPrice;
        String typeId;
        BigDecimal score;
        Date begin;
        Date end;
        if (gameQuery != null) {
            name = gameQuery.getName();
            minPrice = gameQuery.getMinPrice();
            maxPrice = gameQuery.getMaxPrice();
            typeId = gameQuery.getTypeId();
            score = gameQuery.getScore();
            begin = gameQuery.getBegin();
            end = gameQuery.getEnd();
            if (!StringUtils.isEmpty(name)) {
                wrapper.like("name", name);
            }
            if (!StringUtils.isEmpty(typeId)) {
                wrapper.eq("type_id", typeId);
            }
            if (!StringUtils.isEmpty(minPrice)) {
                wrapper.gt("price", minPrice);
            }
            if (!StringUtils.isEmpty(maxPrice)) {
                wrapper.lt("price", maxPrice);
            }
            if (!StringUtils.isEmpty(score) && score.doubleValue() != -1) {
                wrapper.eq("score", score);
            }
            if (!StringUtils.isEmpty(begin)) {
                wrapper.gt("gmt_create", begin);
            }
            if (!StringUtils.isEmpty(end)) {
                wrapper.lt("gmt_create", end);
            }
        }

        wrapper.orderByDesc("gmt_modified");

        gameService.page(page, wrapper);
        long total = page.getTotal();
        List<Game> records = page.getRecords();
        List<GameVo> list = new ArrayList<>();
        for (Game game : records) {
            GameVo gameVo = new GameVo();
            BeanUtils.copyProperties(game, gameVo);
            gameVo.setTypeName(typeService.getById(game.getTypeId()).getName());
            list.add(gameVo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", list);

        return R.ok().data(map);
    }

    /**
     * 根据 id 查询 game
     *
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
     * 添加游戏基本信息
     *
     * @param game 类型对象
     * @return R gameId
     */
    @PostMapping("/saveInfo")
    public R saveInfo(@RequestBody Game game) {
        String id = gameService.saveInfo(game);
        return id == null ? R.error().message("添加失败") : R.ok().message("添加成功").data("gameId", id);
    }

    /**
     * 根据 id 删除 game
     *
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
     *
     * @param game 游戏
     * @return R
     */
    @PutMapping("/update")
    public R update(@RequestBody Game game) {
        boolean b = gameService.updateById(game);
        List<GameBanner> gameBannerList = game.getGameBannerList();
        if (gameBannerList != null) {
            for (GameBanner gameBanner : gameBannerList) {
                gameBannerService.save(gameBanner);
            }
        }
        return b ? R.ok().message("修改成功") : R.error().message("修改失败");
    }

    /**
     * 根据 game id 获取 gameBannerList
     *
     * @param id game.id
     * @return bannerList
     */
    @GetMapping("/banner/{id}")
    public R getBannerList(@PathVariable("id") String id) {
        QueryWrapper<GameBanner> wrapper = new QueryWrapper<>();
        wrapper.eq("game_id", id);
        List<GameBanner> gameBannerList = gameBannerService.list(wrapper);
        return R.ok().data("gameBannerList", gameBannerList);
    }

    @DeleteMapping("/banner/{id}")
    public R deleteBanner(@PathVariable("id") String id) {
        boolean b = gameBannerService.removeById(id);
        if (b) {
            return R.ok().message("删除成功");
        } else {
            throw new BingoException(20001, "删除失败");
        }
    }

    @PostMapping("/banner/{id}")
    public R saveBanner(@RequestParam(name = "url") String url, @PathVariable("id") String id) {
        GameBanner gameBanner = new GameBanner();
        gameBanner.setPicture(url);
        gameBanner.setGameId(id);
        gameBannerService.save(gameBanner);
        return R.ok();
    }

}

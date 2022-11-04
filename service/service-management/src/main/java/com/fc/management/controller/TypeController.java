package com.fc.management.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.R;
import com.fc.management.entity.Type;
import com.fc.management.service.TypeService;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Everglow
 * Created at 2022/11/02 16:48
 */
@RestController
@RequestMapping("/management/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

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
        Page<Type> page = new Page<>(current, limit);
        typeService.page(page, null);
        long total = page.getTotal();
        List<Type> records = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return R.ok().data(map);
    }

    /**
     * 查询所有 type
     */
    @GetMapping("/list")
    public R getAll() {
        List<Type> list = typeService.list();
        return R.ok().data("typeList", list);
    }

    /**
     * 根据 id 查询 type
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public R getTypeById(@PathVariable("id") String id) {
        Type type = typeService.getById(id);
        if (type == null) {
            throw new BingoException(20001, "没有查到相关记录");
        }
        return R.ok().data("type", type);
    }

    /**
     * 添加类型
     * @param type 类型对象
     * @return R
     */
    @PostMapping("/save")
    public R save(@RequestBody Type type) {
        boolean isExist = typeService.isExist(type);
        if (isExist) {
            return R.error().message("已存在该类型, 请重新输入");
        }
        boolean save = typeService.save(type);
        return save ? R.ok().message("添加成功") : R.error().message("添加失败");
    }

    /**
     * 根据 id 删除 type
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id) {
        boolean b = typeService.removeById(id);
        return b ? R.ok().message("删除成功") : R.error().message("删除失败");
    }

    /**
     * 修改 type
     * @param type 类型
     * @return R
     */
    @PutMapping("/update")
    public R update(@RequestBody Type type) {
        boolean b = typeService.updateById(type);
        return b ? R.ok().message("修改成功") : R.error().message("修改失败");
    }

}

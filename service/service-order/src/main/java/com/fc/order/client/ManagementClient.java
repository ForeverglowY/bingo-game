package com.fc.order.client;

import com.fc.commonutils.Game;
import com.fc.commonutils.R;
import com.fc.commonutils.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author Everglow
 * Created at 2022/11/10 15:19
 */
@FeignClient("service-management")
@Component
public interface ManagementClient {

    @GetMapping("/management/user/userInfo/{id}")
    Map<String, String> getById(@PathVariable("id") String id);


    @GetMapping("/management/game/game/{id}")
    Map<String, Object> getInfoGameId(@PathVariable("id") String id);

    /**
     * 根据 id 查询 type
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    R getTypeById(@PathVariable("id") String id);

}

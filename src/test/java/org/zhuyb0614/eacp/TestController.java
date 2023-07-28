package org.zhuyb0614.eacp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunbo.zhu
 * @version 1.0
 * @date 2023/7/27 16:37
 */
@RestController
@Slf4j
public class TestController {

    @PostMapping("/user")
    public UserInfo get(@RequestBody UserInfo userInfo) {
        log.info("real userId {}", userInfo.getUserId());
        if (!"1".equals(userInfo.getUserId())) {
            throw new IllegalArgumentException("真是用户ID应该是 [ 1 ]");
        }
        userInfo.setUsername("Hello User");
        return userInfo;
    }

}

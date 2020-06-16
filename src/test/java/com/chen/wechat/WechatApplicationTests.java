package com.chen.wechat;

import com.chen.wechat.utils.LoginUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WechatApplicationTests {

    @Test
    void contextLoads() {
        LoginUtils.login();
    }

}

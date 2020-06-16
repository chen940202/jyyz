package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.mapper.ProgressConfigInfoDao;
import com.chen.wechat.mapper.WxUserProgressDao;
import com.chen.wechat.model.ProgressConfigInfoEntity;
import com.chen.wechat.model.WxUserProgressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public abstract class Content {

    @Autowired
    private ProgressConfigInfoDao progressConfigInfoDao;
    @Autowired
    private WxUserProgressDao wxUserProgressDao;

    protected String getContent(WxMessageVo wxMessageVo) {
        //从数据库获取数据
        ProgressConfigInfoEntity info = progressConfigInfoDao.findInfoById(wxMessageVo.getProgress());
        String s = info.getConfigContent() + "\n";
        return s;
    }


    protected abstract Long getProgress();

}

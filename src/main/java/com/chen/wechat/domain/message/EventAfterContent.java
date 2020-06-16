package com.chen.wechat.domain.message;


import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.mapper.WxUserInfoDao;
import com.chen.wechat.mapper.WxUserProgressDao;
import com.chen.wechat.model.WxUserInfoEntity;
import com.chen.wechat.model.WxUserProgressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventAfterContent extends Content{

    @Override
    protected String getContent(WxMessageVo wxMessageVo) {
        String content = super.getContent(wxMessageVo);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content);
        stringBuilder.append("存储记忆，输入'存'").append("\n");
        stringBuilder.append("读取记忆，输入'读'").append("\n");
        stringBuilder.append("删除记忆, 输入'删除'").append("\n");
//        stringBuilder.append("疫情进度,输入'疫情最新进度'").append("\n");
        wxMessageVo.setNextProgress(1l);
        return stringBuilder.toString();
    }
    @Override
    protected Long getProgress() {
        return null;
    }
}

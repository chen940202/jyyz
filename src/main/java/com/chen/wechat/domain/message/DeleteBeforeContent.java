package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.mapper.UserGoodsRecordDao;
import com.chen.wechat.model.UserGoodsRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DeleteBeforeContent extends Content{
    @Autowired
    private UserGoodsRecordDao userGoodsRecordDao;
    @Override
    protected String getContent(WxMessageVo wxMessageVo) {
        String content = super.getContent(wxMessageVo);
        List<UserGoodsRecordEntity> info = userGoodsRecordDao.findInfoByUserId(wxMessageVo.getToUserName());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content);
        if(CollectionUtils.isEmpty(info)){
            return "data_not_exist";
        }
        for(int i = 0;i<info.size();i++ ){
            UserGoodsRecordEntity userGoodsRecordEntity = info.get(i);
            stringBuilder.append(i).append("、").append(" ").append(userGoodsRecordEntity.getRecordDesc()).append(":").append(userGoodsRecordEntity.getUserName());
            String passWord = userGoodsRecordEntity.getPassWord();
            if(!StringUtils.isEmpty(passWord)){
                stringBuilder.append("-").append(passWord);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    @Override
    protected Long getProgress() {
        return null;
    }
}

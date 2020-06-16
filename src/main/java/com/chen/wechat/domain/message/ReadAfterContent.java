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
public class ReadAfterContent extends Content {
    @Autowired
    private UserGoodsRecordDao userGoodsRecordDao;

    @Override
    protected String getContent(WxMessageVo wxMessageVo){
        String content = super.getContent(wxMessageVo);
        List<UserGoodsRecordEntity> info = userGoodsRecordDao.findInfoByUserId(wxMessageVo.getToUserName());
        if(CollectionUtils.isEmpty(info)){
            return "data_not_exist";
        }
        String voContent = wxMessageVo.getContent();
        Integer id = null;
        try {
            id = Integer.valueOf(voContent.toString());
        }catch (Exception e){
            return "serial_number";
        }
        if(id > info.size()-1){
            return "serial_number";
        }
        UserGoodsRecordEntity userGoodsRecordEntity = info.get(id);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content).append(userGoodsRecordEntity.getRecordDesc()).append(":").append(userGoodsRecordEntity.getUserName());
        String passWord = userGoodsRecordEntity.getPassWord();
        if(!StringUtils.isEmpty(passWord)){
            stringBuilder.append("-").append(passWord);
        }
        wxMessageVo.setNextProgress(1l);
        return stringBuilder.toString();
    }
    @Override
    protected Long getProgress() {
        return null;
    }
}

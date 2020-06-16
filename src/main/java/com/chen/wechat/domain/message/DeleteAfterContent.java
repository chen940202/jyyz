package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.mapper.UserGoodsRecordDao;
import com.chen.wechat.model.UserGoodsRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DeleteAfterContent extends  Content {
    @Autowired
    private UserGoodsRecordDao userGoodsRecordDao;
    @Override
    protected String getContent(WxMessageVo wxMessageVo) {
        String content = super.getContent(wxMessageVo);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content);
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
        int delete = userGoodsRecordDao.delete(userGoodsRecordEntity.getRecordId(),1);
        wxMessageVo.setNextProgress(1l);
        return stringBuilder.toString();
    }
    @Override
    protected Long getProgress() {
        return null;
    }
}

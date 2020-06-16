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
public class ReadBeforeContent extends Content {

    @Autowired
    private UserGoodsRecordDao userGoodsRecordDao;
    @Override
    protected String getContent(WxMessageVo wxMessageVo) {
        String content = super.getContent(wxMessageVo);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content).append("\n");
        //从数据库查询该用户数据
        List<UserGoodsRecordEntity> info = userGoodsRecordDao.findInfoByUserId(wxMessageVo.getToUserName());
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
        return 1l;
    }
}

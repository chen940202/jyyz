package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.mapper.UserGoodsRecordDao;
import com.chen.wechat.model.UserGoodsRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteAfterContent extends Content {

    @Autowired
    private UserGoodsRecordDao userGoodsRecordDao;
    @Override
    protected String getContent(WxMessageVo wxMessageVo) {
        String content = wxMessageVo.getContent();
        if (!content.contains("-")) {
            return "incorrect_format";
        }
        String[] s = content.split("-");
        if (s.length > 3) {
            return "incorrect_format";
        } else {
            UserGoodsRecordEntity userGoodsRecordEntity = new UserGoodsRecordEntity();
            if (s[0] != null) {
                userGoodsRecordEntity.setRecordDesc(s[0]);
            }
            if (s.length >= 2) {
                userGoodsRecordEntity.setUserName(s[1]);
            }
            if (s.length >= 3) {
                userGoodsRecordEntity.setPassWord(s[2]);
            }
            //插入记录
            userGoodsRecordEntity.setWxOpenId(wxMessageVo.getToUserName());
            userGoodsRecordDao.insert(userGoodsRecordEntity);

        }
        wxMessageVo.setNextProgress(1l);
        return super.getContent(wxMessageVo);

}

    @Override
    protected Long getProgress() {
        return null;
    }
}

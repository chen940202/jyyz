package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import org.springframework.stereotype.Service;

@Service
public class WriteBeforeContent extends Content {


    @Override
    protected String getContent(WxMessageVo wxMessageVo){
        String content = wxMessageVo.getContent();
        if(content.contains("存")){
            return super.getContent(wxMessageVo);
        }else {
            return "write_error";
        }
    }
    @Override
    protected Long getProgress() {
        return 2l;
    }
}

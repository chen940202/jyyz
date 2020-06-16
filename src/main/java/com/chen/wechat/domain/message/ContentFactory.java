package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.model.ErrorMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContentFactory implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ErrorMessageService errorMessageService;

    private static Map<Long, Content> factoryMap = new HashMap<>();

    public String create(WxMessageVo wxMessageVo) {
        Content contentFactory = factoryMap.get(wxMessageVo.getProgress());

        String content = contentFactory.getContent(wxMessageVo);

        if (!StringUtils.isEmpty(content)) {
            ErrorMessageEntity errorCode = errorMessageService.findInfoByErrorCode(content);
            if (errorCode != null) {
                wxMessageVo.setNextProgress(null);
                return errorCode.getErrorMessage();
            } else {
                if (wxMessageVo.getNextProgress() == null) {
                    wxMessageVo.setNextProgress(wxMessageVo.getProgress() + 1);
                }
                return content;
            }
        } else {
            return "请输入正确指令";
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        factoryMap.put(6l, applicationContext.getBean("readBeforeContent", ReadBeforeContent.class));
        factoryMap.put(7l, applicationContext.getBean("readAfterContent", ReadAfterContent.class));
        factoryMap.put(1l, applicationContext.getBean("eventAfterContent", EventAfterContent.class));
        factoryMap.put(2l, applicationContext.getBean("writeBeforeContent", WriteBeforeContent.class));
        factoryMap.put(3l, applicationContext.getBean("writeAfterContent", WriteAfterContent.class));
        factoryMap.put(8l, applicationContext.getBean("deleteBeforeContent", DeleteBeforeContent.class));
        factoryMap.put(9l, applicationContext.getBean("deleteAfterContent", DeleteAfterContent.class));
    }
}

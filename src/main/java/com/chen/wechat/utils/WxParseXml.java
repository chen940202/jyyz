package com.chen.wechat.utils;

import com.chen.wechat.domain.WxMessageVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
public class WxParseXml {

    /**
     * dom4j 解析 xml 转换为 map
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    public static String toXml(WxMessageVo wxMessageVo){
        //<![CDATA[toUser]]>
        String template = "<![CDATA[%s]]>";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>").append("\n");
        Field[] declaredFields = wxMessageVo.getClass().getDeclaredFields();
        for(Field field : declaredFields){
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(wxMessageVo);
                if(value == null || StringUtils.isEmpty(value)){
                    continue;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }finally {
                field.setAccessible(false);
            }
            String format = null;
            try {
                format = String.format(template, new String(value.toString().getBytes(),"ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            stringBuilder.append("<").append(fieldName).append(">").append(format).append("</").append(fieldName).append(">\n");
        }
        stringBuilder.append("</xml>");
        return stringBuilder.toString();
    }

}

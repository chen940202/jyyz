package com.chen.wechat.utils;

import com.alibaba.fastjson.JSON;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
public class LoginUtils {

    @Async
    @Scheduled(cron = "0/3 * * * * ?")
    public void test2() {
        //logger.info("执行");
        System.out.println("执行2:"+Thread.currentThread().getName());
//        HttpClient httpClient = new HttpClient();
//        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", wechat.getAppId(), wechat.getAppSecret());
//        String json = httpClient.get(url, "", "", 50000, "utf-8");
//        Map<String, String> map = JSON.parseObject(json, Map.class);
//        if("7200".equals(map.get("expires_in"))) {
//            wechat.setAccess_token(map.get("access_token"));
//        }else{
//            logger.info("定时获取参数失败:"+JSON.toJSONString(map));
//        }
    }

    public static void login() {
        //https://github.com/mozilla/geckodriver/releases
        System.setProperty("webdriver.firefox.bin", "F:\\fire\\" + "firefox.exe");
        System.setProperty("webdriver.gecko.driver", "F:\\fire\\" + "geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxOptions options = new FirefoxOptions();
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy("127.0.0.1");
        options.setProxy(proxy);
        options.addArguments("disable-cache");
//        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        //FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\admin\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\c7prxtxr.default"));
        //options.setProfile(profile);
        FirefoxDriver driver = new FirefoxDriver(options);
        driver.get("https://login.1688.com/member/signin.htm?tracelog=account_verify");
        //下面开始完全模拟正常人的操作，所以你会看到很多 sleep 操作
        // driver.findElement(By.className("login-switch")).click();
        String js = "Object.defineProperty(navigator, 'webdriver', {  get: () => false, });";
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(js);
        WebElement usernameElement = driver.findElement(By.id("fm-login-id"));
        //模拟用户点击用户名输入框
        usernameElement.click();
        String username = "cc18781380866@163.com";//你的手机号
        String password = "ccoli520";//你的密码1
        Random rand = new Random();
        try {
            for (int i = 0; i < username.length(); i++) {
                Thread.sleep(rand.nextInt(1000));//随机睡眠0-1秒
                //逐个输入单个字符
                usernameElement.sendKeys("" + username.charAt(i));
            }
            WebElement passwordElement = driver.findElement(By.id("fm-login-password"));
            passwordElement.click();
            //输入完成用户名后，随机睡眠0-3秒
            Thread.sleep(rand.nextInt(3000));
            for (int i = 0; i < password.length(); i++) {
                Thread.sleep(rand.nextInt(1000));
                passwordElement.sendKeys("" + password.charAt(i));
            }
            Thread.sleep(rand.nextInt(1000));
//            int j = 2;
//            for(;j<= 11;j++) {
//                String key = "divSlider" + j + "_bar";
//                WebElement element = driver.findElement(By.id(key));
//                if (element != null) {
//                    Actions builder = new Actions(driver);
//                    Action dragAndDrop = null;
//                    builder.clickAndHold(element).perform();
//                    for (int i = 0; i <= 275; i += 50) {
//                        if (i >= 275) {
//                            builder.moveByOffset(275, 0).perform();
//                            break;
//                        }
//                        int start = 90;
//                        builder.moveByOffset(start, 0).perform();
//                        Thread.sleep(100);
//                    }
//
//                    Actions actions = new Actions(driver);
//                    actions.release().perform();
//                    Thread.sleep(5000);
//                }
//            }
//            WebElement element = driver.findElement(By.id("q12"));
//            String content = "不需要改变，已经很好了";
//            for (int i = 0; i < content.length(); i++) {
//                Thread.sleep(rand.nextInt(1000));//随机睡眠0-1秒
//                //逐个输入单个字符
//                element.sendKeys("" + content.charAt(i));
//            }
            Thread.sleep(2000);
            driver.findElement(By.className("fm-button fm-submit password-login")).click();


            //登录成功之后打开淘宝联盟
            Set<String> windowHandles = driver.getWindowHandles();
            driver.switchTo().window(windowHandles.iterator().next());
            driver.get("https://pub.alimama.com/manage/overview/orders.htm");
            //窗口切换
//            String windowHandleBefore = driver.getWindowHandle(); //当前页面
////            System.out.println(windowHandleBefore);
////            Set<String> windowHandles = driver.getWindowHandles();
////            for(String url : windowHandles){
////                if(url.equals(windowHandles)){
////                    continue;
////                }
////                driver.switchTo().window(url);
////                break;
////            }
////            driver.findElementByXPath("/html/body/div[6]/div[2]/a/div[2]/div").click();
//            String currentUrl = driver.getCurrentUrl();
//            Thread.sleep(5000);
//            Alert alert = driver.switchTo().alert();
//            String js = "window.open(\"https://www.alimama.com/index.htm\");";
//            driver.executeScript(js);
//            Set<String> windowHandles = driver.getWindowHandles();
//            String windowHandle = driver.getWindowHandle();
//            for(String url : windowHandles){
//                if(url.equals(windowHandle)){
//                    continue;
//                }
//                driver.switchTo().window(url);
//            }
//            String currentUrl2 = driver.getCurrentUrl();
//            String jstoTk = "window.open(\"https://pub.alimama.com/manage/overview/index.htm\");";
//            driver.executeScript(jstoTk);
//            String currentUrl1 = driver.getCurrentUrl();
//            String windowHandle1 = driver.getWindowHandle();
//            Set<String> windowHandles1 = driver.getWindowHandles();
//            for(String url : windowHandles){
//                if(url.equals(windowHandle) || url.equals(windowHandle1)){
//                    continue;
//                }
//                driver.switchTo().window(url);
//            }
//            String currentUrl3 = driver.getCurrentUrl();
            Set<Cookie> cookies = driver.manage().getCookies();
            System.out.println(JSON.toJSONString(cookies));
//            if(!CollectionUtils.isEmpty(cookies)){
//                StringBuilder stringBuilder = new StringBuilder();
//                for(Cookie cookie : cookies){
//                    stringBuilder.append(cookie.getName()).append("=").append(cookie.getValue()).append(";").append(" ");
//                }
//                String string = stringBuilder.toString();
//                String substring = string.substring(0, string.length() - 2);
//                //存入redis
//                redisUtil.set(key,substring,1*60*60*24);
//            }
//        } catch (Exception e) {
//            logger.info("模拟登录失败:"+e.getMessage());
//            e.printStackTrace();
//        }
//        try {
//            Thread.sleep(100000);
//
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
            Thread.sleep(2000);
            driver.quit();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

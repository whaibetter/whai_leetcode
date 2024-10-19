package cn.whaifree.selenium;

import cn.whaifree.utils.SeleniumUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 20:06
 * @注释
 */
public class FirstSeleniumDemo {

    public static void main(String[] args) {

        SeleniumUtils.init();

        findElementDemo("https://www.baidu.com", SeleniumUtils.getWebDriver());

    }

    public static void findElementDemo(String url,WebDriver driver) {
        driver.get(url);
        // 通过id进行定位，百度搜索框input的id为kw
        driver.findElement(By.id("kw"));
        // 通过name进行定位，百度搜索框input的id为wd
        driver.findElement(By.name("wd"));
        // 通过class name进行定位，百度搜索框input的class name为s_ipt
        driver.findElement(By.className("s_ipt"));
        // 通过tag name进行定位，百度搜索框input的tag name为input
        driver.findElement(By.tagName("input"));
        // 通过xpath进行定位，xpath定位有N种写法，这里列几个常用写法：
        driver.findElement(By.xpath("//*[@id='kw']"));
        driver.findElement(By.xpath("//*[@name='wd']"));
        driver.findElement(By.xpath("//input[@class='s_ipt']"));
        driver.findElement(By.xpath("//form[@id='form']/span/input"));
        driver.findElement(By.xpath("//input[@id='kw' and @name='wd']"));
        // 通过css进行定位，css定位有N种写法，这里列几个常用写法：
        driver.findElement(By.cssSelector("#kw"));
        driver.findElement(By.cssSelector("[name=wd]"));
        driver.findElement(By.cssSelector(".s_ipt"));
        driver.findElement(By.cssSelector("form#form > span > input"));
        // 通过link text进行定位，定位新闻
        driver.findElement(By.linkText("新闻"));
        // 通过partialLink text进行定位，定位新闻
        driver.findElement(By.partialLinkText("新"));
        driver.close();
    }



    @Test
    public void testControl() throws InterruptedException {
        String url = "https://www.baidu.com";
        WebDriver driver = SeleniumUtils.getWebDriver();
        controlDemo(url,driver);
    }


    /**
     * 控制浏览器操作
     * @param url
     * @param driver
     */
    public static void controlDemo(String url,WebDriver driver) throws InterruptedException {
        driver.get(url);
        // 设置浏览器的窗口大小为500*300,
        driver.manage().window().setSize(new Dimension(500,300));
        // 设置浏览器显示位置
        driver.manage().window().setPosition(new Point(200,300));
        // 访问百度首页
        driver.get("http://www.baidu.com");
        // 延迟3s
        TimeUnit.SECONDS.sleep(3L);
        // 设置浏览器最大化
        driver.manage().window().maximize();




        // 获得输入框的尺寸大小
        Dimension dimension = driver.findElement(By.id("kw")).getSize();
        System.out.println("搜索框的宽高为：" + dimension.width + " * " + dimension.height);
        // 新闻超链接的显示文本
        System.out.println("新闻超链接的显示文本：" + driver.findElement(By.partialLinkText("新")).getText());
        // 搜索框是否用户可见
        System.out.println("搜索框是否用户可见：" + driver.findElement(By.id("kw")).isDisplayed());
        // 搜索框输入Selenium
        driver.findElement(By.id("kw")).sendKeys("Selenium");
        // 搜索框的name属性值为
        System.out.println("搜索框的name属性值为：" + driver.findElement(By.id("kw")).getAttribute("name"));
        // 延迟3s
        TimeUnit.SECONDS.sleep(3L);
        // 清除输入框内容
        driver.findElement(By.id("kw")).clear();
        // 延迟3s
        TimeUnit.SECONDS.sleep(3L);
        // 搜索框输入Selenium
        driver.findElement(By.id("kw")).sendKeys("Selenium");
        // 延迟3s
        TimeUnit.SECONDS.sleep(3L);
        // 点击百度一下按钮
        // driver.findElement(By.id("su")).click();
        // 模拟提交
        driver.findElement(By.id("kw")).submit();
        // 延迟3s
        TimeUnit.SECONDS.sleep(3L);
        driver.close();
    }
}

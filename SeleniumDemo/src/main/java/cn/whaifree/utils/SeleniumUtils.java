package cn.whaifree.utils;


import cn.hutool.core.io.FileUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 20:14
 * @注释
 */
public class SeleniumUtils {

    static {
        init();
    }

    public static void init() {
        /**
         * https://www.cnblogs.com/aiyablog/articles/17948703
         */
        // resource 下的chromedriver.class
        String property = System.getProperty("user.dir");
        String parent = FileUtil.getParent(property, 1);
        String chromedriver = parent + "/static/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromedriver);
    }

    public static WebDriver getWebDriver() {
        return new ChromeDriver();
    }
}

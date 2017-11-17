package com.appium.testPro;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MainPage {
	
	AndroidDriver<AndroidElement> driver;
	List<AndroidElement> mainNaviBtn;
	ReadyForNext readyForNext;
	SearchPage searchPage;
	
//	定义搜索框ID
	String searchBoxId = "com.firstshop:id/searchContent";
//	定义搜索商品activity
	String searchGoodsActivity = ".ui.search.SearchActivity";
	
	public MainPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		readyForNext = new ReadyForNext(driver);
	}

	// 启动app
	public void launchApp() throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		  dc.setCapability("browserName", "");
		  dc.setCapability("platformName", "Android");
		  dc.setCapability("platformVersion", "4.4.2");
		  dc.setCapability("deviceName", "127.0.0.1:52001");
		  dc.setCapability("newCommandTimeout", "180");  //设置收到下一条命令的超时时间,超时appium会自动关闭session ,默认60秒
		  dc.setCapability("appPackage", "com.firstshop");  //你想运行的Android应用的包名
		  dc.setCapability("appActivity", ".SplashActivity");  //你要启动的Android 应用对应的Activity名称|比如`MainActivity`, `.Settings`|
		  dc.setCapability("appWaitActivity", ".mian.MainFragementActivity");  //你想要等待启动的Android Activity名称|比如`SplashActivity`|
	      //每次启动时覆盖session，否则第二次后运行会报错不能新建session
	      dc.setCapability("sessionOverride", true);
	      
		  driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		  
		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	// 通过点击底部导航栏切换显示页面
	public void clickBottomNavi(int bottomNaviIndex) {
		// 获得底部导航栏List
		mainNaviBtn = driver.findElementsById("com.firstshop:id/tab_itme_txt");
		mainNaviBtn.get(bottomNaviIndex).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	// 退出APP
	public void quitApp() {
		for (int i = 0; i < 6; i++) {
			driver.pressKeyCode(4);  // 按下返回键4
			ReadyForNext ready = new ReadyForNext(driver);
			// 判断确认按钮是否存在
			boolean flag = ready.isElementExistById(20000, "com.firstshop:id/positive");
			if (flag) {
				driver.findElementById("com.firstshop:id/positive").click();	
				break;
			}
		}
		driver.quit();
	}
	
//	搜索商品
	public void searchGoods(String goodsName) {
		driver.findElementById("searchBoxId").click();
//		判断是否进入搜索商品activity
		boolean flagSearch = readyForNext.activityVerify(2000, driver, searchGoodsActivity);
		if (flagSearch) {
			searchPage = new SearchPage(driver);
			boolean flagGoodsExist = searchPage.searchGoods(goodsName);
			if (flagGoodsExist) {
				searchPage.viewGoods();
			}
		} else {
			System.out.println("超时错误，当前所在页面activity为：" + driver.currentActivity());
		}
	}
}

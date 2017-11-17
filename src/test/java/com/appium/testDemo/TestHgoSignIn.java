package com.appium.testDemo;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestHgoSignIn {
	private AndroidDriver<AndroidElement> driver;
	private String launchActivity = ".SplashActivity";
	private String mainActivity = ".mian.MainFragementActivity";
//	private String positiveActivity
	private String loginActivity = ".ui.login.activity.LoginActivity";
	private String signInActivity = ".ui.home.activity.SignActivity";

	
	/*
	 * 进入cmd，输入以下命令查看Android设备当前所在APP包名及activity名
	 * adb shell dumpsys window w |findstr \/ |findstr name=
	 * 测试签到
	 */
	@Test
	public void testSignIn() {
//		验证APP启动成功，并且当前打开了launchActivity
		if (activityVerify(3000, driver, launchActivity)) {
//			点击跳过
			driver.findElementById("com.firstshop:id/skip").click();
			// 验证跳过了启动广告页，并且当前打开了mainActivity
			if (activityVerify(3000, driver, mainActivity)) {
//				尝试签到
				mainPageElementExist("签到");
				
				// 判断跳转页面，如果未登录，则判断是否进入了登录页面
				if (activityVerify(3000, driver, loginActivity)) {
					// 如果当前打开的是loginActivity，则开输入账号密码登录
					driver.findElementById("com.firstshop:id/phone").sendKeys("18096600175");
					driver.findElementById("com.firstshop:id/pass").sendKeys("a123456");
					driver.findElementById("com.firstshop:id/login").click();

//					进行签到
					if (!mainPageElementExist("签到")) {
//						进入主页
						switchBottomNavi("首页");
						mainPageElementExist("签到");
					}
					Assert.assertEquals(driver.currentActivity().toString(), signInActivity);
					Assert.assertEquals(driver.findElementById("com.firstshop:id/signDays").getText().substring(0, 4), "累计签到");
				} else if (activityVerify(3000, driver, signInActivity)) {
					Assert.assertEquals(driver.findElementById("com.firstshop:id/signDays").getText().substring(0, 4), "累计签到");
				}
			} else {
				System.out.println(driver.currentActivity().toString());
				System.out.println("打开" + mainActivity +"超时");
				appClose();
			}
		} else {
			System.out.println(driver.currentActivity().toString());
			System.out.println("打开" + launchActivity +"超时");
			appClose();
		}

			/*
			 * for (WebElement ele : elements) { if (ele.getText().equals("签到")) {
			 * ele.click();
			 * 
			 * System.out.println("成功定位签到icon"); } }
			 */
			// driver.findElement(By.id(id));
		Assert.assertNotEquals(driver.currentActivity(), mainActivity);
		
	}

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", "127.0.0.1:52001");
		dc.setCapability("appPackage", "com.firstshop");
		dc.setCapability("appActivity", ".SplashActivity");

		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);

//		try {
//			Thread.sleep(6000);
//		} catch (InterruptedException ie) {
//			ie.printStackTrace();
//		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// 验证当前activity的方法
	public boolean activityVerify(long time, AndroidDriver<AndroidElement> driver2, String activity) {
		for (int i = 0; i < time / 1000; i++) {
			appWait(1000);
			if (driver2.currentActivity().equals(activity)) {
				return true;
			}
		}
		System.out.println("Could not find activity : " + activity);
		return false;
	}

	// 线程等待方法
	public void appWait(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
//	关闭APP
	public void appClose() {
		for (int k = 0; k < 6; k++) {
			driver.pressKeyCode(4);
			if (activityVerify(2000, driver, mainActivity)) {
//				判断退出APP是弹框按钮‘是’出现
				String closeCofirmElement = "com.firstshop:id/positive";
				if (ifElementExist(closeCofirmElement)) {
//					点击‘是’确认退出APP
					driver.findElementById(closeCofirmElement).click();
					System.out.println("退出APP");
					break;
				}
			}
		}
	}

//	判断元素是否存在
	public boolean ifElementExist(String eleStr) {
		try {
			return driver.findElementById(eleStr).isDisplayed();
//			return true;
		} catch (NoSuchElementException noEle) {
			return false;
		}
	}
	
//	定位首页快捷入口元素是否存在，存在则点击
	public boolean mainPageElementExist(String quickEntryName) {
//		获取首页元素
		List<AndroidElement> elements = driver.findElements(By.id("com.firstshop:id/text"));
		System.out.println(elements);
		for (int j = 0; j < elements.size(); j++) {
			// 尝试定位首页的quickEntryName入口元素
			if (elements.get(j).getText().equals(quickEntryName)) {
				// 定位成功后点击quickEntryName
				elements.get(j).click();
				// System.out.println("成功定位入口：" + quickEntryName);
//				break;
				return true;
			}
		}
		return false;
	}
	
//	切换底部导航菜单
	public void switchBottomNavi(String naviName) {
//		获取底部菜单元素
		List<AndroidElement> bottomNavi = driver.findElementsById("com.firstshop:id/tab_itme_txt");
		for (int l = 0; l < bottomNavi.size(); l++) {
//			找到naviName对应的底部菜单
			if (bottomNavi.get(l).getText().equals(naviName)) {
				bottomNavi.get(l).click();
				break;
			}
		}
	}
}

package com.appium.testPro;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class testMain {
	
	public AndroidDriver<AndroidElement> driver;
	  String loginActivity = ".ui.login.activity.LoginActivity";
	  String nickName = ".缺感";
	  
	MainPage mainPage = new MainPage(driver);
	  ReadyForNext readyForNext = new ReadyForNext(driver);

	  
  @Test
  public void testMakeOrder() {
//	  进入我的页面。0：首页，1：商家，2：社区，3：购物车，4：我的
	  mainPage.clickBottomNavi(4);
	  
//	  判断是否要登录，进入我的页面后，如果尚未登录，会自动跳转登录页面
	  boolean flagLogin = readyForNext.activityVerify(3000, mainPage.driver, loginActivity);
	  if (flagLogin) {
		  LoginPage loginPage = new LoginPage(driver);
		  loginPage.login("18096600175", "a123456");
	  }

//	  确保登录成功
	  Assert.assertEquals(driver.findElementById("com.firstshop:id/usename"), nickName);
//	  进入首页
	  mainPage.clickBottomNavi(0);
//	  mainPage
	  mainPage.searchGoods("差价啊");

//	  搜索商品
	  
	  
//	  mainPage.driver.findElementById("com.firstshop:id/userImage").click();
//System.out.println("进入个人信息编辑页面");
  }
  
  // 打开APP
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
//	  mainPage = new MainPage(driver);
	  mainPage.launchApp();

  }

  @AfterClass
  public void afterClass() {
//	  driver.quit();
	  mainPage.quitApp();
  }

}

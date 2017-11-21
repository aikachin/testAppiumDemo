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
	  
	MainPage mainPage = new MainPage(driver);
	  ReadyForNext readyForNext = new ReadyForNext(driver);

	  
  @Test
  public void testMakeOrder() {
//	  进入我的页面。0：首页，1：商家，2：社区，3：购物车，4：我的
	  mainPage.clickBottomNavi(4);
	  String nickName = "Onigokko";
	  String goodsName = "差价";
	  
//	  进行登录确认
	  mainPage.loginConfirm("18096600175", "a123456", nickName);

//	  确保登录成功
	  String nickNameF = mainPage.driver.findElementById("com.firstshop:id/usename").getText();
	  Assert.assertEquals(nickNameF, nickName);
//	  进入首页
	  mainPage.clickBottomNavi(0);
//	  搜索、查看商品
	  if (mainPage.searchGoods(goodsName)) {
			SearchPage searchPage = new SearchPage(driver);
			boolean flagGoodsExist = searchPage.searchGoods(goodsName);
			//		搜索到商品，则查看商品
			if (flagGoodsExist) {
				searchPage.viewGoods();
			}
	  }
//	  将商品加入购物车
	  mainPage.addShoppingCart("紫色", "M", 2);
	  
	  boolean flag = mainPage.viewShoppingCart("", "差价");
	  Assert.assertEquals(flag, true);
	  
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

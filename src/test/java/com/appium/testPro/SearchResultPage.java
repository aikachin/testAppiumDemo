package com.appium.testPro;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SearchResultPage {
	AndroidDriver<AndroidElement> driver;
	WebElement viewGoods;
	
	public SearchResultPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
	public boolean searchResult(String goodsName) {
		ReadyForNext ready = new ReadyForNext(driver);
//		判断搜索结果页面是否能定位到的商品属性元素
		boolean flag = ready.isElementExistById(3000, "com.firstshop:id/goodsDescription");
//		boolean flagClear = readyForNext.isElementExistById(2000, "com.firstshop:id/clear");
		System.out.println("判断是否搜索到商品。。。");
		if (flag) {
//				如果能查到包含检索字段的商品名称
			String goodname = driver.findElementById("com.firstshop:id/goodsDescription").getText();
			Assert.assertEquals(goodname, goodsName);
			return true;
		}
		return false;
	}
	
//	查看商品详情
	public void viewGoods() {
//		查看商品
		viewGoods = driver.findElementById("com.firstshop:id/goodsDescription");
		viewGoods.click();
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
	}
}

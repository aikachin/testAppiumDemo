package com.appium.testPro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SearchPage {
	AndroidDriver<AndroidElement> driver;
	WebElement searchContent;
	WebElement searchBtn;
	WebElement viewGoods;
	
	
	public SearchPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
//	根据搜索框输入内容搜索商品
	public boolean searchGoods(String goodsName) {
		searchContent = driver.findElementById("com.firstshop:id/searchContent");
		searchContent.sendKeys(goodsName);
		searchBtn.click();
//		判断是否能搜索到结果
		ReadyForNext readyForNext = new ReadyForNext(driver);
		boolean flag = readyForNext.isElementsExistByclassName(3000, "android.widget.LinearLayout");
		if (flag) {
			viewGoods = driver.findElementByClassName("android.widget.LinearLayout");
			return true;
		} else {
			System.out.println("找不到与输入内容  【" + goodsName + "】  相关的商品，或者是发生了搜索超时错误");
			return false;
		}
	}
	
//	查看商品详情
	public void viewGoods() {
		viewGoods.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
	}
	
}

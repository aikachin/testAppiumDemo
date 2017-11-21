package com.appium.testPro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SearchPage {
	AndroidDriver<AndroidElement> driver;
	WebElement searchContent;
	WebElement searchBtn;
	String searchResultActivity = ".ui.search.SearchResultActivity";
	
	
	public SearchPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
//	根据搜索框输入内容搜索商品
	public boolean searchGoods(String goodsName) {
//		输入内容 com.firstshop:id/searchContent
		searchContent = driver.findElementByClassName("android.widget.EditText");
		searchContent.click();
		searchContent.sendKeys(goodsName);
//		点击搜索
		searchBtn = driver.findElementById("com.firstshop:id/search");
		searchBtn.click();
//		判断是否能搜索到结果
		ReadyForNext readyForNext = new ReadyForNext(driver);
		System.out.println("开始判断是否搜索到商品。。。");
//		判断当前activity是否是searchResultActivity
		if (readyForNext.activityVerify(2000, driver, searchResultActivity)) {
			SearchResultPage searchResultPage = new SearchResultPage(driver);
			return searchResultPage.searchResult(goodsName);
		}
		System.out.println("当前activity不是搜索结果activity：" + driver.currentActivity());
		return false;
	}
	
//	查看商品
	public void viewGoods() {
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		searchResultPage.viewGoods();
	}
}

package com.appium.testPro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GoodsDetailsPage {
	AndroidDriver<AndroidElement> driver;
	WebElement addShoppingCart;
	WebElement buyNow;
	
	public GoodsDetailsPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
//	加入购物车
	public void addShoppingCart(String kind, String size, int num) {
		addShoppingCart = driver.findElementById("com.firstshop:id/addToShopCart");
		addShoppingCart.click();
		selectSpec(kind, size, num);
	}
	
//	立即购买
	public void buyNow(String kind, String size, int num) {
		buyNow = driver.findElementById("com.firstshop:id/buyDirect");
		buyNow.click();
		selectSpec(kind, size, num);
	}
	
//	添加收藏
	public void addCollection() {
		
	}
	
//	查看店铺主页
	public void viewShop() {
		
	}
	
//	添加关注店铺
	public void addFollow() {
		
	}
	
//	选择规格
	public void selectSpec(String kind, String size, int num) {
		
//		driver.switchTo().alert();
		System.out.println("看看能否选中");
		ReadyForNext readyForNext = new ReadyForNext(driver);
		if (readyForNext.isElementExistByXPath(2000, "//android.widget.TextView[contains(@text, 'M')]")) {
			
//		选择种类，颜色等
		WebElement selectKind = driver.findElementByXPath("//android.widget.TextView[contains(@text,"+ kind+")]");
		selectKind.click();
		driver.tap(1, 34, 373, 100); // 选择紫色
//		选择大小，尺寸
		WebElement selectSize = driver.findElementByXPath("//android.widget.TextView[contains(@text,"+ size+")]");
		selectSize.click();
		driver.tap(1, 50, 436, 100); // 选择尺寸M

//		当前数量
		WebElement currentNum = driver.findElementById("com.firstshop:id/etAmount");
//		增加数量按钮
		WebElement btnIncrease = driver.findElementById("com.firstshop:id/btnIncrease");
//		driver.tap(1, 439, 477, 100); // 点击+
////		减少数量按钮
//		WebElement btnDecrease = driver.findElementById("com.firstshop:id/btnDecrease");
//		如果数量不符合预期，循环点击
		while (!currentNum.getText().equals(String.valueOf(num))) {	// 更多int和String互转方法参考：http://blog.csdn.net/memray/article/details/7312817/
			btnIncrease.click();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		}
//		点击确定按钮
		WebElement confirmBtn = driver.findElementById("com.firstshop:id/confirm");
		confirmBtn.click();
//		driver.tap(1, 227, 766, 100); // 点击确定

		}
	}
}

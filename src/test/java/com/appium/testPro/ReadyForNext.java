package com.appium.testPro;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ReadyForNext {
	
	AndroidDriver<AndroidElement> driver;
	AndroidElement element;
	
	public ReadyForNext(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	// 判断当前activity是否符合预期，参数：等待时间，driver，activity名
	public boolean activityVerify(long time, AndroidDriver<AndroidElement> driver, String activity) {
		for (int i = 0; i < time/1000; i++) {
			appWait(1000);
			if (driver.currentActivity().equals(activity)) {
				return true;
			}
		}
		return false;
	}
	
	// 设置APP等待时间
	public void appWait(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	// 根据resourceId判断单个元素是否存在
	public boolean isElementExistById(long time, String id) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElementById(id)));
		}  catch (NoSuchElementException ne) {
//			ne.printStackTrace();
			System.out.println("找不到该元素：" + id);
			return false;
		}
		return true;
	}
	
	// 根据resourceId获取单个元素
	public AndroidElement getElementById(long time, String id) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElementById(id)));
			return driver.findElementById(id);
		}  catch (NoSuchElementException ne) {
//			ne.printStackTrace();
			System.out.println("找不到该元素：" + id);
			return null;
		}
	}
	
	
	
	

	// 根据resourceId判断元素是否存在，List
	public boolean isElementsExistById(long time, String id, int index) {
		try {
			Thread.sleep(time);
			driver.findElementsById(id).get(index).isDisplayed();
		} catch (NoSuchElementException ne) {
			return false;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
			return false;
		}
		return true;
	}
	
	// 根据resourceId+text判断元素是否存在
	public boolean isElementsExistById(long time, String id, String text) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
//			在页面元素中是否包含特定的文本：textToBePresentInElementLocated(By locator, String text)
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className(id), text));
			//			wait.until(new ExpectedCondition<AndroidElement>() {
//				@Override
//				public AndroidElement apply(WebDriver driver) {
//					// TODO Auto-generated method stub
//					List<AndroidElement> elements = driver.f(id);
//					for (AndroidElement element : elements) {
//						if (element.getText().equals(text)) {
//							return element;
//						}
//					}
//				}
//			});
			
		} catch (NoSuchElementException ne) {
			return false;
//		} catch (InterruptedException ie) {
//			ie.printStackTrace();
//			return false;
		}
		return true;
	}
	
	
	
	
	// 根据className判断元素是否存在，List
	public boolean isElementsExistByclassName(long time, String className, int index) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			List<AndroidElement> elements = driver.findElementsByClassName(className);
			wait.until(ExpectedConditions.visibilityOf(elements.get(index)));
		} catch (NoSuchElementException ne) {
			System.out.println("找不到该元素： " + className + " ， index : " + index);
			return false;
		}
		return true;
	}
	
	
	
//	根据XPath路径来判断元素是否存在
	public boolean isElementExistByXPath(long time, String XPath) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath(XPath)));
		}  catch (NoSuchElementException ne) {
//			ne.printStackTrace();
			System.out.println("找不到该元素：" + XPath);
			return false;
		}
		return true;
	}
	
}

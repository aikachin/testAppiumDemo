package com.appium.testPro;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

	// 判断当前activity是否符合预期
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

//	public AndroidElement getElementById(String id) {
//		return driver.findElementById(id);
//	}
	
	
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
	
	// 根据className判断元素是否存在，List
	public boolean isElementsExistByclassName(long time, String className) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		List<AndroidElement> elements = driver.findElementsByClassName(className);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) elements));
				
		return true;
	}
	
	
	
}

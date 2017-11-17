package com.appium.testPro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage {
	
	AndroidDriver<AndroidElement> driver;
	WebElement phonenum;
	WebElement password;
	WebElement loginbtn;
	
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		phonenum = driver.findElementById("com.firstshop:id/phone");
		password = driver.findElementById("com.firstshop:id/pass");
		loginbtn = driver.findElementById("com.firstshop:id/login");
	}
	
	public void login(String phoneNum, String pwd) {
		phonenum.sendKeys(phoneNum);
		password.sendKeys(pwd);
		loginbtn.click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

}

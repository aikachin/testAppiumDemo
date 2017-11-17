package com.appium.testDemo;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class NewAPPTest {
	
	private AndroidDriver driver;
	
//  @Test
//  public void f() {
//  }
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
	  DesiredCapabilities dc = new DesiredCapabilities();
//	  配置平台名称
	  dc.setCapability("platformName", "Android");
//	  配置设备名称
	  dc.setCapability("deviceName", "127.0.0.1:52001");
//	  配置包名
	  dc.setCapability("appPackage", "com.ibox.calculators");
//	  配置activity名
	  dc.setCapability("appActivity", "com.ibox.calculators.CalculatorActivity");
	  
	//A new session could not be created的解决方法
      dc.setCapability("appWaitActivity","com.ibox.calculators.CalculatorActivity");
      //每次启动时覆盖session，否则第二次后运行会报错不能新建session
      dc.setCapability("sessionOverride", true);

//	  利用Appium打开设备
	  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
	  
//	  打开设备后等待载入页面5秒，防止找不到元素
	  try {
		  Thread.sleep(5000);
	  } catch (InterruptedException ie) {
		  ie.printStackTrace();
	  }
			 
  }
  
  @Test
  /**
   * 完成1+2=3的计算
   */
  public void plus() {
//	  点击键盘C清空键
	  driver.findElementById("com.ibox.calculators:id/clear").click();
//	  点击键盘数字1
	  driver.findElementById("com.ibox.calculators:id/digit1").click();
//	  点击键盘+加号键
	  driver.findElementById("com.ibox.calculators:id/plus").click();
//	  点击键盘数字2
	  driver.findElementById("com.ibox.calculators:id/digit2").click();
//	  点击键盘=等于键
	  driver.findElementById("com.ibox.calculators:id/equal").click();
//	  String result;
//	  result = driver.findElementById("com.ibox.calculators:id/output_month").getText();
//	  System.out.println("计算结果是： " + result);
	  Reporter.log("结束方法： " + Thread.currentThread().getStackTrace()[1].getMethodName());
  }

  @AfterClass
  public void afterClass() {
//	  退出driver
	  driver.quit();
  }

}

package com.appium.testPro;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MyPage {
	AndroidDriver<AndroidElement> driver;
//	  定义设置activity
	  String settingActivity = ".activity.SettingActivity";
	
	public MyPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
//	注销账号
	public void logout() {
		ReadyForNext readyForNext = new ReadyForNext(driver);
		//	注销用户，重新登录phoneNum
		if (readyForNext.isElementsExistById(3000, "android.widget.TextView", "设置")) {
			List<AndroidElement> elements = driver.findElementsByClassName("android.widget.TextView");
			for (AndroidElement element : elements) {
				if (element.getText().contains("设置")) {
					element.click();
//					判断是否处于settingActivity
					if (readyForNext.activityVerify(3000, driver, settingActivity)) {
//						退出登录
						readyForNext.getElementById(2000, "com.firstshop:id/exit").click();
//						driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					} else {
						System.out.println("发生错误，当前activity不合预期：" + driver.currentActivity());
					}
					break;
				}
			}
		} else {
			System.out.println("找不到设置按钮。。。");
		}
	}
}

package com.appium.testDemo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
//import io.appium.java_client.android.Connection;

/*
 * 本类用来测试appium的一些方法
 */
public class TestAppiumApi {
	private AndroidDriver driver;
//	private AppiumDriver driver2;
  
	//@Test
  public void testApi() {
//	  driver.runAppInBackground(5); // 将当前活跃的应用放在后台运行X秒
	  
	  appWait(5000);
/*	  driver.openNotifications(); // 打开Android的下拉通知栏
	  appWait(2000);
	  driver.pressKeyCode(4); // 按下返回键4
	  appWait(2000);
	  System.out.println("判断com.firstshop是否安装：" + driver.isAppInstalled("com.firstshop")); //判断应用是否安装
	  appWait(2000);
	  driver.pressKeyCode(3);
	  appWait(2000);
	  driver.removeApp("com.ibox.calculators"); //卸载应用
	  appWait(2000);
    */
	  driver.pressKeyCode(3);
	  appWait(2000);
	  driver.swipe(300, 300, 100, 300, 200); //模拟用户滑动，java-client 4.1.2以后版本不支持
	  appWait(2000);
	  driver.swipe(200, 300, 400, 300, 200); //模拟用户滑动，java-client 4.1.2以后版本不支持
	  appWait(2000);
/*	  driver.installApp("D:\\SVN\\apk\\com.ibox.calculators.apk");  //安装应用
	  appWait(2000);
	  driver.startActivity("com.ibox.calculators", "com.ibox.calculators.CalculatorActivity"); //启动其他app的activity
	  appWait(2000);
	  driver.pressKeyCode(3); // 按下Home键3
	  appWait(2000);
    */
//	  driver.lockDevice(); //锁屏，java-client 4.1.2可行
//	  appWait(5000);
	  System.out.println("已锁屏...");
	  driver.pressKeyCode(26); // 按下电源键26
	  appWait(2000);
	  WebElement ele1 = driver.findElementByXPath("//android.widget.Button[contains(@text, '1')]");
	  ele1.click();
	  System.out.println(driver.isLocked());
	  driver.findElementByXPath("//android.widget.Button[contains(@text, '2')]").click();
	  driver.findElementByXPath("//android.widget.Button[contains(@text, '3')]").click();
	  driver.findElementByXPath("//android.widget.Button[contains(@text, '4')]").click();
	  driver.findElementByXPath("//android.widget.Button[contains(@text, '5')]").click();
	  driver.findElementByXPath("//android.widget.Button[contains(@text, '6')]").click();
	  driver.findElementById("com.android.keyguard:id/key_enter").click();

	  appWait(1000);
	  System.out.println(driver.isLocked());
	  
  }
 
//  @Test
  public void testActionForFile() {
	  // push, pull 文件操作
	  File file = new File("d:\\u.txt");
	  String fileContent = null;
	  try {
		  fileContent = FileUtils.readFileToString(file);
	  } catch (IOException ioe) {
		  ioe.printStackTrace();
	  }
	  byte[] data = Base64.encodeBase64(fileContent.getBytes());
	  driver.pushFile("data/local/tmp/file2.txt", data);  //推送文件到设备中去
	  byte[] pullData = driver.pullFile("data/local/tmp/file1.txt");  //从设备中拉出文件
	  System.out.println(new String(Base64.decodeBase64(pullData)));
	  /*
	  try {
		  driver.pushFile("data/local/tmp/file2.txt", file);  //推送文件到设备中去, java-client4.1.2可行
	  } catch (IOException ioe) {
		  ioe.printStackTrace();
	  }*/
	  
	  
//	  driver.pushFile("data/local/tmp/notepad.txt", "this is a notepad");
  }
  
//  @Test
  public void testApi2() {
//	  appWait(5000);
	  // 截取屏幕文件，导出到本地
/*	  File snapShot = driver.getScreenshotAs(OutputType.FILE);
	  File screenShot = new File("f:\\test\\snapShot\\screenShot1.png");
	  try {
		  FileUtils.copyFile(snapShot, screenShot);
	  } catch (IOException ioe) {
		  ioe.printStackTrace();
	  }*/
	  
	  System.out.println(driver.getOrientation()); // 获取当前屏幕的方向
	  appWait(2000);
//	  driver.rotate(ScreenOrientation.LANDSCAPE); // 设置屏幕横屏 ，java-client 3.2.0未成功
//	  appWait(2000);
//	  driver.rotate(ScreenOrientation.LANDSCAPE); // 设置屏幕竖屏
//	  appWait(2000);

	  
	  // 获取网络状态
	  int netstatus = driver.getNetworkConnection().value; // java-client 3.2.0可行
	  System.out.println(netstatus); // java-client 3.2.0可行


//	  System.out.println(driver.getContextHandles()); //可用上下文，context可以理解为可进入的窗口，如果是native则为native_app，如果是webview为对应webview；
//	  appWait(2000);
//	  System.out.println(driver.getAppStringMap()); //获取应用的字符串
//	  appWait(2000);
//	  driver.closeApp(); // 关闭App
//	  appWait(2000);
//	  System.out.println("start to set networkConnection...");
//	  driver.setNetworkConnection(new NetworkConnectionSetting(false, false, true)); // // java-client 3.2.0夜深模拟器卡住没反应
	  appWait(2000);
//	  System.out.println("set networkConnection end...");
//
//	  driver.lockScreen(3); // X秒后锁屏？java-client 3.2.0
	  appWait(2000);
	  driver.scrollToExact("11.11"); // 未成功 // java-client 3.2.0可行
	  appWait(2000);
	  driver.scrollToExact("签到");
	  
//	  driver.

  }
  
  @Test
  public void testApi3() {
	  driver.pressKeyCode(3);
	  appWait(3000);
	  driver.findElementByAccessibilityId("图库").click();
	  appWait(2000);
	  driver.tap(1, 100, 100, 100); // fingers, x, y, duration
	  appWait(2000);
	  driver.tap(1, 100, 100, 100); // fingers, x, y, duration
	  appWait(2000);
	  driver.tap(1, 100, 100, 100); // fingers, x, y, duration
	  appWait(2000);
	  System.out.println("缩小。。。");
	  WebElement ele = driver.findElementById("com.android.gallery3d:id/gl_root_view");
//	  driver.performTouchAction((new TouchAction(driver)).tap(240,400)).waitAction().perform();
	  driver.pinch(240, 400); // 缩小 java-client 3.2.0未成功
	  appWait(2000);
	  System.out.println("放大。。。");
//	  driver.performTouchAction((new TouchAction(driver)).tap(240,400)).waitAction().perform();
	  driver.zoom(240, 400); // 放大 java-client 3.2.0未成功
	  appWait(2000);
  }
 
  @BeforeClass
  public void beforeClass() throws MalformedURLException{
	  DesiredCapabilities dc = new DesiredCapabilities();
	  dc.setCapability("browserName", ""); // web 浏览器名称（'Safari' ,'Chrome'等）。如果对应用进行自动化测试，这个关键字的值应为空。
	  dc.setCapability("platformName", "Android"); //你要测试的手机操作系统
	  dc.setCapability("platformVersion", "4.4.2"); //手机操作系统版本
//	  dc.setCapability("automationName", "selendroid");  //你想使用的自动化测试引擎：Appium (默认) 或 Selendroid
	  dc.setCapability("deviceName", "127.0.0.1:52001"); //使用的手机类型或模拟器类型，真机时输入Android Emulator或者手机型号
//	  dc.setCapability("udid", udid); //连接的物理设备的唯一设备标识,Android可以不设置
	  dc.setCapability("newCommandTimeout", "300");  //设置收到下一条命令的超时时间,超时appium会自动关闭session ,默认60秒
	  dc.setCapability("unicodeKeyboard", "True");//支持中文输入，会自动安装Unicode 输入法。默认值为 false
	  dc.setCapability("resetKeyboard", "True"); //在设定了 unicodeKeyboard 关键字的 Unicode 测试结束后，重置输入法到原有状态
//	  dc.setCapability("app", "D:\\SVN\\apk\\com.ibox.calculators.apk");  //未安装应用时，设置app的路径
	//手机已安装app，直接从手机启动app，上面路径不设置
	  dc.setCapability("appPackage", "com.firstshop");  //你想运行的Android应用的包名
	  dc.setCapability("appActivity", ".SplashActivity");  //你要启动的Android 应用对应的Activity名称|比如`MainActivity`, `.Settings`|
	  dc.setCapability("appWaitActivity", ".mian.MainFragementActivity");  //你想要等待启动的Android Activity名称|比如`SplashActivity`|
	  
	  driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public boolean activityVerify(long time, AndroidDriver driver, String activity) {
	  for (int i = 0; i < time/1000; i++) {
		  if (!driver.currentActivity().equals(activity)) {
			  appWait(1000);
		  } else {
			  return true;
		  }
	  }
	  return false;
  }

  public void appWait(long time) {
	  try {
		  Thread.sleep(time);
	  } catch (InterruptedException ie) {
		  ie.printStackTrace();
	  }
  }
}

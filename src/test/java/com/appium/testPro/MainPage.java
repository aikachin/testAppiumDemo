package com.appium.testPro;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MainPage {
	
	public AndroidDriver<AndroidElement> driver;
	List<AndroidElement> mainNaviBtn;
	
//	定义搜索框ID
	String searchBoxId = "com.firstshop:id/searchContent";
//	定义mainActivity
	String mainActivity = ".mian.MainFragementActivity";
//	定义搜索商品activity
	String searchGoodsActivity = ".ui.search.SearchActivity";
//	定义登录activity
	  String loginActivity = ".ui.login.activity.LoginActivity";
//	  定义商品信息activity
	  String goodsInfoActivity = ".ui.goods.GoodsInfoActivity";
	
	public MainPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	// 启动app
	public void launchApp() throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		  dc.setCapability("browserName", "");
		  dc.setCapability("platformName", "Android");
		  dc.setCapability("platformVersion", "4.4.2");
		  dc.setCapability("deviceName", "127.0.0.1:52001");
		  dc.setCapability("newCommandTimeout", "180");  //设置收到下一条命令的超时时间,超时appium会自动关闭session ,默认60秒
		  
		  dc.setCapability("unicodeKeyboard", "True");//支持中文输入，会自动安装Unicode 输入法。默认值为 false
		  dc.setCapability("resetKeyboard", "True"); //在设定了 unicodeKeyboard 关键字的 Unicode 测试结束后，重置输入法到原有状态
		  
		  dc.setCapability("appPackage", "com.firstshop");  //你想运行的Android应用的包名
		  dc.setCapability("appActivity", ".SplashActivity");  //你要启动的Android 应用对应的Activity名称|比如`MainActivity`, `.Settings`|
		  dc.setCapability("appWaitActivity", ".mian.MainFragementActivity");  //你想要等待启动的Android Activity名称|比如`SplashActivity`|
	      //每次启动时覆盖session，否则第二次后运行会报错不能新建session
	      dc.setCapability("sessionOverride", true);
	      
		  driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		  
		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	// 通过点击底部导航栏切换显示页面：按钮下标。0：首页，1：商家，2：社区，3：购物车，4：我的
	public void clickBottomNavi(int bottomNaviIndex) {
		// 获得底部导航栏List
		mainNaviBtn = driver.findElementsById("com.firstshop:id/tab_itme_txt");
		mainNaviBtn.get(bottomNaviIndex).click();
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	// 退出APP
	public void quitApp() {
		ReadyForNext ready = new ReadyForNext(driver);
		for (int i = 0; i < 6; i++) {
			driver.pressKeyCode(4);  // 按下返回键4
			// 判断确认按钮是否存在
			boolean flag = ready.isElementExistById(3000, "com.firstshop:id/positive");
			if (flag) {
				driver.findElementById("com.firstshop:id/positive").click();	
				break;
			}
		}
		driver.quit();
	}
	
//	搜索商品，并查看，参数：商品名称
	public boolean searchGoods(String goodsName) {
		ReadyForNext ready = new ReadyForNext(driver);
//		判断搜索框是否存在
		if (ready.isElementExistById(2000, "com.firstshop:id/searchContent")) {
			driver.findElementById("com.firstshop:id/searchContent").click();
		
//			判断是否进入搜索商品activity
			boolean flagSearch = ready.activityVerify(2000, driver, searchGoodsActivity);
			if (flagSearch) {
				return true;
			} else {
				System.out.println("超时错误，当前所在页面activity为：" + driver.currentActivity());
				return false;
			}
		} else {
			System.out.println("找不到搜索输入框，当前页面不在首页， 或者超时错误");
			return false;
		}
	}
	
	
//	购买商品：类别，大小，数量
	public void buyNow(String kind, String size, int num) {
		ReadyForNext ready = new ReadyForNext(driver);
//		判断当前activity是否是商品信息activity
		boolean flagGoodsInfo = ready.activityVerify(2000, driver, goodsInfoActivity);
		if (flagGoodsInfo) {
//			购买商品
			GoodsDetailsPage goodsDetailsPage = new GoodsDetailsPage(driver);
			goodsDetailsPage.buyNow(kind, size, num);
		} else {
			System.out.println("Wrong page to but goods...Current activity is : " + driver.currentActivity());
		}
		
	}
	
//	加入购物车：类别，大小，数量
	public void addShoppingCart(String kind, String size, int num) {
		ReadyForNext ready = new ReadyForNext(driver);
//		判断当前activity是否是商品信息activity
		boolean flagGoodsInfo = ready.activityVerify(2000, driver, goodsInfoActivity);
		if (flagGoodsInfo) {
//			加入购物车
			GoodsDetailsPage goodsDetailsPage = new GoodsDetailsPage(driver);
			goodsDetailsPage.addShoppingCart(kind, size, num);
		} else {
			System.out.println("Wrong page to add goods to shopping cart...Current activity is : " + driver.currentActivity());
		}
//		进入购物车判断添加成功
	}

//	登录用户正确性确认
	public void loginConfirm(String phoneNum, String pwd, String nickName) {
		ReadyForNext ready = new ReadyForNext(driver);
		LoginPage loginPage = new LoginPage(driver);
//		判断是否要登录，进入我的页面后，如果尚未登录，会自动跳转登录页面
		boolean flagLogin = ready.activityVerify(3000, driver, loginActivity);
		if (flagLogin) {
			loginPage.login(phoneNum, pwd);
//		如果已经登录
		} else {
//			判断当前登录用户是否符合预期
			if(!driver.findElementById("com.firstshop:id/usename").getText().equals(nickName)) {
				logout();
				loginPage.login(phoneNum, pwd);
			}
		}
	}
	
//	退出登录
	public void logout() {
		ReadyForNext ready = new ReadyForNext(driver);
		if (ready.activityVerify(3000, driver, mainActivity)) {
			clickBottomNavi(4);
			MyPage myPage = new MyPage(driver);
			myPage.logout();
		}
	}
	
//	查看购物车商品是否存在
	public boolean viewShoppingCart(String id, String goodsName) {
		ReadyForNext ready = new ReadyForNext(driver);
		backToMainActivity();
//		进入购物车页面
		clickBottomNavi(3);
		
		return ready.isElementsExistById(2000, "com.firstshop:id/goodsDescription", goodsName);
	}
	
//	返回mainActivity
	public void backToMainActivity() {
		ReadyForNext ready = new ReadyForNext(driver);
		while (!ready.activityVerify(2000, driver, mainActivity)) {
			driver.pressKeyCode(4);  // 按下返回键4
		}
	}
}

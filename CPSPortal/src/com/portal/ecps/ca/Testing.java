package com.portal.ecps.ca;

//Author : Testing Github

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class Testing {
	
	WebDriver driver;
	
		
	@BeforeClass
	@Parameters("BrowserType")
	public void setup(String BrowserType) throws Exception {
		
		         Logger log = Logger.getLogger(Log.class.getName());
                 DOMConfigurator.configure("log4j.xml"); 
	
                //Redirects the logs in Console for Firefox browser
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\logsgecko.txt");
                
				log.info("#######################################Log Started#################################");
				 //Setting up Webdriver for browsers 
				if (BrowserType.equalsIgnoreCase("firefox")) {
					//System.setProperty("webdriver.gecko.driver","C:\\Nanda\\Geckodriver\\geckodriver-v0.26.0-win64\\geckodriver.exe");				
					//driver = new FirefoxDriver();
					DesiredCapabilities cap = DesiredCapabilities.firefox();
					cap.setPlatform(Platform.WINDOWS);
					URL url=new URL("http://192.168.56.1:4444/wd/hub");
					driver=new RemoteWebDriver(url,cap);					
					log.info("Firefox Browser object created");
					Reporter.log("Firefox Browser object created",true);
				} else if (BrowserType.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", "C:\\Nanda\\Chromedriver\\78.0.3904.70\\chromedriver_win32\\chromedriver.exe");
				    driver = new ChromeDriver();
					log.info("Chrome Browser object created");
					Reporter.log("Chrome Browser object created",true);
				} else if (BrowserType.equalsIgnoreCase("edge")) {
					System.setProperty("webdriver.edge.driver","C:\\Nanda\\Edge driver\\MicrosoftWebDriver.exe");	
					driver = new EdgeDriver();
					log.info("Edge Browser object created");
					Reporter.log("Edge Browser object created",true);
				/*}  else if (BrowserType.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", "C:\\Nanda\\IE Driver\\IEDriver3.14.0\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					log.info("IE Browser object created");
					Reporter.log("IE Browser object created",true);	*/
				} else {
					System.out.println("Invalid Browser type");
				}
				  
							
				Thread.sleep(3000);
				driver.manage().window().maximize();
				log.info("Maximize browser window");
				Reporter.log("Maximize Browser Window",true);
				driver.get("https://qa-portal.ecps.ca");
				log.info("Navigate to eCPS QA portal");
				Thread.sleep(7000);
				Reporter.log("Navigate to eCPS QA portal",true);
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				log.info("Waiting for QA portal to load");
				Reporter.log("Waiting for QA portal to load",true);
				
				String title = driver.getTitle();
				System.out.println("The Tile text:"+title);
				Assert.assertTrue(title.contains("Home"), "Not Navigated to QA portal login page");
				//driver.findElement(By.xpath("//a[contains(text(),'Member Log In')]")).click();
				driver.findElement(By.cssSelector("li.loginlink")).click();
		        log.info("Clicks Member login");
				Reporter.log("Clicks on Member login link",true);
				
		        //driver.findElement(By.xpath("//input[@id='usernameInput']")).sendKeys("cps-admin1");
		        driver.findElement(By.cssSelector("input#usernameInput")).sendKeys("cps-admin1");
		        log.info("Enters user Name");
				Reporter.log("Enters user Name",true);
		        
		        driver.findElement(By.xpath("//input[@id='passwordInput']")).sendKeys("passw0rd1");
		        log.info("Enters Password");
				Reporter.log("Enters Password",true);
		        
		        //driver.findElement(By.xpath("//input[@name='btn_submit']")).click();
		        driver.findElement(By.cssSelector("input[name='btn_submit']")).click();
		        log.info("Clicks on login button");
		        Reporter.log("Clicks on login button",true);
		        //Verify user logged in success or not 

                WebDriverWait appheader = new WebDriverWait(driver, 40);
                WebElement loginpage = appheader.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@name='applicationHeader']")));
                boolean loginpagestatus = loginpage.isDisplayed();
                
                if (loginpagestatus == true) {
		        String textname =  driver.findElement(By.xpath("//span[@name='applicationHeader']")).getText();            
		        Assert.assertTrue(textname.contains("Application Links"), "User logged in not Success");
                }
				
	}
	
	
	
	
	@Test(priority=1)
	public void testingexercise() throws Exception {
		Logger log = Logger.getLogger(Log.class.getName());
        DOMConfigurator.configure("log4j.xml"); 
       //Mouse hover on Menus & Recipes tab 
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        //Window Resize 
        Dimension d = new Dimension(1024,768);
        driver.manage().window().setSize(d);
        driver.manage().window().maximize();
        Thread.sleep(2000);
   		Actions recipestab = new Actions(driver);
   		Thread.sleep(3000);
   		WebDriverWait wait13 = new WebDriverWait(driver, 40);
        wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Menus & Recipes')]")));
        WebElement maintab = driver.findElement(By.xpath("//a[contains(.,'Menus & Recipes')]"));
        Thread.sleep(2000);
   		recipestab.moveToElement(maintab).build().perform();
   		log.info("Selects Menus & Recipes Main tab");
   	    Reporter.log("Selects Menus & Recipes Main tab",true);
   		//*************	
   		//Thread.sleep(3000);
   	    WebDriverWait wait14 = new WebDriverWait(driver, 40);
   	    wait14.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='nav_aramark']//a[@class='1'][contains(text(),'Recipes')]")));
   		WebElement subtab = driver.findElement(By.xpath("//ul[@id='nav_aramark']//a[@class='1'][contains(text(),'Recipes')]"));
   		//WebElement subtab = driver.findElement(By.xpath("//a[contains(@href,'/wps/myportal/eCPS/root/MenusAndRecipes/RecipesHome/!ut/p/z1/04_Sj9CPykssy0xPLMnMz0vMAfIjo8zig509TRydDB0NLHxdDQwcfUM9zA1DTQ0MDM30wwkpiAJKG-AAjgZA_VFgJc7ujh4m5j4GBu6-AW4GRn4-LgH-fuaGBhYGUAV4zCjIjTDIdFRUBACVH6ft/dz/d5/L2dJQSEvUUt3QS80TmxFL1o2XzdBQUVIQTAxTU9FREMwUUdLTDUxSUMzME8x/')]"));
   		recipestab.moveToElement(subtab).build().perform(); 
   		Thread.sleep(4000);
   		recipestab.click(subtab).build().perform();
   		log.info("Selects Recipes Sub tab");
	    Reporter.log("Selects Recipes Sub tab",true);      
	    //Verify Recipes Home page displayed not not 
	    WebDriverWait wait15 = new WebDriverWait(driver, 40);
	    wait15.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='pull-left margin-top-5 ng-binding']")));
	    String recipeshome = driver.findElement(By.xpath("//h1[@class='pull-left margin-top-5 ng-binding']")).getText();
	    Assert.assertTrue(recipeshome.contains("Recipe Category"), "Recipes home page not exists");
	    log.info("Recipes Home page exists");
	    Reporter.log("Recipes Home page exists",true);
	    
	    //Click on recipe category 
	    WebDriverWait wait0 = new WebDriverWait(driver, 40);
	    wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='cat-card']//span[@class='ng-binding ng-scope'][contains(text(),'Appetizers')]"))).click();
	    //driver.findElement(By.xpath("//a[@class='cat-card']//span[@class='ng-binding ng-scope'][contains(text(),'Appetizers')]")).click();
	    WebDriverWait wait11 = new WebDriverWait(driver, 40);
	    wait11.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ng-binding']")));
	   String recipecategorypage = driver.findElement(By.xpath("//li[@class='ng-binding']")).getText();
	   Assert.assertTrue(recipecategorypage.contains("Recipe Listing"), "Recipelist page not exists");
	   log.info("Recipes List page exists");
	    Reporter.log("Recipes List page exists",true);
       
       
       
       //Explicit wait 
       
       WebDriverWait wait = new WebDriverWait(driver, 40);
       
       WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='listingGrid']")));
       
       boolean displaystatus = element.isDisplayed();
       
       if (displaystatus == true) {
    	log.info("Recipes table loaded");
   	    Reporter.log("Recipes table loaded",true);  
   	    

		//Get the no of rows from the webtable in recipes list page 
       int totalrows = driver.findElements(By.xpath("//table[@id='listingGrid']//tbody/tr")).size();
       System.out.println("Total no of rows :" +totalrows);
       
       //Get total no of columns 
       int totalcols = driver.findElements(By.xpath("//table[@id='listingGrid']//tbody/tr[2]/td")).size();
       System.out.println("Total no of columns :" +totalcols);
       
       //Get total number of Rows & Columns 
       int totalrowscols = driver.findElements(By.xpath("//table[@id='listingGrid']//tbody/tr/td")).size();
       System.out.println("Total no of rows & Columns :" +totalrowscols);
       
       
       //Click on Recipe Name 
       //String recipename = driver.findElement(By.xpath("//tr[2]//td[2]//a[1]//span[1]")).getText();
       //driver.findElement(By.xpath("//tr[2]//td[2]//a[1]//span[1]")).click();
       
       String recipename = Utility.isElementPresnt(driver,".//*//tr[2]//td[2]//a[1]//span[1]",30).getText();
       System.out.println("The Recipename:"+recipename);
       driver.findElement(By.xpath("//tr[2]//td[2]//a[1]//span[1]")).click();
       log.info("Clicks on Recipe name in list page");
  	   Reporter.log("Clicks on Recipe name in list page",true);  
  	    WebDriverWait wait19 = new WebDriverWait(driver, 40);
	    wait19.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='recNameAttr']")));
       String viewrecipe = driver.findElement(By.xpath("//span[@id='recNameAttr']")).getText();
       SoftAssert assertion = new SoftAssert();
       assertion.assertEquals(recipename, viewrecipe,"The View Recipepage not exists");
       assertion.assertAll();
       Thread.sleep(4000);
    	   
    	   
       }
       
      
		
	}
	
	
	
	@Test(priority=0)
	public void Marketingtab() throws Exception {
    
		
		Logger log = Logger.getLogger(Log.class.getName());
        DOMConfigurator.configure("log4j.xml");
        
        
		WebDriverWait waitMkt = new WebDriverWait(driver, 40);
        WebElement element = waitMkt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Marketplace')]")));

        
        boolean displaystatus = element.isDisplayed();
        
        System.out.println("The value of"+displaystatus);
        
        if (displaystatus == true) {
        WebElement mainMenu = driver.findElement(By.xpath("//ul[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Marketplace')]"));
        Actions act = new Actions(driver);
        act.moveToElement(mainMenu).build().perform(); 
        Thread.sleep(3000);
        act.click(mainMenu).build().perform();
        long start = System.currentTimeMillis();
		log.info("Clicks on Marketplace tab");
		Reporter.log("Clicks on Marketplace tab",true);
		WebDriverWait waitMkt2 = new WebDriverWait(driver, 30);
		waitMkt2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='eduSelectedLink']")));
		String marketplacehomepage = driver.findElement(By.xpath("//li[@class='eduSelectedLink']")).getText();
		SoftAssert assertion = new SoftAssert();
	    assertion.assertTrue(marketplacehomepage.contains("Marketplace"),"Marketplace home page not exists");
	    assertion.assertAll();
	    System.out.println("Marketplace home page start");
	    
	    //Clicks on Add Supplier icon 
	    WebDriverWait waitMkt12 = new WebDriverWait(driver, 7);
	    boolean invisible = waitMkt12.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
	    long finish = System.currentTimeMillis();
	    long totalTime = finish - start; 
	    System.out.println("Total Time for page load - "+totalTime); 
	    
	    
	    if (invisible) {
	      try {	
	       //WebDriverWait waitMktadd = new WebDriverWait(driver, 10);
	       //waitMktadd.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='add-marketplace-icon']")));	  
	       System.out.println("Click Start");
	       driver.findElement(By.xpath("//img[@class='add-marketplace-icon']")).click();
	       WebDriverWait waitMkt3 = new WebDriverWait(driver, 20);
	 	   waitMkt3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='txt-blue ng-binding']")));
	 	   String createsupplier = driver.findElement(By.xpath("//h1[@class='txt-blue ng-binding']")).getText();
	 	   SoftAssert assertion1 = new SoftAssert();
	 	   assertion1.assertTrue(createsupplier.contains("Create Supplier Partner"),"Create Supplier page not exists");
	 	   assertion1.assertAll();
	 	   log.info("Clicks on Add supplier icon");
	 	   Reporter.log("Clicks on Add supplier icon",true);
	      } catch(NoSuchElementException | StaleElementReferenceException e) {
      	    System.out.println("The Add Supplier is not Applicable");
      	}
	      
	    }
	    
	    
		//waitMkt12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='add-marketplace-icon']"))).click();
	   
	    
	   //Clicks on Marketplace Reports 
	   driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	   driver.findElement(By.xpath("//img[@class='generate-report-icon']")).click();
	   WebDriverWait waitMkt4 = new WebDriverWait(driver, 30);
	   waitMkt4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='ng-binding']")));
	   String mktreports = driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText();
	   assertion.assertTrue(mktreports.contains("Marketplace Reports"),"Marketplace Reports page not exists");
	   assertion.assertAll();
	   log.info("Clicks on Generate Reports icon");
	   Reporter.log("Clicks on Generate Reports icon",true);
	   //Clicks on My Favorities 
	   driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	   Thread.sleep(3000);
	   driver.findElement(By.xpath("//img[@class='fav-contacts-icon']")).click();
	   WebDriverWait waitMkt5 = new WebDriverWait(driver, 30);
	   waitMkt5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='myfav_1']//h2[contains(text(),'Distributors')]")));
	   String myfavoritiestext = driver.findElement(By.xpath("//div[@id='myfav_1']//h2[contains(text(),'Distributors')]")).getText();
	   System.out.println("Favorities text:"+myfavoritiestext);
	   assertion.assertTrue(myfavoritiestext.contains("Distributors"),"Marketplace MyFavorities page not exists");
	   assertion.assertAll();
	   log.info("Clicks on My Favorities icon");
	   Reporter.log("Clicks on My Favorities icon",true);
	}
	
	}
	
	@Test(priority=2)
	public void menubuildertab() throws Exception {
		
		Logger log = Logger.getLogger(Log.class.getName());
        DOMConfigurator.configure("log4j.xml");
        
        //Window Resize 
        Dimension d = new Dimension(1024,768);
        driver.manage().window().setSize(d);
        driver.manage().window().maximize();
        
        SoftAssert assertion = new SoftAssert();
        WebDriverWait waitMB = new WebDriverWait(driver, 50);
        waitMB.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Menus & Recipes')]")));
        WebElement mainMenu = driver.findElement(By.xpath("//a[contains(text(),'Menus & Recipes')]"));
        //WebElement mainMenu =  Utility.isElementPresnt(driver,".//*[contains(text(),'Menus & Recipes')]",30);
        Thread.sleep(2000);
        Actions act = new Actions(driver);
        act.moveToElement(mainMenu).build().perform(); 
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        WebElement subtab = driver.findElement(By.xpath("//ul[@id='nav_aramark']//a[@class='1'][contains(text(),'MenuBuilder')]"));
        //WebElement subtab = Utility.isElementPresnt(driver,".//*[@id='nav_aramark']//a[@class='1'][contains(text(),'MenuBuilder')]",30);
        act.moveToElement(subtab).build().perform(); 
        Thread.sleep(5000);
        act.click(subtab).build().perform();
		log.info("Clicks on Menus & Recipes tab");
		Reporter.log("Clicks on Menus & Recipes tab",true);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebDriverWait waitMBname = new WebDriverWait(driver, 60);
		String mymenustext= waitMBname.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@name='baseMenusLabel']"))).getText();
		//String mymenustext = driver.findElement(By.xpath("//span[@name='baseMenusLabel']")).getText();
	    assertion.assertTrue(mymenustext.contains("Base Menus"),"Menubuilder page not exists");
		assertion.assertAll();
		log.info("Menu builder page exists");
		Reporter.log("Menu builder page exists",true);
        Thread.sleep(4000);
		
	}
	
	@Test(priority=3)
	public void Themedaystab() {
		Logger log = Logger.getLogger(Log.class.getName());
        DOMConfigurator.configure("log4j.xml");
        SoftAssert assertion = new SoftAssert();
        WebDriverWait waitTD = new WebDriverWait(driver, 50);
        waitTD.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Theme Days')]")));
        WebElement mainMenu = driver.findElement(By.xpath("//a[contains(text(),'Theme Days')]"));
        Actions act = new Actions(driver);
        act.moveToElement(mainMenu).build().perform(); 
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        act.click(mainMenu).build().perform();
		log.info("Clicks on Themedays tab");
		Reporter.log("Clicks on Themedays tab",true);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		WebDriverWait waitTDname = new WebDriverWait(driver, 50);
		waitTDname.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Theme Days')]")));
		String mythemedaystext = driver.findElement(By.xpath("//li[contains(text(),'Theme Days')]")).getText();
	    assertion.assertTrue(mythemedaystext.contains("Theme Days"),"Themedays page not exists");
		assertion.assertAll();
		log.info("Themedays page exists");
		Reporter.log("Themedays page exists",true);
		
	}
	
	@Test(priority=4)
	public void Educationtab() throws Exception {
		Logger log = Logger.getLogger(Log.class.getName());
        DOMConfigurator.configure("log4j.xml");
        SoftAssert assertion = new SoftAssert();
        WebDriverWait waitTD = new WebDriverWait(driver, 30);
        waitTD.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Education')]")));
        WebElement mainMenu = driver.findElement(By.xpath("//ul[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Education')]"));
        Actions act = new Actions(driver);
        act.moveToElement(mainMenu).build().perform(); 
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        act.click(mainMenu).build().perform();
		log.info("Clicks on Education tab");
		Reporter.log("Clicks on Education tab",true);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		WebDriverWait waitTDname = new WebDriverWait(driver, 30);
		waitTDname.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='TopicsbyCategory']")));
		String myeducationtext = driver.findElement(By.xpath("//span[@id='TopicsbyCategory']")).getText();
	    assertion.assertTrue(myeducationtext.contains("Topic By Category"),"Education page not exists");
		assertion.assertAll();
		log.info("Education page exists");
		Reporter.log("Education page exists",true);
		Thread.sleep(5000);
		
	}
	
	
	@AfterClass
	public void teardown() {
		driver.quit();
		
		
	}
	
	
	
	
	
	
}

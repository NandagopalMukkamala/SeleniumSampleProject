package com.portal.ecps.ca;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Verifylogin {

	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(Log.class.getName());
		DOMConfigurator.configure("log4j.xml");
				
		
		//System.setProperty("webdriver.gecko.driver","C:\\Nanda\\Geckodriver\\geckodriver-v0.22.0-win64\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Nanda\\Chromedriver\\74.0.3729.6\\chromedriver_win32\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Nanda\\Chromedriver\\76.0.3809.68\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "C:\\Nanda\\IE Driver\\IEDriver3.14.0\\IEDriverServer.exe");
		//System.setProperty("webdriver.edge.driver","C:\\Nanda\\Edge driver\\MicrosoftWebDriver.exe");
		

		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\logsgecko.txt");

		
		
		log.info("##################################################################################");
		log.info("###############################Log Started#########################################");
		
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new InternetExplorerDriver();
	      //WebDriver driver = new ChromeDriver();
		log.info("Browser object created");
		//WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		log.info("Maximize browser");
		driver.get("https://qa-portal.ecps.ca");
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        System.out.println("This is my title:"+driver.getTitle());
        //Thread.sleep(5000);
        
        Utility.isElementPresnt(driver,".//*[contains(text(),'Member Log In')]",20).click();
        Utility.isElementPresnt(driver,".//*[@id='usernameInput']",20).sendKeys("cps-siteuser");
        Utility.isElementPresnt(driver,".//*[@id='passwordInput']",20).sendKeys("passw0rd2");
        Utility.isElementPresnt(driver,".//*[@name='btn_submit']",20).click();
        
        //driver.findElement(By.xpath("//a[contains(text(),'Member Log In')]")).click();
       // driver.findElement(By.xpath("//input[@id='usernameInput']")).sendKeys("cps-admin1");
       // driver.findElement(By.xpath("//input[@id='passwordInput']")).sendKeys("passw0rd1");
        //driver.findElement(By.xpath("//input[@name='btn_submit']")).click();
        log.info("Clicks on login in login page");
        //Thread.sleep(10000);
        
       
        WebElement MainMenu = Utility.isElementPresnt(driver,".//*[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Marketplace')]",10);
        Actions act = new Actions(driver);
        act.moveToElement(MainMenu).build().perform();
        Thread.sleep(3000);
        act.click(MainMenu).build().perform();
        WebDriverWait waitMkt12 = new WebDriverWait(driver, 10);
        
       Thread.sleep(5000);
        
        if(driver.findElement(By.id("loader"))!= null){
        	System.out.println("Element is Present");
        	}else{
        	System.out.println("Element is Absent");
        	}
        
        
        	
        	boolean invisible = waitMkt12.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
            if (invisible) {
            	
            	Thread.sleep(3000);
            	
            	try {
            	   driver.findElement(By.xpath("//img[@class='add-marketplace-icon']")).click();
            	   System.out.println("Clicks on Add Supplier icon");
            	     
            	} catch(NoSuchElementException | StaleElementReferenceException e) {
            	    System.out.println("Handled NO Such Element Exceptions");
            	}
               
            		//Utility.isElementPresnt(driver,".//*[@class='add-marketplace-icon']",10).click();
            	
            	
            
            }
        //driver.findElement(By.xpath("//img[@class='add-marketplace-icon']")).click();
        
        //WebDriverWait wait = new WebDriverWait(driver, 20);
        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Marketplace')]")));

        
        //boolean displaystatus = element.isDisplayed();
        
        //if (displaystatus == true) {
       
        
        //WebElement mainMenu = driver.findElement(By.xpath("//ul[@id='nav_aramark']//a[@class='arrow'][contains(text(),'Marketplace')]"));
       // Actions act = new Actions(driver);
        //act.moveToElement(mainMenu).build().perform();  
        //Thread.sleep(3000);
        //act.click(mainMenu).build().perform();
        
        
        //driver.findElement(By.xpath("//div[@id='ns_Z7_SCI4AB1A0O75C0A1DM05RJ00O0_view_PF_SmartRefreshID']//a[1]//img[1]")).click();
        
        //driver.findElement(By.xpath("//img[@class='add-marketplace-icon']")).click();
        
        
        //driver.quit();
              
        
       // }

        
             	
        	
        	
        }
        
        

	}



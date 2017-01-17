package com.Metrodigi.util;

import org.testng.annotations.BeforeMethod;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;




//import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Metrodigi.pagehelper.EditProjectsHelper;
import com.Metrodigi.pagehelper.HomePageHelper;
import com.Metrodigi.pagehelper.LoginPageHelper;


public abstract class DriverTestCase 
{

	enum DriverType 
	{ Firefox, IE, Chrome }

	//Define objects
	protected WebDriver driver;

	//Initialize objects
	public PropertyReader propertyReader = new PropertyReader();
	public HomePageHelper homepageHelper = new HomePageHelper(getWebDriver());
//	public LoginPageHelper loginpageHelper = new LoginPageHelper(getWebDriver());
	public EditProjectsHelper editProjectsHelper;
	//Define variables
	public String application_url = propertyReader.readApplicationFile("URL");
	String env = propertyReader.readApplicationFile("Environment"); 
    public String username1 = propertyReader.readApplicationFile("Username1");
	public String password1 = propertyReader.readApplicationFile("Password1");
	public String collectionname1 = propertyReader.readApplicationFile("Collectionname1");
	File downloadDir;
	
	
	
	@BeforeMethod
	@BeforeTest
	public void setUp() 
	{		
		System.setProperty("webdriver.chrome.driver", "D:/AutomationWorkspace/Metrodigi/lib/chromedriver.exe");
		driver = new ChromeDriver();
		/*String driverType = propertyReader.readApplicationFile("BROWSER");		
		if (DriverType.Firefox.toString().equals(driverType)) 
		{ 
			//Set Folder path in which files gets saved
			if(env.equals("Windows"))
				downloadDir = new File(System.getProperty("user.dir")+"\\data");
			if(env.equals("Linux"))
				downloadDir = new File(System.getProperty("user.dir")+"//data");
			if(env.equals("Mac"))
				downloadDir = new File(System.getProperty("user.dir")+"//data");
			  //Delete all the files in the folder
			  String[] list = downloadDir.list();
			  for(String s: list)
			  {
				  File currentFile = new File(downloadDir.getPath(),s);
				  currentFile.delete();
				  System.out.println("direcorty delete"); 
			  }
			
			  //Create Folder if not exists
	           if (!downloadDir.exists()){
		            downloadDir.mkdir();
		            System.out.println("direcorty created"); 
		        }
	            String strDownloadPath = "";
	            if(env.equals("Windows"))
	            	strDownloadPath =(System.getProperty("user.dir")+"\\data"); 
	            if(env.equals("Linux"))
	            	strDownloadPath =(System.getProperty("user.dir")+"//data");
	            if(env.equals("Mac"))
	            	strDownloadPath =(System.getProperty("user.dir")+"//data");
		        //use Firefox profile with desired capabilities
		        FirefoxProfile profile=new FirefoxProfile();
				DesiredCapabilities cap = DesiredCapabilities.firefox();
				//Set preferences in firefox profile         
		        profile.setPreference("browser.download.folderList", 2);
		        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, text/csv, application/download, application/epub image/png ,image/jpeg, application/pdf, text/html, text/plain, application/octet-stream");
		        profile.setPreference("browser.download.dir", strDownloadPath);
		        System.out.println("Path is:"+strDownloadPath);
		        profile.setPreference("browser.download.manager.showWhenStarting", false);
		        profile.setPreference("browser.download.manager.focusWhenStarting", false);
		        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		        profile.setPreference("browser.download.manager.closeWhenDone", false);
		        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		        profile.setPreference("browser.download.manager.useWindow", false);
		        profile.setPreference("browser.download.manager.showWhenStarting",false);
		        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		        profile.setPreference("pdfjs.disabled", true);
		        //set new profile preferences
		        profile.setAcceptUntrustedCertificates(false);
		        profile.setAssumeUntrustedCertificateIssuer(true);
		       
		        //Set proxy IP and port. Here localhost Is proxy IP and 8085 Is Port number. 
		        //You can change both values as per your requirement. 
		        String PROXY = "localhost:8085"; 
		        //Bellow given syntaxes will set browser proxy settings using DesiredCapabilities. 
		        Proxy proxy = new Proxy(); 
		        proxy.setHttpProxy(PROXY).setFtpProxy(PROXY)
		        						 .setSslProxy(PROXY) 
		        						 .setSocksProxy(PROXY); 
		        //Use Capabilities when launch browser driver Instance. 
		        cap = DesiredCapabilities.firefox(); 
		        //setting desired capabilities
		        //cap.setCapability(CapabilityType.PROXY, proxy); 
		        cap.setCapability(FirefoxDriver.PROFILE, profile);
		        driver =  new FirefoxDriver(cap);
		        //driver = new FirefoxDriver(profile);
		} 		

		else if (DriverType.IE.toString().equals(driverType)) 
		{ 
			String path1 = getPath();
			File file = new File(path1+"//IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);			
			driver = new InternetExplorerDriver(capabilities); 
			//driver = new InternetExplorerDriver();
		}
		else if (DriverType.Chrome.toString().equals(driverType)) 
		{ 
			if(env.equals("Mac")) {
				System.setProperty("webdriver.chrome.driver", "/Users/mayankpurohit/Desktop/Mayank/AutomationWorkspace/Metrodigi/lib/chromedriver");
			}
			else {
			String path1 = getPath();
			System.out.println(path1);
			String chromeDriverPath= path1+"\\chromedriver.exe";

			//Set the required properties to instantiate Chrome driver. Place any latest Chromedriver.exe files under Drivers folder
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options); } 
		else 
		{ driver = new FirefoxDriver(); }	*/		

		//Maximize window
		driver.manage().window().maximize();

		//Delete cookies
		driver.manage().deleteAllCookies();
	}	

	@AfterTest
    public void afterMainMethod() 
	{			
		try {
			Thread.sleep(5000);
			//driver.quit();
		} catch (Exception e) {
		}
	}
	
	@AfterMethod
	public void endMethods() throws Exception
	{		
		ExecutionLog.LogEndClass(this.getClass().getName());
	}

	public String getNewDate(int num)
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, num);
		Date todate1 = cal.getTime();
		String fromdate = dateFormat.format(todate1);
		return fromdate;		
	}

	public WebDriver getWebDriver()
	{
		return driver;
	}

	//Handle child windows
	public String switchPreviewWindow()
	{
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();		
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}

	//Get random integer
	public int getRandomInteger(int aStart, int aEnd){
		Random aRandom = new  Random();
		if ( aStart > aEnd ) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		//get the range, casting to long to avoid overflow problems
		long range = (long)aEnd - (long)aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long)(range * aRandom.nextDouble());
		int randomNumber =  (int)(fraction + aStart);    
		return randomNumber;
	}

	//Get random string
	public String randomString( int len ) 
	{
		//String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String AB = "abcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}

	//Get absolute path
	public String getPath()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return path;
	}

	//Get absolute path
	public String getPathUpload()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("/", "//");		
		return path;
	}


	//Switch frame
	public void switchFrame(String[] arr){
		for (int i = 0; i < arr.length; i++)
		{			
			getWebDriver().switchTo().frame(arr[i]);
		}
	}


	//capturing screenshot 
	public void captureScreenshot(String fileName) {
		try {
			String screenshotName = this.getFileName(fileName);
			FileOutputStream out = new FileOutputStream("screenshots//" + screenshotName + ".jpg");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
			String path = getPath();
			String  screen = "file://"+path+"/screenshots/"+screenshotName + ".jpg";
			Reporter.log("<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
		} catch (Exception e) {

		}
	}

	//Creating file name
	public  String getFileName(String file){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		 
		String fileName = file+dateFormat.format(cal.getTime());
		return fileName;
	}	

	public boolean isTextAvailable(String text){
		try{
			boolean b = getWebDriver().getPageSource().contains(text);
			return b;
		}
		catch(Exception e){
			return false;
		}
	}

	//Switch frame
	public void switchFrame(String arr)
	{
		getWebDriver().switchTo().frame(arr);			
	}

	//Navigate to any URL
	public void RedirectURL (String URL)
	{
		getWebDriver().navigate().to(URL);
	}
	
	public void launchURLInNewTab(String URL) throws IOException {
        try {
        	getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
            getWebDriver().manage().timeouts()
                    .pageLoadTimeout(3000, TimeUnit.MILLISECONDS);
            getWebDriver().navigate().to(URL);
            Reporter.log("Successfully launched Shipadmin page in new tab <br> <p>");
        } catch (Exception e) { 
        	e.printStackTrace();            
        }
        getWebDriver().manage().timeouts()
                .pageLoadTimeout(3000, TimeUnit.MILLISECONDS);// Setting back
        // to default
        // Selenium
        // timeout
    }

	

	/*
	 * Common re usable method  * 	
	 */

	public void waitForElementLoad(int x)
	{
		int num = x*1000;
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Wait for element present
		public void WaitForElementPresent(String locator, int timeout) 
		{
			for (int i = 0; i < timeout; i++) {
				if (isElementPresent(locator)) {
					break;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Assert element present
		public Boolean isElementPresent(String locator) 
		{
			Boolean result = false;
			try {
				getWebDriver().findElement(ByLocator(locator));
				result = true;
			} catch (Exception ex) {
			}
			return result;
		}
		
		// Handle locator type
		public By ByLocator(String locator) {
			By result = null;

			if (locator.startsWith("//")) {
				result = By.xpath(locator);
			} else if (locator.startsWith("css=")) {
				result = By.cssSelector(locator.replace("css=", ""));
			} else if (locator.startsWith("#")) {
				result = By.name(locator.replace("#", ""));
			} else if (locator.startsWith("link=")) {
				result = By.linkText(locator.replace("link=", ""));
			} else {
				result = By.id(locator);
			}
			return result;
		}
		//File Upload method
		
		public void typeCharacter(Robot robot, String letter)
	      {

	    	Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
	    	StringSelection str = new StringSelection(letter);
	    	clip.setContents(str, str);
	    	robot.delay(250);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.delay(50);
		    robot.keyRelease(KeyEvent.VK_ENTER);
	       } 
		
		public void uploadfile() throws Exception{
				
		//put path to your file in a clipboard
	    
		Robot robot = new Robot();
		String filepath = System.getProperty("user.dir")+"\\workspace\\Metrodigi\\Attachments\\Master Word.docx";
		this.typeCharacter(robot, filepath);
  	    //imitate mouse events like ENTER, CTRL+C, CTRL+V
	   }		
}

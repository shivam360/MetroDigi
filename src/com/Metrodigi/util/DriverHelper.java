package com.Metrodigi.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;


public abstract class DriverHelper {
	// Define objects
	public WebDriver driver;

	// Declare objects
	public PropertyReader propertyReader = new PropertyReader();
	public DriverHelper(WebDriver webdriver) {
		driver = webdriver;
		//selenium = new WebDriverBackedSelenium(driver, "");
	}
	
	
	// Return web driver object
	public WebDriver getWebDriver() {
		return driver;
	}

	// Print message on screen
	public void Log(String logMsg) {
		System.out.println(logMsg);
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

	// Wait for element not present
	public void WaitForElementNotPresent(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (!isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element enabled
	public void WaitForElementEnabled(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not enabled
	public void WaitForElementNotEnabled(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element visible
	public void WaitForElementVisible(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator))
						.isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not visible
	public void WaitForElementNotVisible(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator))
						.isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for text present
	public void WaitForTextPresent(String locator, String text, int timeout) {
		WaitForElementPresent(locator, timeout);
		for (int i = 0; i < timeout; i++) {
			if (isTextPresent(locator, text)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Handle mouse over action
	public void mouseOver(String locator) {
		this.WaitForElementPresent(locator, 50);
		WebElement el = getWebDriver().findElement(ByLocator(locator));

		// build and perform the mouseOver with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(el).build().perform();		
	}	

	// Handle mouse double click action
	public void mouseDoubleClick(String locator) {
		this.WaitForElementPresent(locator, 50);
		WebElement el = getWebDriver().findElement(ByLocator(locator));

		// build and perform the mouse click with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());		
		builder.doubleClick(el).perform();
	}	

	// Handle drag and drop action
	public void dragAndDrop(String draggable, String to) {
		this.WaitForElementPresent(draggable, 50);
		this.WaitForElementPresent(to, 50);
		WebElement elDraggable = getWebDriver().findElement(ByLocator(draggable));
		WebElement todrag = getWebDriver().findElement(ByLocator(to));

		// build and perform drag and drop with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());
		builder.dragAndDrop(elDraggable, todrag).perform();
	}

	// Handle click action
	public void clickOn(String locator) {
		//this.WaitForElementPresent(locator, 30);
		this.WaitForElementEnabled(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.click();
	}

	// Handle send keys action
	public void sendKeys(String locator, String text) {
		this.WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.clear();
		el.sendKeys(text);
	}
	
	public void importFile(String path) {
	    WebElement fileInput = driver.findElement(By.xpath("//*[@id='imageUpload']/div/div[2]/input"));
	    fileInput.sendKeys(path);
     }
	
	// Handle send keys action
		public void sendKeysAction(String locator, String text) {
			this.WaitForElementPresent(locator, 30);
			Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
			WebElement el = getWebDriver().findElement(ByLocator(locator));
			Actions builder = new Actions(getWebDriver());
			//builder.sendKeys(el, text).perform();
			builder.click(el).perform();
			builder.sendKeys(text).perform();
			//builder.sendKeys(Keys.ENTER).perform();		
			}

	// Select value from drop down
	public void selectDropDown(String locator, String targetValue) {
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+ locator + " Not found");
		this.WaitForElementPresent(locator, 30);
		new Select(getWebDriver().findElement(ByLocator(locator)))
				.selectByVisibleText(targetValue);

	}

	// Assert text present
	public boolean isTextPresent(String locator, String str) {
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+ locator + " Not found");
		String message = getWebDriver().findElement(ByLocator(locator)).getText();
		if (message.contains(str)) {
			return true;
		} else {
			return false;
		}
	}
	// Verify text is available or not
	public boolean isTextAvailable(String text){
		try{
			boolean b = getWebDriver().getPageSource().contains(text);
			return b;
		}
		catch(Exception e){
			return false;
		}
	}
	// Store text from a locator
	public String getText(String locator) {
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+ locator + " Not found");
		String text = getWebDriver().findElement(ByLocator(locator)).getText();
		return text;
	}

	// Store row count from a locator
	public int getCount(String locator) {
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+ locator + " Not found");
		List<WebElement> el = getWebDriver().findElements(ByLocator(locator));
		int count = el.size();
		return count;
	}

	// Assert check box selected
	public boolean isChecked(String locator) {
		boolean checkStatus = false;
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+ locator + " Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		checkStatus = el.isSelected();
		return checkStatus;
	}

	// Get attribute value
	public String getAttribute(String locator, String attribute) {
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+ locator + " Not found");
		String text = getWebDriver().findElement(ByLocator(locator)).getAttribute(attribute);
		return text;
	}

	// Get integer value
	public Integer getInt(String strString) {
		Pattern intsOnly = Pattern.compile("\\d+");
		String input = strString;
		Matcher makeMatch = intsOnly.matcher(input);
		makeMatch.find();
		String digitStr = makeMatch.group();
		Integer digit = Integer.parseInt(digitStr);
		return digit;
	}

	// Execute java script
	public void javaScriptExecute(String javascrpt) {
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript(javascrpt);
	}

	public void acceptAlert() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = getWebDriver().switchTo().alert();
		alert.accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifyTextPresent(String text)
	 {
	  
	  try {
	   Thread.sleep(5000);
	  } catch (InterruptedException e) {
	   e.printStackTrace();
	  }
	  boolean result = driver.findElement(By.cssSelector("body")).getText().contains(text);
	  Assert.assertTrue(result);
	  //return result;
	 }
	
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
	public void refreshPage() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getWebDriver().navigate().refresh();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	//Hold and drag method
	public void holdAndDrag(String locator, int hold_X, int hold_Y, int drag_X, int drag_Y) {
		
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(getWebDriver().findElement(ByLocator(locator)), hold_X, hold_Y).clickAndHold().moveByOffset(drag_X, drag_Y).release().build().perform();

	}
	
	public void selectCanvasArea(String locator, int xCanvas, int yCanvas, int xTarget, int yTarget) {
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(getWebDriver().findElement(ByLocator(locator)),xCanvas,yCanvas) //(300,300)
	            .clickAndHold()
	            .release()
	            .build()
	            .perform();
	}
	
	//Drag and Type method
	public void dragAndType(String locator, int hold_X, int hold_Y,String text) {
		
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(getWebDriver().findElement(ByLocator(locator)), hold_X, hold_Y)
			   .click()
			   .sendKeys(text)
			   .perform();

	}
	
	//File Upload method
	public void typeCharacter(Robot robot, String letter)
    {

  	Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
  	StringSelection str = new StringSelection(letter);
  	clip.setContents(str, str);
  //imitate mouse events like ENTER, CTRL+C, CTRL+V
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
	    robot.delay(90);
	    Reporter.log("Uploaded file path is :"+letter, true);
     } 
	
	public void uploadDocxfile() throws Exception{
		Robot robot1 = new Robot();	
	//put path to your file in a clipboard
    
	String filepath = System.getProperty("user.dir")+"\\Attachments\\Master Word.docx";
	this.typeCharacter(robot1, filepath);
	
   }
	public void uploadAudiofile() throws Exception{
		
		//put path to your file in a clipboard
	    Robot robot2 = new Robot();
		String filepath = System.getProperty("user.dir")+"\\Attachments\\Audio.mp3";
		this.typeCharacter(robot2, filepath);
		}
   public void uploadimagefile() throws Exception{
		
		//put path to your file in a clipboard
	    Robot robot3 = new Robot();
		String filepath2 = System.getProperty("user.dir")+"\\Attachments\\Tiger.jpg";
		this.typeCharacter(robot3, filepath2);
		}
   public void uploadVideofile() throws Exception{
		
		//put path to your file in a clipboard
	    Robot robot = new Robot();
		String filepath3 = System.getProperty("user.dir")+"\\Attachments\\video.mp4";
		this.typeCharacter(robot, filepath3);
		}
   public void uploadepubfile() throws Exception{
		Robot robot4 = new Robot();	
	//put path to your file in a clipboard
   
	String filepath = System.getProperty("user.dir")+"\\Attachments\\accessible_epub_3.epub";
	this.typeCharacter(robot4, filepath);
	
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
 	//Select value from unordered list
 	public void selectValueFromUnorderedList(WebElement unorderedList, final String value) {
 	    List<WebElement> options = unorderedList.findElements(By.tagName("label"));

 	    for (WebElement option : options) {
 	        if (value.equals(option.getText())) {
 	            option.click();
 	            break;
 	        }
 	    }
 	}
}

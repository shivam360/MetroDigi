/*==============================================================================================================================
 File Name    : LoginPageHelper.java
 ClassName    : LoginPageHelper
 Summary      : Contains methods of Login Page.
 ===============================================================================================================================
 History      :   Company            Created By     
                  360logica                         

 ===============================================================================================================================
 Remarks      :   Tests - 
 ===============================================================================================================================*/

package com.Metrodigi.pagehelper;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.Metrodigi.locators.LocatorReader;
import com.Metrodigi.util.DriverHelper;
import com.Metrodigi.util.PropertyReader;

public class LoginPageHelper extends DriverHelper 
{
	public LocatorReader loginpageLocator;	

	int random= this.getRandomInteger(1, 99999);
	PropertyReader propertyReader = new PropertyReader();
	public LoginPageHelper(WebDriver driver) {
		super(driver);
		loginpageLocator = new LocatorReader("LoginPage.xml");
	}

	
	/**
	 * @description This method is used to perform login to the application
	 * @author 360logica
	 * @param username
	 * @param password
	 * @return none
	 */
	 public void Login (String username, String password) {
		 try {
		 String usernametextbox = loginpageLocator.getLocator("Login.UserName");
		 String passwordtextbox = loginpageLocator.getLocator("Login.Password");
		 String loginbutton = loginpageLocator.getLocator("Login.LoginButton");
		 String verifylogin = loginpageLocator.getLocator("Login.VerifyLogin");
		 String verifycollection = loginpageLocator.getLocator("Login.VerifyCollection");
		 WaitForElementPresent(loginbutton, 30);
		 Boolean Usernamebutton = isElementPresent(usernametextbox);
		 System.out.println("User Name button element is present: "+Usernamebutton);
		 sendKeys(usernametextbox, username);
		 Reporter.log("Enter username '"+username+"' into username text box '"+usernametextbox+"'", true);
		 Boolean Passwordbutton = isElementPresent(passwordtextbox);
		 System.out.println("Password button element is present: "+Passwordbutton);
		 sendKeys(passwordtextbox, password);
		 Reporter.log("Enter password '"+password+"' into password text box '"+passwordtextbox+"'", true);
		 Boolean Loginbutton = isElementPresent(loginbutton);
		 System.out.println("Login button element is present: "+Loginbutton);
		 clickOn(loginbutton);
		 Reporter.log("Click on Log in button:"+loginbutton, true);
		 WaitForElementNotPresent(verifylogin, 80);
		 String verifyLogin = getText(verifylogin);
		 Boolean textavailable = isTextAvailable(verifyLogin);
		 System.out.println("Login text "+verifyLogin+ " is available "+textavailable);
		 WaitForElementNotPresent(verifycollection, 20);
		 Boolean collectionverify = isElementPresent(verifycollection);
		 System.out.println("Collection available: "+collectionverify);
		 String verifyCollection = getText(verifycollection);
		 Boolean textavailable1 = isTextAvailable(verifyCollection);
		 System.out.println("Login text "+verifyCollection+ " is available "+textavailable1);
		} catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
}
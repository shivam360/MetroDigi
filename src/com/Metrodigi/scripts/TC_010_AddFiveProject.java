/*==============================================================================================================================
 File Name    : Demo.java
 ClassName    : Demo
 Summary      : Contains demo test to login.
 ===============================================================================================================================
 History      :   Company            Created By     
                  360logica                         

 ===============================================================================================================================
 Remarks      :   Tests - 
 ===============================================================================================================================*/

package com.Metrodigi.scripts;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Metrodigi.locators.LocatorReader;
import com.Metrodigi.pagehelper.EditProjectsHelper;
import com.Metrodigi.pagehelper.HomePageHelper;
import com.Metrodigi.pagehelper.LoginPageHelper;
import com.Metrodigi.util.DriverTestCase;
import com.Metrodigi.util.DriverHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import com.Metrodigi.locators.LocatorReader;
import com.Metrodigi.util.DriverHelper;
import com.Metrodigi.util.PropertyReader;


public class TC_010_AddFiveProject extends DriverTestCase
{	
	private LoginPageHelper loginpageHelper;
	
	/**
	 * 
	 * @author 360logica
	 * @version 1.0
	 * <br>Date Created:  26/12/2016
	 * <br>Test Case: AddFiveProject
	 * <br>Priority: P1
	 * <br>Status: Active
	 * <br>Automated: Yes
	 * <br>JiraProject: Demo Jira Project
	 * <br>JiraIssue: Demo Jira test case ID
	 * <br>Jira Open Bug: Mention if any
	 * <br>Execution Time: 1 minutes.
	 * <br><b>Steps:</b>
	 * Step 1: Launch web browser (FireFox,IE, Chrome).
	 * Step 2: Login into the application
	 * Step 3: Click on New Project button to add New Project
	 * Step 4: Add Collection Details
	 * Step 5: Click on Add New Project button
	 * Step 6: Add Project Details
	 * Step 7: Upload Document file
	 * Step 8: Save Project
	 * Step 9: Again project is created up to five
	 * @param none
	 * @return None.
	 * @throws Exception 
	 * @exception None.
	 */
	@Test
	public void logintest() throws Exception {	
		loginpageHelper = new LoginPageHelper(getWebDriver());

		//System.out.println("Metro Digi");
		getWebDriver().get(application_url);
		loginpageHelper.Login(username1, password1);
		homepageHelper = new HomePageHelper(getWebDriver());
		editProjectsHelper= new EditProjectsHelper(getWebDriver());
		Thread.sleep(10000);
		
		homepageHelper.AddProject();
		editProjectsHelper.CollectionDetails ();
		//editProjectsHelper.Newproject();
		 
		// for loop for add five projects in one collection
		for(int i=0; i<5; i++){
			//editProjectsHelper.AddProject();
			System.out.println("Under for loop");
			
			editProjectsHelper.Newproject();

			editProjectsHelper.addfiveprojects();
			editProjectsHelper.AddProjectButton();
		}
		editProjectsHelper.savebutton();
  	    //boolean textavailable2 = isTextAvailable(nameofproject);
	    //System.out.println("Created Project is available "+textavailable2);
		Thread.sleep(1000);
		
		 }
	}
	
	

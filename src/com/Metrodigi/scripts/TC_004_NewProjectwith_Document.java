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
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.Metrodigi.pagehelper.EditProjectsHelper;
import com.Metrodigi.pagehelper.HomePageHelper;
import com.Metrodigi.pagehelper.LoginPageHelper;
import com.Metrodigi.util.DriverTestCase;


public class TC_004_NewProjectwith_Document extends DriverTestCase
{	
	private LoginPageHelper loginpageHelper;
	
	/**
	 * 
	 * @author 360logica
	 * @version 1.0
	 * <br>Date Created:  04/12/2016
	 * <br>Test Case: Login
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
	 * Step 9: Verify the created Collection and Project
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
		Thread.sleep(10000);
		homepageHelper.AddProject();			
		editProjectsHelper = new EditProjectsHelper(getWebDriver());
		String collectionName = editProjectsHelper.CollectionDetails();
		String projectName = editProjectsHelper.Newproject();
		Reporter.log("Added " +projectName +" Project in collection " +collectionName, true );
		editProjectsHelper.uploaddocument();
		boolean textavailable1 = isTextAvailable(collectionName);
  	    System.out.println("Created collection is available "+textavailable1);
  	    boolean textavailable2 = isTextAvailable(projectName);
	    System.out.println("Created Project is available "+textavailable2);		
	}
}

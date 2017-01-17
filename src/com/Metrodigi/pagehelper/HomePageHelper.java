/*==============================================================================================================================
 File Name    : HomePageHelper.java
 ClassName    : HomePageHelper
 Summary      : Contains methods of Home Page.
 ===============================================================================================================================
 History      :   Company            Created By     
                  360logica                         

 ===============================================================================================================================
 Remarks      :   Tests - 
 ===============================================================================================================================*/

package com.Metrodigi.pagehelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import com.Metrodigi.locators.LocatorReader;
import com.Metrodigi.util.DriverHelper;
import com.Metrodigi.util.PropertyReader;

public class HomePageHelper extends DriverHelper 
{
	public LocatorReader homepageLocator;	

	int random= this.getRandomInteger(1, 99999);
	PropertyReader propertyReader = new PropertyReader();
	public HomePageHelper(WebDriver driver)
	{
		super(driver);
		homepageLocator = new LocatorReader("HomePage.xml");
	}
	/**
	 * @description This method is used to perform Add New Project to the application
	 * @return none
	 */
	public void AddProject () {
		 try {
		 String Newprojectbutton = homepageLocator.getLocator("Homepage.NewProjectButton");
		 WaitForElementPresent(Newprojectbutton, 30);
		 Boolean NewProjectbutton = isElementPresent(Newprojectbutton);
		 System.out.println("New Project button element is present: "+NewProjectbutton);
		 clickOn(Newprojectbutton);
		 Reporter.log("Click on New Project in button:"+Newprojectbutton, true);
		 WaitForElementNotPresent(Newprojectbutton, 80);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
	/**
	 * @description This method is used to Delete Project from Collection
	 * @return none
	 */
    public void DeleteProject(){
	      try{
	          String Deleteproject = homepageLocator.getLocator("Homepage.Deleteprojectbutton");
	          String ConfirmDelete = homepageLocator.getLocator("Homepage.Confirmdelete");
	          String DeleteprojectName = homepageLocator.getLocator("Homepage.Deletedprojectname"); 
	          WaitForElementPresent(Deleteproject, 10);
	          String deleteprojectnname = getText(DeleteprojectName);
		      Reporter.log("Deleted Collection name is "+deleteprojectnname, true); 
	          clickOn(Deleteproject);
	          Reporter.log("Click on Delete Project button:"+Deleteproject, true);
	          WaitForElementPresent(ConfirmDelete, 10);
	          clickOn(ConfirmDelete);
	          Reporter.log("Click on Confirm Delete button:"+ConfirmDelete, true);
	          boolean textavailable1 = isTextAvailable(deleteprojectnname);
	    	  System.out.println("Deleted Project is available "+textavailable1);
	          Thread.sleep(10000);
	          }catch (Exception e) {
	            e.printStackTrace();
              }
	      }
	/**
	 * @description This method is used to Delete Collection from application
	 * @return none
	 */
    public void DeleteCollection(){
    	try{
    	    String Deletecollection = homepageLocator.getLocator("Homepage.Deletecollection");
    	    String Confirmdeletecollection = homepageLocator.getLocator("Homepage.ConfirmDeletecollection");
    	    String DeletecollectionName = homepageLocator.getLocator("Homepage.Deletedcollectionname");
    	    WaitForElementPresent(Deletecollection, 10);
    	    String deletecollectionname = getText(DeletecollectionName);
	        Reporter.log("Deleted Collection name is "+deletecollectionname, true); 
	        clickOn(Deletecollection);
	        Reporter.log("Click on Delete Collection Project button:"+Deletecollection, true);	        
	        WaitForElementPresent(Confirmdeletecollection, 10);
	        clickOn(Confirmdeletecollection);
	        Reporter.log("Click on Confirm Delete Collection button:"+Confirmdeletecollection, true);	        
	        boolean textavailable1 = isTextAvailable(deletecollectionname);
    		System.out.println("Deleted Collection is available "+textavailable1);
	        Thread.sleep(10000);
    	}catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
	 * @description This method is used to Open Project
	 * @return none
	 */
	public void Openproject()
	{
		try{
			String openproject = homepageLocator.getLocator("Homepage.ClickonProject");
			String verifyopenProject = homepageLocator.getLocator("Homepage.Verifyprojectopen");
			String verifyopenProject2 = homepageLocator.getLocator("Homepage.Verifyprojectopen2");
			String verifyopenProject3 = homepageLocator.getLocator("Homepage.Verifyprojectopen3");
			String verifyopenProject4 = homepageLocator.getLocator("Homepage.Verifyprojectopen4");
			WaitForElementNotPresent(openproject, 10);
			String openprojectname = getText(openproject);
			Reporter.log("Open Project name is: "+openprojectname, true);
			boolean textavailable1 = isTextAvailable(openprojectname);
      	    System.out.println("Project is available: "+textavailable1);
			clickOn(openproject);
			Thread.sleep(20000);
			//Reporter.log("Click on selected Project name:", true);
			WaitForElementNotPresent(verifyopenProject, 30);
			Boolean verifyelementpresent = isElementPresent(verifyopenProject);
			System.out.println("Content Editor element is present: "+verifyelementpresent);	
			Boolean verifyelementpresent2 = isElementPresent(verifyopenProject2);
			System.out.println("Edit Content element is present: "+verifyelementpresent2);
			Boolean verifyelementpresent3 = isElementPresent(verifyopenProject3);
			System.out.println("Section element is present: "+verifyelementpresent3);
			Boolean verifyelementpresent4 = isElementPresent(verifyopenProject4);
			System.out.println("Navigation element is present: "+verifyelementpresent4);
		}catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    /**
	 * @description This method is used to Open Existing Collection from application
	 * @return none
	 */
    
    public void OpenExistingCollection(){
    	try{
    		String Opencollection = homepageLocator.getLocator("Homepage.ClickonCollection");    		
    		String opencollectionname = getText(Opencollection);
    		Reporter.log("Open collection name is: "+opencollectionname, true);
    		boolean textavailable1 = isTextAvailable(opencollectionname);
      	    System.out.println("Collection is available: "+textavailable1);
    		clickOn(Opencollection);
    		Thread.sleep(10000);
    		}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public void Manageprojectteam(){
    	try{
    		String ManageProjectteam = homepageLocator.getLocator("Homepage.ManageProjectTeam");
    		String savebutton = homepageLocator.getLocator("Homepage.SaveButton");
    		String chooseuser1 = homepageLocator.getLocator("Homepage.ChooseUser1");
    		String chooseuser2 = homepageLocator.getLocator("Homepage.ChooseUser2");
    		String addtoprojectbutton = homepageLocator.getLocator("Homepage.AddtoProjectTeam");
    		clickOn(ManageProjectteam);
    		Reporter.log("Click on Manage Project team:", true);
    		WaitForElementPresent(savebutton, 10);
     		JavascriptExecutor js = (JavascriptExecutor) driver;
     		js.executeScript("document.querySelector('#team-add-user>div.dropdown-toggle').click()");
    		clickOn(chooseuser1);    		
    		//get text of selected user
    		String user1 = getText(chooseuser1);
    		Reporter.log("Selected first user is "+user1, true);
    		clickOn(chooseuser2);
    		//get text of selected user
    		String user2 = getText(chooseuser2);
    		Reporter.log("Selected second user is "+user2, true);    		
    		clickOn(addtoprojectbutton);
    		Reporter.log("Click on add project botton: "+addtoprojectbutton, true);
    		//Verify the selected options
    		boolean textavailable1 = isTextAvailable(user1);
    		System.out.println(textavailable1);
    		boolean textavailable2 = isTextAvailable(user2);
    		System.out.println(textavailable2);    		
    		Thread.sleep(5000);
    		clickOn(savebutton); 
    		Reporter.log("Click on Save botton:" +savebutton, true);
    		Thread.sleep(7000);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}

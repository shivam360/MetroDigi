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



public class EditProjectsHelper extends DriverHelper 
{
	public LocatorReader editprojectsLocator;	

	int random= this.getRandomInteger(1, 99999);
	PropertyReader propertyReader = new PropertyReader();
	public EditProjectsHelper(WebDriver driver)
	{
		super(driver);
		editprojectsLocator = new LocatorReader("EditProjects.xml");
	}
	/**
	 * @description This method is used to Edit Collection Details to the application
	 * @return none
	 */
	public String NewCollection() {
		String collectionname = null; 
		try {
		 String CollectionName = editprojectsLocator.getLocator("EditProjects.Collection.CollectionName");
		 collectionname = propertyReader.readApplicationFile("Collectionname1")+String.valueOf(getRandomInteger(1,999));
		 String AddProjectButton = editprojectsLocator.getLocator("EditProjects.Collection.AddProjectButton");
		 sendKeys(CollectionName, collectionname);
		 WaitForElementNotPresent(AddProjectButton, 20);
		 Reporter.log("New Collection Name is :"+collectionname, true);
		 String savebutton = editprojectsLocator.getLocator("EditProjects.NewProject.Savebutton");
		 clickOn(savebutton);
		 }catch (Exception e) {
			 e.printStackTrace();
		 } return collectionname;
	}
	/**
	 * @description This method is used to Add Collection details
	 * @return none
	 */
	public String CollectionDetails () {
		String collectionname = null; 
		try {
		 String CollectionName = editprojectsLocator.getLocator("EditProjects.Collection.CollectionName");
		 collectionname = propertyReader.readApplicationFile("Collectionname1")+String.valueOf(getRandomInteger(1,999));
		 String AddProjectButton = editprojectsLocator.getLocator("EditProjects.Collection.AddProjectButton");
		 sendKeys(CollectionName, collectionname);
		 WaitForElementNotPresent(AddProjectButton, 10);
		 Reporter.log("New Collection Name is :"+collectionname, true);
		 clickOn(AddProjectButton);
		 Reporter.log("Click on Add Project button:"+AddProjectButton, true);
		 WaitForElementNotPresent(AddProjectButton, 10);
		 } catch (Exception e) {
			 e.printStackTrace();
		 } return collectionname;
	}
	/**
	 * @description This method is used to click on Add Project button
	 * @return none
	 */
	public String AddProject() {
		String collectionname = null; 
		try {
		 String AddProjectButton = editprojectsLocator.getLocator("EditProjects.Collection.AddProjectButton");
		 clickOn(AddProjectButton);
		 Reporter.log("Click on Add Project button of existing Collection:"+AddProjectButton, true);
		 Boolean elementpresent = isElementPresent(AddProjectButton);	
		 System.out.println("Element is present: "+elementpresent);
		 } catch (Exception e) {
			 e.printStackTrace();
		 } return collectionname;
	}
	/**
	 * @description This method is used to Add New Project with Uploaded file to the application
	 * @return none
	 */
		 public String Newproject()
		 {
			 String projectName = null;
			 try{
				 //Click on Reflowable button
				 
				 String reflowable = editprojectsLocator.getLocator("EditProjects.NewProject.Reflowable");
				 Boolean reflowableelementpresent = isElementPresent(reflowable);	
				 System.out.println("Reflowable Element is present: "+reflowableelementpresent);
				 clickOn(reflowable);
				 Reporter.log("Click on Reflowable radio button:"+reflowable, true);
				 
				 /*
				  Click on Fixed layout radio button
				 String fixedlayout = editprojectsLocator.getLocator("EditProjects.NewProject.Fixedlayout");
				 Boolean fixedlayoutelementpresent = isElementPresent(fixedlayout);	
				 System.out.println("Fixed layout Element is present: "+fixedlayoutelementpresent);
				 clickOn(fixedlayout);
				 Reporter.log("Click on fixedlayout radio button:"+fixedlayout, true);*/
				 
				 // Click on Do you have files to convert? Yes or No radio button
				 
				 String yes = editprojectsLocator.getLocator("EditProjects.NewProject.Yes");
				 Boolean Yeselementpresent = isElementPresent(yes);	
				 System.out.println("Yes Element is present: "+Yeselementpresent);
				 clickOn(yes);
				 Reporter.log("Click on Yes radio button:"+yes, true);
				 
				 /*String no = editprojectsLocator.getLocator("EditProjects.NewProject.No");
				 Boolean Noelementpresent = isElementPresent(no);	
				 System.out.println("No Element is present: "+Noelementpresent);
				 clickOn(no);	
				 Reporter.log("Click on No radio button:"+no, true);*/
				 				 
				 //Add Project Details
				 
				 String Projectname = editprojectsLocator.getLocator("EditProjects.NewProject.ProjectName");								 
				 projectName = propertyReader.readApplicationFile("Projectname1")+String.valueOf(getRandomInteger(1,999));
				 Boolean Projectnameelementpresent = isElementPresent(Projectname);
				 System.out.println("Project Name Element is present: "+Projectnameelementpresent);
				 sendKeys(Projectname, projectName);
				 String nameofproject = projectName;
				 Reporter.log("New Project Name is :"+projectName, true);
				 System.out.println(" Project name is" + projectName);
			 }catch (Exception e) {
				 e.printStackTrace();
			 }return projectName;
		 }
		 /**
			 * @description This method is used to upload document file
			 * @return none
			 */
			 public void uploaddocument()
			 {  try{
				 String locatefiles = editprojectsLocator.getLocator("EditProjects.NewProject.LocateFilesButton");
			     String savebutton = editprojectsLocator.getLocator("EditProjects.NewProject.Savebutton");	 
			 // Upload Files
			     Boolean Locateelementpresent = isElementPresent(locatefiles);
				 System.out.println("Locate button Element is present: "+Locateelementpresent);
				 clickOn(locatefiles);
				 uploadDocxfile();
				 //waitForElementLoad(50);
				 WaitForElementNotPresent(savebutton, 50);
				 
				 //Click on Save Button
				 Boolean Savebuttonelementpresent = isElementPresent(savebutton);
				 System.out.println("Save button Element is present: "+Savebuttonelementpresent);
				 clickOn(savebutton);
				 Reporter.log("Click on Save button is :"+savebutton, true);
				 Thread.sleep(90000);
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
			 /**
				 * @description This method is used to upload epub file
				 * @return none
				 */
				 public void uploadepub()
				 {  try{
					 String locatefiles = editprojectsLocator.getLocator("EditProjects.NewProject.LocateFilesButton");
				     String savebutton = editprojectsLocator.getLocator("EditProjects.NewProject.Savebutton");	 
				 // Upload Files
					 clickOn(locatefiles);
					 uploadepubfile();
					 //waitForElementLoad(20);
					 WaitForElementNotPresent(savebutton, 20);
					 
					 //Click on Save Button
					 clickOn(savebutton);
					 Reporter.log("Click on Save button is :"+savebutton, true);
					 Thread.sleep(100000);
				 }catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
			 /**
				 * @description This method is used to upload media files
				 * @return none
				 */
			 public void uploadmedia()
			 {  try{
				 String locatefiles = editprojectsLocator.getLocator("EditProjects.NewProject.LocateFilesButton");
			     String savebutton = editprojectsLocator.getLocator("EditProjects.NewProject.Savebutton");	 
			     // Upload Files
				 clickOn(locatefiles);
				 uploadAudiofile();
				 clickOn(locatefiles);
				 uploadimagefile();
				 clickOn(locatefiles);
				 uploadVideofile();
				 //waitForElementLoad(50);
				 WaitForElementNotPresent(savebutton, 50);
				 
				 //Click on Save Button
				 clickOn(savebutton);
				 Reporter.log("Click on Save button is :"+savebutton, true);
				 Thread.sleep(90000);
				 
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 
		 }
			 public void savebutton(){
				 try{
					String savebutton = editprojectsLocator.getLocator("EditProjects.NewProject.Savebutton");
					 //Click on Save Button
					 Boolean Savebuttonelementpresent = isElementPresent(savebutton);
					 System.out.println("Save button Element is present: "+Savebuttonelementpresent);
					 clickOn(savebutton);
					 Reporter.log("Click on Save button is :"+savebutton, true);
			 	 }
			 catch (Exception e) {
				 e.printStackTrace();
			 }
			 
		 }
			 
			 public void addfiveprojects(){
				 try{
					 String locatefiles = editprojectsLocator.getLocator("EditProjects.NewProject.LocateFilesButton");
				     //String savebutton = editprojectsLocator.getLocator("EditProjects.NewProject.Savebutton");	 
				 // Upload Files
				     Boolean Locateelementpresent = isElementPresent(locatefiles);
					 //System.out.println("Locate button Element is present: "+Locateelementpresent);
					 clickOn(locatefiles);
					 uploadDocxfile();
					 //waitForElementLoad(50);
					 //WaitForElementNotPresent(savebutton, 50);
			 	 }
			 catch (Exception e) {
				 e.printStackTrace();
			 }
			 
		 }
			 public void AddProjectButton(){
			 try{	 
				 String AddProjectButton = editprojectsLocator.getLocator("EditProjects.Collection.AddProjectButton");
				 WaitForElementNotPresent(AddProjectButton, 10);
				 clickOn(AddProjectButton);
				 Reporter.log("Click on Add Project button:"+AddProjectButton, true);
				 WaitForElementNotPresent(AddProjectButton, 10);
			 }
			 
			 catch (Exception e) {
				 e.printStackTrace();
			 }
			 }
			 
}




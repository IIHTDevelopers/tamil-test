

package testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.FaceBookPage;
import pages.StartupPage;
import testBase.AppTestBase;
import testdata.LocatorsFactory;

public class Facebook_L1_AutomationTest extends AppTestBase {
	
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath+"expected_data.json";
	StartupPage startupPage;
	FaceBookPage FaceBookPageInstance;
	LocatorsFactory LocatorsFactoryInstance = new LocatorsFactory(driver);
	
	
	@Parameters({"browser", "environment"})
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readJson(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);
		
		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl, configData.get("url")+" might be Server down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}
	
	
	@Test(priority = 1, groups = {"sanity"}, description="Navigate to the URL and Validate the fa LogIn Page Title")
	public void validateFacebookTitleofLoginPage() throws Exception {
		logger.info("execution started for the methiod validateFacebookTitleofLoginPage()");
		FaceBookPageInstance = new FaceBookPage(driver);	
		Map<String, String> expectedData = new FileOperations().readJson(expectedDataFilePath, "LoginPage_Title");
		Assert.assertEquals(FaceBookPageInstance.validateFacebookTitleOfLoginPage(), expectedData.get("loginpageTitle"));
		Assert.assertTrue(LocatorsFactoryInstance.getLoginButton(driver).isDisplayed(), "login button is not present in the current page, Please check manually");
		logger.info("execution completed for the methiod validateFacebookTitleofLoginPage()");
	}
	
	@Test(priority = 2, groups = {"sanity"}, description="verify Presence Of All Fields in The Login Page")
	public void verifyPresenceOfAllFieldsinTheLoginPage() throws Exception {
		logger.info("execution started for the methiod verifyPresenceOfAllFieldsinTheLoginPage()");
		FaceBookPageInstance = new FaceBookPage(driver);
		Assert.assertTrue(FaceBookPageInstance.verifyPresenceOfALlFields(), "Any of the elememt is not present, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.getLoginButton(driver).isDisplayed(), "login button is not present in the current page, Please check manually");
		logger.info("execution completed for the methiod verifyPresenceOfAllFieldsinTheLoginPage()");
	}
	
	@Test(priority = 3, groups = {"sanity"}, description="validate the error message, when user does not provide any credentials")
	public void validateErrorMessageWithoutProvideAnyCredentials() throws Exception {
		logger.info("execution started for the methiod validateErrorMessageWithoutProvideAnyCredentials()");
		FaceBookPageInstance = new FaceBookPage(driver);
		Map<String, String> expectedData = new FileOperations().readJson(expectedDataFilePath, "errorMessage");
		Assert.assertEquals(FaceBookPageInstance.validateErrorMessage(), expectedData.get("errorMessageText"), "error message is not matching, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.emailTextField(driver).isDisplayed(), "Email Text Field is not present in the current page, Please check manually");
		logger.info("execution completed for the method validateErrorMessageWithoutProvideAnyCredentials()");
	}
	
	@Test(priority = 4, groups = {"sanity"}, description="Enter Email or Phone No in Email or Phone No Text field and click on Login and validate Error Message")
	public void validateErrorMessageWithoutProvidePassword() throws Exception {
		logger.info("execution started for the methiod validateErrorMessageWithoutProvidePassword()");
		FaceBookPageInstance = new FaceBookPage(driver);
		
		String expectedDataFilePath = testDataFilePath+"expected_data.json";
		Map<String, String> expectedData1 = new FileOperations().readJson(expectedDataFilePath, "EmailOrPhoneNumber");
		FaceBookPageInstance.enterEmailIdOrPhoneNumber(expectedData1);
		Map<String, String> expectedData2 = new FileOperations().readJson(expectedDataFilePath, "errorMessage1");
		Assert.assertEquals(FaceBookPageInstance.validateErrorMessageWithoutProvidePassword(expectedData2), expectedData2.get("errorMessageText"), "error message is not matching, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.getPasswordTextfield(driver).isDisplayed(), "Password Text Field is not present in the current page, Please check manually");
		logger.info("execution completed for the methiod validateErrorMessageWithoutProvidePassword()");
	}
	
	@Test(priority = 5, groups = {"sanity"}, description="Enter Password in password Text field and click on Login and validate Error Message")
	public void validateErrorMessageWithoutProvideEmailOrPhoneNumber() throws Exception {
		logger.info("execution started for the methiod validateErrorMessageWithoutProvideEmaild()");
		FaceBookPageInstance = new FaceBookPage(driver);
		
		String expectedDataFilePath = testDataFilePath+"expected_data.json";
		Map<String, String> expectedData1 = new FileOperations().readJson(expectedDataFilePath, "password");
		FaceBookPageInstance.enterPassword(expectedData1);
		Map<String, String> expectedData = new FileOperations().readJson(expectedDataFilePath, "errorMessage");
		Assert.assertEquals(FaceBookPageInstance.validateErrorMessage(), expectedData.get("errorMessageText"), "error message is not matching, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.emailTextField(driver).isDisplayed(), "Email Text Field is not present in the current page, Please check manually");
		logger.info("execution completed for the methiod validateErrorMessageWithoutProvideEmail()");
	}
	
	@Test(priority = 6, groups = {"sanity"}, description="Go Back To LogIn Page And Validate CreateNewAccount button Is Present or not")
	public void goBackToLogInPageAndValidateCreateNewAccountButtonIsPresentOrNot() throws Exception {
		logger.info("execution started for the methiod goBackToLogInPageAndValidateCreateNewAccountButtonIsPresentOrNot()");
		FaceBookPageInstance = new FaceBookPage(driver);
		Assert.assertTrue(FaceBookPageInstance.goBackToLogInPageAndValidateCreateNewAccountButtonIsPresentOrNot(), "Create New Account Button is not present, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.getCreateNewAccount(driver).isDisplayed(), "Email Text Field is not present in the current page, Please check manually");
		logger.info("execution completed for the methiod goBackToLogInPageAndValidateCreateNewAccountButtonIsPresentOrNot()");
	}
	
	@Test(priority = 7, groups = {"sanity"}, description="Click on Create Account Button and Verify nevigate to the signUp page.")
	public void validate_navigate_to_sign_up_page() throws Exception {
		logger.info("execution started for the methiod validateNavigateToTheSignUpPage()");
		FaceBookPageInstance = new FaceBookPage(driver);
		Map<String, String> expectedData = new FileOperations().readJson(expectedDataFilePath, "Sign_up_page");
		Assert.assertEquals(FaceBookPageInstance.validateNavigateToTheSignUpPage(), expectedData.get("Sign_up_page_title"), "page title is not matching, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.signUpButton(driver).isDisplayed(), "SignUp Button is not present in the current page, Please check manually");	
		logger.info("execution completed for the methiod validateNavigateToTheSignUpPage()");
	}
	
	
	@Test(priority = 8, groups = {"sanity"}, description="verify first name,surname,Mobile Number email address,New password field are present in the signup page.")
	public void VerifyAllFieldsArePresentInTheSignUpPage() throws Exception {
		logger.info("execution started for the method VerifyAllFieldsArePresentInTheSignUpPage()");	
		FaceBookPageInstance = new FaceBookPage(driver);
		FaceBookPageInstance = new FaceBookPage(driver);
		Assert.assertTrue(FaceBookPageInstance.verifyPresenceOfAllFieldsPresentInTheSignUpPage(), "Any of the elememt is not present, please check manually");
		Assert.assertTrue(LocatorsFactoryInstance.signUpButton(driver).isDisplayed(), "signUp button is not present in the current page, Please check manually");
		logger.info("execution completed for the methiod VerifyAllFieldsArePresentInTheSignUpPage()");
	}
	@Test(priority = 9, groups = {"sanity"}, description="Enter data in the first name field")
	public void Enter_data_in_the_first_name_field_() throws Exception {
		logger.info("execution started for the method enterDataInFirstNameField()");	
		FaceBookPageInstance = new FaceBookPage(driver);
		String expectedDataFilePath = testDataFilePath+"expected_data.json";
		Map<String, String> expectedData1 = new FileOperations().readJson(expectedDataFilePath, "logInCredential");
		FaceBookPageInstance.enterDataInFirstNameField(expectedData1);
		Assert.assertTrue(LocatorsFactoryInstance.firstNameTextField(driver).isDisplayed(), "firstNameTextField is not present in the signU page, Please check manually");	
		logger.info("execution completed for the methiod enterDataInFirstNameField()");
	}
	@Test(priority = 10, groups = {"sanity"}, description="Enter data (Read the data from Json File)in the surname,Mobile Number email address,New password field.")
	public void Enter_data_in_some_field_in_the_SignUp_page() throws Exception {
		logger.info("execution started for the method enterDataInSignUpPage()");	
		FaceBookPageInstance = new FaceBookPage(driver);
		String expectedDataFilePath = testDataFilePath+"expected_data.json";
		Map<String, String> expectedData1 = new FileOperations().readJson(expectedDataFilePath, "logInCredential");
		FaceBookPageInstance.enterDataInSignUpPage(expectedData1);
		Assert.assertTrue(LocatorsFactoryInstance.newpasswordTextField(driver).isDisplayed(), "firstNameTextField is not present in the signU page, Please check manually");	
		logger.info("execution completed for the methiod enterDataInSignUpPage()");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}
	
	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));
	}
}

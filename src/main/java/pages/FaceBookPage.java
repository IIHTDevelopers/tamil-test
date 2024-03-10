package pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FaceBookPage extends StartupPage {
	//please write all the locators for facebook page only 

	By loginButton = By.xpath("//button[@name='login']"); 
	By emailAddsPhoneNumberTextbox = By.xpath("//input[@placeholder='Email address or phone number']"); 
	By passwordTextbox = By.xpath("//input[@placeholder='Password']"); 
	By profileIconImage = By.xpath("(//div//div//span)[14]"); 
	By logoutButton = By.xpath("//span[contains(text (), 'Log out')]"); 
	By forgettenPasswordLink = By.xpath("//a[.='Forgotten password?']"); 
	By createNewAccountLink = By.xpath("//a[contains(text() , 'Create new account')]"); 
	By emailTextField = By.id("email"); 
	By errorMessageWithoutCredential = By.xpath("//div[@id='email_container']//div[2]"); 
	By errorMessageWithoutCredentialForgetPassword = By.xpath("//div[@id='email_container']//div[2]/a"); 
	By withoutPasswordErrorMessage = By.xpath("//div[contains(@class, 'clearfix') and contains(@class, 'mg')]//div[2]");
	
	By createNewAccount = By.xpath("//a[contains(text(),'Create new account')]"); 
	By signUpButton = By.xpath("//button[@name=\"websubmit\"]"); 
	By nameFieldErorMessage = By.xpath("//div[@id='js_134']"); 
	By firstNameTextField = By.xpath("//input[@name=\"firstname\"]"); 
	By surNameTextField = By.xpath("//input[@name=\"lastname\"]"); 
	By mobileNumberAndPassword = By.xpath("//input[@name=\"reg_email__\"]"); 
	By newPassword = By.xpath("(//input[@type=\"password\"])[2]"); 
	 
	 
	 

	public FaceBookPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this); // Initialize Page Factory
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * about this method validateFacebookTitleOfLoginPage() 
	 * @param : null
	 * @description : it is getting the page title and return the same,
	 * @return : title of string type
	 * @author : Yaksha
	 */
	public String validateFacebookTitleOfLoginPage() throws Exception {
		
		try {
			
			String currentPageTitle	=  driver.getTitle();
			System.out.println("Title of the Login Page : " + currentPageTitle );
			return currentPageTitle;
			}catch(Exception e) {
				throw e;
			}
		
	}

	/**
	 * about this method verifyPresenceOfALlFields() 
	 * @param : null
	 * @description : it is verify all fields is present in current page or not 
	 * @return : true
	 * @author : Yaksha
	 */
	public Boolean verifyPresenceOfALlFields() throws Exception {
		try {
			
			if(driver.findElement(createNewAccountLink).isDisplayed() &&
				driver.findElement(forgettenPasswordLink).isDisplayed() &&
				driver.findElement(passwordTextbox).isDisplayed()
				//&& driver.findElement(emailAddsPhoneNumberTextbox).isDisplayed()
				) {
			return true;
			}
			}catch(Exception e) {
				throw e;
			}
		return false ;
	}
	

	/**
	 * about this method validateErrorMessage() 
	 * @param : null
	 * @description : it is getting the error message if login button clicked without passing email and password
	 * @return : errorMessage as string type
	 * @author : Yaksha
	 */
	public String validateErrorMessage() throws Exception {
		
		driver.findElement(loginButton).click();
		new WebDriverWait(driver, Duration.ofSeconds(60))
		.until(ExpectedConditions.presenceOfElementLocated(emailTextField));
		String errormsgwithoutcre =driver.findElement(errorMessageWithoutCredential).getText();
		System.out.println(errormsgwithoutcre);
		return errormsgwithoutcre;
	}

	/**
	 * about this method enterEmailIdOrPhoneNumber() 
	 * @param : Map<String, String> expectedData
	 * @description : it is sending the email or phone no as per json expected data ,
	 * @return : null
	 * @author : Yaksha
	 */
	public void enterEmailIdOrPhoneNumber(Map<String, String> expectedData) throws Exception {
	
		driver.findElement(emailTextField).sendKeys(expectedData.get("emailAddsPhoneNumber"));
		return;
	}
	
	/**
	 * about this method validateErrorMessageWithoutProvidePassword() 
	 * @param : null
	 * @description : it is getting the error message and validate the error message ,
	 * @return : errorMessage as string type
	 * @author : Yaksha
	 */
	public String validateErrorMessageWithoutProvidePassword(Map<String, String> expectedData) throws Exception {
		driver.findElement(loginButton).click();
		new WebDriverWait(driver, Duration.ofSeconds(60))
		.until(ExpectedConditions.presenceOfElementLocated(emailTextField));
		String errormsgwithoutcre =driver.findElement(withoutPasswordErrorMessage).getText();
		System.out.println(errormsgwithoutcre);
		return errormsgwithoutcre;
	}
	
	/**
	 * about this method enterPassword() 
	 * @param : null
	 * @description : it is sending the only the password without providing email and click login ,
	 * @return : null
	 * @author : Yaksha
	 */
	public void enterPassword(Map<String, String> expectedData) throws Exception {
	driver.findElement(emailAddsPhoneNumberTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys(expectedData.get("password"));
			
		return ;
	}
	
	/**
	 * about this method goBackToLogInPageAndValidateCreateNewAccountButtonIsPresentOrNot() 
	 * @param : null
	 * @description : here checking whether the element is present or not 
	 * @return : true
	 * @author : Yaksha
	 */
	public Boolean goBackToLogInPageAndValidateCreateNewAccountButtonIsPresentOrNot() throws Exception {
		try {
			driver.navigate().back();
			driver.get("https://www.facebook.com/");
			Thread.sleep(5000);
			new WebDriverWait(driver, Duration.ofSeconds(60))
		.until(ExpectedConditions.presenceOfElementLocated(createNewAccount));
		
			if(driver.findElement(createNewAccount).isDisplayed()) {
			return true;
			}	
		}catch(Exception e) {
			throw e;	
		}
		return false;
	}
	
	/**
	 * about this method validateNavigateToTheSignUpPage() 
	 * @param : null
	 * @description : click on the createnew account and validate the page title ,
	 * @return : title of string type
	 * @author : Yaksha
	 */
	public String validateNavigateToTheSignUpPage() throws Exception {
	driver.findElement(createNewAccount).click();
		new WebDriverWait(driver, Duration.ofSeconds(60))
		.until(ExpectedConditions.presenceOfElementLocated(createNewAccount));
			
			String signUpPageTitle	=  driver.getTitle();
			System.out.println("Title of the Login Page : " + signUpPageTitle );
			return signUpPageTitle;
	}


	
	/**
	 * about this method verifyPresenceOfAllFieldsPresentInTheSignUpPage() 
	 * @param : null
	 * @description : verify all fields are present in the signup page ,
	 * @return : boolean
	 * @author : Yaksha
	 */
	public Boolean verifyPresenceOfAllFieldsPresentInTheSignUpPage() throws Exception {
	try {
			if(driver.findElement(firstNameTextField).isDisplayed() &&
			driver.findElement(surNameTextField).isDisplayed()&&
			driver.findElement(mobileNumberAndPassword).isDisplayed()&&
			driver.findElement(newPassword).isDisplayed()) {
			return true;
			}	
		}catch(Exception e) {
			throw e;	
		}
		return false;
}
	
/**
	 * about this method enterDataInFirstNameField() 
	 * @param : Map<String, String>
	 * @description : Enter data in the first name text field ,
	 * @return : String
	 * @author : Yaksha
	 */
	public  String enterDataInFirstNameField( Map<String, String> expectedData) throws Exception {
		try {
		driver.findElement(firstNameTextField).sendKeys(expectedData.get("FirstName"));
			
			String firstNameTextFieldString=driver.findElement(firstNameTextField).getText();
			System.out.println(firstNameTextFieldString);
			return firstNameTextFieldString;
		}catch(Exception e) {
			throw e;
		}
		
	}
	/**
	 * about this method enterDataInSignUpPage() 
	 * @param : Map<String, String>
	 * @description : enter data in the  SignUpPage page,
	 * @return : null
	 * @author : Yaksha
	 *
	 */
		public  void enterDataInSignUpPage( Map<String, String> expectedData) throws Exception {
			try {
			
			driver.findElement(surNameTextField).sendKeys(expectedData.get("surname"));
			driver.findElement(mobileNumberAndPassword).sendKeys(expectedData.get("mobileNumberandpassword"));
			driver.findElement(newPassword).sendKeys(expectedData.get("newPassword"));
			
				

			}catch(Exception e) {
				throw e;
			}
		}
	

}

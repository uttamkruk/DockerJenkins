package CICD.DockerJenkins;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utility.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;

//import listeners.ListenerV3;


//@Listeners(listeners.Listener.class)

public class TestOne {
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest(alwaysRun = true)	
	public void beforeTest(String browser) throws MalformedURLException {	
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
		//ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\extent-reports\\Reports_"+timeStamp+".html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\extent-reports\\Reports.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Extent Report V3.X");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Test Report");
        report.setSystemInfo("Host Name", "Windows 10");
		report.setSystemInfo("Environment", "Verification01");
		report.setSystemInfo("Build","10.0.120.46");
		report.setSystemInfo("User Name","Uttam Kumar");
		
        
		if(browser.equalsIgnoreCase("firefox")) {
			
			  // Firefox Browser
			  //Run without exe
			  //WebDriverManager.firefoxdriver().setup();
			  System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
			  // if above property is not working or not opening the application in browser then try below property //
			  //System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
			  driver = new FirefoxDriver();
			  driver.manage().window().maximize();
			
			 /* // Headless mode
			 System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\driver\\geckodriver.exe"); 
		     FirefoxBinary firefoxBinary = new FirefoxBinary();
		     firefoxBinary.addCommandLineOptions("--headless");		     
		     FirefoxOptions firefoxOptions = new FirefoxOptions();
		     firefoxOptions.setBinary(firefoxBinary);
		     driver = new FirefoxDriver(firefoxOptions);*/
			 
		}else if(browser.equalsIgnoreCase("ie")) {
			
			 // IE Browser
			 //Run without exe
			 //WebDriverManager.iedriver().setup();
			 System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\driver\\IEDriverServer.exe"); 
			 driver = new InternetExplorerDriver();
			 driver.manage().window().maximize();
			 
		}else if(browser.equalsIgnoreCase("chrome")) {
			
			// Chrome Browser
			//Run without exe
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			/*// for headless browser
			options.addArguments("window-size=1400,800","headless");*/
			driver = new ChromeDriver(options);
			System.out.println("Chrome starting...");
			
			/*//Selenium Grid
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			DesiredCapabilities cap=DesiredCapabilities.chrome();			 
			// Set the platform where we want to run our test- we can use MAC and Linux and other platforms as well
			cap.setPlatform(Platform.WINDOWS);			 
			// Here you can use hub address, hub will take the responsibility to execute the test on respective node
			URL url=new URL("http://172.21.192.1:4444/wd/hub");			 
		    driver=new RemoteWebDriver(url, cap);*/
			
		}
}

	@Test(priority = 1, enabled = true)
	public void testEasy() throws InterruptedException, IOException {
		
		logger = report.createTest("Verify testEasy Title name").assignCategory("Functional").assignAuthor("UK");
		logger.log(Status.INFO, "Browser Started ...");

		driver.get("https://www.google.com/");
		logger.log(Status.INFO, "Application is up and running ...");
		
		// Capture the Particular WebElement Image
		WebElement element = driver.findElement(By.cssSelector("#hplogo"));
		CaptureScreenshot.getScreenshot(driver, element);
		System.out.println("WebElement is captured successfully..");
		logger.log(Status.PASS, "WebElement is captured successfully...");

		String title = driver.getTitle();		
		Assert.assertTrue(title.contains("Google"));	

		logger.log(Status.PASS, "Tilte Verified Successfully...");			
		
		//logger.createNode("Invalid title name verification");	
		
	}

	@Test(priority = 2, enabled = true)
	public void captureScreenshot() {

		try {			
			logger = report.createTest("Verify captureScreenshot").assignCategory("Functional").assignAuthor("UK");
			logger.log(Status.INFO, "Capture Screenshot Started ...");
			driver.get("https://www.ebay.in/");

			File oScnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Save in a Physical location
			FileUtils.copyFile(oScnShot, new File(System.getProperty("user.dir") + "\\outfiles\\Screenshot_" + System.currentTimeMillis()+ ".png"));

			logger.log(Status.PASS, "Screenshot is captured successfully...");

		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}
	
	
	 @Test (priority = 3, enabled = true)
	 public void brokenLinks() {
		 
		 	logger = report.createTest("Verify broken link").assignCategory("Functional").assignAuthor("UK");
			logger.log(Status.INFO, "Browser Started ...");
		 	String homePage = "http://www.freecrm.com";
	        String url = "";
	        HttpURLConnection huc = null;
	        int respCode = 200;
	        
	        driver.get(homePage);
	        
	        List<WebElement> links = driver.findElements(By.tagName("a"));
	        
	        Iterator<WebElement> it = links.iterator();	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");	            
	            System.out.println(url);
	        
	            if(url == null || url.isEmpty()){
	            	System.out.println("URL is either not configured for anchor tag or it is empty");
	                continue;
	            }
	            
	            if(!url.startsWith(homePage)){
	                System.out.println("URL belongs to another domain, skipping it.");
	                continue;
	            }
	            
	            try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                
	                // Set method as "HEAD" to get only headers instead of GET which will give whole request body
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                    logger.log(Status.FAIL,"Broken link is found...");
	                }
	                else{
	                	logger.log(Status.INFO, url+" is a valid link and Response code is :--> "+respCode);
	                    System.out.println(url+" is a valid link");
	                    
	                }
	                    
	            } catch (MalformedURLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            
	        }
	        
	        logger.log(Status.PASS, "Links verified Successfully...");
	    }
	    
  

	@AfterMethod(alwaysRun = true) // ITestResult will work only with @AfterMethod (It will run after each @Test)
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotName = CaptureScreenshot.captureScreenshot(driver, result.getMethod().getMethodName());				
			logger.fail("Fail due to some issue", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());
			logger.fail(result.getThrowable());
			//logger.addScreenCaptureFromPath("Fail due to some issue", screenshotName);
		}else if(result.getStatus() == ITestResult.SKIP){
			 logger.log(Status.SKIP, "Test Case Skipped is -: "+result.getMethod().getMethodName());
		 }else if(result.getStatus() == ITestResult.SUCCESS){
			 logger.log(Status.PASS, "Test Casename as -: "+result.getMethod().getMethodName()+" is Passed");
			 /*String screenshotName = CaptureScreenshot.captureScreenshot(driver, result.getMethod().getMethodName());
			 logger.pass("Successfully executed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());*/
		 }
		   report.flush();
		
		 
	}

	@AfterTest(alwaysRun = true) // Zip report and send Email after complete execution
	public void tearDown() {
	//public void tearDown() throws ATUTestRecorderException {
		try {

			
			 /*// Zip report directory 
			  File sourceDir = new File(System.getProperty("user.dir")+"\\target\\surefire-reports"); 
			  File destinationDir = new File(System.getProperty("user.dir")+"\\Misc\\zipReport\\TestReport.zip");
			  ZipFiles zip = new ZipFiles(); 
			  ZipFiles.zipReport(sourceDir,destinationDir); 
			  String reportFileName = destinationDir.toString();
			  System.out.println("Files zipped successfully..");*/
			
			 /*//String reportFileName = System.getProperty("user.dir")+"\\extent-reports\\Reports.html";
			  File reportFileName = GetLatestFile.getTheNewestFile(System.getProperty("user.dir")+"/extent-reports/","html");			  
			  SendAttachmentInEmail.emailReport(reportFileName.toString());
			  System.out.println("Email sent successfully..");*/
			 
			// report.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not send email..");
		} finally {
			driver.quit();
			
		}
	}

}
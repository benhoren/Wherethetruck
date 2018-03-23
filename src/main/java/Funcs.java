import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;



public class Funcs {
	/**
	 * usful methods 
	 */
	
	

	/**
	 * start webDriver with url in this.window of visability
	 * @param url first url
	 * @return started driver
	 */
	public WebDriver startWebDriver(String url, WindowState window){
				
		if(window == WindowState.visible){
			return startVisibleWebDriver(url);
		}
		if(window == WindowState.Background){
			return startDistanceWebDriver(url);
		}
		if(window == WindowState.Invisible){
			return startHeadlessWebDriver(url);
		}
		return null;
	}


	/**
	 * this function open and start new visable WebDriver
	 * @param url -first url
	 * @return new webDriver open with param url
	 */
	private WebDriver startVisibleWebDriver(String url){
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", Main.folder+"/chromedriver");
		ChromeOptions options = new ChromeOptions();
		//		options.addArguments("--headless");
		options.addArguments("--start-maximized");
		options.addArguments("--mute-audio");

		driver = new ChromeDriver(options);
		try{
			//			driver.manage().window().setPosition(new Point(-2000, 0));
		}
		catch(Exception e){System.err.println(e);}


		if(url.isEmpty() || url.equals(""))
			return driver;
		driver.get(url);
		return driver;
	}



	/**
	 * this function open and start new invisible WebDriver
	 * @param url -first url
	 * @return new webDriver open with param url
	 */
	private WebDriver startHeadlessWebDriver(String url){

		for(int i=0; i<3; i++){
			try{
				WebDriver driver;
				System.setProperty("webdriver.chrome.driver", Main.folder+"/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--start-maximized");
				options.addArguments("--mute-audio");
				driver = new ChromeDriver(options);

				if(url.isEmpty() || url.equals(""))
					return driver;
				driver.get(url);
				return driver;

			}catch(Exception e){e.printStackTrace();}
		}
		return null;

	}




	/**
	 * this function open and start new WebDriver in background
	 * @param url -first url
	 * @return new webDriver open with param url
	 */
	private WebDriver startDistanceWebDriver(String url){
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", Main.folder+"/chromedriver");
		ChromeOptions options = new ChromeOptions();
		//		options.addArguments("--headless");
		options.addArguments("--start-maximized");
		options.addArguments("--mute-audio");
		driver = new ChromeDriver(options);
		try{
			driver.manage().window().setPosition(new Point(-2000, 0));
		}
		catch(Exception e){System.err.println(e);}


		if(url.isEmpty() || url.equals(""))
			return driver;
		driver.get(url);
		return driver;
	}

	/**
	 * use to click on invisible elements, using javaScript. not recommended but usualy working 
	 * @param driver -webfriver
	 * @param element -WebElement to click. 
	 */
	public void clickInvisible(WebDriver driver, WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * send keys to invisible elements. not recommended but working
	 * @param driver -webfriver
	 * @param element -WebElement field. 
	 */
	public void sendKeysInvisible(WebDriver driver, WebElement element){
		RemoteWebDriver r=(RemoteWebDriver) driver;
		r.executeScript("arguments[0].value='admin'",element);

	}





	/**
	 * move to element in page using Actions. 
	 * @param driver
	 * @param web element
	 */
	public boolean moveTo(WebDriver driver, WebElement we){
		Actions actions = new Actions(driver);
		try{
			actions.moveToElement(we).perform();
		}
		catch (IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	/**
	 * move to element in page using javaScript. 
	 * @param driver
	 * @param web element
	 * @return false if faild.
	 */
	public boolean moveTo2(WebDriver driver, WebElement we){
		try{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView()", we); 
		}catch(Exception e) {return false;}
		return true;
	}
	
	

	/**
	 * thread sleep for mil milliseconds
	 * @param mil
	 */
	public static void sleep(int mil){
		try {
			Thread.sleep(mil);
		} catch (InterruptedException e) {
			System.err.println("Interrupted");
		}
	}


	
	
	
	
}

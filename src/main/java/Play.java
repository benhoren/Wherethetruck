import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Play extends Funcs implements Runnable{
	WebDriver driver;
	String username;
	String password;
	boolean active;
	WindowState window = WindowState.Background;
	
	
	public Play(String user, String pass){
		this.username = user;
		this.password = pass;
		this.active = true;
	}
	
	
	public void run() {
		while(active){
			System.out.print("log in.. ");
			logIn();
			System.out.println("log");
			sleep(60000);
			turnOff();
			Funcs.sleep(1000*60);
		}
		System.out.println("end");
	}


	



	public void logIn(){
		String url = "http://wherethetruck.at/";	

		try{
			if(driver != null){
				turnOff();
			}
			driver = startWebDriver(url, window);
			WebElement bttn = driver.findElement(By.xpath("//*[@id='nav']//li[contains(@class,'bp-login-nav')]"));
			bttn.click();
//			clickInvisible(driver, bttn);
			
			sleep(4000);

			WebElement emailfield = driver.findElement(By.xpath("//input[@id='user_login']"));
			WebElement passwordfield = driver.findElement(By.xpath("//input[@id='user_pass']"));

			emailfield.clear();
			emailfield.click();
			emailfield.sendKeys(this.username);

			passwordfield.clear();
			passwordfield.click();
			passwordfield.sendKeys(this.password);

			sleep(2000);

			WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));
			login.click();

			sleep(1000);
			
			if(driver.getCurrentUrl().contains("wp-login"))
				throw new Exception("error message");
			
		}catch(Exception e){e.printStackTrace();}

	}


	public void logOut(){

	}

	public void turnOff(){
		System.out.println("turn off");
		try{
			driver.close();
			driver.quit();
		}catch(Exception e){}
		
		try {
			Runtime.
			getRuntime().
			exec("taskkill /im chromedriver.exe /f");
		} catch (IOException e) {}
		sleep(2000);
		
		driver = null;
	}





}

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Play extends Funcs implements Runnable{
	WebDriver driver;
	String username;
	String password;
	boolean active;
	WindowState window = WindowState.Invisible;
	
	
	public static final int ttl = 1000*60*25;


	public Play(String user, String pass){
		this.username = user;
		this.password = pass;
		this.active = true;
	}


	public void run(){
		while(active){
			mainScreen.log.setText("working...");
			System.out.print("log in.. ");

			try{
				logIn();
				
				if(!active){
					turnOff();
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
				break;
			}
			turnOff();
			mainScreen.writeOk();
			System.out.println("log");
			Funcs.sleep(ttl);
		}
		System.out.println("end");
	}






	public void logIn() throws Exception{
		String url = "http://wherethetruck.at/";	

		try{
			if(driver != null){
				turnOff();
			}
			driver = startWebDriver(url, window);
			WebElement bttn = driver.findElement(By.xpath("//*[@id='nav']//li[contains(@class,'bp-login-nav')]"));
			
//			WebElement logi = driver.findElement(By.xpath("//*[@id='menu-item-15347']/a"));
			//*[@id='menu-item-15347']/a
			
			String ahref = bttn.findElement(By.tagName("a")).getAttribute("href");

			driver.get(ahref);
			
//			bttn.click();

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

			sleep(30000);



		}
		catch(NoSuchElementException e){e.printStackTrace(); mainScreen.writeToLog("error"); this.active = false;}
		catch(Exception e){e.printStackTrace(); this.active = false;}
		
		if(driver.getCurrentUrl().contains("wp-login")){
			System.err.println("wrong password");
			mainScreen.writeToLog("incorrect");	
			this.active = false;
		}

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

package it.prova.myebay.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestSeleniumServlet {
	
	private static String DRIVER_NAME = "webdriver.chrome.driver";
	private static String DRIVER_PATH = "C:\\corso\\ws_eclipse\\myebay\\src\\test\\java\\chromedriver.exe";
	
	@Test
	void testExecuteSearchAnnunciServlet() {
		System.setProperty(DRIVER_NAME, DRIVER_PATH);
		
		WebDriver driver = new ChromeDriver();
		String expected = "iphone 13 pro max super ultra";

		driver.get("http://localhost:8080/myebay");
		
		driver.findElement(By.xpath("//*[@id=\"titolo\"]")).sendKeys("iphone");
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
		
		String text = driver.findElement(By.xpath("/html/body/main/div/div[3]/div[2]/div/table/tbody/tr/td[1]")).getText();
        
        assertEquals(text, expected);
	}
	
}

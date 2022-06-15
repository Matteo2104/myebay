package it.prova.myebay.servlet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestSeleniumServlet {
	
	private static String DRIVER_NAME = "webdriver.chrome.driver";
	private static String DRIVER_PATH = "C:\\corso\\ws_eclipse\\myebay\\src\\test\\java\\chromedriver.exe";
	
	@Test
	void testExecuteSearchAnnunciServlet_1interfaccia() {
		System.setProperty(DRIVER_NAME, DRIVER_PATH);
		
		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8080/myebay");
		
		
		driver.findElement(By.xpath("//*[@id=\"titolo\"]")).sendKeys("iphone");
		driver.findElement(By.xpath("//*[@id=\"prezzo\"]")).sendKeys("750");
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
		
		String text = driver.findElement(By.xpath("/html/body/main/div/div[3]/div[2]/div/table/caption")).getText();
		
		
		// numero dei risultati della ricerca
		int numberOfResult = Integer.parseInt(token(text, " "));
		
        assertTrue(numberOfResult >= 1);
        
	}
	
	private static String token(String string, String separator) {
		int i = 0;
		String token = "";
		
		while (string.charAt(i) != ' ') {
			token += string.charAt(i);
			i++;
		}
		
		return token;
	}
	
}

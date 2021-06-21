package com.qa.animelist.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.animelist.AnimeListApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:anime-schema.sql", "classpath:anime-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(classes = AnimeListApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test") 
public class AnimeListAcceptanceTest {

	private WebDriver driver;
	
	@BeforeEach
	public void setup() {
		//ChromeOptions config = new ChromeOptions();  BUGGY
		//config.setHeadless(true);
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void createAnimeTest() throws InterruptedException {
		driver.get("http://localhost:8080/");
		
		driver.findElement(By.id("title")).sendKeys("Erased");
		driver.findElement(By.id("genre")).sendKeys("Thriller");
		driver.findElement(By.id("episodes")).sendKeys("12");
		driver.findElement(By.id("season")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id=\"createForm\"]/button[2]")).click();
		
		Thread.sleep(500);
		
		assertThat(driver.findElements(By.className("card-text")).get(4).getText()).isEqualTo("Title: Erased");
	}
	
	@Test
	public void deleteAnimeTest() throws InterruptedException {
		driver.get("http://localhost:8080/");
		
		driver.findElement(By.xpath("//*[@id=\"output\"]/div/div/div/div/button[2]")).click();
		
		Thread.sleep(500);
		
		assertThat(driver.findElements(By.className("card-text")).isEmpty()).isTrue();
	}
	
	@Test
	public void updateAnimeTest() throws InterruptedException{
		driver.get("http://localhost:8080/");
		
		driver.findElement(By.id("title")).sendKeys("Erased");
		driver.findElement(By.id("genre")).sendKeys("Thriller");
		driver.findElement(By.id("episodes")).sendKeys("12");
		driver.findElement(By.id("season")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id=\"createForm\"]/button[2]")).click();
		
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//*[@id=\"output\"]/div[2]/div/div/div/button[1]")).click();
		
		Thread.sleep(500);
		
		driver.findElement(By.id("epTitle")).sendKeys("The Beginning of the End");
		driver.findElement(By.id("epDesc")).sendKeys("A fated meeting sets things into motion");
		
		driver.findElement(By.xpath("//*[@id=\"epout\"]/div[1]/form/button")).click();
		
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//*[@id=\"output\"]/div[2]/div/div/div/button[1]")).click();
		
		Thread.sleep(500);
		
		assertThat(driver.findElement(By.id("epTitle")).getAttribute("value")).isEqualTo("The Beginning of the End");
		assertThat(driver.findElement(By.id("epDesc")).getAttribute("value")).isEqualTo("A fated meeting sets things into motion");
	}
	
	
	
	@AfterEach
	public void cleanup() {
		driver.quit();
	}
	
}

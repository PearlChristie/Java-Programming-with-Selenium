package com.in28minutes.pageobjects.updatetodo;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateTodoBasicTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	public void loginPageObject() {
		
		driver.get("http://localhost:8080/login");
		
		LoginPage page = PageFactory.initElements(driver, LoginPage.class);
		
		//driver.findElement(By.id("name")).getAttribute("type")
		System.out.println(page.name.getAttribute("type"));//text
		
		//driver.findElement(By.id("password")).getAttribute("type")
		System.out.println(page.password.getAttribute("type"));//password
		
	}
	
	@Test
	public void updateTodo() {		
		
		LoginPage page = PageFactory.initElements(driver, LoginPage.class);
		page.login("in28minutes", "dummy");
					
		driver.findElement(By.linkText("Click here")).click();
		
		ListTodoPage listTodoPage = new ListTodoPage(driver);
		listTodoPage.clickUpdateFor("10002");
		
		TodoPage todoPage = PageFactory.initElements(driver, TodoPage.class);
		todoPage.enterDescription("Become a Tech Guru - 2");
		todoPage.enterTargetDate("12/09/2019");
		todoPage.submit();
		
		assertEquals(listTodoPage.getDescription("10002"), 
							"Become a Tech Guru - 2");
		assertEquals(listTodoPage.getTargetDate("10002"), "12/09/2019");
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}

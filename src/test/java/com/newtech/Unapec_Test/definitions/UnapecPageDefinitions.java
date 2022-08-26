package com.newtech.Unapec_Test.definitions;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.testng.Assert.assertNotEquals;


public class UnapecPageDefinitions {
    WebDriver driver;
    String stepname = "";

    @Before
    public void SetUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

    }
    @Given("^Launch UNAPEC Home Page$")
    public void userOnHomePage(){
        driver.get("https://unapec.edu.do");
        Assert.assertTrue(driver.getTitle().equals("Universidad APEC - UNAPEC"));
        stepname = "Launch UNAPEC Home Page";

    }
    @When("^User clicks on 'Ofertas de Grado' button$")
    public void clickProgramasDeGrado(){
        driver.findElement(new By.ByXPath("/html/body/div[1]/div[1]/div/div[2]/div[1]/a")).click();
        stepname = "User clicks on 'Ofertas de Grado' button";
    }
    @When("^User should be able to go to next page$")
    public void successfulRedirect(){
        Assert.assertTrue(driver.getTitle().equals("Universidad APEC - Academia"));
        stepname = "User should be able to go to next page";
    }
    @Then("^User clicks on other 'Ofertas de Grado' button$")
    public void clickMoreProgramasDeGrado(){
        driver.findElement(new By.ByXPath("//*[@id=\"menu\"]/ul[1]/li[1]/a")).click();
        String newPageText = driver.findElement(new By.ByXPath("/html/body/div[1]/div[3]/div[2]/div[3]/h3")).getText();
        System.out.println("Text in the new Page: " + newPageText);
        Assert.assertEquals(newPageText, "Ofertas de Grado");
        stepname = "User clicks on other 'Ofertas de Grado' button";

    }
    @Then("^User sees all academic offers$")
    public void successfulLoad(){
        WebElement fatherList = driver.findElement(new By.ByXPath("/html/body/div[1]/div[3]/div[2]/div[3]/div/div[1]/ul[1]"));
        List<WebElement> children = fatherList.findElements(new By.ByTagName("li"));
        for(WebElement child : children){
            System.out.println(child.getText());
        }
        assertNotEquals(children.size(), 0);
        stepname = "User sees all academic offers";
    }
    @AfterStep
    public void takeScreenshot(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png",stepname);
    }
    @After
    public void teardown(){
        driver.quit();
    }

}


package ru.academits.HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class DemoQATests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriver driver = null;

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

            driver.get("https://demoqa.com/automation-practice-form");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
    @Test
    public void inputFirstNameTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys("Elena");
        Thread.sleep(2000);

        firstNameInput.clear();
        Thread.sleep(2000);

        Assertions.assertTrue(firstNameInput.getText().equals(""));
    }

    @Test
    public void inputLastNameTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("Zaytseva");
        Thread.sleep(2000);

        lastNameInput.clear();
        Thread.sleep(2000);

        Assertions.assertTrue(lastNameInput.getText().equals(""));
    }

    @Test
    public void inputEmailTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("elena@example.com");
        Thread.sleep(2000);

        emailInput.clear();
        Thread.sleep(2000);

        Assertions.assertTrue(emailInput.getText().equals(""));
    }

    @Test
    public void selectGenderTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.xpath("//*[@id='gender-radio-2']")).isSelected();
        Thread.sleep(3000);
    }

    @Test
    public void inputNumberTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement input = driver.findElement(By.cssSelector("input[id='userNumber']"));
        input.sendKeys("1234567890");
        Thread.sleep(2000);

        input.clear();
        Thread.sleep(2000);

        Assertions.assertTrue(input.getText().equals(""));
    }

    @Test
    public void selectBirthDateTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement input = driver.findElement(By.id("dateOfBirthInput"));
        input.click();

        WebElement monthDropdown = driver.findElement(By.xpath("//div//select[@class='react-datepicker__month-select']"));
        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByVisibleText("April");

        WebElement yearDropdown = driver.findElement(By.xpath("//div//select[@class='react-datepicker__year-select']"));
        Select yearSelect = new Select(yearDropdown);
        yearSelect.selectByVisibleText("1990");

        WebElement daySelect = driver.findElement(By.xpath("//div[@aria-label='Choose Sunday, April 1st, 1990']"));
        daySelect.click();
        Thread.sleep(2000);
    }

    @Test
    public void inputSubjectsTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement input = driver.findElement(By.id("subjectsInput"));
        input.sendKeys("Economics");
        input.sendKeys("English");
        Thread.sleep(2000);

        input.clear();
        Thread.sleep(2000);

        Assertions.assertTrue(input.getText().equals(""));
    }

    @Test
    public void selectCheckboxTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement hobbiesCheckbox = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-2']"));
        hobbiesCheckbox.click();
        Thread.sleep(3000);
    }

    @Test
    public void uploadPictureTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement input = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
        String text = input.getText();
        Thread.sleep(3000);
    }

    @Test
    public void inputAddressTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement currentAddressInput = driver.findElement(By.xpath("//*[@id='currentAddress']"));
        currentAddressInput.sendKeys("123, Street, City");
        Thread.sleep(2000);

        currentAddressInput.clear();
        Thread.sleep(2000);

        Assertions.assertTrue(currentAddressInput.getText().equals(""));
    }

    @Test
    public void selectStateTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

       driver.findElement(By.xpath("//div[@id='stateCity-wrapper']/div[2]")).isDisplayed();
        Thread.sleep(2000);
    }

    @Test
    public void selectCityTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.xpath("//div[@id='stateCity-wrapper']/div[3]")).isEnabled();
        Thread.sleep(2000);
    }

    @Test
    public void clickSubmitTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.xpath("//*[@id='submit']")).click();
        Thread.sleep(3000);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
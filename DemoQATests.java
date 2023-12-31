package ru.academits.HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Java6StandardSoftAssertionsProvider;
import org.assertj.core.api.SoftAssertions;
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

import java.io.File;
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
    public void fillOutPracticeFormTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys("Elena");

        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("Zaytseva");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("elena@example.com");

        WebElement genderSelect = driver.findElement(By.xpath("//*[@id='gender-radio-2']"));
        genderSelect.isSelected();

        WebElement input = driver.findElement(By.cssSelector("input[id='userNumber']"));
        input.sendKeys("1234567890");

        WebElement dateOfBirthInput = driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']"));
        dateOfBirthInput.click();

        WebElement monthDropdown = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        monthDropdown.click();
        driver.findElement(By.xpath("//option[contains(text(), 'April')]"));

        WebElement yearDropdown = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        yearDropdown.click();
        driver.findElement(By.xpath("//option[contains(text(), '1990')]"));

        WebElement daySelect = driver.findElement(By.xpath("//div[@class='react-datepicker__month']/div[1]/div[1]"));
        daySelect.click();

        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("Economics, English");

        WebElement hobbiesCheckbox = driver.findElement(By.id("hobbies-checkbox-2"));
        hobbiesCheckbox.click();

        File picture = new File("src/test/java/ru/academits/HW/picture.jpg");
        WebElement uploadInput = driver.findElement(By.id("uploadPicture"));
        uploadInput.sendKeys(picture.getAbsolutePath());

        WebElement addressInput = driver.findElement(By.id("currentAddress"));
        addressInput.sendKeys("123, Street, City");

        WebElement stateDropdown = driver.findElement(By.xpath("//div[@id='stateCity-wrapper']/div[2]"));
        stateDropdown.click();
        driver.findElement(By.xpath("//div[contains(text(), 'NCR')]"));

        WebElement cityDropdown = driver.findElement(By.xpath("//div[@id='stateCity-wrapper']/div[3]"));
        cityDropdown.isEnabled();

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        Thread.sleep(3000);

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(driver.findElement(By.id("firstName")).getAttribute("value"))
                .isEqualTo("Elena");

        softAssert.assertThat(driver.findElement(By.id("lastName")).getAttribute("value"))
                .isEqualTo("Zaytseva");

        softAssert.assertThat(driver.findElement(By.id("userEmail")).getAttribute("value"))
                .isEqualTo("elena@example.com");

        softAssert.assertThat(driver.findElement(By.xpath("//*[@id='gender-radio-2']"))
                .getAttribute("value")).isEqualTo("elena@example.com");

        softAssert.assertThat(driver.findElement(By.id("gender-radio-2")).isSelected())
                .as("Select gender").isTrue();

        softAssert.assertThat(driver.findElement(By.cssSelector("input[id='userNumber']"))
                .getAttribute("value")).isEqualTo("1234567890");

        softAssert.assertThat(driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"))
                .getText()).isEqualTo("April");

        softAssert.assertThat(driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"))
                .getText()).isEqualTo("1990");

        softAssert.assertThat(driver.findElement(By.xpath("//div[@class='react-datepicker__month']/div[1]/div[1]"))
                .isSelected());

        softAssert.assertThat(driver.findElement(By.id("subjectsInput")).getAttribute("value"))
                .isEqualTo("Economics, English");

        softAssert.assertThat(driver.findElement(By.id("hobbies-checkbox-2")).isSelected());

        softAssert.assertThat(driver.findElement(By.id("uploadPicture")).getAttribute("value")).endsWith("picture.jpg");

        softAssert.assertThat(driver.findElement(By.id("currentAddress")).getAttribute("value"))
                .isEqualTo("123, Street, City");

        softAssert.assertThat(driver.findElement(By.xpath("//div[contains(text(), 'NCR')]"))
                .isDisplayed()).as("The 'State' field should be visible").isTrue();

        softAssert.assertThat(driver.findElement(By.xpath("//div[@id='stateCity-wrapper']/div[3]"))
                .isEnabled()).as("The 'City' field should be active").isTrue();

        softAssert.assertThat(driver.findElement(By.id("submit")).isDisplayed());

        softAssert.assertAll();
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

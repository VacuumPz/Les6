package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class AbstractTest {
    static WebDriver driver;

    @BeforeAll
    static void before(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        options.setPageLoadTimeout(Duration.ofSeconds(8));

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @BeforeEach
    void goTo(){
        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("https://www.kinopoisk.ru/"),
                "Страница не доступна");
    }

    @AfterAll
    static void close(){
        //if(driver !=null) driver.quit();
        //driver.quit();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}

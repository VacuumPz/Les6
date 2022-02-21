package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class MainPageLinks extends AbstractPage{
    public MainPageLinks(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = ".//div[starts-with(@class, 'styles_root')]/a[@href='/media/']")
    private WebElement media;

    @FindBy(xpath = ".//a[@href='/media/news/'][contains(text(), 'Новости')]")
    private WebElement news;

    @FindBy(xpath = ".//div[starts-with(@class, 'styles_root')]/a[@href='/lists/films/1/']")
    private WebElement films;

    @FindBy(xpath = ".//button[text() = 'Войти']")
    private WebElement signIn;

    @FindBy(xpath = ".//nav/a[text() = 'Онлайн-кинотеатр']")
    private WebElement watch;

    public void goToMedia(){
        media.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("media"));
    }

    public void goToNews(){
        news.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("news"));
    }

    public void goToFilms(){
        films.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("films/1/"));
    }

    public void goToSignIn(){
        signIn.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.textMatches(By.xpath(".//span[@class = 'passp-add-account-page-title']"), Pattern.compile("Войдите или зарегистрируйтесь")));
    }

    public void goToWatch(){
        watch.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("hd.kinopoisk.ru"));
    }


}

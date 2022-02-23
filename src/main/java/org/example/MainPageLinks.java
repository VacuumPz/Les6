package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = ".//span[contains(text(), '250 лучших фильмов')]")
    private WebElement top250;

    @FindBy(xpath = ".//*[text() = '2021']")
    private WebElement top250year;

    @FindBy(xpath = ".//span[contains(text(), 'Все годы')]")
    private WebElement allYear;

    @FindBy(xpath = ".//a[contains(text(), 'Куда приводят мечты')]")
    private WebElement findByName;

    @FindBy(xpath = ".//div[@class = 'posts-grid__main-section']/div/div[1]/div/div/a")
    private WebElement lastNews;

    @FindBy(xpath = ".//a[@href = '/box/']")
    private WebElement box;

    @FindBy(css = "body")
    private WebElement body;

    @FindBy(xpath = ".//button[starts-with(@class, 'HeaderContent__search-button')]")
    private WebElement searchTrailer;

    @FindBy(xpath = ".//select[@name='year']")
    private WebElement year;

    @FindBy(xpath = ".//option[@value='2021']")
    private WebElement checkYear;

    @FindBy(xpath = ".//a[starts-with(@onclick, 'document.form_box_year.submit')]")
    private WebElement subDates;

    @FindBy(xpath = ".//input[@type = 'search']")
    private WebElement searchText;

    @FindBy(xpath = ".//div[starts-with(@class, 'SuggestList__content')]/div/a")
    private WebElement sugSearch;

    @FindBy(xpath = ".//button[@name = 'Trailer']")
    private WebElement trailer;



    public MainPageLinks setSearchText(String search){
        this.searchText.sendKeys(search);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//p[text() = 'Французский вестник. Приложение к газете «Либерти. Канзас ивнинг сан»']")));
        return this;

    }

    public void goToSugSearch(){
        sugSearch.click();
    }

    public void goToTrailer(){
        trailer.click();
    }

    public void goToBox(){
        body.click();
        body.sendKeys(Keys.CONTROL, Keys.END);
        box.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("box"));
    }
    public void goToYear(){
        year.click();
        checkYear.click();
    }

    public void goToSubDates(){
        subDates.click();
    }

    public void goToSearchTrailer(){
        searchTrailer.click();
    }

    public void goToLastNews(){
        lastNews.click();
    }

    public void goToMedia(){
        media.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("media"));
    }
    public void goToTop250(){
        top250.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("top250"));
    }

    public void goToTop250Year(){
        top250year.click();
    }

    public void goToAllYear(){
        allYear.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[text() = '2021']")));
    }

    public void goToNews(){
        news.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("news"));
    }

    public void goToFilms(){
        films.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("films/1/"));
    }

    public void goToFindByName(){
        findByName.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("15139"));
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

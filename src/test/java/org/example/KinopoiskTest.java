package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;



public class KinopoiskTest extends AbstractTest{

    @Test
    @Tag("Positive")
    public void top250Test() {
        new MainPageLinks(getDriver()).goToFilms();
        getDriver().findElement(By.xpath(".//span[contains(text(), '250 лучших фильмов')]")).click();
        getDriver().findElement(By.xpath(".//span[contains(text(), 'Все годы')]")).click();

        WebDriverWait ulWait = new WebDriverWait(getDriver(), 5);
        ulWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//div[@class='selections-select__dropdown-wrapper']")));
        getDriver().findElement(By.linkText("2021")).click();

        Assertions.assertEquals("Человек-паук: Нет пути домой", getDriver().findElement(By.xpath(".//a[@href = '/film/1309570/']/p[1]")).getText()); //Лучший фильм года
    }

    @Test
    @Tag("Positive")
    public void searchTest() {

        new Search(getDriver()).setSearch("Куда приводят мечты");
        getDriver().findElement(By.xpath(".//a[contains(text(), 'Куда приводят мечты')]")).click();

        Assertions.assertEquals("Куда приводят мечты (1998)", getDriver().findElement(By.xpath(".//h1/span")).getText()); //Сверяем название фильма
    }

    @Test
    @Tag("Dynamic")
    public void lastNewsTest() {
        new MainPageLinks(getDriver()).goToMedia();
        new MainPageLinks(getDriver()).goToNews();
        getDriver().findElement(By.xpath(".//div[@class = 'posts-grid__main-section']/div/div[1]/div/div/a")).click(); //открываем первую новость в списке

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Assertions.assertEquals(getDriver().findElement(By.xpath(".//span[@class = 'media-news__published-date']")).getAttribute("content"), dateFormat.format(date));
        //Сравнение даты последней новости с текущим днем. Если последняя новость сегодняшняя тест проходит, если последняя новость вчерашняя - нет. Копирайтеры должны быстрее работать
    }

    @Test
    @Tag("Positive")
    public void boxCheckTest() {
        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);


        getDriver().findElement(By.xpath(".//a[@href = '/box/']")).click();

        getDriver().findElement(By.xpath(".//select[@name='year']")).click();
        getDriver().findElement(By.xpath(".//option[@value='2021']")).click();
        getDriver().findElement(By.xpath(".//a[starts-with(@onclick, 'document.form_box_year.submit')]")).click();

        Assertions.assertEquals("2021 год", getDriver().findElement(By.xpath(".//h1/span[2]")).getText()); //Сверяем год статистики кассовых сборов

    }

    @Test
    @Tag("Negative")
    void loginTest() throws InterruptedException {
        new MainPageLinks(getDriver()).goToSignIn();
        new SignIn(getDriver()).setLogin("testid").setPassword("passwd");

        Thread.sleep(100); //время на авторизацию
        //Тестируем неверный ввод данных
        Assertions.assertEquals("Неверный пароль", getDriver().findElement(By.xpath(".//div[@id = 'field:input-passwd:hint']")).getText());
    }

    @Test
    @Tag("Positive")
    public void watchTheaterTrailerTest() {
        new MainPageLinks(getDriver()).goToWatch();
        getDriver().findElement(By.xpath(".//button[starts-with(@class, 'HeaderContent__search-button')]")).click();
        getDriver().findElement(By.xpath(".//input[@type = 'search']")).sendKeys("Французский вестник");
        WebDriverWait ulWait = new WebDriverWait(getDriver(), 30);
        ulWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//p[text() = 'Французский вестник. Приложение к газете «Либерти. Канзас ивнинг сан»']")));
        getDriver().findElement(By.xpath(".//div[starts-with(@class, 'SuggestList__content')]/div/a")).click();
        getDriver().findElement(By.xpath(".//button[@name = 'Trailer']")).click();

        Assertions.assertEquals("Французский вестник. Приложение к газете «Либерти. Канзас ивнинг …", getDriver().findElement(By.xpath(".//h1/span")).getText()); //Сверяем год статистики кассовых сборов

    }

}

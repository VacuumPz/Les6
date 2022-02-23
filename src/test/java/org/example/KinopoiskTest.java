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
        new MainPageLinks(getDriver()).goToTop250();
        new MainPageLinks(getDriver()).goToAllYear();

        new MainPageLinks(getDriver()).goToTop250Year();

        Assertions.assertEquals("Человек-паук: Нет пути домой", getDriver().findElement(By.xpath(".//span[text() = 'Человек-паук: Нет пути домой']")).getText()); //Лучший фильм года
    }

    @Test
    @Tag("Positive")
    public void searchTest() {

        new Search(getDriver()).setSearch("Куда приводят мечты");
        new MainPageLinks(getDriver()).goToFindByName();

        Assertions.assertEquals("Куда приводят мечты (1998)", getDriver().findElement(By.xpath(".//h1/span")).getText()); //Сверяем название фильма
    }

    @Test
    @Tag("Dynamic")
    public void lastNewsTest() {
        new MainPageLinks(getDriver()).goToMedia();
        new MainPageLinks(getDriver()).goToNews();
        new MainPageLinks(getDriver()).goToLastNews(); //открываем первую новость в списке

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Assertions.assertEquals(getDriver().findElement(By.xpath(".//span[@class = 'media-news__published-date']")).getAttribute("content"), dateFormat.format(date));
        //Сравнение даты последней новости с текущим днем. Если последняя новость сегодняшняя тест проходит, если последняя новость вчерашняя - нет. Копирайтеры должны быстрее работать
    }

    @Test
    @Tag("Positive")
    public void boxCheckTest() {
        new MainPageLinks(getDriver()).goToBox();
        new MainPageLinks(getDriver()).goToYear();
        new MainPageLinks(getDriver()).goToSubDates();

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
        new MainPageLinks(getDriver()).goToSearchTrailer();
        new MainPageLinks(getDriver()).setSearchText("Французский вестник");
        new MainPageLinks(getDriver()).goToSugSearch();
        new MainPageLinks(getDriver()).goToTrailer();

        Assertions.assertEquals("Французский вестник. Приложение к газете «Либерти. Канзас ивнинг …", getDriver().findElement(By.xpath(".//h1/span")).getText()); //Сверяем год статистики кассовых сборов

    }

}

package org.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search extends AbstractPage{
    public Search(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@placeholder='Фильмы, сериалы, персоны']")
    private WebElement search;


    public Search setSearch(String search){
        this.search.sendKeys(search, Keys.ENTER);
        return this;
    }

}

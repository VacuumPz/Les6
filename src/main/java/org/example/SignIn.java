package org.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignIn extends AbstractPage{
    public SignIn(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@name = 'login']")
    private WebElement login;

    @FindBy(xpath = ".//input[@name = 'passwd']")
    private WebElement password;

    @FindBy(xpath = ".//button[@type = 'submit']")
    private WebElement submit;

    public SignIn setLogin(String login){
        this.login.sendKeys(login, Keys.ENTER);
        return this;
    }

    public SignIn setPassword(String password){
        this.password.sendKeys(password, Keys.ENTER);
        return this;
    }

}

package com.qacart.todo.pages;

import com.qacart.todo.Base.BasePage;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewTOdoPage extends BasePage { // owel 7aja na3mlou extends lel basepage  w nebniw child constructor , puis n7otou l'element , puis n7otou les methodes
    public NewTOdoPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "[data-testid=\"new-todo\"]")
    private WebElement NewItem ;
    @FindBy(css = "[data-testid=\"submit-newTask\"]")
    private WebElement ClickNewItem ;


    @Step
    public /*void*/ NewTOdoPage load(){
        //driver.get(ConfigUtils.getInstance().getBaseUrl()+"/todo/new"); // "/todo/new" hard coded donc twalli fil endpoint
        driver.get(ConfigUtils.getInstance().getBaseUrl()+ EndPoint.NEW_TODO_END_POINT);
        return this;
    }
    @Step
    public /*void*/ TodoPage SendNewtodo(String NewAdd){ // text edheka "(String NewAdd)" bech yji ml testcase , el methode hedhi 3malneha te5ou el text w ta3ml click konna najmou kol wa7da wa7adha
        NewItem.sendKeys(NewAdd);
        ClickNewItem.click();
        return new TodoPage(driver);
    }


}

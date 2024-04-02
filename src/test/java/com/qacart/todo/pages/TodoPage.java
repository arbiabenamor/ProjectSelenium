package com.qacart.todo.pages;

import com.qacart.todo.Base.BasePage;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoPage extends BasePage {
    // le constructorChild
    public TodoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-testid=\"welcome\"]")
    private WebElement DisplayedMsg;


    @FindBy(css = "[data-testid=\"add\"]")
    private WebElement Addplus ;

    @FindBy(css = "[data-testid=\"todo-item\"]")
    private WebElement Gettext ;


    @FindBy(css = "[data-testid=\"delete\"]")
    private WebElement Deleteitem ;

    @FindBy(css = "[data-testid=\"no-todos\"]")
    private WebElement MesgNotodo ;


    @Step
    public /* void */ TodoPage load (){
        //driver.get(ConfigUtils.getInstance().getBaseUrl()+"/todo"); // nafs el get mta3 load elli 7attinha bel loginpage
        driver.get(ConfigUtils.getInstance().getBaseUrl()+ EndPoint.TODO_PAGE_ENDPOINT);
        return this; // bech na3mlou enchainement tet3adda lel methode elli ba3dha

    } // ba3d ma zedna el methode hedhi nemchi nfassa5 el loginpage methode fil todotest



    @Step
    public Boolean IswelcomeMesgDidplayed(){
        return DisplayedMsg.isDisplayed(); // hedhi return bech traj3elna true or false donc l methoide de type booleen mouch void
        // tawa nemchiw na3mlou fi logintest objet mel todopage
    }


    @Step

    public NewTOdoPage /*void*/ ClickOnButtonAdd () {
        Addplus.click(); // w nemchiw lel todotestcase w n3aytou lel objet
        return new NewTOdoPage(driver); // hedhi l methode ki neclikiw 3leha traj3elna comme resultat newtodopage
    }





    @Step
    public String ReturnActualText(){
        return Gettext.getText(); // 7attina return 5ater el gettext edhika n7ebou nraj3ouha 7ajetna biha
    } //w nemchiw n3arfou cet objet fil todotest





    @Step
    public /*void*/ TodoPage DLETETODO (){ //la methode DLETETODO() matrajja3 chay 5aterha void fil todopage donc n7otouha trajja3
        Deleteitem.click();
        return this ;
    }



    @Step
    public Boolean MSGofNOTOdoavailable (){
        return MesgNotodo.isDisplayed();
    }



}

package com.qacart.todo.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    // hedhom jebthom w 7attithom fil base page 5ater bech nesta3mlhom barcha 1er w 2eme el constructor w na3mlou extends fil LoginPge bech na3mlou inheritance
    protected WebDriver driver; // hedha badelneh protected bech elli bech ye5dhou ingheritance ykoun 3anhom acces 3al,driver mahouch private w driver edheka l value mte3ou jeya mel testcase ki n3arfou [LoginPage logginPage = new LoginPage(driver);], ba3d yemchi lel loginpage yjib driver w yab3thou lel super elli hiya parent lel loginpage=el BasePage, donc nemchiw lel basepage :tji l value mta3 driver ml loginpage elli howa zeda jebha ml logintest
    // donnc testcase => tab3eth value mta3 driver lel loginPage wel LoginPage==> yab3ethha lel BasePage w hakkeka ya3mel initilize lel driver bel [protected WebDriver driver;] w y7ot l valeur mte3ou = la valeur elli jet mel testcase w yebni mnha kol les elements mta3 this [PageFactory.initElements(driver,this );] : this edhioka tarja3 lelli b3ath el valeur mta3 driver elli heya el loginPage
    public BasePage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this );
    }
}

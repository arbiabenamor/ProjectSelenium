package com.qacart.todo.pages;

import com.qacart.todo.Base.BasePage;
import com.qacart.todo.utils.ConfigUtils;
import com.qacart.todo.utils.PropertiesUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

//donc ay page na3mlouha ta3ml extends mel "BasePage" w ay testcase na3mlouha ta3ml extends mel "BaseTest" : donc famma des choses principales lel pages w des choses principales lel testcase
//best practise ay 7aja n3arfouha n5aliwha private
//pageobject : y9olli a3mel classe l kol page ; houma 3 pges : login/TOD/todoNew w kol page 3andha classe
// w el best practise lkolhom youféw b page

public class LoginPage extends BasePage { // fil BasePage 3anna constructor donc fi child lezm famma constructor
    // child constructor
    public LoginPage(WebDriver driver) {
        super(driver);// ya3ni a3ml kol code l mawjoud fil parentConstructor
    }
    // on doit definir driver aussi
  /*  private WebDriver driver; // driver hehi kifech bch na3mloulha initialisation


    public LoginPage(WebDriver driver) // fonction qui prend le mm nom de classe = constructeur , el "WebDriver driver" jebha ml login test eka driver elli b3athneh
    {
        //donc elli 3lina na3mlouh enou n7otou el driver 3arefneh fi classe private n7otouh fil driver elli jetna ml LoginTest
this.driver = driver; // this.driver ==> private WebDriver driver;
        // en utilisant ce driver on va faire l'initialosation lel element elli louta
        // w bech na3mlou initialisation lel element li louta lezm on utilise  classe w n7otouh bel constructor w classe classe esmou pageFactory classe men selinium w static ya3ni najmou nesta5dmouha directly besm l classe
        PageFactory.initElements(driver,this ); // methode initElements(); ta3ml initialise lel elements li louta
        // methode initElements() : te5ou 2 arguments (driver : lezm ykoun famma driver bech na3mlou init lel element, la classe ou page elli bech na3mlou initialisation lel webEbelement w 7atytina this 5ater el elements mawjouda b nafs la page)
        // methode initElements() : temchi tlawj 3la  @FindBy(css = "[data-testid=\"email\"]") w tebnilna private WebElement emailInput;
    }
    */


    @FindBy(css = "[data-testid=\"email\"]") // classe mel selinuim t3awenna nal9aw l'element // cette FindBY traj3elna 7aja esmha webelement
    private WebElement emailInput; // on a definit web_element esmha emailInput w el valeur mte3ha (css = "[data-testid=\"email\"]")

    @FindBy(css = "[data-testid=\"password\"]")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid=\"submit\"]")
    private WebElement submitt;
    //la 1er methode
    // ay methode mawjouda fil page object hiya = step, setup

    @Step("load the login page")
    public /*void*/ LoginPage load (){ // l methode hedhi ma trajja3 chay 5aterha void , ema najmou n5aliwha trajja3 l page hedhi mta3 "LoginPage" bech ba3d nesta5dmou el methode elli ba3dha directement elli heya loggin, do,c bech nesta3mlou el builder pattern ( le deplacement entre les methode) donc hedha bel return wel this
       /*
        // si on veut utiliser prperties , na3mlou objet mel classe Propertiesutils w n3aytou lel methode
       //=>  ema puisque bech na9rawha marra wa7da najmou nbadlouha static fil classe  wma n3arfouch hakka objetc :
        // PropertiesUtils propertiesUtils = new PropertiesUtils();
        // propertiesUtils.loadProperties();
        //tawalli hakka n3arfou direct bel classe
       Properties props=  PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/production.properties");
        //=>cette methode traj3elna de classe Properties donc n7otouh fi variable esmou props:
        driver.get(props.getProperty("baseUrl")); // fil kol page lezm 3anna l methode load bech nvisitou l site
        return this; */

        // ema a7na ki baddelna classe configutils  twalli hakka mouch kima l fou9 :
        /* ConfigUtils configUtils = new ConfigUtils(); // 3arrefna objet mel classe ConfigUtils() ema ba3d el getinstance wallét zeyda el definition hedhi */
        driver.get(ConfigUtils.getInstance().getBaseUrl());
        return this;
    }
    //==> ema hedha lkol zeyd mouch bech no93od kol marra ena na3ml nafs l 7keya lel password wala email wala ...w na9ra mn page hedhi nafsha, nemchi n3arref classe okhra configUtils
    //=>donc na3mlou classe
    @Step
    public /*void*/ TodoPage loggin (String email, String password){ // 3malna methode bech n7otou feha les 3 lignes elli fi tests cases bech ne5tasrou w ces arguments tjibhom mel testcase LoginTest
        emailInput.sendKeys(email);// sta3melna variable emailInput puisque 3arefneh lfou9 w b3athna fih l email elli jebneh mel testcase , man7ebouch n7otouh hard email ya3ni nektoub ktiba
        passwordInput.sendKeys(password);
        submitt.click(); // submit hedhi bech traj3elna comme result  Todopage donc najmou na3mlou liason binethom :
        return new TodoPage(driver) ; // donc return fiha todopage w eka l void twalli esm l classe w naemchiw nfaskhou elli 3arefneh fi todotest
    }
}
/* ay page fi silinuim , ki n7eb na3ml pageobject lezmou ykoun metkawen men 4 choses principales :
1er definition de driver : private WebDriver driver;
2eme: constructor: elli njibou fiha valeur de driver mn blasa wen general tkkoun ml testcase
public LoginPage(WebDriver driver) {
this.driver = driver;
PageFactory.initElements(driver,this );} w el initElements lezm tkoun fi ay page
 3eme : n7otou les elements
4eme : la methode
public void loggin (String email, String password){
emailInput.sendKeys(email);
passwordInput.sendKeys(password);
submitt.click(); }
               */
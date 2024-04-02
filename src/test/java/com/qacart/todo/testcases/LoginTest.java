package com.qacart.todo.testcases;

import com.qacart.todo.Base.BaseTest;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.utils.ConfigUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

// 1er test cases hiya loginTest
@Feature("Auth Feature")
public class LoginTest extends BaseTest {
    //  private WebDriver driver; // hedha 3arefneh bech njibouh mel classe factory et de best practise n7otoou private mais si il reste private dans Factory ou baseTest
    // na3mlou inheritance lel classe lkolha on hedhi :  public class LoginTest extends BaseTest w hakka ykoun 3anna acces 3al les methodes lkol de cette classe puisque houma public w el parivate ywalii protected
    @Story("login with email and password")
//description hedhi bech tetzed 3al partie droite de report
    @Description("it will login by filling the eamil and the password and navigate to the todo page")
    @Test (description = "Test the login fonctionnality using email and password")
    public void souldbeableTologinWithemailandPassword() // la 1er testcases : on peut faire login en utilisant email et password
    {

// si on veut faire interaction avec website on doit definir le driver
        /* WebDriverManager.chromedriver().setup(); // au lieur nemchi nchouf l version mta3 l browser mte3i w nhabtou le driver de facon manuel w n'ajoutih 3al projet, on utilisant dependency <Webdrivermanagerio.github.bonigarcia>,cette  ligne de code verifi chnow l version li teb3a chrome elli 3al laptope, yji lel internet yhabbet driver approprié lel browser mte3i w ya3mel setup lel environnement
        WebDriver driver = new ChromeDriver(); //  WebDriver : classe de selinuim
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize(); */
        // driver = new DriverFactory().initializeDriver(); // la valeur de driver 3arrefneha bel classe driverFactory elli te5ou methode initializeDriver
        /* driver.get("https://todo.qacart.com/"); : fasse5neha 5ater 3awedhneha bl methode load w n3aytou lel methode bel objet elli 3arefneh   */

        //LoginPage logginPage = new LoginPage(driver); // 3arefna object ml clase LoginPage w b3athna el value m3aha lel constructeur elli bech y5azenha
        LoginPage logginPage = new LoginPage(getDriver());

        //mademou el driver howa m3aref fil test cases b fazét el prtotected donc , nab3tou driver hedha ki na3mlou initilisation lel LoginPage , el constructor elli 3malneh bech y5azen el driver edheka elli b3athneh
        //w a7na ki n7ebou nest5admou Loginpage n3arfou edheka l'opbjet 5ater n7ebou nesta5dmou au code ml classe edhika :  3malna objet mel classe LoginPage w b3athna value m3aha w hedhi bel constructeur elli 3malneh fil classe LoginPage, el constructor howa el methode ki nsamiwha b nafs esm el classe
        // logginPage.load(); // tzour lurl , hedhi owel methode nesta5dmouha , w el metyhode hedhi mawjouda fil login page
        //=>twalli
        Boolean isDisplayedWELCOME =
                logginPage
                        .load()
                        /* .loggin("arbiabenamor111@gmail.com", "projet_12345678")*/.loggin(ConfigUtils.getInstance().getEmail(),ConfigUtils.getInstance().getPassword())
                        .IswelcomeMesgDidplayed();
        // ==>3malna load w ba3d load hedhi traj3elna nafs el loginpage wel methode edhika loggin elli tarj3elna TodoPage donc nwalli nzidou "TodoPage todoobject=" w ba3d ki bech nzidou n'enchainiw m3a l methode Isdisolayed twalli trajja3 boolean w hiyaa ekek
        // w nfas5ou elli kent *
        // w bech netsa5dmou ay methode ml logginPage lezmna na3mlou "logginPage.load();" w ba3d "logginPage.loggin" w ba3d ken mchina lel page thenya na3mlou todopage
        // * TodoPage todoobject =logginPage.loggin("arbiabenamor111@gmail.com", "projet_1234567"); // 5ater traj3elna page todo
//==> hedha howa el builder patterna tetna99el men methode l methode sans utiliser l'object ,sta5demna l'object marra wa7da a7na khw
        // idha bech nesta5dmou ay methode mawjouda fi west ay page lezm n7otouu l'objet elli teb3 ela pge ba3d .load , w ema najmou nbadlouha bel builder pattern
/*taw najmou na7iwhom hedhom
        driver.findElement(By.cssSelector("[data-testid=\"email\"]")).sendKeys("arbiabenamor111@gmail.com");
        driver.findElement(By.cssSelector("[data-testid=\"password\"]")).sendKeys("projet_1234567");
        driver.findElement(By.cssSelector("[data-testid=\"submit\"]")).click(); */

        // TodoPage todoobject = new TodoPage(driver);
        /* Boolean isDisplayedWELCOME = todoobject.IswelcomeMesgDidplayed();*/
/* w elli louta nfas5ouha
        Boolean isDisplayedWELCOME = driver.findElement(By.cssSelector("[data-testid=\"welcome\"]")).isDisplayed(); // si welcome message displayed , traj3elna true
        //cet element mawjoud bel todopage , ya3ni fil todo page nebniw methode traj3elna si displayed ou non et puisque 3anna basepage , nemchiw lel Todopage w na3mlou extends lel basepage . w betbi3a ba3d bech nzidou el constructorChild , puis el findelement puis la methode n3arfouha elli bech trajja3li dispolayed ou non "IswelcomeMesgDidplayed"
        */
        Assert.assertTrue(isDisplayedWELCOME);
        //driver.quit();

        // je cherche le trme de webdriver et chromedriver

    }
}

// testNG ki yji ylawej ylawej 3la testcases bel classe : la classe de testcases  lezm toufa b Test : todoTest/signupTest ..
// le testNG pour build tetscases
//allure serve allure-results : pour transformer les fichier json en html sousforme de report
//allure --version
//mvn clean test
//mvn test -Dtest=LoginTest
//mvn clean test -Dbrowser=FIREFOX



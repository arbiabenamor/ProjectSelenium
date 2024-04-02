package com.qacart.todo.Base;

import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.utils.CookieUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// on peut ajouter any methode qu'on veut de telle facon tkoun available bel les tests cases el kol
// la base de tous les tests cases mte3na
// fi kol tests cases on utilisent melloul initiialiaze w fi lkher on utilise quit , donc on travaille avec before et aftermethode
public class BaseTest {

    // private WebDriver driver ; // on definit un variable de webdriver et le best  practises c'est de laisser la variable private de telle facon q'on peut faire l'encapsulation w ken 7abbina nesta3mlouha fi blasa okhra nbadlou l'acces
    //protected  WebDriver driver ;// radineha protected 5ater lokhrin mouch ychoufou fih
    protected ThreadLocal <WebDriver> driver = new ThreadLocal<>() ; // bnina object mel threadLocal w nemchiw na3mlou 2 methodes set lel objet edheka elli de type threadlocal, w ba3d get lel valeur elli a7na 3malnelha set
    // on definit methode set

    public void setDriver(WebDriver driver ) // te5ou webdriver el methode hedhi : bech ta3ml set mel valeur driver elli bech na3mloulou intiliasation  lel objet driver elli de type thredlocal
    {this.driver.set(driver);
    }
    public /*void*/ WebDriver getDriver() // l methode hedhi trajja3li la valeur de l'objet de type threadlocal elli setina fiha driver de type webdriver intialisz , puis najmou nawliw on l'utilise fi ay blasa n7ebou 3liha au lieu de mettre "driver.quit" fil aftermethod , on utilise getDriver
    {return this.driver.get();}

    @BeforeMethod
    public void setup () {
        WebDriver driver = new DriverFactory().initializeDriver(); // driver walla de type threadlocal et pas webdriverdonc c'est pourcela bech na3mlou set w get lel valeur , l'objet  edhika el de type threadlocal
        //lenna ba3d ma ktebna le methode set , jina 3arrefna local driver au niveeau de methode setup w edheka elli bech nsettih fil objet driver de type thread
        setDriver(driver); // b3athna el driver elli a7na 3malnelha intialisation
    } // puis nemchi na3mel methode get

    @AfterMethod
    public void teardown (ITestResult result) { // wa9telli ay testcase ta3ml run w tji 3al aftermethode , najmou njibou esm el testcase elli 3amlet call lel after methode, el ITestResult tkoun 3andna access 3leha ken bel aftermethod , ma tkounch 3andna 3liha access bl methode Scrrenshot
        String testCaseName= result.getMethod().getMethodName();// trakjja3li esm testcase elli 3amlet trigger lel teardown
        File destFile = new File("target" + File.separator + "Screenshots" + File.separator + testCaseName+".png" ); // File.separator wa7adha selon loperatoe system ta3ref chta5dem
        takeScreenshot(destFile); // on utilise la methode takeScreenshot w nab3thoulha el file heka // destFile : l'emplaceent et le nom dynamique

        //driver().quit();
        // wallet la valeur de driver nal9awha tawa fili bech traj3ou methode getDriver

        //lenna bech nzidou el screenshot
        //  File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); //5oudhliscreenshot w 7otheli fi typeFile
//File classe mel java , OutputType.FILE zeda hiya classe
        //  try {
        //    FileUtils.copyFile(file,new File("screenshots/image1.png")); // catch jet ml surround with try catch
        //}

        // catch (IOException e) {
        //   throw new RuntimeException(e);
        // }
        getDriver().quit();
    }

    @Step
    // a7na au dedans de test case bech na3mlou request: APIRegister w ba3d bech yabda 3anna acces 3al cookies
    public void InjectCookieToBrowser(List<Cookie> ListofCookiesRestassured) // bech nesta9blou ml testcase liste of testassured
    {
        // ba3d ma esta9belna el liste de cookies RestAssured , on utilise l methode CookieUtils bech n7awlouha lel selinuimcookies
        List<org.openqa.selenium.Cookie> listOfCookieSelinium = CookieUtils.convertRestAssuredCookiesToSelinuimCookies(ListofCookiesRestassured);

//tawa na3mlou loup 3la liste de sliumcookies w n'ajoutiw 3al browser '

        for (org.openqa.selenium.Cookie i : listOfCookieSelinium) {
            // driver.manage().addCookie(i);// on inject bel wa7da bel wa7da
            getDriver().manage().addCookie(i);


        }
// voila ! cette methode najmou on l'utilise dans ay testcase
        // w nemchiw lel dummyw nfas5ou elli ktebneh el kol , ma 3ach 7ajetna biha
    }

    public void takeScreenshot(File destFile){

        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            // FileUtils.copyFile(file,new File("screenshots/image1.png"));
            FileUtils.copyFile(file,(destFile)); // destFile : l'emplaceent et le nom :1/ ne5dhou snapshot
            InputStream is = new FileInputStream(destFile);//2/ on la convertit en inputstream
            Allure.addAttachment("screen",is);//3/ w n7otouha lel methode hedhi ==> pour attacher image
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
// si on utilise TestNG surement on va utiliser threalocal 5ater kol test case mawjouda b la meme classe , bech ykoun 3anhom nafs el instance (driver) mta3 l classe => donc juste on modifie une classe BaseTest, driver en threadlocal driver , et on utilise des methodes get ety set
// on installe allure report :bech na3mlou report mieux ordonné w nhabtou dependency bech na3mlou generate lel allure m3a testNG
package com.qacart.todo.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

// le but de cette classe_driver howa bech nebniw les drivers mte3na fil classe hedhi
public class DriverFactory {
    //private WebDriver driver ; // on definit un variable de webdriver et le best  practises c'est de laisser la variable private de telle facon q'on peut faire l'encapsulation w ken 7abbina nesta3mlouha fi blasa okhra nbadlou l'acces
// 3arefneh privé 5ater najmou nesta3mlouh fi methode okhra louta

    public WebDriver initializeDriver () //3malt methode ta3mel initialisation lel driver
    { //  de cette facon : driver = new ChromeDriver();  on a utilisé le driver elli 3arfneh el fou9 private , ou



        //tawa on va utiliser envirenment variable avec maven bel commande : >mvn clean test -Dbrowsser=FIREFOX

        String browser = System.getProperty("browser","CHROME");// (nom de variable elli 7attienh, valeur pardefaut ken mab3athna chay)
        WebDriver driver;

        switch (browser){
            case "CHROME" :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "FIREFOX" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "EDGE" :
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("the browser is not supported");// ken we7d kteb 7aja okhra a part edhoukom 3 n9ouloulou raw not supported


        }

        /* na77ineha 5ater WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // lenna na3mlou fih ken 3la l chrome */

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver ;
    }
//donc cette methode tebni driver selon ces conditions elli 7attithomlha w ba3d trajja3li ce driver

    //n7ebou nchoufou kifch l'application mte3na dynamique najmou na3mloulha run 3la ay environnement, dans le monde réel ykoun 3anna aktrher mn environnement: local, stating, acceptance, production...w a7na n7rbou nchoufou tari9a ennou b'le faite de changer un detail sghir najmou na3mlou run l test mte3na 3la ces environnement
//donc kol envirnnement na3mloulha password w url , email selon l user w kol enviroonement 3andou file spécifique lih , w ba3d selon condition bech nchoufou men ema file na9raw
// w n5aznou fil properities file
}

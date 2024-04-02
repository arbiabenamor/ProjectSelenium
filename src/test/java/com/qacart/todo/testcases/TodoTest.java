package com.qacart.todo.testcases;

import com.qacart.todo.Base.BaseTest;
import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TasksApi;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.pages.NewTOdoPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.utils.ConfigUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
// les 2 tests casess  shares ml classe BaseTest , yet9asmou el driver elli fil classe BaseTest : donc wa9t testcase loula tabda execution , tji thenya w ta3ml initialise donc la 1er valeur mta3 l browser va etre supprimé
//=> donc bel testNG bech n7awlou n7elou l probléme hedha w n5aliw kol test case 3adnha el separate intance mn hal driver en utilisant laclasse thread_locals : ya3ni bech n5aliw driver ykoun unique lkol thread (ouverture de chrome)
@Feature("Todo Feature")
public class TodoTest extends BaseTest { // kol ma nzidou classe jdida l ay testcase jdida owel 7aja na3mlouha inheritance lel clase BaseTest
    @Story("Add Todo")
    //  private WebDriver driver; // hedha 3arefneh bech njibouh mel classe factory et de best practise n7otoou private
    // na3mlou inheritance lel classe lkolha on hedhi :  public class LoginTest extends BaseTest w hakka ykoun 3anna acces 3al les methodes lkol de cette classe puisque houma public w el parivate ywalii protected
    @Test(description = "should be able to add a new todo correctly ")
    public void ShouldBeabletoAddnewTOdo () // le nom de cas de test
    {
        //  driver = new DriverFactory().initializeDriver();
        //driver.get("https://todo.qacart.com/");

//==> API steps
        RegisterApi registerApi = new RegisterApi();// 3malna objet , 5ater bech na3mlou APIrequest owel 7aja
        registerApi.register(); // 3malna l API call , ki tzedt el cookies 3al browser ya3ni wallina logged in

        // TodoPage todoPage = new TodoPage(driver);
        // todoPage.load(); // cette 1er visite bech nal9aw rwe7na fil loginPage
        //2eme : ya3mel inject lel cookies
        //puis 2eme load nal9aw rwe7na fil todopage
//a7na 3malna login en utilisant API et pas UI (userinterface)

        // on peut aussi l'utiliser avec newtodopage
        //NewTOdoPage newTOdoPage= new NewTOdoPage(driver); : badelneha ba3d ma wallina on utilise threadlocal
        NewTOdoPage newTOdoPage= new NewTOdoPage(getDriver());
        newTOdoPage.load();
//registerApi.getRestAssuredCookies(); // cette ligne najmou on l'utilise fil base test : hiya liste de cookies RestAssured , 7attineha felli ta7t
        InjectCookieToBrowser(registerApi.getRestAssuredCookies()); // on l'a utiliser directement 5ater 3amlin extends ml classe BaseTest w el methode hedhi tjoibelna kol cookies w t'ajoutiha 3al browser , l methode hedhi lezm n7otouha ken ba3d ma na3mlou load lel web site


        // hedhi ta3ml load lel url mta3 todopage https://todo.qacart.com/todo , l methode edhika ki yabda famma cookies thezna lel todo bel refraiche w ken famch cookies thezna lel login , donc lezemna load marra okhra ba3dl'inject
//==> UI steps
        // String actualResul=  todoPage
        String actualResul=  newTOdoPage

                .load()// 2eme refaiche ==> lenna bdina ml todopage w fi 2eme cas bdina ml newtodopage , load mouch UI , hiya juste visite
                //.ClickOnButtonAdd() : hedhi na7ineha 5aterna deja fil newtodo page ma3anech el bouton+ edhika
                .SendNewtodo("Learn Selenuim")
                .ReturnActualText();

// w hakka ma3ach ne3tamdou 3la loginapplication state , ya3ni ma ynajjm ya3ml todo ken ki yaa3ml login
        //donc bel ordre haw chsar : l'marra loula t7allet el loginpage  , donc ki 3mal refraiche l9a  el cookies mta3 elli sarlou enregistrement donc howa logeed bih 3ibara donc l9a rou7ou fil todopage  w 9all good evening ozelle w ajouti todo , donc ajpouta , hazni lel new todo w 5arejli elli todo elli zedtha
// donc le but c'est que les pages sont independandts
        // n7awlou dima kol test cases ykoun fih max 3 UI step beh tkoun best practise(3click, 3 type,...) + 1 assertion
        //chaque UI action hiya chance lel flukiness (difficile la détermination de la cause exacte des résultats inattendus) : ya3ni kol UI element , kol ma nesta3mlou driver.findelement.Click: 5ater el click edhika bech ta3ml request ml code mte3na lel driver w driver bech ya3ml API request lel browser
        // c'est pourcela que la chance de flukiness tkoun augmenté bel UI step
        // donc c'est meiux de reduire le max des UI steps et les remplacer par API
// ce trique le faite que nesta3mel el cookies elli raja3heli , nasta3mlouha fil kol les sites





        /*ma 3ach 7ajetna bech nabdew bel login page najmou nabdew bel todopage 5ater ba3d l'ajout de cookies wallina comme si loged in w najmou nkounou direct fil todopage


        LoginPage logginPage = new LoginPage(driver); // lenna 3arefna lobjet elli tebe3 login page
        String actualResul=
                logginPage
                        .load()
                        // =>3malna load
                        .loggin(ConfigUtils.getInstance().getEmail(),ConfigUtils.getInstance().getPassword())
                        //=> lenna bech na3mlou login , w najmou ne5tasrouha el ligne hedhi 5ater mouch lezm nabdew bel login , ba3d l'enregistrement de cookie bech nal9aw rwe7na deja fil Todopage,
                        //=> donc nemchiw n3adlou fil TodoPage w nzidou methode de laod
                        .ClickOnButtonAdd()
                        .SendNewtodo("Learn Selenuim")
                        .ReturnActualText(); // wel ClickOnButtonAdd() traj3elna Newtodopage
        */

        //==> w nfas5ou elli louta
        // TodoPage clickadd=  logginPage.loggin("arbiabenamor111@gmail.com", "projet_1234567"); // l methode hedhi najmou n5aliwha traj3elna todopage menghir ma no93dou n3awdou n3arfou fiha louta twalli hiya l resultat mta3 ligne hedhi


        // TodoPage clickadd =new TodoPage(driver); // lenna 3arefna lobjet elli tebe3  todopage
        //fasa5neha ba3d el pattern:
        // NewTOdoPage SendNew=  clickadd.ClickOnButtonAdd(); // w nfas5ou elli ta7tha , // l methode hedhi najmou n5aliwha traj3elna newtodopage menghir ma no93dou n3awdou n3arfou fiha louta twalli hiya l resultat mta3 ligne hedhi
        // w mm chose lel liason m3a newtodopage
        //driver.findElement(By.cssSelector("[data-testid=\"add\"]")).click(); // cette action mawjouda fil todo page donc nzidouha fil todopage w na3mlou methode ta3ml click
//nwaliw nfas5ou elli louta
        // NewTOdoPage SendNew = new NewTOdoPage(driver); // lenna 3arefna lobjet elli tebe3  newtodopage
        //fassa5neha ba3d el pattern
        //  clickadd=  SendNew.SendNewtodo("Learn Selenuim"); // w nfas5ou elli louta
//==>l.methode hedhi ki na3mlou add new task traja3na lel todopage ba3d ma nzidou w neclikiw
        //  driver.findElement(By.cssSelector("[data-testid=\"new-todo\"]")).sendKeys("Learn Selenuim");//mawjouda bel new todopage
        //  driver.findElement(By.cssSelector("[data-testid=\"submit-newTask\"]")).click(); //mawjouda bel new todopage

        //  TodoPage Actualresl = new TodoPage(driver);
        // String actualResul = Actualresl.ReturnActualText(); // w nfas5ou elli ta7tha

        // String actualResul = driver.findElement(By.cssSelector("[data-testid=\"todo-item\"]")).getText(); //mawjouda bel todopage
        Assert.assertEquals(actualResul,"Learn Selenuim");
        //  driver.quit();




    }
    @Story("Delete Todo")
    @Test (description = "soud be able to delete a todo correctly") // 5ater depend de user state kent false
    public void ShouldBeabletodeleteTOdo ()
    {
        //driver = new DriverFactory().initializeDriver();

        // driver.get("https://todo.qacart.com/");
        RegisterApi registerApi = new RegisterApi();
        registerApi.register(); // register hedhi bech traj3elna token w elli bech nesta3mlouh fil TaskAPI
        TasksApi tasksApi = new TasksApi();
        tasksApi.addTask(registerApi.getToken());// el token elli test7a9ou tasksAPI yjo mel methode registerApi.getToken() w ba3d il ajoute task en background elli bech nfaskhouha
        //=> w nemchiw na7wi el .SendNewtodo elli louta 5ater el delete fonctionalyty mawjouda fil todopage ,ki nfaskhou wa7da ml todos
        //==> w nabdew ml todopage
        //TodoPage todoPage=new TodoPage(driver);
        TodoPage todoPage=new TodoPage(getDriver());
        //  NewTOdoPage newTOdoPage= new NewTOdoPage(driver);
        // newTOdoPage.load();
        todoPage.load();
        InjectCookieToBrowser(registerApi.getRestAssuredCookies());
        //LoginPage logginPage = new LoginPage(driver);

        // Boolean NotodosIsdisplayed  = logginPage
        Boolean NotodosIsdisplayed  = todoPage
                .load() // lamrraloula ba3d register bech tet7al page login , na3mlou refaiche tet7al page el todow fiha item , ya3ml delete  w khw
                // .loggin(ConfigUtils.getInstance().getEmail(),ConfigUtils.getInstance().getPassword())
                //l ClickOnButtonAdd() hedhi nesta3mlouha en utilisant el UI w a7na n7ebou on l'utilise en utilisant API w n5aliw test case mte3na tabda ml DeleteTo => donc
                // .ClickOnButtonAdd() // donc najmou na3mlouha en utilisant el API
                //.SendNewtodo("Learn java")
                .DLETETODO() // ml load direct najem na3ml delete mouch bedharoura na3ml addTask
                .MSGofNOTOdoavailable();// la methode DLETETODO() matrajja3 chay 5aterha void fil todopage donc n7otouha trajja3


        // fasa5neha ba3d ell buildpattern TodoPage clickadd = logginPage.loggin("arbiabenamor111@gmail.com", "projet_1234567");

        // TodoPage clickadd =new TodoPage(driver); :na77ineha 5ater 7attineha fil rjou3 mta3 lmethode  logginPage.loggin();
        // NewTOdoPage SendNew=  clickadd.ClickOnButtonAdd(); // w nfas5ou elli ta7tha

        // driver.findElement(By.cssSelector("[data-testid=\"add\"]")).click();

        //NewTOdoPage SendNew = new NewTOdoPage(driver); : na77ineha 5ater 7attineha fil rjou3 mta3 lmethode  clickadd.ClickOnButtonAdd();
        //   clickadd= SendNew.SendNewtodo("Learn java");// w nfas5ou li ta7t


        // driver.findElement(By.cssSelector("[data-testid=\"new-todo\"]")).sendKeys("Learn Selenuim"); // hedha fi new todopage

        // driver.findElement(By.cssSelector("[data-testid=\"submit-newTask\"]")).click(); // hedha fi new todopage

        //fasse5neha ba3d el build pattern: TodoPage deleteclick = new TodoPage(driver);
        //fasse5neha ba3d el build pattern:  deleteclick.DLETETODO(); // w nfas5ou elli ta7tha


        //  driver.findElement(By.cssSelector("[data-testid=\"delete\"]")).click();// //mawjouda bel new todopage
        //fasse5neha ba3d el build pattern:
//TodoPage MSGofavailability = new TodoPage(driver);
        //fasse5neha ba3d el build pattern:Boolean NotodosIsdisplayed = MSGofavailability.MSGofNOTOdoavailable(); // w nfas5ou elli ta7tha

        //Boolean NotodosIsdisplayed = driver.findElement(By.cssSelector("[data-testid=\"no-todos\"]")).isDisplayed(); // mawjouda bel todo
        Assert.assertTrue(NotodosIsdisplayed);
        // driver.quit();

    }
    // en automation framework on doit pas répéter le code dans les cas de tests
}
// l but mel liason enou n3arfou page wa7da khw w ba3d ay page e7na on se deplace leha during le testtet3arref mn 5ilel el pade nafsha mouch mn 5ilel ta3rifna leha fil testcase
// ==> donc le but de build pattern c'est de faire chaine mn methode l methode okhra
//le disavantage de buildpattern ennou ki tabda fonctionnalites ma temchich te5reb l fonctionnalites lokhra elli m3aha
// test cases te3tamed 3al aplication state : @Test (enabled = false) ya3ni na3ml mute lel fonctionnalités w ne5dem lokhra 3adi
//test case zeda te3tamed 3al user state : exemple el delete lezem user 3andou tache wa7da si non n'affiche pas le msg mta3 no available delete : MSGofNOTOdoavailable() methode hedhi ma temchich
//et ça c'est pas de best practises ennou te3tamed 3al application state et user state
//on va voir avec le maven comment on fais le run des tests cases
//sur le plan pratique l'execution des tests cases se fait par communication avec le cloud en utilisant command lines w bech nchoufou kifch n5aliw les framework mte3na dynamique w ta3mel run 3al akther mn browsers en utilisant commands lines (maven)
//en general maven ki yji ya3mel exécute lel tests cases ylawj 3al les files elli youféw bel "Test" w mawjoudin fil Testfolder
// donc en lancant la commande "mvn clean test" : tlancili les 2 tests cases fil les differents classes : loginTest et TodoTest
// qd on utilise maven , on a l'acces à environements variables et system variables w najmou nab3thou variables ml command lines lel application mte3na ==> l framwork mte3na twalli dynamique aktheer
// a7na najmou nab3thou l browser elli n7ebou 3lih en utilisant el command line w norbtouh bel framwork mte3na w ba3d na3mlou exécution 3al browser elli a7na nab3thouh

///nansech bech ntayech el t chat gpt w hwa yrésumili chnowa ya3ml el code w el architecture
//mvn clean test -Denv=PRODUCTION -Dbrowser=FIREFOX : hedhi ntayecha te5demli bel production w elli te5dem 3lih howa firfox

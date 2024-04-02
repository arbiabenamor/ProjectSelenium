package com.qacart.todo.api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.objects.User;
import com.qacart.todo.utils.ConfigUtils;
import com.qacart.todo.utils.UserUtils;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterApi {
    // 3mana methode n7eb à partir d'elle na3ml el post request registration
// ay 7aja nest7a9ha ml API , mel response  hedhika n3arref private menha au niveau de classe  kima cookies parexemple
    // ba3d ma tjina el reponse l valeur mta3 ces variables bech n7otha fi hedhoukom elli 3arefthom private
    private static /*Cookies*/ List<Cookie> RestAssuredCookies ; // badelneha liste traja3
    private static String accessToken ;
    //=> puisque sont private , na3mlou methode get
// yrajja3 zeda el userID w el first name donc na3mlouhom private w na3mlouhom des methodes get bech ykoun 3anna access 3lehom

    private static String userId ;
    private static String firstName ;

    public String getToken (){ // cette methode trajja3li el acess token elli private , njibou el token ba3d ma ya3lelha set ml api call
        return  this.accessToken;
    }

    public String getuserId (){
        return  this.userId;
    }
    public String getfirstName (){
        return  this.firstName;
    }

    public /*Cookies*/ List<Cookie> getRestAssuredCookies () {
        return this.RestAssuredCookies;
    }

//w hedha lkol bech ye9leb kol chay fil dummy elli ken lkol bech nfas5ouh
//w hedha lkol bech ye9leb kol chay fil dummy elli ken lkol bech nfas5ouh

    public void register (){

     /* ba3d el random twalli hakka :
       User user = new User("Arbia","BEN AMOR","arbiabenamor88@gmail.com","projet1200"); */
        User user =UserUtils.generateRandomUser(); // sta3melneha direct 5ater 7attinha static , si non n5aliwha bel new w khw
        Response response =
                given()
                        // .baseUri("https://todo.qacart.com")
                        .baseUri(ConfigUtils.getInstance().getBaseUrl())

                        .header("Content-Type","application/json")
                        .body(user)
                        .log().all() // bech y5arjelna el request el kol
                        .when()
                        //  .post("/api/v1/users/register") // hard coded =>donc twalli hakka
                        .post(EndPoint.REGISTER_ENDPOINT)
                        .then()
                        .log().all() // bech y5arjelna el reponse el kol
                        .extract().response();
        if(response.statusCode()!=201)
        {
            throw  new RuntimeException("somthing is wronf with the request");
        }
        // hedha kol elli nest7a9ou lel API registration
        // lenna bech n7ebou n5aznou el cookies elli raj3etha ba3d ma 3malna el resitration , ki nchoufou fil console win l'application bech nal9aw l cookies fiha chnouma elli tzedou
        // elli houma user id /firstname/accesstoken , donc n7ebou na3mlou extract lel cookies edhika w n5aznouha fi 7aja esmha cookies
        RestAssuredCookies = response.getDetailedCookies().asList(); // el cookies hedhi elli raj3etlna hiya de classe Cookies mel restAssuerd , ya3ni mouch edhika elli bech n7otouha fil browser, donc on definit cette methode en Dummy
        accessToken = response.path("access_token"); // 3malnelha extract 5ater bech nest7a9ouha
        userId = response.path("userID");
        firstName = response.path("firstName");


    }
}

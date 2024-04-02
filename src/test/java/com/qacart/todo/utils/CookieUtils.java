package com.qacart.todo.utils;

import io.restassured.http.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    public static /*void*/ List<org.openqa.selenium.Cookie> convertRestAssuredCookiesToSelinuimCookies(List<Cookie> restAssuredcookies) // traj3elna liste
    { // n7eb nrajja liste de selinuim cookies bel fonction hedhi
        List<org.openqa.selenium.Cookie> ListofcookiesSelinuim=new ArrayList<>(); // arraylist fonction ml java
        for (io.restassured.http.Cookie i : restAssuredcookies) {
            org.openqa.selenium.Cookie Selinuimecookie = new org.openqa.selenium.Cookie(i.getName(), i.getValue());

            ListofcookiesSelinuim.add(Selinuimecookie); // a chaque fois on convert on ajoute a la liste de ListofcookiesSelinuim elli bech nraj3ouha
        }
        return ListofcookiesSelinuim ; //lista bech tarja3li ba3d ma t3abbet
    }
}
// on a fait API-call en utilisant RestAssured lel End point spécifique w raj3etlna 4 cookies par exemple
// => lenna ma 3annech access 3la driver bech n'ajoutiw lel browser : 5ater c'est pas ni testcase ni page
// driver 3anna available seulement en page et testcase
// a7na on veut faire injecter le cookie men de5el el testcase mouch elpage , 5ater au dedans de test case a7na bech na3mlou APIcall, puis bech tjina testRestAssuredcookies w fi west e l test case a7na bech na3mlou inject lel selinuim cookies 3al browser
// donc a7sen blasa n7otou feha methode enou na3mlou inject lel cookie fil browser hiya baseTest bech tnajm tkoun available b kol les tests cases

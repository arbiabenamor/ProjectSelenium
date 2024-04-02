package com.qacart.todo.api;
// n7otou fiha kol el API elli en relation bel task
//fil newtodo ki na3mlou addnewitem , tetb3ath token_barear mta3 user fil header bech n9olou autorise cet user bech y'ajouti task
//w fil payload tetb3ath el iscompleted w el item
// kima sba9 w 9olna si n7ebou nab3thou body m3a request mte3na , na3mlou pjo classe nab3thou fih ces deux iscompleted w el item

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.objects.Task;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TasksApi {

    public void addTask(String BrearToken) //fonction elli t'ajoutilna task , el token elli ba3yhneha 5ater ba3d el rigistration , nab3thou token m3a askAPI
    {
        Task task=new Task("Learn Selenium",false);// hard coded
        // et on utilise tawa el RestAssured : Given, when,then

        Response response= given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .header("Content-Type","application/json")
                .body(task)
                .auth().oauth2(BrearToken)// hethi testa9bel la valeur de token : tebnilna requestHeader "Authorization" w t'ajouti kelmet Bareer espace w t7ot la valeur de token elli a7na 7ajetna biha w tzidha lel request mte3na
                .when()
                //.post("/api/v1/tasks")  //el endpoint hedhi jebneha ml Url fil header
                .post(EndPoint.API_TASK_ENDPOINT)
                .then()
                .log().all().extract().response();// y5arejli kol chay bel terminal


        if (response.statusCode()!=201){
            throw new RuntimeException("somthing whent wrong in adding the todo ");
        }

    }

}

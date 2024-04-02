package com.qacart.todo.utils;

import java.util.Properties;

//son role ta9ra ml propertiesUtils w trajja3li
public class ConfigUtils {

    private  Properties properties; // 3arrefna variable de type Properties w 7attina private 5ater mel best practise mte3na

    //1erment : bech na3mlou constructor ya3ni mn nafs esm l classe configUtils
// behi a7na l valeur mte3 l config =el proprteis sont stables donc na3mlou load marra bark w ba3d nesta3mlouha b kol les tests cases donc n7otouha private le constructeur configUtils 5ater n7ebha 3la niveau l classe khw , ken n5aliwha public ya3ni ay we7d ynajjem yaccedi lel load ema ena n7eb na9raha marra wa7da khw  w n5azenha bel variable (object mta3 l constructeur ConfigUtils())
    // donc 5allina n3arfou object mel constructeur=classe ConfigUtils(): w ba3dha nemchiw n3arfou methode getinstance
    private static ConfigUtils configUtils ; // object mel classe constructor ConfigUtils()
    /*public*/ private  ConfigUtils() // constructeur
    {
        String env= System.getProperty("env","PRODUCTION"); // ki n7ebou na9raw 7aja be3thinha bel maven_commande : mvn clean test -Denv=PRODUCTION
        switch (env) {
            case "PRODUCTION":
                properties = PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/production.properties");
                break;
            case "LOCAL":
                properties = PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/local.properties");

                break;

            default:
                throw new RuntimeException("environement is not supported");
        }


        // ==> w hakka 5allina framwork mte3na dynamique
/*
        // n7eb owel ma ya3mel instance mel constructeur edheka , n7ebou ya9ra properties l varibale elli 3malneha
        properties=PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/production.properties");
        //ya3ni owel ma ya3ml instance , ya9ra l file mte3na w y5azenha fi properties*/
    }
    public /*void*/ static ConfigUtils getInstance() { // de telle facon ena najem ken mel classe constructor [private ConfigUtils() ] najmou n5allih ya9ra [ properties=PropertiesUtils.loadProperties( ..]w 7attitha static bech no93odch kol marra na3ml [conf = new ConfigUtils()]
// getinstance traj3elna objet mn type [private static ConfigUtils conf ;]
        // on s'assurer idha ken el objet [private ConfigUtils conf ] elli a7na 3malneh 3rrafneha 9bal wala le
        // System.out.println("Valeur de env : "+System.getProperty("env"));
        if (configUtils == null)
        {
            configUtils = new ConfigUtils(); // ya3ni idha kent null a9ra [PropertiesUtils.loadProperties]
        }
        return configUtils ; // ema idha kent 9imetha mouch null rajja3li l conf elli 3arrefneha deja l fou9 elli heya objet ml constructor

    }
    // en utilisabnt getinstance() je vais verifier si ena m3arfetha 9bal wla le

    //w n3arfou lenna les methodes elli 9olna 3lihom email , password , basdeurl
    public /*void*/ String getBaseUrl() {
        //w najem n5alliha static bech najem nesta3ml l methode getbaseUrl () directement
        String prop = properties.getProperty("baseUrl"); // 5azentha bl prop 5ater bch na3ml check
        if (prop != null) return prop; // ken mouch null rajja3li prop
        throw new RuntimeException("baseurl not found in the property file ");// else
    }
    // lel email
    public /*void*/ String getEmail()
    {
        String prop= properties.getProperty("email");
        if (prop != null)
            return prop;
        throw new RuntimeException("email not found in the property file ");
    }

//lel password

    public /*void*/ String getPassword()
    {
        String prop= properties.getProperty("password");
        if (prop != null)
            return prop;
        throw new RuntimeException("password not found in the property file ");
    }



}
// tawa bech nchoufou m3a TestAssured pour ressoudre le probleme de application et user state : on ressoudre ce probléme avec APIs
//==> donc le but bech n5aliw les tests cases mte3na ne sont pas dependants
//==> pour utiloiser API on utilise library de testAssured : en utilisant TestAssured najmou na3mlou ay API  reqeuest n7ebou 3lih
//nemchiw nchoufou chneya el API elli na3mlouha ki na3mlou register : donc ispecter , network: wghadi nchoufou ay 7aja tatla3 w tokhroj ml site , w xhr calls , hiya network call : nchouf beha kol API calss  elli yatl3ou ml application mlte3na w chnowa el reponse elli tji ml serveur
// ba3d ma na3mlou el signaup bech nchoufou elli APIcall tatala3 : yo5rjou 2 API calls : wa7da esmha register , w lokhra esmha tasks
//fil register: nal9aw el url (https://todo.qacart.com/api/v1/users/register) elli bhowa baseUri(https://todo.qacart.com) el endpoint(/api/v1/users/register) w el response w request POST wel content type (type de request w response : type json), 3cookies(access_token,userID,firstName)
// w ki 3malna POSTrequest b3athna body
//el response elli jena : access token
//Appercu , preview : tratbelna el response mte3na : accestoken/firstname/useiID
//==> donc hedha bech na3melou automatisation bel Postman w TestAssured
//=>b3athna url bl postman w methode POST w fil body les donnés w b3athneha el APIcall :  rajja3li fil body elli ritou fil preview :accestoken/firstname/useiID
//=> bech nchoufou tawa hedha kifch na3mlouh en utilisant RestAssured
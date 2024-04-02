package com.qacart.todo.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {

    //na3mlou methode role mte3ha enha ta3ml load lel properties w yrajja3heli
    public /*void*/ static Properties loadProperties(String filePath) // ki 7attina static ya3nitkoun 3al classe level mouch lezm na3mlou instance menha fi blasa okhra w nwalliw nesta3mlouha direct
    { // cette methode traj3elna de type classe "Properties"
        File file = new File(filePath); // a9ra el file elli a7na nab3thouhelk , puisque 3anna abrcha filet

        try {
            InputStream inputStream = new FileInputStream(file);// bel exception n7ot try catsh 5atr n7ebou yrajja3li erreur ena n7eb nektbou
            // n3arfou el classe properties
            Properties properties = new Properties();
            properties.load(inputStream);// w load hedhi na3mloulha exeption catch bech ken ma njmch ya3ml load lel properties wala mal9achou y5arejli msg edhekqa
            //9bal return kamlna bl inputStream nsakrouha

            inputStream.close();  // w n7ot return 5ater n7eb yrajja3heli el properties
            return properties; // nbadlou fi blaset el vois twalli classe Prperties
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file is not found");
        } catch (IOException e) {
            throw new RuntimeException("error while loading the properties");        }
    }
}

// w ba3d bech nchoufou kifch bech nesta3mlouha fil test cases mte3na




   /*

    //1er nzidou main bech najmou na3mmlou exécution
    public static void main(String[] args) throws IOException {
// fil java famma classe esmou file
        File file = new File("src/test/java/com/qacart/todo/config/production.properties"); // 3arrefna objet esmou file mel classe File w n7otou fi westpu el path content root mta3 el file
//walla 3anna el file mte3na fil variable "file"
// wbech na9raw ml file bel java bel inpustream
        InputStream inputStream = new FileInputStream(file); // houni 3arrafna objet inputsteram bech na9raw ml file w nzidou l'exeption : throws FileNotFoundException , bech ken ma l9ach el file wala el path mte3na ghalet ma te9fch l'application
// el java 3andou classe spécifique bech ya9ra mel properties : "Properties"

        Properties properties = new Properties(); //3arrefna object ml classe Properties
properties.load(inputStream); // parmi les methodes de cette classe : load
        // w hakka walaet l'objet properties 3andha acces 3la ay properties mawjouda bl file mte3na
        String baseurlproprty= properties.getProperty("baseUrl");// ki n7ebou nraj3ou el baseurl
        System.out.println(baseurlproprty);
    }
}
// hakka faserna kifech na9raw ml properties
// tawa hedhi el classe role mte3ha juste ta3ml load w traj3elna el properties  */
// bech nchoufou tawa kifch bech nesta3mlouha fil les tests cases
package com.qacart.todo.objects;
///à l'interieru de cette classe bech n7ot kol chay en relation avec le request
// l'idée de pojo classe  c'est : any item mawjoud bel request , na3mloulou property bel classe mte3na  w tkoun private
// POJO classe dima fih des attributes w fih des methodes getw set w fih constructor
public class User {
    //w lezm ykounou le mm nom que les item
    private String email ;
    private String firstName ;
    private String lastName ;
    private String password ;
    //constructor
    public User(String firstName,String lastName, String email, String password ) { //je vais receiilli la valeur de user à partir des tests cases
        this.firstName= firstName; // this hiya firstname mta3 l classe = first name mta3 el constructor elli jeyetna ml test case
        this.lastName= lastName;
        this.email= email;
        this.password= password;
    }
    //=> puisque sont private , lezm na3lou el getter w el,setter: ken n7eb na3mel new user bech nab3eth les items w ba3d ken nest7a9 wa7da mnhom bech nesta5dem methode lezmha tkoun public trajja3li parexemple last name
    // w ken n7eb na3mel set lel value mta3 lastname kifkif tkoun 3anna public methode de telle facon najmou na3mlou set l value
    //=> donc le but de getter et setter , enou ykoun 3anna private proporities bel getter njib el value mte3ha w el setter nab3eth
// 3malna ces deusx methodes bel generate w les roles mte3hom trajja3 el value wala tab3eth el value


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
/*fi ay POJO classe :

1er : on precise l'elements private
2mee: les getters/setters
3eme: constructor pour faire new user


 */


package com.qacart.todo.objects;
// fiha kol el item elli nab3thouha w n5aliwha private kima : Iscompleted , item...
public class Task {

    private Boolean isCompleted ;// le nom elli mab3ou howa bidou lezm
    private String item;

    // w ay POJO classe lezm ykoun 3anna constructor
    public Task (String item,Boolean isCompleted){
        this.isCompleted=isCompleted;
        this.item=item;

    }

    // on ajoute aussi Setter et getter


    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}

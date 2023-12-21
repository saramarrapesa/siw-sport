package it.uniroma3.siw.sport.Model;

public enum Role {

    PORTIERE ("portiere") , DIFENSORE ("difensore"), CENTROCAMPISTA ("centrocampista") , ATTACCANTE ("attaccante");

    private final String name;

    Role(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}



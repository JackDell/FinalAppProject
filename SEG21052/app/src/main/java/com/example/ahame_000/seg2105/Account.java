package com.example.ahame_000.seg2105;

/**
 * Created by amelie on 2017-11-23.
 */
import java.util.List;
import java.util.ArrayList;
public class Account {

    // Do we actually need an account name?...We didn't ask for one in the ui
    private String name, email, password;

    //TODO: change String to Chore
    private List<String> generalChores;

    //TODO: change String to Profile
    private List<String> profiles;

    //New account
    public Account(String name, String email, String password){

        this.name = name;
        this.email = email;
        this.password = password;
        this.generalChores = new ArrayList<String>();
        this.profiles = new ArrayList<String>();


    }
    //existing account
    public Account(String name, String email, String password, List<String> chores, List<String> profiles){
        this.name = name;
        this.email=email;
        this.password=password;
        this.generalChores = chores;
        this.profiles=profiles;

    }
    public boolean setName(String aName){
        boolean wasSet = false;
        this.name =aName;
        wasSet = true;
        return wasSet;
    }
    public boolean setPassword(String aPassword){
        boolean wasSet = false;
        this.name =aPassword;
        wasSet = true;
        return wasSet;
    }
    public boolean setEmail(String anEmail){
        boolean wasSet = false;
        this.name =anEmail;
        wasSet = true;
        return wasSet;
    }


    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public List<String> getProfiles(){
        return this.profiles;
    }
    public List<String> getGeneralChores(){
        return this.generalChores;
    }
    //TODO: change String to Chore
    public void addChore ( String chore){

        if(!this.generalChores.contains(chore)){
            this.generalChores.add(chore);
        }
    }
    //TODO: change String to Chore
    public void removeChore( String chore){
        if(!this.generalChores.isEmpty()){
            this.generalChores.remove(chore);
        }

    }


}

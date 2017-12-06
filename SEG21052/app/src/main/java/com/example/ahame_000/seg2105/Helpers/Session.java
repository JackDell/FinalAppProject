
package com.example.ahame_000.seg2105.Helpers;


import com.example.ahame_000.seg2105.DataStructures.Account;
import com.example.ahame_000.seg2105.DataStructures.Child;
import com.example.ahame_000.seg2105.DataStructures.Profile;

public class Session {
    // Session is a static class that we created to manage the current logged in account and profile
    // it allows us to reduced the amount of times that we need to access a database significantly

    private static Account loggedInAccount;
    private static Profile loggedInProfile;
    private static Child viewedChild;

    public static Child getViewedChild() { return viewedChild; }

    public static void setViewedChild(Child viewedChild) { Session.viewedChild = viewedChild; }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void setLoggedInAccount(Account loggedInAccount) {
        Session.loggedInAccount = loggedInAccount;
    }

    public static boolean loginProfile(String name, String password){
        Profile profile = loggedInAccount.getProfile(name);
        if (profile.getPassword().equals(password)){
            loggedInProfile = profile;
            return true;
        }
        return false;
    }
    public static Profile getLoggedInProfile() {
        return loggedInProfile;
    }

    public static void logoutProfile(){
        loggedInProfile = null;
    }

    public static void logoutAccount(){
        loggedInAccount = null;
    }

}


package com.example.ahame_000.seg2105;


public class Session {
    
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

    public static Profile getLoggedInProfile() {
        return loggedInProfile;
    }

    public static void setLoggedInProfile(Profile loggedInProfile) {
        Session.loggedInProfile = loggedInProfile;
    }
}

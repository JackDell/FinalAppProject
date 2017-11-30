<<<<<<< HEAD
package ca.uottawa.jackdell.choreapplication;

/**
 * Created by Jack on 2017-11-05.
 */

public class Session {

    private static Account account = null;
    private static Profile profile = null;

    /**
     * @return returns the Account of the current session.
     */
    public static Account getAccount() {
        return account;
    }

    /**
     * @param account   set the Account to the passed Account instance.
     */
    public static void setAccount(Account account) {
        Session.account = account;
    }

    /**
     * @return returns the Profile of the current session.
     */
    public static Profile getProfile() {
        return profile;
    }

    /**
     * @param profile   set the Profile to the passed Profile instance.
     */
    public static void setProfile(Profile profile) {
        Session.profile = profile;
=======
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
>>>>>>> 2052eebb79db5df0fdba4c0a8751795cb7bf0751
    }
}

package com.example.ahame_000.seg2105;

/**
 * Created by Jack on 2017-11-28.
 */

public class Session {
    
    private static Account account;
    private static Profile profile;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        Session.account = account;
    }

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        Session.profile = profile;
    }
}

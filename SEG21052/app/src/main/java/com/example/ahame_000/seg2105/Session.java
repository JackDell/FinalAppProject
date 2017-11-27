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
    }
}

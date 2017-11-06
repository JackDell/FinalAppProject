package ca.uottawa.jackdell.choreapplication;

/**
 * Created by Jack on 2017-11-05.
 */

public enum ChoreStatus {

    INCOMPLETE("Incomplete"), IN_PROGRESS("In Progress"), COMPLETE("Complete");

    private String name;

    ChoreStatus(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static ChoreStatus fromString(String s) {
        for(ChoreStatus cs : ChoreStatus.values()) {
            if(cs.toString().equalsIgnoreCase(s)) return cs;
        }
        return null;
    }

}

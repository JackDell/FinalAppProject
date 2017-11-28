package com.example.ahame_000.seg2105;

/**
 * Created by Jack on 2017-11-27.
 */

public enum ChoreState {
    INCOMPLETE("In-Complete"), IN_PROGRESS("In Progress"), COMPLETE("COMPLETE");

    private String str;

    ChoreState(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }
}

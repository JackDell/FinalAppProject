package com.example.ahame_000.seg2105;


public enum ChoreState {
    UNASSIGNED ("UNASSIGNED"), TODO("TODO"), PASTDUE("PASTDUE"), COMPLETED("COMPLETED"), DELETED("DELETED");

    private String str;

    ChoreState(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }
}

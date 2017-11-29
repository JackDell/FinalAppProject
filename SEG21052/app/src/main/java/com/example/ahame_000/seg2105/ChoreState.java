package com.example.ahame_000.seg2105;


public enum ChoreState {
    UNASSIGNED ("Unassigned"), TODO("To-Do"), PASTDUE("Past Due"), COMPLETED("Completed"), DELETED("Deleted");

    private String str;

    ChoreState(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }
}

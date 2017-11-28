package com.example.ahame_000.seg2105;

import com.example.ahame_000.seg2105.ChoreState;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 2017-11-27.
 */

public class Chore {

    // Instance variables
    private String name;
    private String description;
    private ChoreState state;
    private Date creationDate;
    private int daysToComplete;
    private int reward;

    /**
     * Constructor
     */
    public Chore(String name, String description, ChoreState state, Date creationDate, int daysToComplete, int reward) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.creationDate = creationDate;
        this.daysToComplete = daysToComplete;
        this.reward = reward;
    }

    /**
     * Compressed Constructor
     */
    public Chore(String name, String description, int daysToComplete, int reward) {
        this(name, description, ChoreState.INCOMPLETE, new Date(), daysToComplete, reward);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ChoreState getState() {
        return state;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getDaysToComplete() {
        return this.daysToComplete;
    }

    public void setDaysToComplete(int daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    /**
     * Based on the idea that an average 'chore' would have about 1 week to be completed, right now the
     * reward value is calculated with (reward - ((reward * 5%) * (num of days since creation))).
     * Essentially removing 5% of the total reward value everyday. If the completion date is after
     * the 'complete by' date the reward is 50% of the original.
     * @return
     */
    public int getReward() {
        // The date now must be before the complete by Date
        // Getting the current date
        Date currentDate = new Date();
        // Creating a calendar object
        Calendar currentCalendar = Calendar.getInstance();
        // Setting its date to only the day, month, and year
        currentCalendar.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay(), 0, 0, 0);
        currentCalendar.add(Calendar.DATE, this.daysToComplete);

        // Creating a calendar object for the completion date
        Calendar completionCalendar = Calendar.getInstance();
        completionCalendar.set(this.creationDate.getYear(), this.creationDate.getMonth(), this.creationDate.getDay(), 0, 0,0);

        // If the current date is after the completion date, return half of the reward
        if(currentCalendar.after(completionCalendar)) {
            return ((int) Math.round(this.reward * 0.5));
        }
        // Determining how long the Chore has existed for and returning proper reward
        int amountOfDays = 0;
        while(currentCalendar.before(completionCalendar)) {
            amountOfDays++;
            currentCalendar.add(Calendar.DATE, 1);
        }

        return ((int) Math.round(reward - ((reward * 0.05) * amountOfDays)));
    }

    /**
     * Takes a Date object and returns a List of Integers, the first Integer in the list is the day
     * of the month, the second object is the month, the third object is the year.
     * @param date the Date object you wish to convert
     * @return a List object containing the converted Date data
     */
    private static List<Integer> convertDate(Date date) {
        List<Integer> dateList = new ArrayList<>();
        String[] splitDate = date.toString().split(" ")[3].split(":");
        dateList.add(Integer.parseInt(splitDate[0]));
        dateList.add(Integer.parseInt(splitDate[1]));
        dateList.add(Integer.parseInt(splitDate[2]));
        return dateList;
    }
}

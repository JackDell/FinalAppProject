package ca.uottawa.jackdell.choreapplication;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Jack on 2017-11-05.
 */

public class Chore {

    private String path;
    private String name;
    private String description;
    private Date deadline;
    private ChoreStatus status;
    int reward;
    int penalty;

    public Chore(String path, String name, Date deadline, int reward, int penalty) {
        this(path, name, "", deadline, ChoreStatus.INCOMPLETE, reward, penalty);
    }

    public Chore(String path, String name, String description, Date deadline, ChoreStatus status, int reward, int penalty) {
        this.path = path;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.reward = reward;
        this.penalty = penalty;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public ChoreStatus getStatus() {
        return this.status;
    }

    public void setStatus(ChoreStatus status) {
        this.status = status;
    }

    public int getReward() {
        return this.reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getPenalty() {
        return this.penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
}

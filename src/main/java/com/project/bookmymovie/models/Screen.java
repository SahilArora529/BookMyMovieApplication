package com.project.bookmymovie.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Screen.findAllOrderedDescending",
        query = "SELECT c FROM Screen c ORDER BY c.screenId DESC")
@Component("screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id",insertable = false, updatable = false)
    private int screenId;

    @Column(name = "screen_name", nullable = false)
    private String screenName;

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenId=" + screenId +
                ", screenName='" + screenName + '\'' +
                '}';
    }

}

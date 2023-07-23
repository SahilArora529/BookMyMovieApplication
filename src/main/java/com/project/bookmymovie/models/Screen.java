package com.project.bookmymovie.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

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

    public Screen() {
    }

    public Screen(int screenId, String screenName) {
        this.screenId = screenId;
        this.screenName = screenName;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screen screen = (Screen) o;
        return screenId == screen.screenId && screenName.equals(screen.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(screenId, screenName);
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenId=" + screenId +
                ", screenName='" + screenName + '\'' +
                '}';
    }

}

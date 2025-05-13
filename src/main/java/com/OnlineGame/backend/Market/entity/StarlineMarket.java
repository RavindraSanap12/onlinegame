package com.OnlineGame.backend.Market.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;

@Entity
public class StarlineMarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String marketType;
    private String title;
    private int closeMin;
    private boolean highlight;
    private boolean showInApp;
    private boolean showInWeb;

    private boolean status;

    // Weekday flags
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;

    // Time slots from second image
    @ElementCollection
    private Set<LocalTime> timeSlots = new HashSet<>();

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCloseMin() {
        return closeMin;
    }

    public void setCloseMin(int closeMin) {
        this.closeMin = closeMin;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public boolean isShowInApp() {
        return showInApp;
    }

    public void setShowInApp(boolean showInApp) {
        this.showInApp = showInApp;
    }

    public boolean isShowInWeb() {
        return showInWeb;
    }

    public void setShowInWeb(boolean showInWeb) {
        this.showInWeb = showInWeb;
    }

    // Weekday getters/setters
    public boolean isMonday() { return monday; }
    public void setMonday(boolean monday) { this.monday = monday; }
    public boolean isTuesday() { return tuesday; }
    public void setTuesday(boolean tuesday) { this.tuesday = tuesday; }
    public boolean isWednesday() { return wednesday; }
    public void setWednesday(boolean wednesday) { this.wednesday = wednesday; }
    public boolean isThursday() { return thursday; }
    public void setThursday(boolean thursday) { this.thursday = thursday; }
    public boolean isFriday() { return friday; }
    public void setFriday(boolean friday) { this.friday = friday; }
    public boolean isSaturday() { return saturday; }
    public void setSaturday(boolean saturday) { this.saturday = saturday; }
    public boolean isSunday() { return sunday; }
    public void setSunday(boolean sunday) { this.sunday = sunday; }

    // Time slots management
    public Set<LocalTime> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(Set<LocalTime> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public void addTimeSlot(LocalTime time) {
        this.timeSlots.add(time);
    }

    public void removeTimeSlot(LocalTime time) {
        this.timeSlots.remove(time);
    }
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
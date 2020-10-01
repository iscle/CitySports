package me.iscle.elclub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Partit {
    private int id;
    private String location;
    private long date;
    private String team;
    @SerializedName("player_count")
    private int playerCount;
    private List<Player> players;

    public Partit() {

    }

    public Partit(int id, String location, long date, String team, int playerCount) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.team = team;
        this.playerCount = playerCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}

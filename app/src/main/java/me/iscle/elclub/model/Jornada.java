package me.iscle.elclub.model;

import com.google.gson.annotations.SerializedName;

public class Jornada {
    private String title;
    private String date;
    private Match[] matches;

    public Match[] getMatches() {
        return matches;
    }

    public static class Match {
        @SerializedName("player_1")
        private String player1;
        @SerializedName("player_2")
        private String player2;

        public String getPlayer1() {
            return player1;
        }

        public String getPlayer2() {
            return player2;
        }
    }
}

package com.example.nguyhuy.msl;

public class Season {

    private int episode_count;
    private String poster_path;
    private int season_number;

    public Season() {

    }

    public Season(int episode_count, String poster_path, int season_number) {
        this.episode_count = episode_count;
        this.poster_path = poster_path;
        this.season_number = season_number;
    }

    public int getEpisode_count() {
        return episode_count;
    }

    public void setEpisode_count(int episode_count) {
        this.episode_count = episode_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }
}

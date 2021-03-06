package com.example.nguyhuy.msl;

import org.json.JSONArray;

import java.util.ArrayList;

public class Serie {
    private String image;
    private String title;

    private int user_score;
    private double tmdb_score;

    private int actual_episode;
    private int max_episode;

    private int actual_season;
    private int max_season;

    private ArrayList<Season> seasons;

    public Serie () {
        this.actual_episode = 0;
        this.actual_season = 1;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_score() {
        return user_score;
    }

    public void setUser_score(int user_score) {
        this.user_score = user_score;
    }

    public double getTmdb_score() {
        return tmdb_score;
    }

    public void setTmdb_score(double tmdb_score) {
        this.tmdb_score = tmdb_score;
    }

    public int getActual_episode() {
        return actual_episode;
    }

    public void setActual_episode(int actual_episode) {
        this.actual_episode = actual_episode;
    }

    public int getMax_episode() {
        return max_episode;
    }

    public void setMax_episode(int max_episode) {
        this.max_episode = max_episode;
    }

    public int getActual_season() {
        return actual_season;
    }

    public void setActual_season(int actual_season) {
        this.actual_season = actual_season;
    }

    public int getMax_season() {
        return max_season;
    }

    public void setMax_season(int max_season) {
        this.max_season = max_season;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
}

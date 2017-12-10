package com.example.franciscoandrade.starwarssearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by franciscoandrade on 12/10/17.
 */

public class People {

    private String name;
    private String height;
    private String mass;
    @SerializedName("hair_color")
    private String hairColor;
    @SerializedName("skin_color")
    private String skinColor;
    @SerializedName("eye_color")
    private String eyeColor;
    private String genre;
    private String homeWorld;
    @SerializedName("birth_year")
    private String birthYear;

    private String[] films;

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getGenre() {
        return genre;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String[] getFilms() {
        return films;
    }
}

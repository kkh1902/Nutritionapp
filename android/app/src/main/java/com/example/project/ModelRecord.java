//서버(Record)에 보내는 데이터 모델
package com.example.project;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ModelRecord {
    private int record_id;
    private String user_id;
    private String date;
    private String food;
    private String meal_time;
    private String tan;
    private String dan;
    private String fat;
    private String sugar;
    private String salt;
    private String kcal;

    public ModelRecord(int record_id, String user_id, String date, String food, String meal_time, String tan, String protein, String fat, String sugar, String salt, String kcal) {
        this.record_id = record_id;
        this.user_id = user_id;
        this.date = date;
        this.food = food;
        this.meal_time = meal_time;
        this.tan = tan;
        this.dan = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.salt = salt;
        this.kcal = kcal;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getMeal_time() {
        return meal_time;
    }

    public void setMeal_time(String meal_time) {
        this.meal_time = meal_time;
    }

    public String getTan() {
        return tan;
    }

    public void setTan(String tan) {
        this.tan = tan;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }
}
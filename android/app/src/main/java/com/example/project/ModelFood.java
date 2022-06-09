//서버(Record)에 보내는 데이터 모델
package com.example.project;

public class ModelFood {
    private int food_id;
    private String food_name;
    private String food_once;
    private String kcal;
    private String tan;
    private String dan;
    private String fat;
    private String sugar;
    private String salt;

    public ModelFood(int food_id, String food_name, String food_once, String kcal, String tan, String dan, String fat, String sugar, String salt) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_once = food_once;
        this.kcal = kcal;
        this.tan = tan;
        this.dan = dan;
        this.fat = fat;
        this.sugar = sugar;
        this.salt = salt;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_once() {
        return food_once;
    }

    public void setFood_once(String food_once) {
        this.food_once = food_once;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
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
}
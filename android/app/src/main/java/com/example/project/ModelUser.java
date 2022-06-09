//user 받아오는 모델
package com.example.project;

public class ModelUser {
    private String user_id;
    private String gender;
    private int weight;
    private int height;
    private int age;
    private int bmr;
    private int tan2;
    private int dan2;
    private int fat2;

    public ModelUser(String user_id, String gender, int weight, int height, int age, int bmr, int tan2, int dan2, int fat2) {
        this.user_id = user_id;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmr = bmr;
        this.tan2 = tan2;
        this.dan2 = dan2;
        this.fat2 = fat2;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    public int getTan2() {
        return tan2;
    }

    public void setTan2(int tan2) {
        this.tan2 = tan2;
    }

    public int getDan2() {
        return dan2;
    }

    public void setDan2(int dan2) {
        this.dan2 = dan2;
    }

    public int getFat2() {
        return fat2;
    }

    public void setFat2(int fat2) {
        this.fat2 = fat2;
    }
}

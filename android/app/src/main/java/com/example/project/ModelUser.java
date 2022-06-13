//user 받아오는 모델
package com.example.project;

public class ModelUser {
    private String user_id;
    private String user_name;
    private String gender;
    private int weight;
    private int height;
    private int age;
    private int bmr;

    public ModelUser(String user_id, String user_name, String gender, int weight, int height, int age, int bmr) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmr = bmr;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

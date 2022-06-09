//음식 정보 받아오는 데이터 모델
package com.example.project;

public class ModelListFood {
    private String food_name;
    private String food_attribute;
    private String food_service_wt;
    private String food_kcal;
    private String food_tan;
    private String food_dan;
    private String food_ji;

    public ModelListFood(String name, String attribute, String service_wt, String kcal, String tan, String dan, String ji) {
        this.food_name = name;
        this.food_attribute = attribute;
        this.food_service_wt = service_wt;
        this.food_kcal = kcal;
        this.food_tan = tan;
        this.food_dan = dan;
        this.food_ji = ji;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_attribute() {
        return food_attribute;
    }

    public void setFood_attribute(String food_atribute) {
        this.food_attribute = food_atribute;
    }

    public String getFood_kcal() {
        return food_kcal;
    }

    public void setFood_kcal(String food_kcal) {
        this.food_kcal = food_kcal;
    }

    public String getFood_service_wt() {
        return food_service_wt;
    }

    public void setFood_service_wt(String food_service_wt) {
        this.food_service_wt = food_service_wt;
    }

    public String getFood_tan() {
        return food_tan;
    }

    public void setFood_tan(String food_tan) {
        this.food_tan = food_tan;
    }

    public String getFood_dan() {
        return food_dan;
    }

    public void setFood_dan(String food_dan) {
        this.food_dan = food_dan;
    }

    public String getFood_ji() {
        return food_ji;
    }

    public void setFood_ji(String food_ji) {
        this.food_ji = food_ji;
    }
}
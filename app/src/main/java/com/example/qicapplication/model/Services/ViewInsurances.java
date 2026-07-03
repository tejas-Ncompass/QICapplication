package com.example.qicapplication.model.Services;

public class ViewInsurances {

    String insuranceTitle, subtxt;

    public ViewInsurances(String insuranceTitle, String subtxt) {
        this.insuranceTitle = insuranceTitle;
        this.subtxt = subtxt;
    }

    public String getInsuranceTitle() {
        return insuranceTitle;
    }

    public void setInsuranceTitle(String insuranceTitle) {
        this.insuranceTitle = insuranceTitle;
    }

    public String getSubtxt() {
        return subtxt;
    }

    public void setSubtxt(String subtxt) {
        this.subtxt = subtxt;
    }
}

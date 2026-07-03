package com.example.qicapplication.model.Approval;

public class ViewItems {

    String leaveType, name, designantion, days, date, date_time,  txtColor, bgColor, status, statusColor;
    int profile, statusIcon;

    public ViewItems(String leaveType,String name,String designantion,String days,String date,String date_time,String txtColor,String bgColor,int profile, String status,int statusIcon,String statusColor)
    {
        this.leaveType=leaveType;
        this.name=name;
        this.designantion=designantion;
        this.days=days;
        this.date=date;
        this.date_time=date_time;
        this.status=status;
        this.txtColor=txtColor;
        this.bgColor=bgColor;
        this.profile=profile;
        this.statusIcon=statusIcon;
        this.statusColor= statusColor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignantion() {
        return designantion;
    }

    public void setDesignantion(String designantion) {
        this.designantion = designantion;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//
    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }
//
    public int getStatusIcon() {
        return statusIcon;
    }

    public void setStatusIcon(int statusIcon) {
        this.statusIcon = statusIcon;
    }

    public String getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(String textColor) {
        this.txtColor = textColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }
}

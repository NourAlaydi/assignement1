package com.example.assignement1;

import androidx.annotation.NonNull;

public class question {
    private String num ;
    private int points;


    public question(String num, int points) {
        this.num = num;
        this.points=points;

    }

    public int getPoints() {
        return points;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    @Override
    public String toString(){
        return num +"                                 pts: "+points;
    }
}


package com.wadimkazak.test;

import com.orm.SugarRecord;

/**
 * Created by Wadim on 10.02.2018.
 */

public class Man extends SugarRecord {
    private String countOfLimbs;
    private String name;
    private String surname;
    private String tel;
    private String pass;

    public Man() {
    }

    public Man(String countOfLimbs, String name, String surname, String tel, String pass) {
        this.countOfLimbs = countOfLimbs;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.pass = pass;
    }

    public String getCountOfLimbs() {
        return countOfLimbs;
    }

    public void setCountOfLimbs(String countOfLimbs) {
        this.countOfLimbs = countOfLimbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

package com.indra.posbackend.models;

/**
 * Created by Indrap on 19/05/2018.
 */
public class LookupItem {
    private String lookupCode;
    private String name;
    private String nameEN;
    private String value1;
    private String value2;
    private String value3;

    public LookupItem() {
    }

    public LookupItem(String lookupCode, String name, String nameEN, String value1, String value2, String value3) {

        this.lookupCode = lookupCode;
        this.name = name;
        this.nameEN = nameEN;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}

package com.example.kittisak.top20.model;

import com.univocity.parsers.annotations.Parsed;

import java.math.BigDecimal;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public class Company {

    @Parsed(field = "Code")
    private String mCode;

    @Parsed(field = "Company")
    private String mCompany;

    @Parsed(field = "Sector")
    private String mSector;

    @Parsed(field = "Market Cap")
    private String mMarketCap;

    @Parsed(field = "Weight(%)")
    private String mWeight;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getSector() {
        return mSector;
    }

    public void setSector(String sector) {
        mSector = sector;
    }

    public String getMarketCap() {
        return mMarketCap.replace(",", "");
    }

    public void setMarketCap(String marketCap) {
        mMarketCap = marketCap;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }
}

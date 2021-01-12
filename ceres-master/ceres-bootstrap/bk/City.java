package com.liuliu.ceres.bootstrap.controller;

import com.liuliu.ceres.utils.JsonUtils;

/**
 * 
 * @author liuliu
 *
 */
public class City {

    private Long id;

    private Long provinceId;

    private String cityName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static void main(String[] args) {
        City city = new City();
        city.setCityName("cityName");
        city.setDescription("description");
        city.setProvinceId(1234L);
        String jsonCity=JsonUtils.toJson(city);
        System.out.println(jsonCity);
    }
}
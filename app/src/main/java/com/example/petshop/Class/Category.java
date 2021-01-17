package com.example.petshop.Class;

import java.util.Map;

public class Category {
    private String idCategory;
    private String nameCategory;
    private String imageCategory;

    public  Category(){}

    public Category(String idCategory, String nameCategory, String imageCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.imageCategory = imageCategory;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }
}
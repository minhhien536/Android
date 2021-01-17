package com.example.petshop.Class;

public class ChildCategory {
    private String idChildCategory;
    private String childCategoryName;
    private String imgChildCategory;
    private String parentCatId;

    public ChildCategory() {
    }

    public ChildCategory(String idChildCategory, String childCategoryName, String imgChildCategory, String parentCatId) {
        this.idChildCategory = idChildCategory;
        this.childCategoryName = childCategoryName;
        this.imgChildCategory = imgChildCategory;
        this.parentCatId = parentCatId;
    }

    public String getIdChildCategory() {
        return idChildCategory;
    }

    public void setIdChildCategory(String idChildCategory) {
        this.idChildCategory = idChildCategory;
    }

    public String getChildCategoryName() {
        return childCategoryName;
    }

    public void setChildCategoryName(String childCategoryName) {
        this.childCategoryName = childCategoryName;
    }

    public String getImgChildCategory() {
        return imgChildCategory;
    }

    public void setImgChildCategory(String imgChildCategory) {
        this.imgChildCategory = imgChildCategory;
    }

    public String getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(String parentCatId) {
        this.parentCatId = parentCatId;
    }
}
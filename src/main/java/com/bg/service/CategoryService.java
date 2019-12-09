package com.bg.service;

public interface CategoryService {

    /**
     * fill data map
     */
    void fillDataMap();

    /**
     * print data map
     */
    void printDataMap();

    /**
     * add category
     * @param categoryName category name
     */
    void addCategory(String categoryName);
}

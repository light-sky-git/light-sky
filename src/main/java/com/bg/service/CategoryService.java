package com.bg.service;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    /**
     * fill data map
     */
    void fillDataMap();

    /**
     * add category
     *
     * @param categoryName category name
     */
    void addCategory(String categoryName);

    /**
     * get data map
     *
     * @return data map
     */
    Map<String, List<String>> getDataMap();
}

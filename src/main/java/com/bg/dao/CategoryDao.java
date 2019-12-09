package com.bg.dao;

import java.util.List;
import java.util.Map;

public interface CategoryDao {

    /**
     * get data map
     * @return data map
     */
    Map<String, List<String>> getDataMap();

    /**
     * add category to data map
     * @param categoryName category name
     */
    void addCategory(String categoryName);

    /**
     * fill data map
     */
    void fillDataMap();
}

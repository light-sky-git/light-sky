package com.bg.service;

import com.bg.dao.CategoryDao;

import java.util.List;
import java.util.Map;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void fillDataMap() {
        categoryDao.fillDataMap();
    }

    @Override
    public void addCategory(String categoryName) {
        categoryDao.addCategory(categoryName);
    }

    @Override
    public Map<String, List<String>> getDataMap() {
        return categoryDao.getDataMap();
    }
}

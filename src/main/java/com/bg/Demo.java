package com.bg;

import com.bg.dao.CategoryDao;
import com.bg.dao.CategoryDaoImpl;
import com.bg.service.CategoryService;
import com.bg.service.CategoryServiceImpl;

import java.io.File;

public class Demo {

    private final static String FILE_NAME = "input2.txt";

    public static void main(String[] args) {
        CategoryDao categoryDao = new CategoryDaoImpl(new File(FILE_NAME));
        CategoryService categoryService = new CategoryServiceImpl(categoryDao);
        categoryService.addCategory(CategoryDaoImpl.CARS);
        categoryService.fillDataMap();
        categoryService.printDataMap();
    }
}

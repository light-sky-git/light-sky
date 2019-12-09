package com.bg.service;

import com.bg.dao.CategoryDao;
import com.bg.dao.CategoryDaoImpl;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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
    public void printDataMap() {
        for (String key : categoryDao.getDataMap().keySet()) {
            System.out.println(key);
            List<String> categoryValues = categoryDao.getDataMap().get(key);
            if (CategoryDaoImpl.NUMBERS.equals(key)) {
                printNumberCategoryValues(categoryValues);
            } else {
                if (CategoryDaoImpl.CARS.equals(key)) {
                    categoryValues.stream()
                            .map(String::toLowerCase)
                            .distinct()
                            .sorted(Comparator.reverseOrder())
                            .map(s -> s + "(" + DigestUtils.md5Hex(s.getBytes()) + ")")
                            .forEach(System.out::println);
                } else {
                    categoryValues.stream()
                            .distinct()
                            .sorted()
                            .forEach(System.out::println);
                }
            }
        }
    }

    @Override
    public void addCategory(String categoryName) {
        categoryDao.addCategory(categoryName);
    }

    /**
     * print number category values
     * @param categoryValues category values list
     */
    private void printNumberCategoryValues(List<String> categoryValues) {
        categoryValues.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new))
                .forEach((mapKey, value) -> System.out.println(mapKey + ": " + value));
    }
}

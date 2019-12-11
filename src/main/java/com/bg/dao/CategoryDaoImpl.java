package com.bg.dao;

import com.bg.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {

    private static final String CATEGORIES_CONFIG_PROPERTY_NAME = "conf.categories";

    private Map<String, List<String>> dataMap = new HashMap<>();
    private File inputFile;

    public CategoryDaoImpl(File inputFile) {
        //todo check if config exist
        List<String> categoriesList = Arrays.asList(Configuration.getConfigurationValue(CATEGORIES_CONFIG_PROPERTY_NAME).split("\\s*,\\s*"));
        categoriesList.forEach(item -> dataMap.put(item, new ArrayList<>()));
        this.inputFile = inputFile;
    }

    @Override
    public Map<String, List<String>> getDataMap() {
        return new HashMap<>(dataMap);
    }

    @Override
    public void addCategory(String categoryName) {
        dataMap.put(categoryName, new ArrayList<>());
    }

    @Override
    public void fillDataMap() {
        try {
            List<String> fileLines = Files.readAllLines(inputFile.toPath());
            List<String> dataMapKeys = new ArrayList<>(dataMap.keySet());
            String currentCategory = fileLines.get(0).toUpperCase();
            for (int i = 1; i < fileLines.size(); i++) {
                if (dataMapKeys.contains(fileLines.get(i).toUpperCase())) {
                    currentCategory = fileLines.get(i).toUpperCase();
                } else {
                    dataMap.get(currentCategory).add(fileLines.get(i));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(new Formatter().format("Error during working with file %s", inputFile.toPath()).toString());
        }
    }
}

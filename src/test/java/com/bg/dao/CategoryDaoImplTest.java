package com.bg.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class CategoryDaoImplTest {

    private static final String ANIMALS = "ANIMALS";
    private static final String NUMBERS = "NUMBERS";
    private static final String CARS = "CARS";

    private CategoryDaoImpl categoryDao;

    public CategoryDaoImplTest() throws IOException {
    }

    @Before
    public void setUp() throws IOException {
        File testFile = new File(getClass().getClassLoader().getResource("./input2.txt").getFile());
        //todo Configuration could be mocked here to use test configuration .properties file
        categoryDao = new CategoryDaoImpl(testFile);
    }

    @Test
    public void shouldReturnDataMap() {
        int expectedMapSize = 3;

        Map<String, List<String>> dataMap = categoryDao.getDataMap();

        assertEquals(expectedMapSize, dataMap.size());
    }

    @Test
    public void shouldReturnDataMapAfterAdditionalCategoryWasAdded() {
        int expectedMapSize = 4;
        categoryDao.addCategory("TEST");

        Map<String, List<String>> dataMap = categoryDao.getDataMap();

        assertEquals(expectedMapSize, dataMap.size());
    }

    @Test
    public void shouldFillDataMap() {
        Map<String, List<String>> expectedDataMap = initExpectedDatMap();

        categoryDao.fillDataMap();
        Map<String, List<String>> actualDataMap = categoryDao.getDataMap();

        assertEquals(expectedDataMap, actualDataMap);
    }

    @Test
    public void shouldFillDataMapAfterAdditionalCategoryWasAdded() {
        int expectedMapSize = 4;
        String categoryName = "TEST";
        List<String> expectedTestCategoryList = new ArrayList<>();
        expectedTestCategoryList.add("test1");

        categoryDao.addCategory(categoryName);
        categoryDao.fillDataMap();
        Map<String, List<String>> actualDataMap = categoryDao.getDataMap();

        assertTrue(actualDataMap.size() == expectedMapSize);
        assertTrue(actualDataMap.get(categoryName) != null);
        assertEquals(expectedTestCategoryList, actualDataMap.get(categoryName));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionDuringDataMapFilling() {
        categoryDao = new CategoryDaoImpl(new File("test.txt"));

        categoryDao.fillDataMap();
    }

    private Map<String, List<String>> initExpectedDatMap() {
        List<String> expectedAnimalsList = new ArrayList<>();
        expectedAnimalsList.add("sheep");
        expectedAnimalsList.add("horse");
        expectedAnimalsList.add("cow");
        expectedAnimalsList.add("horse");
        expectedAnimalsList.add("moose");

        List<String> expectedCarsList = new ArrayList<>();
        expectedCarsList.add("AUDI");
        expectedCarsList.add("BMW");
        expectedCarsList.add("Audi");
        expectedCarsList.add("VW");
        expectedCarsList.add("OPEL");
        expectedCarsList.add("Opel");
        expectedCarsList.add("TEST");
        expectedCarsList.add("test1");

        List<String> expectedNumbersList = new ArrayList<>();
        expectedNumbersList.add("one");
        expectedNumbersList.add("three");
        expectedNumbersList.add("two");
        expectedNumbersList.add("one");
        expectedNumbersList.add("three");
        expectedNumbersList.add("seven");
        expectedNumbersList.add("six");
        expectedNumbersList.add("six");

        Map<String, List<String>> expectedDataMap = new HashMap<>();
        expectedDataMap.put(ANIMALS, expectedAnimalsList);
        expectedDataMap.put(CARS, expectedCarsList);
        expectedDataMap.put(NUMBERS, expectedNumbersList);
        return expectedDataMap;
    }

}
package com.bg.dao;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CategoryDaoImplTest {

    private CategoryDaoImpl categoryDao;

    public CategoryDaoImplTest() throws IOException {
    }

    @Before
    public void setUp() throws IOException {
        File testFile = new File(getClass().getClassLoader().getResource("./input2.txt").getFile());
        categoryDao = new CategoryDaoImpl(testFile);
    }

    @Test
    public void shouldReturnDataMap() {
        int expectedMapSize = 2;

        Map<String, List<String>> dataMap = categoryDao.getDataMap();

        assertEquals(expectedMapSize, dataMap.size());
    }

    @Test
    public void shouldReturnDataMapAfterAdditionalCategoryWasAdded() {
        int expectedMapSize = 3;
        categoryDao.addCategory("TEST");

        Map<String, List<String>> dataMap = categoryDao.getDataMap();

        assertEquals(expectedMapSize, dataMap.size());
    }

    @Test
    public void shouldFillDataMap() {
        int expectedAnimalsListSize = 12;
        int expectedNumbersListSize = 8;

        categoryDao.fillDataMap();
        Map<String, List<String>> actualDataMap = categoryDao.getDataMap();

        assertEquals(expectedAnimalsListSize, actualDataMap.get("ANIMALS").size());
        assertEquals(expectedNumbersListSize, actualDataMap.get("NUMBERS").size());
    }

    @Test
    public void shouldFillDataMapAfterAdditionalCategoryWasAdded() {
        int expectedAnimalsListSize = 5;
        int expectedNumbersListSize = 8;
        int expectedCarsListSize = 6;
        categoryDao.addCategory("CARS");

        categoryDao.fillDataMap();
        Map<String, List<String>> actualDataMap = categoryDao.getDataMap();

        assertEquals(expectedAnimalsListSize, actualDataMap.get("ANIMALS").size());
        assertEquals(expectedNumbersListSize, actualDataMap.get("NUMBERS").size());
        assertEquals(expectedCarsListSize, actualDataMap.get("CARS").size());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionDuringDataMapFilling() {
        categoryDao = new CategoryDaoImpl(new File("test.txt"));

        categoryDao.fillDataMap();
    }

}
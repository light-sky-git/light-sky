package com.bg.service;

import com.bg.dao.CategoryDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private CategoryDaoImpl categoryDao;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldInvokeFillDataMapMethod() {
        doNothing().when(categoryDao).fillDataMap();

        categoryService.fillDataMap();

        verify(categoryDao, times(1)).fillDataMap();
    }

    @Test
    public void printDataMap() {
    }

    @Test
    public void shouldInvokeAddCategoryMethod() {
        String category = "TEST_CATEGORY";
        doNothing().when(categoryDao).addCategory(category);

        categoryService.addCategory(category);

        verify(categoryDao, times(1)).addCategory(category);
    }

    @Test
    public void shouldPrintDataMapAccordingToSpecification() {
        Map<String, List<String>> dataMap = new HashMap<>();
        List<String> testDataList = new ArrayList<>();
        testDataList.add("test1");
        testDataList.add("test2");
        testDataList.add("test1");
        dataMap.put(CategoryDaoImpl.ANIMALS, testDataList);
        dataMap.put(CategoryDaoImpl.NUMBERS, testDataList);
        when(categoryDao.getDataMap()).thenReturn(dataMap);

        categoryService.printDataMap();

        assertEquals("ANIMALS" + System.lineSeparator()
                + "test1" + System.lineSeparator()
                + "test2" + System.lineSeparator()
                + "NUMBERS" + System.lineSeparator()
                + "test1: 2" + System.lineSeparator()
                + "test2: 1" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void shouldPrintDataMapForCarsCategoryAccordingToSpecification() {
        Map<String, List<String>> dataMap = new HashMap<>();
        List<String> testDataList = new ArrayList<>();
        testDataList.add("test1");
        testDataList.add("test2");
        testDataList.add("TEST1");
        dataMap.put(CategoryDaoImpl.CARS, testDataList);
        when(categoryDao.getDataMap()).thenReturn(dataMap);

        categoryService.printDataMap();

        assertEquals("CARS" + System.lineSeparator()
                + "test2(ad0234829205b9033196ba818f7a872b)" + System.lineSeparator()
                + "test1(5a105e8b9d40e1329780d62ea2265d8a)" + System.lineSeparator(), outContent.toString());
    }

}
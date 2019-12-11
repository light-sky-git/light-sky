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
import java.util.HashMap;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryDaoImpl categoryDao;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void shouldInvokeFillDataMapMethod() {
        doNothing().when(categoryDao).fillDataMap();

        categoryService.fillDataMap();

        verify(categoryDao, times(1)).fillDataMap();
    }

    @Test
    public void shouldInvokeAddCategoryMethod() {
        String category = "TEST_CATEGORY";
        doNothing().when(categoryDao).addCategory(category);

        categoryService.addCategory(category);

        verify(categoryDao, times(1)).addCategory(category);
    }

    @Test
    public void shouldInvokeGetDataMapMethod() {
        when(categoryDao.getDataMap()).thenReturn(new HashMap<>());

        categoryService.getDataMap();

        verify(categoryDao, times(1)).getDataMap();
    }

}
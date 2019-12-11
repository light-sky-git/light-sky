package com.bg.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PrintCommandOccurrenceMappingImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private PrintCommandOccurrenceMappingImpl printCommandOccurrenceMapping = new PrintCommandOccurrenceMappingImpl();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintDataListMappedAndSortingByFrequency() {
        List<String> testDataList = new ArrayList<>();
        testDataList.add("test1");
        testDataList.add("test2");
        testDataList.add("test1");
        testDataList.add("test1");

        printCommandOccurrenceMapping.print(testDataList);

        assertEquals("test1: yes" + System.lineSeparator()
                + "test2: 1" + System.lineSeparator(), outContent.toString());
    }

}
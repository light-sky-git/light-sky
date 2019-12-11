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
public class PrintCommandItemsWithHashImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private PrintCommandItemsWithHashImpl printCommandItemsWithHash = new PrintCommandItemsWithHashImpl();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintDataListAlphabetDescendingSortingWithHashValues() {
        List<String> testDataList = new ArrayList<>();
        testDataList.add("test1");
        testDataList.add("test2");
        testDataList.add("Test1");

        printCommandItemsWithHash.print(testDataList);

        assertEquals("test2(ad0234829205b9033196ba818f7a872b)" + System.lineSeparator()
                + "test1(5a105e8b9d40e1329780d62ea2265d8a)" + System.lineSeparator(), outContent.toString());
    }

}
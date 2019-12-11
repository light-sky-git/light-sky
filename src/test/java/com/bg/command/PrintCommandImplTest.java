package com.bg.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PrintCommandImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private PrintCommandImpl printCommand = new PrintCommandImpl();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintDataListAscendingSortingByValues() {
        List<String> testDataList = new ArrayList<>();
        testDataList.add("test2");
        testDataList.add("test1");


        printCommand.print(testDataList);

        assertEquals("test1" + System.lineSeparator()
                + "test2" + System.lineSeparator(), outContent.toString());
    }

}
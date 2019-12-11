package com.bg.service;

import com.bg.command.PrintCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrintServiceImplTest {

    @Mock
    private Map<String, PrintCommand> printCommandMap;
    @Mock
    private PrintCommand printCommand;
    @InjectMocks
    private PrintServiceImpl printService;

    @Test
    public void shouldInvokePrintCommandMethod() {
        List<String> dataList = new ArrayList<>();
        dataList.add("testValue");
        String printView = "simplePrint";
        //use parameter to avoid any-matchers usage
        when(printCommandMap.get(printView)).thenReturn(printCommand);
        doNothing().when(printCommand).print(dataList);

        printService.print(dataList, printView);

        verify(printCommand, times(1)).print(dataList);
    }

    @Test
    public void shouldInvokeAddPrintCommandMethod() {
        String commandName = "simplePrint";
        when(printCommandMap.put(commandName, printCommand)).thenReturn(printCommand);

        printService.addPrintCommand(commandName, printCommand);

        verify(printCommandMap, times(1)).put(commandName, printCommand);
    }

    @Test
    public void shouldInvokeRemovePrintCommandMethod() {
        String commandName = "simplePrint";
        when(printCommandMap.remove(commandName)).thenReturn(printCommand);

        printService.removePrintCommand(commandName);

        verify(printCommandMap, times(1)).remove(commandName);
    }

}
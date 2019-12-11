package com.bg.service;

import com.bg.command.PrintCommand;
import com.bg.command.PrintCommandFrequencyImpl;
import com.bg.command.PrintCommandImpl;
import com.bg.command.PrintCommandItemsWithHashImpl;
import com.bg.command.PrintCommandOccurrenceMappingImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintServiceImpl implements PrintService {

    private Map<String, PrintCommand> printCommandMap = new HashMap<>();

    public PrintServiceImpl(Map<String, PrintCommand> printCommandMap) {
        this.printCommandMap = printCommandMap;
        addPrintCommand("simplePrint", new PrintCommandImpl());
        addPrintCommand("frequencyPrint", new PrintCommandFrequencyImpl());
        addPrintCommand("hashPrint", new PrintCommandItemsWithHashImpl());
        addPrintCommand("occurrenceMappingPrint", new PrintCommandOccurrenceMappingImpl());
    }

    @Override
    public void print(List<String> dataList, String printFormat) {
        printCommandMap.get(printFormat).print(dataList);
    }

    @Override
    public void addPrintCommand(String commandName, PrintCommand command) {
        printCommandMap.put(commandName, command);
    }

    @Override
    public void removePrintCommand(String commandName) {
        printCommandMap.remove(commandName);
    }
}

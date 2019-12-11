package com.bg.service;

import com.bg.command.PrintCommand;

import java.util.List;

public interface PrintService {

    /**
     * print data list
     *
     * @param dataList  data list
     * @param printView print format name
     */
    void print(List<String> dataList, String printView);

    /**
     * add print command
     *
     * @param commandName command name
     * @param command     command
     */
    void addPrintCommand(String commandName, PrintCommand command);

    /**
     * remove print command
     *
     * @param commandName command name
     */
    void removePrintCommand(String commandName);
}

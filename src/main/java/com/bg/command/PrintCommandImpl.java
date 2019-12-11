package com.bg.command;

import java.util.List;

public class PrintCommandImpl implements PrintCommand {

    @Override
    public void print(List<String> dataList) {
        dataList.stream()
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}

package com.bg.command;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PrintCommandOccurrenceMappingImpl implements PrintCommand {

    @Override
    public void print(List<String> dataList) {
        int itemsCountToBeMapped = 2;
        dataList.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new))
//todo we can reduce lines of code here
                .forEach((mapKey, value) -> {
                    if (value > itemsCountToBeMapped) {
                        System.out.println(mapKey + ": yes");
                    } else {
                        System.out.println(mapKey + ": " + value);
                    }
                });
    }
}

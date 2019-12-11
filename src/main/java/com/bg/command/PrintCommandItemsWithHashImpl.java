package com.bg.command;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Comparator;
import java.util.List;

public class PrintCommandItemsWithHashImpl implements PrintCommand {

    @Override
    public void print(List<String> dataList) {
        dataList.stream()
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .map(s -> s + "(" + DigestUtils.md5Hex(s.getBytes()) + ")")
                .forEach(System.out::println);
    }
}

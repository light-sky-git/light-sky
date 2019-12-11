package com.bg;

import com.bg.dao.CategoryDao;
import com.bg.dao.CategoryDaoImpl;
import com.bg.service.CategoryService;
import com.bg.service.CategoryServiceImpl;
import com.bg.service.PrintService;
import com.bg.service.PrintServiceImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

//todo cover with unit tests
public class Demo {

    private final static String FILE_NAME = "input2.txt";
    private final static String NUMBER_COMMAND_NAME = "occurrenceMappingPrint";
    private static final String NUMBERS = "NUMBERS";
    private static final String CARS = "CARS";

    /**
     * first argument is path to file
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //todo add command name argument, add cmd parameter naming to retrieve by name
        String filePath = args.length != 0 ? args[0] : FILE_NAME;
        String numberCommandName = args.length > 1 ? args[1] : NUMBER_COMMAND_NAME;
        CategoryDao categoryDao = new CategoryDaoImpl(new File(filePath));
        CategoryService categoryService = new CategoryServiceImpl(categoryDao);
        PrintService printService = new PrintServiceImpl(new HashMap<>());
        categoryService.fillDataMap();

        for (String key : categoryService.getDataMap().keySet()) {
            System.out.println(key);
            List<String> categoryValues = categoryService.getDataMap().get(key);
            if (NUMBERS.equals(key)) {
                printService.print(categoryValues, numberCommandName);
            } else {
                if (CARS.equals(key)) {
                    printService.print(categoryValues, "hashPrint");
                } else {
                    printService.print(categoryValues, "simplePrint");
                }
            }
        }
    }
}

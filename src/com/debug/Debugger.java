package com.debug;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.exception.LogException;

public class Debugger {

    private static final String DEBUG_LOG_FILE_FOR_UNITTEST = "./WEB-INF/logs/debug_log.txt";
    private static final String DEBUG_LOG_FILE_ON_SERVICE = "./logs/debug_log.txt";
    private static final String DELIMITER = "----------------------------------------------------";

    public static void writeObjectToFile(Object obj, String msg) {

        String workingDir = System.getProperty("user.dir");
        // System.out.println("Working Directory = " + workingDir);

        // 要修正：DEBUG_LOG_FILE_ON_SERVICEはパスが誤っているため書き込めない
        String DEBUG_LOG_FILE = DEBUG_LOG_FILE_FOR_UNITTEST;
        if (workingDir.equals("/")) {
            DEBUG_LOG_FILE = DEBUG_LOG_FILE_ON_SERVICE;
        }

        try (FileWriter fileWriter = new FileWriter(DEBUG_LOG_FILE, false);
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("[DATE] " + new Date());
            printWriter.println("[message] " + msg);
            printWriter.println(obj);
            printWriter.println(DELIMITER);

        } catch (IOException ioe) {

            LogException.writeErrorMsgToFile(ioe);
            // ioe.printStackTrace();
        }
    }

}

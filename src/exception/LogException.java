package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogException {

    private static final String ERROR_LOG_FILE_FOR_UNITTEST = "./WEB-INF/logs/errorlog.txt";
    private static final String ERROR_LOG_FILE_ON_SERVICE = "./logs/errorlog.txt";
    private static final String DELIMITER = "----------------------------------------------------";

    public static void writeErrorMsgToFile(Exception e) {

        String workingDir = System.getProperty("user.dir");
        // System.out.println("Working Directory = " + workingDir);

        // 要修正：ERROR_LOG_FILE_ON_SERVICEはパスが誤っているため書き込めない
        String ERROR_LOG_FILE = ERROR_LOG_FILE_FOR_UNITTEST;
        if (workingDir.equals("/")) {
            ERROR_LOG_FILE = ERROR_LOG_FILE_ON_SERVICE;
        }

        try (FileWriter fileWriter = new FileWriter(ERROR_LOG_FILE, true);
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("[DATE] " + new Date());
            e.printStackTrace(printWriter);
            printWriter.println(DELIMITER);

        } catch (IOException ioe) {

            ioe.printStackTrace();
        }
    }

}

package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogException {

    private static final String ERROR_LOG_FILE = "./WEB-INF/logs/errorlog.txt";
    private static final String DELIMITER = "----------------------------------------------------";

    public static void writeErrorMsgToFile(Exception e) {

        // String workingDir = System.getProperty("user.dir");
        // System.out.println("Working Directory = " + workingDir);

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

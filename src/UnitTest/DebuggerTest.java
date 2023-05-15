package UnitTest;

import java.util.ArrayList;
import java.util.List;

import debug.Debugger;

public class DebuggerTest {
    
    public static void main(String[] args) {
        
        List<Integer> list = new ArrayList<>();
        testWriteObjectToFile(list, "unit test");
    }

    private static void testWriteObjectToFile(Object obj, String msg) {

        Debugger.writeObjectToFile(obj, msg);
    }
}

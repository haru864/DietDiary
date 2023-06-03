package test.SAMPLE;

import java.net.URL;

public class GetClassFileLocation {

    public static void main(String[] args) {

        URL location = GetClassFileLocation.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getPath());

        String tomcatHome = System.getProperty("catalina.home");
        System.out.println(tomcatHome);
    }
}

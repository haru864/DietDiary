package test.SAMPLE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class SampleLogback {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(SampleLogback.class);

        ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) logger;
        // log.setLevel(Level.TRACE); // ★デフォルトだと trace レベルは出力されないので、出力のレベルを TRACE にしている

        logger.debug("debug message");
    }

}

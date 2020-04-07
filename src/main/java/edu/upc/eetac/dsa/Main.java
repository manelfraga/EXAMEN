package edu.upc.eetac.dsa;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
public class Main {
    static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message");
        logger.warn("warning  Test Message!");
        logger.error("error Test Message");

    }
}

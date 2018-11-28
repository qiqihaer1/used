package com.keduox.test;

import org.apache.log4j.Logger;

public class AvroTest {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("AvroTest");
        for (int i=0;i<100;i++){
            logger.info("log4j"+i);
        }


    }
}

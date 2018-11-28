package com.keduox.test;


import org.apache.log4j.Logger;

public class log4jtest {
    public static void main(String[] args) {
        Logger log4j = Logger.getLogger("log4jtest");
        log4j.info("log4j测试");
        java.util.logging.Logger logging = java.util.logging.Logger.getLogger("log4jtest");
        logging.info("logging测试");
    }
}

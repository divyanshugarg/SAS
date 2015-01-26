package com.spa.util;
import org.apache.log4j.Level;

public class CustomLog4JLevel_Pass extends Level {
    public CustomLog4JLevel_Pass(int level, String levelStr, int syslogEquivalent) 
    { 
        super(level, levelStr, syslogEquivalent); 
    } 
    public static CustomLog4JLevel_Pass toLevel(int val, Level defaultLevel) 
    { 
         return PASS; 
    } 
    public static CustomLog4JLevel_Pass toLevel(String sArg, Level defaultLevel) 
    { 
            return PASS; 
    } 
    public static final CustomLog4JLevel_Pass PASS = new CustomLog4JLevel_Pass(10000, "PASS", 0); 
}


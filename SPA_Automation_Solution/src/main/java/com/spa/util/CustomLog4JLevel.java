package com.spa.util;

import org.apache.log4j.Level;

public class CustomLog4JLevel extends Level {
    public CustomLog4JLevel(int level, String levelStr, int syslogEquivalent) 
    { 
        super(level, levelStr, syslogEquivalent); 
    } 
    public static CustomLog4JLevel toLevel(int val, Level defaultLevel) 
    { 
         return SCREENSHOT; 
    } 
    public static CustomLog4JLevel toLevel(String sArg, Level defaultLevel) 
    { 
            return SCREENSHOT; 
    } 
    public static final CustomLog4JLevel SCREENSHOT = new CustomLog4JLevel(10000, "SCREENSHOT", 0); 
}

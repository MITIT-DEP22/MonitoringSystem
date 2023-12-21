package com.company;

import java.io.IOException;

public class CommandChecker {
    public static void checkServerCommand(String command) throws IOException {
        switch(command){
            case "shutdown":
                Runtime.getRuntime().exec("cmd /c shutdown");
                break;
        }
        System.out.println(command);
    }
}

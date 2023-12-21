package com.company;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            try (Socket socket = new Socket("localhost", 8888)) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                Gson gson = new Gson();
                ComputerData comp = Computer.getComputerData();

                System.out.println(comp);
                out.println(gson.toJson(comp));

                while (true) {
                    ComputerVariableData compvar = Computer.getComputerVariableData();

                    System.out.println(compvar);
                    out.println(gson.toJson(compvar));
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Connect exception");
                Thread.sleep(5_000);
                System.out.println("Trying again");
            }
        }
    }
}

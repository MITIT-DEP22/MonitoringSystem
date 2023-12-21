package com.company;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Computer {

    public static double frequencyUpdate = 1;

    public static boolean isRamInProgress;
    public static boolean isCpuInProgress;

    private static int ramLoad;
    private static int cpuLoad;

    public static ComputerData getComputerData() throws IOException {
        ComputerData computerData = new ComputerData();

        computerData.setDisks(getDisks());
        computerData.setMacAddress(getMacAddress());
        computerData.setCpuInfo(getCpuInfo());
        computerData.setGpuInfo(getGpuInfo());
        computerData.setMotherboardInfo(getMotherboardInfo());
        computerData.setRamInfo(getRamInfo());
        computerData.setOsInfo(getOsInfo());

        computerData.setIpv4(InetAddress.getLocalHost().getHostAddress());
        return computerData;
    }

    public static ComputerVariableData getComputerVariableData() throws IOException, InterruptedException {
        ComputerVariableData pcVarData = new ComputerVariableData();

        pcVarData.setNetworkLoad(getNetworkLoad());
        pcVarData.setNetworkUpLoad(getNetworkUpload());
        pcVarData.setIpv4(InetAddress.getLocalHost().getHostAddress());

        if (!isRamInProgress) {
            pcVarData.setRamLoad(ramLoad);

            Runnable runnable = () -> {
                try {
                    getRamLoad();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();
        } else {
            isRamInProgress = false;
        }

        if (!isCpuInProgress) {
            pcVarData.setCpuLoad(cpuLoad);
            Runnable runnable = () -> {
                try {
                    getCpuLoad();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();
        } else {
            isCpuInProgress = false;
        }

        return pcVarData;
    }

    private static String getOsInfo() throws IOException {
        String result = new String(Runtime.getRuntime().exec("cmd /c systeminfo | findstr /B /C:\"OS Name\"").getInputStream().readAllBytes(), "CP866");
        Pattern pattern = Pattern.compile("OS Name:\\s*Майкрософт(.*)");
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println("os info was not found - no matches");
            return null;
        }
    }

    public static List<DiskInfo> getDisks() throws IOException {
        long kilobytes = 1024;
        long megabytes = kilobytes * 1024;
        long gigabytes = megabytes * 1024;
        List<DiskInfo> disks = new ArrayList<>();

        File[] roots = File.listRoots();
        for (File root : roots) {
            try {
                disks.add(new DiskInfo(root.getAbsolutePath(), (root.getTotalSpace() / (float) gigabytes), (root.getUsableSpace() / (float) gigabytes)));
            } catch (Exception e) {
                System.out.println("Disk problem");
                disks.add(new DiskInfo());
            }
        }
        return disks;
    }

    private static String getMacAddress() throws IOException {
        String result = new String(Runtime.getRuntime().exec("cmd /c getmac").getInputStream().readAllBytes(), "CP866");
        Pattern pattern = Pattern.compile("(([0-9a-fA-F]{2}-?){6})");
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            return matcher.group(0);
        } else {
            System.out.println("Mac was not found - no matches");
            return "";
        }
    }

    private static void getCpuLoad() throws IOException {
        String str = new String(Runtime.getRuntime().exec("cmd /c wmic cpu get LoadPercentage").getInputStream().readAllBytes(), "CP866");

        Pattern pat = Pattern.compile("(\\d+)");
        Matcher matcher = pat.matcher(str);
        try {
            if (matcher.find()) {
                cpuLoad = Integer.parseInt(matcher.group(1));
            } else {
                System.out.println("CPU was not found - no matches");
            }
        } catch (NumberFormatException e) {
            System.out.println("Can`t parse cpu to int");
        }
    }

    private static float getNetworkLoad() throws IOException, InterruptedException {
        String str = new String(Runtime.getRuntime().exec("cmd /c netstat -e").getInputStream().readAllBytes(), "CP866");
        Pattern pattern = Pattern.compile("(\\d+)\\s*(\\d+)");

        double bytes1 = 0;
        double bytes2 = 0;
        Matcher match = pattern.matcher(str);
        if (match.find()) {
            try {
                bytes1 = Long.parseLong(match.group(1));
            } catch (NumberFormatException e) {
                System.out.println("Can`t parse first network to long");
                return -1;
            }
        } else {
            System.out.println("Network 1 was not found");
            return -1;
        }

        Thread.sleep((long) frequencyUpdate * 998);

        str = new String(Runtime.getRuntime().exec("cmd /c netstat -e").getInputStream().readAllBytes(), "CP866");
        match = pattern.matcher(str);
        if (match.find()) {
            try {
                bytes2 = Long.parseLong(match.group(1));
            } catch (NumberFormatException e) {
                System.out.println("Can`t parse second network to long");
                return -1;
            }
        } else {
            System.out.println("Network 2 was not found");
            return -1;
        }
        try {
            return (float) Math.round((((bytes2 - bytes1) / (1024 * 1024)) / frequencyUpdate) * 100) / 100;
        } catch (ArithmeticException e) {
            System.out.println("Probably frequencyUpdate = 0");
            return -1;
        }
    }

    private static float getNetworkUpload() throws IOException, InterruptedException {
        String str = new String(Runtime.getRuntime().exec("cmd /c netstat -e").getInputStream().readAllBytes(), "CP866");
        Pattern pattern = Pattern.compile("(\\d+)\\s*(\\d+)");

        double bytes1 = 0;
        double bytes2 = 0;
        Matcher match = pattern.matcher(str);
        if (match.find()) {
            bytes1 = Long.parseLong(match.group(2));
        }
        Thread.sleep((long) frequencyUpdate * 1000);
        str = new String(Runtime.getRuntime().exec("cmd /c netstat -e").getInputStream().readAllBytes(), "CP866");
        match = pattern.matcher(str);
        if (match.find()) {
            bytes2 = Long.parseLong(match.group(2));
        }

        return (float) Math.round((((bytes2 - bytes1) / (1024 * 1024)) / frequencyUpdate) * 100) / 100;
    }

    private static void getRamLoad() throws IOException {

        String resultFree = new String(Runtime.getRuntime().exec("cmd /c systeminfo |find \"Available Physical Memory\"").getInputStream().readAllBytes(), "CP866");

        Pattern pattern = Pattern.compile("((\\d+)\\D*(\\d+))");
        Matcher matcher = pattern.matcher(resultFree);

        double freeRamMemory = 0;
        if (matcher.find()) {
            try {
                freeRamMemory = Double.parseDouble(matcher.group(2) + matcher.group(3));
            } catch (NumberFormatException e) {
                System.out.println("Can't parse free ram memory");
                return;
            }
        } else {
            System.out.println("Free ram was not found - no matches");
            return;
        }

        String resultTotal = new String(Runtime.getRuntime().exec("cmd /c systeminfo |find \"Total Physical Memory\"").getInputStream().readAllBytes(), "CP866");
        matcher = pattern.matcher(resultTotal);

        double totalRamMemory = 0;
        if (matcher.find()) {
            try {
                totalRamMemory = Double.parseDouble(matcher.group(2) + matcher.group(3));
            } catch (NumberFormatException e) {
                System.out.println("Can't parse total ram memory");
                return;
            }
        } else {
            System.out.println("Total ram was not found - no matches");
            return;
        }

        double busyRamMemory = totalRamMemory - freeRamMemory;
        try {
            ramLoad = (int) (busyRamMemory / totalRamMemory * 100);
        } catch (ArithmeticException e) {
            System.out.println("Probably total memory = 0");
        }
    }

    public static String getCpuInfo() throws IOException {
        try {
            String temp1 = new String(Runtime.getRuntime().exec("cmd /c wmic cpu get name").getInputStream().readAllBytes(), "CP866");
            String[] temp2 = temp1.split("\n");
            String cpuInfo = temp2[1].trim();
            return cpuInfo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }

    }

    public static ArrayList<String> getGpuInfo() throws IOException {
        try {
            String temp1 = new String(Runtime.getRuntime().exec("cmd /c wmic PATH Win32_videocontroller GET description").getInputStream().readAllBytes(), "CP866");
            String[] temp2 = temp1.split("\n");
            ArrayList<String> gpuInfo = new ArrayList<>();
            for (int i = 0; i < temp2.length - 1; i++) {
                if (!temp2[i + 1].trim().equals("")) {
                    gpuInfo.add(temp2[i + 1].trim() + " ");
                }
            }

            return gpuInfo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static String getMotherboardInfo() throws IOException {
        try {
            String model = (new String(Runtime.getRuntime().exec("cmd /c wmic baseboard get product").getInputStream().readAllBytes(), "CP866"))
                    .split("\n")[1]
                    .trim();
            String manufacturer = (new String(Runtime.getRuntime().exec("cmd /c wmic baseboard get manufacturer").getInputStream().readAllBytes(), "CP866"))
                    .split("\n")[1]
                    .trim();
            if (!model.isEmpty() && !manufacturer.isEmpty()) {
                return manufacturer + " " + model;
            } else return "error";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }

    public static String getRamInfo() throws IOException {
        try {
            String temp = new String(Runtime.getRuntime().exec("cmd /c wmic memorychip get Manufacturer,PartNumber,Speed").getInputStream().readAllBytes(), "CP866");
            String[] temp2 = temp.split("\n");
            Pattern pattern = Pattern.compile("(\\w*\\S)\\s*(\\w*).(\\w*)\\s*(\\d*)");
            Matcher matcher = pattern.matcher(temp2[1]);
            String producer = "";
            String partNumber = "";
            String capacity = "";
            String frequency = "";
            if (matcher.find()) {
                producer = matcher.group(1);
                partNumber = matcher.group(2);
                capacity = matcher.group(3);
                frequency = matcher.group(4);
            }
            return producer + " " + partNumber + " " + capacity + " " + frequency + "Mhz";
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }
}

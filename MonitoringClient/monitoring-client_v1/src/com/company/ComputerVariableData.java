package com.company;

public class ComputerVariableData {

    private String ipv4;
    private static int frequencyUpdate;
    private float networkLoad;
    private float networkUpLoad;

    private int ramLoad = -1;
    private int cpuLoad = -1;

    public ComputerVariableData(){}

    public static int getFrequencyUpdate() {
        return frequencyUpdate;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public static void setFrequencyUpdate(int frequencyUpdate) {
        ComputerVariableData.frequencyUpdate = frequencyUpdate;
    }

    public int getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(int cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    public float getNetworkLoad() {
        return networkLoad;
    }

    public void setNetworkLoad(float networkLoad) {
        this.networkLoad = networkLoad;
    }

    public float getNetworkUpLoad() {
        return networkUpLoad;
    }

    public void setNetworkUpLoad(float networkUpLoad) {
        this.networkUpLoad = networkUpLoad;
    }

    public int getRamLoad() {
        return ramLoad;
    }

    public void setRamLoad(int ramLoad) {
        this.ramLoad = ramLoad;
    }

    @Override
    public String toString() {
        return "ComputerVariableData{" +
                "ipv4='" + ipv4 + '\'' +
                ", networkLoad=" + networkLoad +
                ", networkUpLoad=" + networkUpLoad +
                ", ramLoad=" + ramLoad +
                ", cpuLoad=" + cpuLoad +
                '}';
    }
}

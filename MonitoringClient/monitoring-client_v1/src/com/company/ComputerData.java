package com.company;

import java.util.ArrayList;
import java.util.List;

public class ComputerData {
    private String ipv4;
    private String macAddress;
    private int numberPC;
    private int numberClassroom;

    private String cpuInfo;
    private ArrayList<String> gpuInfo;
    private String motherboardInfo;
    private String ramInfo;
    private String osInfo;

    private List<DiskInfo> disks;

    public ComputerData() {}

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getOsInfo() {
        return osInfo;
    }

    public void setOsInfo(String osInfo) {
        this.osInfo = osInfo;
    }

    public String getCpuInfo() {
        return cpuInfo;
    }

    public void setCpuInfo(String cpuInfo) {
        this.cpuInfo = cpuInfo;
    }

    public ArrayList<String> getGpuInfo() {
        return gpuInfo;
    }

    public void setGpuInfo(ArrayList<String> gpuInfo) {
        this.gpuInfo = gpuInfo;
    }

    public String getMotherboardInfo() {
        return motherboardInfo;
    }

    public void setMotherboardInfo(String motherboardInfo) {
        this.motherboardInfo = motherboardInfo;
    }

    public String getRamInfo() {
        return ramInfo;
    }

    public void setRamInfo(String ramInfo) {
        this.ramInfo = ramInfo;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getNumberPC() {
        return numberPC;
    }

    public void setNumberPC(int numberPC) {
        this.numberPC = numberPC;
    }

    public int getNumberClassroom() {
        return numberClassroom;
    }

    public void setNumberClassroom(int numberClassroom) {
        this.numberClassroom = numberClassroom;
    }

    public List<DiskInfo> getDisks() {
        return disks;
    }

    public void setDisks(List<DiskInfo> disks) {
        this.disks = disks;
    }

    @Override
    public String toString() {
        return "ComputerData{" +
                "ipv4='" + ipv4 + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", numberPC=" + numberPC +
                ", numberClassroom=" + numberClassroom +
                ", cpuInfo='" + cpuInfo + '\'' +
                ", gpuInfo=" + gpuInfo +
                ", motherboardInfo='" + motherboardInfo + '\'' +
                ", ramInfo='" + ramInfo + '\'' +
                ", osInfo='" + osInfo + '\'' +
                ", disks=" + disks +
                '}';
    }
}

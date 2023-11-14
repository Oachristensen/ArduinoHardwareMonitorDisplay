package org.example;


import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        long physicalMemorySize = os.getTotalMemorySize();
        long freePhysicalMemory = os.getFreeMemorySize();

        System.out.println(humanReadableByteCount(physicalMemorySize));
        System.out.println(humanReadableByteCount(freePhysicalMemory));
        System.out.println(os.getAvailableProcessors());
        System.out.println(os.getCpuLoad()*100);
        try {
            for (int i = 0; i < 100; i++) {
                double systemCpuLoad = 0;
                double currentSystemLoad = 0;
                Thread.sleep(100);
                for (int j = 0; j < 5; j++) {
                    Thread.sleep(100);
                    currentSystemLoad = os.getCpuLoad();
                    systemCpuLoad = systemCpuLoad+currentSystemLoad;
                }
                systemCpuLoad = systemCpuLoad/5;
                System.out.println((Math.round(systemCpuLoad*100)) + "%");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
// I dont need to store all of these but it makes it more readable for now
    public static double humanReadableByteCount(long bytes) {
        long kb = bytes/1024;
        long mb = kb/1024;
        double gb =(double) mb/1024;
        // rounds gb up to 1 decimal place
        gb = (double) Math.round(gb * 10) /10;
        return gb;
    }
}


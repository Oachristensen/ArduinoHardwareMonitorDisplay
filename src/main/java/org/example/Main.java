package org.example;


import jssc.SerialPort;

import java.lang.management.ManagementFactory;


public class Main {
    // The lower this is the faster the program updates its status
    private static final int sleepTimer = 1000;

    // Change this to the COM port you connect your arduino to
    private static final String portName = "COM3";


    private static final SerialPort port = new SerialPort(portName);


    public static void main(String[] args) {
        double physicalMemorySize;
        double freePhysicalMemory;
        int freeMemoryPercentage;
        try {
            port.openPort();
            port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();


            // Main loop
            while (true) {
                physicalMemorySize = humanReadableByteCount(os.getTotalMemorySize());
                freePhysicalMemory = humanReadableByteCount(os.getFreeMemorySize());
                freeMemoryPercentage = 100 - ((int) Math.round((freePhysicalMemory / physicalMemorySize) * 100));

                int systemCpuLoad = (int) Math.round(os.getCpuLoad() * 100);

                // _ is a break char for the serial.read function
                port.writeString(systemCpuLoad + "% " + freeMemoryPercentage + "%_");
                Thread.sleep(sleepTimer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // I dont need to store all of these but it makes it more readable for now
    public static double humanReadableByteCount(long bytes) {
        long kb = bytes / 1024;
        long mb = kb / 1024;
        double gb = (double) mb / 1024;
        // rounds gb up to 1 decimal place
        gb = (double) Math.round(gb * 10) / 10;
        return gb;
    }
}


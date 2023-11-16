# SystemHardwareMonitor
 A java program that tracks cpu and memory usage and sends it to an Arduino to be displayed
  #### Todo
   - Have the app run in the system tray
   - Optimize Arduino coding
   - Model and 3d print a case

#### Use/install guide:
 - You will need to change the COM port in the Java program to whichever com port you are using (Found on line 14)(this will be the default port ArduinoIDE connects you to)
 - I used an OLED display with dimensions 128x32 if you use a different display you will need to change the SCREEN_WIDTH and SCREEN_HEIGHT on lines 6 and 7 of the ino file


#### List of parts
 - Arduino Pro Micro
 - 128x32 I2C OLED display

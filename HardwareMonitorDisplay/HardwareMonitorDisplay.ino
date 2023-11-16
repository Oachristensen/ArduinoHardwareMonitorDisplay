#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 32

#define OLED_RESET -1
#define SCREEN_ADDRESS 0x3c

Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, OLED_RESET);

int counter = 0;
bool firstNumberOneChar;
bool secondNumberOneChar;

// char * pch;

void setup() {
  Serial.begin(9600);
  // initialize the OLED object
  if (!display.begin(SSD1306_SWITCHCAPVCC, SCREEN_ADDRESS)) {
    Serial.println(F("SSD1306 allocat ion failed"));
    for (;;)
      ;  // Don't proceed, loop forever
  }

  display.clearDisplay();
  display.setTextSize(1.5);
  display.setTextColor(WHITE);
  display.setCursor(0, 5);
  display.println("Display Online");
  display.display();
  delay(2000);
}

void loop() {
  firstNumberOneChar = false;
  secondNumberOneChar = false;
  delay(100);
  display.clearDisplay();
  display.setCursor(0, 5);

  if (Serial.available() > 0) {

    String input = Serial.readStringUntil('_');

    if (input[2] == ' ') {
      firstNumberOneChar = true;
    }
    if (input[4] == '%') { 
      secondNumberOneChar = true;
    }
    else if(input[5] == '%' && firstNumberOneChar != true) {
      secondNumberOneChar = true;
    }
    display.print("Cpu Usage: ");
    if (firstNumberOneChar == true) {
      display.print(input[0]);
      display.print(input[1]);
      display.println(input[2]);

      display.print("Mem Usage: ");
    if (secondNumberOneChar == true){
      display.print(input[3]);
      display.print(input[4]);
    }
    else {
      display.print(input[3]);
      display.print(input[4]);
      display.print(input[5]);
    }
    } 
    else {
      display.print(input[0]);
      display.print(input[1]);
      display.print(input[2]);
      display.println(input[3]);

      display.print("Mem Usage: ");
    if (secondNumberOneChar == true){
      display.print(input[4]);
      display.print(input[5]);
    }
    else {
      display.print(input[4]);
      display.print(input[5]);
      display.print(input[6]);
    }
    }
    
    display.display();
  }
}

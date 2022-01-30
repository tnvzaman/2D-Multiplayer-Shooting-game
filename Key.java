package com.game.src.main;

public class Key {

	  private int numTimesPressed = 0;
      private boolean pressed = false;

      public int getNumTimesPressed() {
          return numTimesPressed;
      }

      public boolean isPressed() {
          return pressed;
      }

      public void toggle(boolean isPressed) {
          pressed = isPressed;
          if (isPressed) numTimesPressed++;
      }
}

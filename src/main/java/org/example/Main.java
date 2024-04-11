package org.example;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {

    TryUtilitiy.normalTry();
//    TryUtilitiy.tryWithResources();

    int result = TryUtilitiy.methodWithErrorCodes(-4.0);
    if (result == 0) {
      // continue our code normally
    } else if (result == -1) {
      // we need some handling of error
      System.out.println("You can't provide a negative number");
    }
  }
}
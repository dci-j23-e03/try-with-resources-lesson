package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TryUtilitiy {

  public static void normalTry() throws IOException {
    // normal try block
    // at line 13 we get FileNotFoundException, and lines 14 and 15 will not execute
    // we then go to the finally block, which must execute, where we try to do cleanup
    // at line 20 we try to close resource and getting NullPointerException
    // the question is which one to be returned by compiler
    // it will suppress original exception (FileNotFoundException) and return the last one (NullPointerException)
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream("textfile.txt");
      // we do something with our resource
      // ... some code
    } finally {
      System.out.println("This is finally block where we need to close a resource");
//      if (fileInputStream != null) "handling" runtime exceptions
      if (fileInputStream != null) { // proper way to do this kind of situation
        fileInputStream.close();
      }
    }
  }

  public static void tryWithResources() throws IOException {
    // try-with-resources, no need to call close() method manually
    // it will return suppressed exception, FileNotFoundException
    // and not NullPointerException which will be triggered in close method
    try(FileInputStream fileInputStream1 = new FileInputStream("textfile1.txt")) {
      // we do something with our resource
    }
  }

  public static void tryWithMultipleResources() throws IOException {
    // exapmle of try-with-resources having multiple resources
    try(FileInputStream fileInputStream1 = new FileInputStream("textfile1.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream1));
        FileWriter fileWriter = new FileWriter("somefile.txt")) {
      // close() method will be called automatically
      // and in the reverse order, because of depending resources, first we will have bufferedReader closed,
      // then fileInputStream1
      // ... some code dealing with resources
    }
  }

  public static int methodWithErrorCodes(double someDouble) {

    if (someDouble < 0.0) {
      // normally you would say: throw new NegativeNumberException("Negative numbers not allowed")
      return -1;
    }

    // for some different situations we would return some other codes
    // return -2, -3...

    return 0;
  }
}

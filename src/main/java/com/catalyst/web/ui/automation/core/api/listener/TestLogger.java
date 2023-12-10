package com.catalyst.web.ui.automation.core.api.listener;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.output.WriterOutputStream;

public class TestLogger {

  public static StringWriter requestWriter;
  public static PrintStream requestCapture;
  public static StringWriter responseWriter;
  public static PrintStream responseCapture;
  public static StringWriter errorWriter;
  public static PrintStream errorCapture;

  public TestLogger() {
  }

  public static void initLogger() {
    requestWriter = new StringWriter();
    responseWriter = new StringWriter();
    errorWriter = new StringWriter();
    requestCapture = new PrintStream(new WriterOutputStream(requestWriter, StandardCharsets.UTF_8),
        true);
    responseCapture = new PrintStream(
        new WriterOutputStream(responseWriter, StandardCharsets.UTF_8), true);
    errorCapture = new PrintStream(new WriterOutputStream(errorWriter, StandardCharsets.UTF_8),
        true);
  }
}

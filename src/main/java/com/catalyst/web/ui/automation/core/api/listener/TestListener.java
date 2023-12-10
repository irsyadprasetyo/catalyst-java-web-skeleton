package com.catalyst.web.ui.automation.core.api.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.extension.ExtensionContext;

//public class TestListener implements TestWatcher, AfterAllCallback, BeforeAllCallback {
public class TestListener {

  private final List<TestResultStatus> testResultsStatus = new ArrayList();
  //private TestrailAPIProperties testrailAPIProperties;

  public TestListener() {
  }

  public void afterAll(ExtensionContext context) {
    Map<TestResultStatus, Long> summary = this.testResultsStatus.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println(
        "Test result summary for " + context.getDisplayName() + " " + summary.toString());
  }

  public void testAborted(ExtensionContext context, Throwable cause) {
//        Testrail.updateTestrail(this.testrailAPIProperties.isUpdateTestCase(), this.testrailAPIProperties.isUpdateTestResult(), context.getTags(), context.getDisplayName(), 4, this.testrailAPIProperties.getRunId(), this.getAttachment());
//        this.testResultsStatus.add(com.pm.sdet.automation.api.demo.core.listener.TestListener.TestResultStatus.ABORTED);
  }

  public void testDisabled(ExtensionContext context, Optional<String> reason) {
//        Testrail.updateTestrail(this.testrailAPIProperties.isUpdateTestCase(), this.testrailAPIProperties.isUpdateTestResult(), context.getTags(), context.getDisplayName(), 3, this.testrailAPIProperties.getRunId(), this.getAttachment());
//        this.testResultsStatus.add(com.pm.sdet.automation.api.demo.core.listener.TestListener.TestResultStatus.DISABLED);
  }

  public void testFailed(ExtensionContext context, Throwable cause) {
//        Testrail.updateTestrail(this.testrailAPIProperties.isUpdateTestCase(), this.testrailAPIProperties.isUpdateTestResult(), context.getTags(), context.getDisplayName(), 5, this.testrailAPIProperties.getRunId(), this.getAttachment());
//        this.testResultsStatus.add(com.pm.sdet.automation.api.demo.core.listener.TestListener.TestResultStatus.FAILED);
  }

//    public void beforeAll(ExtensionContext context) throws Exception {
//        this.testrailAPIProperties = (TestrailAPIProperties)SpringExtension.getApplicationContext(context).getBean(TestrailAPIProperties.class);
//    }

  public void testSuccessful(ExtensionContext context) {
//        Testrail.updateTestrail(this.testrailAPIProperties.isUpdateTestCase(), this.testrailAPIProperties.isUpdateTestResult(), context.getTags(), context.getDisplayName(), 1, this.testrailAPIProperties.getRunId(), this.getAttachment());
//        this.testResultsStatus.add(com.pm.sdet.automation.api.demo.core.listener.TestListener.TestResultStatus.PASSED);
  }

//    private File getAttachment() {
//        return new File(this.testrailAPIProperties.getAttachmentPath());
//    }

  private enum TestResultStatus {
    PASSED, ABORTED, FAILED, DISABLED;

    TestResultStatus() {
    }
  }
}

package cc.schimmer.integration.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cc.schimmer.task.DownloadClientTask;
import org.junit.jupiter.api.Test;

class DownloadClientTaskTest extends TaskRunner {

  protected DownloadClientTaskTest() {
    super(DownloadClientTask.TASK_ID);
  }

  @Test
  void test_DownloadClientTask() {
    var result = build();
    assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"));
  }
}

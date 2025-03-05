package cc.schimmer.integration.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cc.schimmer.task.ExtractClientTask;
import org.junit.jupiter.api.Test;

class ExtractClientTaskTest extends TaskRunner {

  protected ExtractClientTaskTest() {
    super(ExtractClientTask.TASK_ID);
  }

  @Test
  void test_ExtractClientTask() {
    var result = build();
    assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"));
  }
}

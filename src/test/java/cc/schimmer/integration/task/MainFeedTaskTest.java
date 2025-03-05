package cc.schimmer.integration.task;

import static org.junit.jupiter.api.Assertions.*;

import cc.schimmer.task.MainFeedTask;
import org.junit.jupiter.api.Test;

class MainFeedTaskTest extends TaskRunner {

  protected MainFeedTaskTest() {
    super(MainFeedTask.TASK_ID);
  }

  @Test
  void test_MainFeedTask() {
    var result = build();
    assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"));
  }
}

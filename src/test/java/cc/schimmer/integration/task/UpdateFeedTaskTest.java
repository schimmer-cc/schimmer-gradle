package cc.schimmer.integration.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cc.schimmer.task.UpdateFeedTask;
import org.junit.jupiter.api.Test;

class UpdateFeedTaskTest extends TaskRunner {

  protected UpdateFeedTaskTest() {
    super(UpdateFeedTask.TASK_ID);
  }

  @Test
  void test_UpdateFeedTask() {
    var result = build();
    assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"));
  }
}

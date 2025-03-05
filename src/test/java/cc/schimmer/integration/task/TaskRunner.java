package cc.schimmer.integration.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.gradle.internal.impldep.com.google.common.io.Files;
import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.BeforeEach;

public abstract class TaskRunner {
  private final String taskId;
  TemporaryFolder projectDir = new TemporaryFolder();
  File buildFile;

  protected TaskRunner(String taskId) {
    this.taskId = taskId;
  }

  @BeforeEach
  void setUp() throws IOException {
    projectDir.create();
    projectDir.getRoot().deleteOnExit();
    buildFile = projectDir.newFile("build.gradle.kts");
    try (BufferedWriter writer = Files.newWriter(buildFile, Charset.defaultCharset())) {
      writer.write(
          """
          plugins {
            id("cc.schimmer-gradle")
          }
          """
              .trim()
              .stripIndent());
      writer.flush();
    }
  }

  public BuildResult build() {
    return GradleRunner.create()
        .withProjectDir(projectDir.getRoot())
        .withPluginClasspath()
        .withDebug(true)
        .withArguments(taskId)
        .build();
  }
}

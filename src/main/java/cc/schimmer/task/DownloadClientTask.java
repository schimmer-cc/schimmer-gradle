package cc.schimmer.task;

import cc.schimmer.SchimmerExtension;
import cc.schimmer.SchimmerGradle;
import java.io.*;
import okhttp3.Request;
import okhttp3.Response;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

public abstract class DownloadClientTask extends DefaultTask {
  public static final String TASK_ID = "client";

  @OutputFile
  public abstract RegularFileProperty getClientExe();

  public DownloadClientTask() {
    super();
    SchimmerExtension extension = SchimmerExtension.get(getProject());
    getClientExe()
        .convention(
            getProject()
                .getLayout()
                .getBuildDirectory()
                .dir(SchimmerGradle.GROUP_ID)
                .get()
                .file(extension.getClientName()));
    dependsOn(MainFeedTask.TASK_ID);
    setGroup(SchimmerGradle.GROUP_ID);
  }

  @TaskAction
  public void run() throws IOException {
    SchimmerExtension extension = SchimmerExtension.get(getProject());
    Request request =
        SchimmerGradle.newHttpRequest()
            .get()
            .url(extension.getDownloadBaseUrl() + extension.getClientName())
            .build();

    try (Response response = SchimmerGradle.HTTP_CLIENT.newCall(request).execute()) {
      if (!response.isSuccessful() || response.body() == null)
        throw new IllegalStateException("Failed to request mainfeed");

      File out = getClientExe().getAsFile().get();
      out.getParentFile().mkdirs();
      if (!out.createNewFile()) throw new IOException("failed to create file");

      OutputStream os = new FileOutputStream(out);
      os.write(response.body().bytes());
      os.flush();
      os.close();
    }
  }
}

package cc.schimmer.task;

import cc.schimmer.SchimmerExtension;
import cc.schimmer.SchimmerGradle;
import cc.schimmer.service.ZipExtractor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

public abstract class ExtractClientTask extends DefaultTask {
  public static final String TASK_ID = "clientJar";

  @InputFile
  public abstract RegularFileProperty getClientExe();

  @OutputFile
  public abstract RegularFileProperty getClientJar();

  public ExtractClientTask() {
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
    getClientJar()
        .convention(
            getProject()
                .getLayout()
                .getBuildDirectory()
                .dir(SchimmerGradle.GROUP_ID)
                .get()
                .file(extension.getClientJar()));
    dependsOn(DownloadClientTask.TASK_ID);
  }

  @TaskAction
  public void run() throws IOException {
    File clientExe = getClientExe().getAsFile().get();
    if (!clientExe.exists() || !clientExe.canRead())
      throw new IllegalStateException("client exe not found");
    byte[] clientExeData = Files.readAllBytes(clientExe.toPath());
    byte[] zipDate = ZipExtractor.extract(clientExeData);

    File out = getClientJar().getAsFile().get();
    out.getParentFile().mkdirs();
    if (!out.createNewFile()) throw new IOException("failed to create file");

    OutputStream os = new FileOutputStream(out);
    os.write(zipDate);
    os.flush();
    os.close();
  }
}

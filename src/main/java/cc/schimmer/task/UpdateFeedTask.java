package cc.schimmer.task;

import cc.schimmer.SchimmerExtension;
import cc.schimmer.SchimmerGradle;
import cc.schimmer.model.UpdateFeed;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;
import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public abstract class UpdateFeedTask extends DefaultTask {
  public static final String TASK_ID = "updatefeed";

  @Input
  public abstract Property<String> getUrl();

  public UpdateFeedTask() {
    super();
    SchimmerExtension extension = SchimmerExtension.get(getProject());
    getUrl().convention(extension.getUpdateFeedUrl());
    getOutputs().upToDateWhen(element -> extension.getUpdateFeed() != null);
    setGroup(SchimmerGradle.GROUP_ID);
  }

  @TaskAction
  public void run() throws IOException {
    Request request = SchimmerGradle.newHttpRequest().get().url(getUrl().get()).build();

    UpdateFeed updateFeed;
    try (Response response = SchimmerGradle.HTTP_CLIENT.newCall(request).execute()) {
      if (!response.isSuccessful() || response.body() == null)
        throw new IllegalStateException("Failed to request updatefeed");
      updateFeed =
          SchimmerGradle.XML_MAPPER.readValue(response.body().byteStream(), UpdateFeed.class);
    }

    SchimmerExtension.get(getProject()).setUpdateFeed(updateFeed);
    setDidWork(true);
  }
}

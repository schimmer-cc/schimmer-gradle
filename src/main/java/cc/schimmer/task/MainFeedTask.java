package cc.schimmer.task;

import cc.schimmer.SchimmerExtension;
import cc.schimmer.SchimmerGradle;
import cc.schimmer.model.MainFeed;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;
import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public abstract class MainFeedTask extends DefaultTask {
  public static final String TASK_ID = "mainfeed";

  @Input
  public abstract Property<String> getUrl();

  public MainFeedTask() {
    super();
    SchimmerExtension extension = SchimmerExtension.get(getProject());
    getUrl().convention(extension.getMainFeedUrl());
    getOutputs().upToDateWhen(element -> extension.getMainFeed() != null);
    setGroup(SchimmerGradle.GROUP_ID);
  }

  @TaskAction
  public void run() throws IOException {
    Request request = SchimmerGradle.newHttpRequest().get().url(getUrl().get()).build();

    MainFeed mainFeed;
    try (Response response = SchimmerGradle.HTTP_CLIENT.newCall(request).execute()) {
      if (!response.isSuccessful() || response.body() == null)
        throw new IllegalStateException("Failed to request mainfeed");
      mainFeed = SchimmerGradle.XML_MAPPER.readValue(response.body().byteStream(), MainFeed.class);
    }

    SchimmerExtension.get(getProject()).setMainFeed(mainFeed);
    setDidWork(true);
  }
}

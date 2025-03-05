package cc.schimmer;

import cc.schimmer.model.MainFeed;
import cc.schimmer.task.DownloadClientTask;
import cc.schimmer.task.ExtractClientTask;
import cc.schimmer.task.MainFeedTask;
import cc.schimmer.task.UpdateFeedTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class SchimmerGradle implements Plugin<Project> {
  public static final String EXTENSION_ID = "schimmer";
  public static final String GROUP_ID = "schimmer";
  public static final OkHttpClient HTTP_CLIENT =
      new OkHttpClient.Builder()
          .connectTimeout(30, TimeUnit.SECONDS)
          .readTimeout(30, TimeUnit.SECONDS)
          .build();
  public static final XmlMapper XML_MAPPER = new XmlMapper();
  public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
  public static MainFeed mainFeed = null;

  public static Request.Builder newHttpRequest() {
    return new Request.Builder()
        //        .addHeader("Accept-Encoding", "gzip, deflate")
        .addHeader("User-Agent", "Mozilla/5.0 (PokeMMO; Updater; windows)");
  }

  @Override
  public void apply(Project target) {
    loadDefaults(target.getExtensions().create(EXTENSION_ID, SchimmerExtension.class));
    target.getTasks().register(MainFeedTask.TASK_ID, MainFeedTask.class);
    target.getTasks().register(UpdateFeedTask.TASK_ID, UpdateFeedTask.class);
    target.getTasks().register(DownloadClientTask.TASK_ID, DownloadClientTask.class);
    target.getTasks().register(ExtractClientTask.TASK_ID, ExtractClientTask.class);
  }

  private void loadDefaults(SchimmerExtension extension) {
    extension.setMainFeedUrl("https://dl.pokemmo.com/live/current/feeds/main_feed.txt");
    extension.setUpdateFeedUrl("https://dl.pokemmo.com/live/current/feeds/update_feed.txt");
    extension.setDownloadBaseUrl("https://dl.pokemmo.com/live/current/client/");
    extension.setClientName("PokeMMO.exe");
    extension.setClientJar("PokeMMO.jar");
  }
}

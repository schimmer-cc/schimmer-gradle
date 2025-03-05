package cc.schimmer;

import cc.schimmer.model.MainFeed;
import cc.schimmer.model.UpdateFeed;
import org.gradle.api.Project;

public interface SchimmerExtension {
  static SchimmerExtension get(Project project) {
    return (SchimmerExtension) project.getExtensions().getByName(SchimmerGradle.EXTENSION_ID);
  }

  MainFeed getMainFeed();

  void setMainFeed(MainFeed mainFeed);

  UpdateFeed getUpdateFeed();

  void setUpdateFeed(UpdateFeed updateFeed);

  String getMainFeedUrl();

  void setMainFeedUrl(String url);

  String getUpdateFeedUrl();

  void setUpdateFeedUrl(String url);

  String getClientName();

  void setClientName(String name);

  String getClientJar();

  void setClientJar(String name);

  String getDownloadBaseUrl();

  void setDownloadBaseUrl(String url);
}

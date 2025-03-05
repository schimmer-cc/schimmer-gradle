package cc.schimmer.unit.xml;

import static org.gradle.internal.impldep.org.junit.Assert.*;

import cc.schimmer.SchimmerGradle;
import cc.schimmer.model.MainFeed;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class MainFeedTest extends AbstractXmlTest {

  public MainFeedTest() {
    super("/main_feed.txt");
  }

  @Test
  public void test_MainFeed_deserialization() throws JsonProcessingException {
    MainFeed mainFeed = SchimmerGradle.XML_MAPPER.readValue(xml, MainFeed.class);
    assertNotNull(mainFeed);
  }
}

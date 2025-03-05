package cc.schimmer.unit.xml;

import static org.gradle.internal.impldep.org.junit.Assert.assertNotNull;

import cc.schimmer.SchimmerGradle;
import cc.schimmer.model.UpdateFeed;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class UpdateFeedTest extends AbstractXmlTest {

  public UpdateFeedTest() {
    super("/update_feed.txt");
  }

  @Test
  public void test_MainFeed_deserialization() throws JsonProcessingException {
    UpdateFeed updateFeed = SchimmerGradle.XML_MAPPER.readValue(xml, UpdateFeed.class);
    assertNotNull(updateFeed);
  }
}

package cc.schimmer.unit.xml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.junit.jupiter.api.BeforeEach;

public class AbstractXmlTest {
  private final String resource;
  String xml;

  public AbstractXmlTest(String resource) {
    this.resource = resource;
  }

  @BeforeEach
  void setUp() throws IOException {
    try (InputStream is = AbstractXmlTest.class.getResourceAsStream(resource)) {
      if (is == null) throw new IllegalStateException("invalid resource name");
      xml = new String(is.readAllBytes(), Charset.defaultCharset());
    }
  }
}

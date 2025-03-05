package cc.schimmer.unit;

import static org.junit.jupiter.api.Assertions.*;

import cc.schimmer.service.SignatureScanner;
import java.nio.ByteBuffer;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SignatureScannerTest {

  public static Stream<Arguments> data_test_SignatureScanner_find() {
    return Stream.of(
        Arguments.of(new byte[] {1}, new byte[] {1}, new boolean[1], 0),
        Arguments.of(new byte[] {1, 2, 3, 5, 6, 7, 8, 9}, new byte[] {5, 6, 7}, new boolean[3], 3),
        Arguments.of(
            new byte[] {1, 2, 3, 5, 6, 7, 8, 9},
            new byte[] {5, 0, 7},
            new boolean[] {false, true, false},
            3));
  }

  @ParameterizedTest
  @MethodSource("data_test_SignatureScanner_find")
  public void test_SignatureScanner_find(
      byte[] data, byte[] signature, boolean[] mask, int expectedOffset) {
    ByteBuffer buffer = ByteBuffer.wrap(data);
    boolean found = SignatureScanner.find(buffer, signature, mask);
    assertTrue(found);
    assertEquals(expectedOffset, buffer.position());
  }
}

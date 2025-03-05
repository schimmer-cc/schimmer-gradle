package cc.schimmer.unit;

import static org.junit.jupiter.api.Assertions.*;

import cc.schimmer.service.ZipExtractor;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ZipExtractorTest {

  private static final byte[] ZIP_FILE_RAW = {
    (byte) 0x50, (byte) 0x4B, (byte) 0x03, (byte) 0x04, (byte) 0x0A, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xBB, (byte) 0x94, (byte) 0x69, (byte) 0x5A,
        (byte) 0x00, (byte) 0x00,
    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x74, (byte) 0x65,
    (byte) 0x73, (byte) 0x74, (byte) 0x2E, (byte) 0x74, (byte) 0x78, (byte) 0x74, (byte) 0x50,
        (byte) 0x4B, (byte) 0x01, (byte) 0x02, (byte) 0x1F, (byte) 0x00, (byte) 0x0A, (byte) 0x00,
        (byte) 0x00, (byte) 0x00,
    (byte) 0x00, (byte) 0x00, (byte) 0xBB, (byte) 0x94, (byte) 0x69, (byte) 0x5A, (byte) 0x00,
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00,
    (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x24, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x20, (byte) 0x00,
        (byte) 0x00, (byte) 0x00,
    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x74, (byte) 0x65, (byte) 0x73,
        (byte) 0x74, (byte) 0x2E, (byte) 0x74, (byte) 0x78, (byte) 0x74, (byte) 0x0A, (byte) 0x00,
        (byte) 0x20, (byte) 0x00,
    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x18,
        (byte) 0x00, (byte) 0x63, (byte) 0x64, (byte) 0xEE, (byte) 0xFD, (byte) 0x19, (byte) 0x91,
        (byte) 0xDB, (byte) 0x01,
    (byte) 0x63, (byte) 0x64, (byte) 0xEE, (byte) 0xFD, (byte) 0x19, (byte) 0x91, (byte) 0xDB,
        (byte) 0x01, (byte) 0x63, (byte) 0x64, (byte) 0xEE, (byte) 0xFD, (byte) 0x19, (byte) 0x91,
        (byte) 0xDB, (byte) 0x01,
    (byte) 0x50, (byte) 0x4B, (byte) 0x05, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x5A, (byte) 0x00,
        (byte) 0x00, (byte) 0x00,
    (byte) 0x26, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
  };
  private static final byte[] GARBAGE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

  private static byte[] combineArray(byte[] a, byte[] b) {
    byte[] combined = new byte[a.length + b.length];
    System.arraycopy(a, 0, combined, 0, a.length);
    System.arraycopy(b, 0, combined, a.length, b.length);
    return combined;
  }

  private static byte[] combineArray(byte[]... arrays) {
    if (arrays.length == 1) return arrays[0];
    byte[] tmp = new byte[0];
    for (byte[] array : arrays) {
      tmp = combineArray(tmp, array);
    }
    return tmp;
  }

  public static Stream<Arguments> data_test_ZipExtractor_extract() {
    return Stream.of(
        Arguments.of(ZIP_FILE_RAW, ZIP_FILE_RAW),
        Arguments.of(combineArray(GARBAGE, ZIP_FILE_RAW), ZIP_FILE_RAW),
        Arguments.of(combineArray(ZIP_FILE_RAW, GARBAGE), ZIP_FILE_RAW),
        Arguments.of(combineArray(GARBAGE, ZIP_FILE_RAW, GARBAGE), ZIP_FILE_RAW));
  }

  @ParameterizedTest
  @MethodSource("data_test_ZipExtractor_extract")
  public void test_ZipExtractor_extract(byte[] data, byte[] expectedZipData) {
    assertArrayEquals(ZipExtractor.extract(data), expectedZipData);
  }
}

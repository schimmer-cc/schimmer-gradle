package cc.schimmer.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ZipExtractor {
  private static final byte[] ZIP_MAGIC = new byte[] {0x50, 0x4B, 0x03, 0x04};
  private static final byte[] ZIP_EOCD_MAGIC = new byte[] {0x50, 0x4B, 0x05, 0x06};
  private static final int EOCD_COMMENT_LENGTH_OFFSET = 20;
  private static final int EOCD_HEADER_SIZE = 22;

  /**
   * Extracts a Zip File from a byte array. Leading and following garbage is ignored.
   *
   * @param chunk input chunk
   * @return a byte array containing the Zip File
   */
  public static byte[] extract(byte[] chunk) {
    // Currently fails for empty Zip Files
    ByteBuffer buffer = ByteBuffer.wrap(chunk).order(ByteOrder.LITTLE_ENDIAN);
    if (!SignatureScanner.find(buffer, ZIP_MAGIC))
      throw new IllegalStateException("chunk doesnt contain Zip File");
    int zipStart = buffer.position();
    if (!SignatureScanner.find(buffer, ZIP_EOCD_MAGIC))
      throw new IllegalStateException("corrupted Zip File");

    int eocdOffset = buffer.position() - zipStart;
    int commentLength = buffer.getShort(buffer.position() + EOCD_COMMENT_LENGTH_OFFSET) & 0xFFFF;
    int zipSize = eocdOffset + EOCD_HEADER_SIZE + commentLength;

    byte[] zip = new byte[zipSize];
    buffer.position(zipStart).get(zip);
    return zip;
  }
}

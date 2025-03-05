package cc.schimmer.service;

import java.nio.ByteBuffer;

public class SignatureScanner {

  /**
   * Finds a byte signature in a {@link java.nio.ByteBuffer} using a boolean mask. <br>
   * The reader index of the ByteBuffer is moved in front of the found signature or not moved.
   *
   * @param data the data to find the signature in
   * @param signature the signature to find in the data
   * @param mask a mask marking bytes of the signature as non-mandatory
   * @return true if the signature was found, false if not.
   */
  public static boolean find(ByteBuffer data, byte[] signature, boolean[] mask) {
    if (signature.length != mask.length)
      throw new IllegalStateException("signature and mask do not match");
    if (signature.length > data.remaining()) return false;
    int sigIdx = 0;
    int dataStart = data.position();
    while (data.hasRemaining()) {
      if (data.get() == signature[sigIdx] || mask[sigIdx]) sigIdx++;
      else sigIdx = 0;
      if (sigIdx == signature.length) {
        data.position(data.position() - signature.length);
        return true;
      }
    }
    data.position(dataStart);
    return false;
  }

  public static boolean find(ByteBuffer data, byte[] signature) {
    return find(data, signature, new boolean[signature.length]);
  }
}

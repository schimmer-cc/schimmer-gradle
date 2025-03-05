package cc.schimmer.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PatchBSD {
  @JacksonXmlProperty(isAttribute = true)
  private String name;

  @JacksonXmlProperty(isAttribute = true, localName = "r")
  private int revision;

  @JacksonXmlProperty(isAttribute = true)
  private String sha256;

  @JacksonXmlProperty(isAttribute = true)
  private long size;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRevision() {
    return revision;
  }

  public void setRevision(int revision) {
    this.revision = revision;
  }

  public String getSha256() {
    return sha256;
  }

  public void setSha256(String sha256) {
    this.sha256 = sha256;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }
}

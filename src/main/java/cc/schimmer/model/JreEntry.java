package cc.schimmer.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class JreEntry {
  @JacksonXmlProperty(localName = "arch")
  private String arch;

  @JacksonXmlProperty(localName = "name")
  private String name;

  @JacksonXmlProperty(localName = "os_name")
  private String os;

  @JacksonXmlProperty(localName = "sha256")
  private String sha256;

  @JacksonXmlProperty(localName = "size")
  private long size;

  public String getArch() {
    return arch;
  }

  public void setArch(String arch) {
    this.arch = arch;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
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

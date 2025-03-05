package cc.schimmer.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class FileEntry {
  @JacksonXmlProperty(localName = "name")
  private String name;

  @JacksonXmlProperty(localName = "only_if_not_exists")
  private Boolean onlyIfNotExists;

  @JacksonXmlProperty(localName = "sha256")
  private String sha256;

  @JacksonXmlProperty(localName = "size")
  private Long size;

  @JacksonXmlProperty(localName = "option_name")
  private String option;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getOnlyIfNotExists() {
    return onlyIfNotExists;
  }

  public void setOnlyIfNotExists(Boolean onlyIfNotExists) {
    this.onlyIfNotExists = onlyIfNotExists;
  }

  public String getSha256() {
    return sha256;
  }

  public void setSha256(String sha256) {
    this.sha256 = sha256;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }
}

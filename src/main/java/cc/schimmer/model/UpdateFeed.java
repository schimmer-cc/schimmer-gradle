package cc.schimmer.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "update_feed")
public class UpdateFeed {

  @JacksonXmlProperty(localName = "min_osx_installer_version")
  private String minOsxInstallerVersion;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "file")
  private List<FileEntry> files;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "jre")
  private List<JreEntry> jreEntries;

  public String getMinOsxInstallerVersion() {
    return minOsxInstallerVersion;
  }

  public void setMinOsxInstallerVersion(String minOsxInstallerVersion) {
    this.minOsxInstallerVersion = minOsxInstallerVersion;
  }

  public List<FileEntry> getFiles() {
    return files;
  }

  public void setFiles(List<FileEntry> files) {
    this.files = files;
  }

  public List<JreEntry> getJreEntries() {
    return jreEntries;
  }

  public void setJreEntries(List<JreEntry> jreEntries) {
    this.jreEntries = jreEntries;
  }
}

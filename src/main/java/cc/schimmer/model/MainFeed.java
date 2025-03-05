package cc.schimmer.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "main_feed")
public class MainFeed {
  private String ip;
  private String ipSea;
  private int port;
  private int minRevision;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "updater_location")
  private List<String> updaterLocations;

  private String updaterHashSha256;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "android_apk_location")
  private List<String> androidApkLocations;

  private String androidApkSha256;
  private long androidApkSize;

  private AndroidPatches androidPatches;

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getIpSea() {
    return ipSea;
  }

  @JacksonXmlProperty(localName = "ip_sea")
  public void setIpSea(String ipSea) {
    this.ipSea = ipSea;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getMinRevision() {
    return minRevision;
  }

  @JacksonXmlProperty(localName = "min_revision")
  public void setMinRevision(int minRevision) {
    this.minRevision = minRevision;
  }

  public List<String> getUpdaterLocations() {
    return updaterLocations;
  }

  public void setUpdaterLocations(List<String> updaterLocations) {
    this.updaterLocations = updaterLocations;
  }

  public String getUpdaterHashSha256() {
    return updaterHashSha256;
  }

  @JacksonXmlProperty(localName = "updater_hash_sha256")
  public void setUpdaterHashSha256(String updaterHashSha256) {
    this.updaterHashSha256 = updaterHashSha256;
  }

  public List<String> getAndroidApkLocations() {
    return androidApkLocations;
  }

  public void setAndroidApkLocations(List<String> androidApkLocations) {
    this.androidApkLocations = androidApkLocations;
  }

  public String getAndroidApkSha256() {
    return androidApkSha256;
  }

  @JacksonXmlProperty(localName = "android_apk_sha256")
  public void setAndroidApkSha256(String androidApkSha256) {
    this.androidApkSha256 = androidApkSha256;
  }

  public long getAndroidApkSize() {
    return androidApkSize;
  }

  @JacksonXmlProperty(localName = "android_apk_size")
  public void setAndroidApkSize(long androidApkSize) {
    this.androidApkSize = androidApkSize;
  }

  public AndroidPatches getAndroidPatches() {
    return androidPatches;
  }

  @JacksonXmlProperty(localName = "android_patches")
  public void setAndroidPatches(AndroidPatches androidPatches) {
    this.androidPatches = androidPatches;
  }
}

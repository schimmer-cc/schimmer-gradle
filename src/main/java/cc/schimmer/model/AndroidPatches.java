package cc.schimmer.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class AndroidPatches {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "base_location")
  private List<String> baseLocations;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "patch")
  private List<Patch> patches;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "patch_bsd")
  private List<PatchBSD> patchesBsd;

  // Getters and Setters
  public List<String> getBaseLocations() {
    return baseLocations;
  }

  public void setBaseLocations(List<String> baseLocations) {
    this.baseLocations = baseLocations;
  }

  public List<Patch> getPatches() {
    return patches;
  }

  public void setPatches(List<Patch> patches) {
    this.patches = patches;
  }

  public List<PatchBSD> getPatchesBsd() {
    return patchesBsd;
  }

  public void setPatchesBsd(List<PatchBSD> patchesBsd) {
    this.patchesBsd = patchesBsd;
  }
}

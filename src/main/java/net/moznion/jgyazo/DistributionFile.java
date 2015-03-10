package net.moznion.jgyazo;

import lombok.Getter;

import java.util.Optional;

@Getter
public class DistributionFile {
  private final byte[] imageData;
  private final String contentType;

  private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";

  public DistributionFile(byte[] imageData, Optional<String> maybeContentType) {
    this.imageData = imageData;
    this.contentType = maybeContentType.orElse(DEFAULT_CONTENT_TYPE);
  }
}

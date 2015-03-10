package net.moznion.jgyazo;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Part;

public class ImageStore {
  private final static String IMAGE_DIR = ConfigLoader.getConfig().getImageDir();

  public static String storeUploadedFile(final Part uploaded) throws NoSuchAlgorithmException,
      IOException {
    // TODO should validate a content type

    byte[] imageData = IOUtils.toByteArray(uploaded.getInputStream());

    MessageDigest messageDigestSHA1 = MessageDigest.getInstance("SHA-1");
    byte[] hash = messageDigestSHA1.digest(imageData);

    String extension = FilenameUtils.getExtension(uploaded.getSubmittedFileName());

    String fileName = new StringBuilder(Hex.encodeHexString(hash))
        .append(".")
        .append(extension)
        .toString();
    Path filePath = Paths.get(IMAGE_DIR, fileName);

    Files.write(filePath, imageData);

    return fileName;
  }
}

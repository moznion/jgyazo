package net.moznion.jgyazo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import me.geso.mech2.Mech2Result;

import net.moznion.jgyazo.ConfigLoader;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class RootControllerTest extends ControllerTestBase {
  private static File imageDir = new File(ConfigLoader.getConfig().getImageDir());

  @Before
  public void initializeImageDirectory() throws IOException {
    FileUtils.deleteDirectory(imageDir);
    imageDir.mkdir();
  }

  @Test
  public void postingImageShuoldBeSuccessful() throws IOException, URISyntaxException {
    final Mech2Result result =
        getMech()
            .postMultipart("/")
            .addBinaryBody("imagedata",
                new File(this.getClass().getClassLoader().getResource("lena.jpg").toURI()))
            .execute();

    assertEquals(200, result.getResponse().getStatusLine().getStatusCode());
    assertEquals("{\"file_name\":\"3444d453367af67e18dd20f99cdb4d90397a1fa9.jpg\"}",
        result.getResponseBodyAsString());

    long fileSize =
        Paths.get(imageDir.getPath(), "3444d453367af67e18dd20f99cdb4d90397a1fa9.jpg").toFile()
            .length();
    assertTrue(fileSize > 0);
  }

  @Test
  public void postingImageWithoutEntity() throws URISyntaxException, IOException {
    final Mech2Result result = getMech()
        .postMultipart("/")
        .execute();
    assertEquals(400, result.getResponse().getStatusLine().getStatusCode());
  }
}

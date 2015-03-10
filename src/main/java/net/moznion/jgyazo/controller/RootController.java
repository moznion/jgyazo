package net.moznion.jgyazo.controller;

import com.google.common.collect.ImmutableMap;

import freemarker.template.TemplateException;
import me.geso.avans.annotation.POST;
import me.geso.webscrew.response.WebResponse;

import net.moznion.jgyazo.ImageStore;
import net.moznion.jgyazo.response.StringAPIResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

public class RootController extends BaseController {
  @POST("/")
  public WebResponse postImage()
      throws IOException, TemplateException, NoSuchAlgorithmException, IllegalStateException,
      ServletException {
    Part uploadedImage = this.getServletRequest().getPart("imagedata");
    if (uploadedImage == null) {
      return this.renderJSON(400, new StringAPIResponse(
          "Must attach an image through a part `imagedata` of multipart"));
    }

    String uploadedFileName = ImageStore.storeUploadedFile(uploadedImage);
    return this.renderJSON(200, ImmutableMap.builder()
        .put("file_name", uploadedFileName)
        .build());
  }
}

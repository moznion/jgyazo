package net.moznion.jgyazo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import me.geso.avans.BasicAPIResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class StringAPIResponse extends BasicAPIResponse {
  private String data;

  public StringAPIResponse(String data) {
    this.data = data;
  }
}

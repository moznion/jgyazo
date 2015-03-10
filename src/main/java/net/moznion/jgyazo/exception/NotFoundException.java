package net.moznion.jgyazo.exception;

public class NotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public NotFoundException(String fileName) {
    super(new StringBuilder()
        .append("File not found: ")
        .append(fileName)
        .toString());
  }
}

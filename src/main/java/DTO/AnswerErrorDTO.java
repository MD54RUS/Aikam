package DTO;

public class AnswerErrorDTO extends Answer {
  private String message;

  public AnswerErrorDTO(String message) {
    type = "error";
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}

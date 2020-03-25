package InputOutput;

import DTO.Answer;
import DTO.AnswerSearchDTO;

import java.io.IOException;

public interface Writer {
    void write(Answer answer) throws IOException;
}

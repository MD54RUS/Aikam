package InputOutput;

import DTO.AnswerSearch;

import java.io.IOException;

public interface Writer {
    void write(AnswerSearch answer) throws IOException;
}

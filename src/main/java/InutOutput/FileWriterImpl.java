package InutOutput;

import DTO.AnswerSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.io.File;
import java.io.IOException;

public class FileWriterImpl implements Writer {
    private File file;

    public FileWriterImpl(String filename) {
        file = new File(filename);
    }

    public void write(AnswerSearch answer) throws IOException {
        final JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, answer);
    }
}

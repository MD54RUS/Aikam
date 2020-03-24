package InutOutput;

import entity.Criteria;

import java.io.IOException;
import java.util.List;

public interface Reader {
    List<Criteria> get() throws IOException;
}

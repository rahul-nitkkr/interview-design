package loader.source;

import loader.model.Data;

import java.io.IOException;

public interface Source {

    public Data readData() throws IOException;
}

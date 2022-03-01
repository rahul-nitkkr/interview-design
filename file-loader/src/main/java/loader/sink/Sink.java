package loader.sink;

import loader.model.Data;

import java.sql.SQLException;

public interface Sink {

    public void loadData(Data data) throws SQLException;
}

package loader;

import loader.sink.Sink;
import loader.sink.SnowflakeSink;
import loader.source.FileSource;
import loader.source.Source;

import java.io.IOException;
import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) throws SQLException, IOException {
        Source source = new FileSource("/Users/rahulroy/Desktop/test_load.csv");
        Sink sink = new SnowflakeSink("jo40229.europe-west4.gcp" , "TEST_SCHEMA" , "DEMO_DB" , "DEMO_LOAD");

        sink.loadData(source.readData());
    }
}

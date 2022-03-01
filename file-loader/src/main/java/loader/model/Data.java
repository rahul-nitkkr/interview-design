package loader.model;

import java.util.List;

public class Data {
    private final List<String> cols;
    private final List<String> data;

    public Data(List<String> cols, List<String> data) {
        this.cols = cols;
        this.data = data;
    }

    public List<String> getCols() {
        return cols;
    }

    public List<String> getData() {
        return data;
    }
}

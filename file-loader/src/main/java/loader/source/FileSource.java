package loader.source;

import loader.model.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSource implements Source {

    private final String filePath;

    public FileSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Data readData() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(filePath));
        List<String> cols = Arrays.stream(allLines.get(0).split(",")).collect(Collectors.toList());
        List<String> data = allLines.subList(1, allLines.size());

        return new Data(cols, data);
    }
}

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Мар'ян on 07.05.2016.
 */
public class CSVToList {
    private CSVReader reader;
    private List<String[]> fileBody;

    public CSVToList(String fileName) throws IOException {
        reader = new CSVReader(new FileReader(fileName));
        fileBody = reader.readAll();
        reader.close();
    }

    public List<String[]> getFileBody() {
        return fileBody;
    }
}

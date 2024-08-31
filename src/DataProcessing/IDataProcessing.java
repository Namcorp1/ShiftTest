package DataProcessing;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDataProcessing {
    public void processingCommandLine(String input);
    public void processinginputFile(String name) throws IOException;
    public void processingFileLine(String line) throws IOException;
    default void writeToFile(String fileName, String line) throws IOException {
    }
}

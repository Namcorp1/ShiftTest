import View.View;
import DataProcessing.DataProcessing;
import DataProcessing.Statistics;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException {
        Presenter presenter = new Presenter(new View(),new DataProcessing(),new Statistics());
        presenter.start();
    }
}

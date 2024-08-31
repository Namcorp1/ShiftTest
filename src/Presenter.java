import View.View;
import DataProcessing.DataProcessing;
import DataProcessing.Statistics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Presenter {
    View view;
    DataProcessing dataProcessing;
    Statistics statistics;

    public Presenter(View view, DataProcessing dataProcessing, Statistics statistics){
        this.view = view;
        this.dataProcessing = dataProcessing;
        this.statistics = statistics;
    }

    public void start() throws IOException {
        int mode = view.startProgramm();
        String commandLine = null;
        if (mode == 2){
            commandLine = view.inputCommandLine();
            dataProcessing.processingCommandLine(commandLine);
        }
        String[] files = view.inputFile();
        for(int i =0;i< files.length;i++){
            dataProcessing.processinginputFile(files[i]);
        }
        if(DataProcessing.shortStatistics || DataProcessing.fullStatistics){
            statistics.printStats();
        }
    }
}

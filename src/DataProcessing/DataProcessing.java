package DataProcessing;
import java.io.FileReader;
import java.io.*;
import java.util.Scanner;

public class DataProcessing implements IDataProcessing{
    private static final String DEFAULT_INTEGER_FILE = "integers.txt";
    private static final String DEFAULT_FLOAT_FILE = "floats.txt";
    private static final String DEFAULT_STRING_FILE = "strings.txt";
    private static String currentDir = System.getProperty("user.dir") + "/src/";
    private static File outputPath;
    public static String filePrefix = "";
    public static boolean appendToFile = false;
    public static boolean fullStatistics = false;
    public static boolean shortStatistics = false;

    @Override
    public void processingCommandLine(String input) {
        String[] commands = input.split(" ");
        for(int i = 0; i < commands.length; i++){
            String command = commands[i];
            if (command.startsWith("-")){
                switch(command) {
                    case "-o":
                        outputPath = new File(currentDir + commands[++i]);
                        outputPath.mkdirs();
                        break;
                    case "-p":
                        filePrefix = commands[++i];
                        break;
                    case "-s":
                        shortStatistics = true;
                        break;
                    case "-f":
                        fullStatistics = true;
                        break;
                    case "-a":
                        appendToFile = true;
                        break;
                    default:
                        String outputText = "Неизвестная команда: " + commands;
                        System.out.print(outputText);
                }
            }
        }
    }

    @Override
    public void processinginputFile(String name) throws IOException {
        File inputFile = new File(currentDir,name);
        FileReader file = new FileReader(inputFile);
        Scanner scanFile = new Scanner(file);
        while(scanFile.hasNextLine()){
            processingFileLine(scanFile.nextLine());
        }
        file.close();
    }

    @Override
    public void processingFileLine(String line) throws IOException {
        Statistics statistics = new Statistics();
        if (line.isEmpty()) return;
        try {
            int num = Integer.parseInt(line);
            writeToFile(DEFAULT_INTEGER_FILE,line);
            statistics.addStats("int",num);
        } catch (NumberFormatException e) {
            try {
                float num = Float.parseFloat(line);
                writeToFile(DEFAULT_FLOAT_FILE, line);
                statistics.addStats("double",num);
            } catch (NumberFormatException e2) {
                writeToFile(DEFAULT_STRING_FILE, line);
                statistics.addStats("string",line);
            }
        }
    }

    @Override
    public void writeToFile(String fileName, String line) throws IOException {
        File outputFile = new File(outputPath, filePrefix + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, appendToFile))) {
            writer.write(line);
            writer.newLine();
        }
    }
}

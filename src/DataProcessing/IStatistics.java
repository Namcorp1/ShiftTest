package DataProcessing;

public interface IStatistics {
    void addStats(String type, int num);
    void addStats(String type, double num);
    void addStats(String type, String line);

    void printStats();

}

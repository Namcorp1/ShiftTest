package DataProcessing;
import java.util.HashMap;
import java.util.Map;

public class Statistics implements IStatistics {
    public int count = 0;
    public int minValueInt = Integer.MAX_VALUE;
    public int maxValueInt = Integer.MIN_VALUE;
    public double minValueDouble = Double.MAX_VALUE;
    public double maxValueDouble = Double.MIN_VALUE;

    public int sumInt = 0;
    public double sumDouble = 0.0;
    public double avg = 0;
    public String shortestLine = "";
    public String longestLine = "";

    public static Map<String, String> SHORT_STATISTICS = Map.of(
            "int", "Integer.\nCount: %d",
            "double", "Double.\nCount: %d",
            "string", "String.\nCount: %d"
    );

    public static Map<String, String> FULL_STATISTICS = Map.of(
            "int", "Integer.\nCount: %d\nMin: %d\nMax: %d\nSum: %d\nAvg: %.2f",
            "double", "Double.\nCount: %d\nMin: %.2f\nMax: %.2f\nSum: %.2f\nAvg: %.2f",
            "string", "String.\nCount: %d\nShortestLine: %s\nLongestLine: %s"
    );

    private static Map<String, Statistics> stats = new HashMap<>();

    @Override
    public void addStats(String type, int num) {
        Statistics stat = stats.computeIfAbsent(type, k -> new Statistics());
        stat.count++;
        stat.minValueInt = Math.min(num, stat.minValueInt);
        stat.maxValueInt = Math.max(num, stat.maxValueInt);
        stat.sumInt += num;
        stat.avg = (double) stat.sumInt / stat.count;
    }
    @Override
    public void addStats(String type, double num) {
        Statistics stat = stats.computeIfAbsent(type, k -> new Statistics());
        stat.count++;
        stat.minValueDouble = Math.min(num, stat.minValueDouble);
        stat.maxValueDouble = Math.max(num, stat.maxValueDouble);
        stat.sumDouble += num;
        stat.avg = (double) stat.sumDouble / stat.count;
    }
    @Override
    public void addStats(String type, String str) {
        Statistics stat = stats.computeIfAbsent(type, k -> new Statistics());
        stat.count++;
        stat.shortestLine = stat.shortestLine.isEmpty() || str.length() < stat.shortestLine.length() ? str : stat.shortestLine;
        stat.longestLine = stat.longestLine.isEmpty() || str.length() > stat.longestLine.length() ? str : stat.longestLine;
    }


    @Override
    public void printStats() {
        for (Map.Entry<String, Statistics> entry : stats.entrySet()) {
            String type = entry.getKey();
            Statistics stat = entry.getValue();
            String statistics="";
            if(DataProcessing.fullStatistics){
                statistics = FULL_STATISTICS.get(type);
            }else if (DataProcessing.shortStatistics){
                statistics = SHORT_STATISTICS.get(type);
            };
            // не пойму почему не срабатывает конструкция switch, она прогоняет double по кейсу int тоже.
//            switch(type){
//                case "double":
//                    System.out.printf(statistics, stat.count, stat.minValueDouble,stat.maxValueDouble,stat.sumDouble, stat.avg);
//                case "int":
//                    System.out.printf(statistics, stat.count,stat.minValueInt, stat.maxValueInt, stat.sumInt, stat.avg);
//                default:
//                    System.out.printf(statistics, stat.count,stat.shortestLine, stat.longestLine);
//            }
            if(type == "double"){
                System.out.printf(statistics, stat.count, stat.minValueDouble,stat.maxValueDouble,stat.sumDouble, stat.avg);
            } else if(type == "int"){
                System.out.printf(statistics, stat.count,stat.minValueInt, stat.maxValueInt, stat.sumInt, stat.avg);
            } else{
                System.out.printf(statistics, stat.count,stat.shortestLine, stat.longestLine);
            }
            System.out.println();
        }
    }

}

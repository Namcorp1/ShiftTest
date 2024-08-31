package View;
import java.util.Scanner;
public class View implements IView {

    Scanner inputText = new Scanner(System.in);

    @Override
    public int startProgramm() {
        System.out.println("Добро пожаловать!" +
                "\nДанная программа позволяет записывать разные типы файлов в отдельные файлы." +
                "\nДля работы программы необходимо разместить исходные файлы в папке inputData." +
                "\nВыберите режим работы программы:" +
                "\n1 - по умолчанию (файлы располагаются в текущей папки, файлы перезаписываются);" +
                "\n2 - пользовательский режим (ввод команды согласно условным обозначениям)." +
                "\nУсловные обозначения:" +
                "\n'-o' - указание пути для сохранения файлов;" +
                "\n'-a' - добавление записей в существующий файл, без перезаписи" +
                "\n'-p' - указание префикса имени файлов;" +
                "\n'-s' - краткая статистика (количество элементов записанных в исходящие файлы);" +
                "\n'-f' - полная статистика (дополнительные количественный параметры для каждого типа файлов).");
        System.out.println();
        boolean checkInputMode = true;
        String mode = "";
        while (checkInputMode) {
            System.out.print("Введите режим работы программы: ");
            mode = inputText.next();
            switch (mode) {
                case "1":
                    checkInputMode = false;
                    System.out.println("Выбран режим по умолчанию!");
                    break;
                case "2":
                    checkInputMode = false;
                    System.out.println("Выбран пользовательский режим!");
                    break;
                default:
                    System.out.println("Некорректная команда!");
            }
        }
        return Integer.parseInt(mode);
    }

    @Override
    public String inputCommandLine() {
        System.out.println("Введите команду в формате условных обозначения команды и их значений через пробел." +
                "\nНапример: -o /some/path -p result_ -a");
        System.out.println("Введите команду: ");
        inputText.nextLine();
        String commandLine = inputText.nextLine();
        return commandLine;
    }

    @Override
    public String[] inputFile() {
        System.out.println("Введите наименование файла(-ов), при указании нескольких файлов в качестве разделителя использовуйте запятую.");
        String inputFiles = inputText.next();
        String[] files = inputFiles.split(",");
        return files;
    }
}
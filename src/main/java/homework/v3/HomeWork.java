package homework.v3;


import com.fasterxml.jackson.databind.ObjectMapper;
import homework.v3.externalizable.JsonFileClass;


import java.io.*;

/**
 * Задание
 * 1) Составить файл с JSON-объектом, который "разложен" в пакете homework.v3.entity.
 * Определить какой элемент является корневым
 * Дать имя файлу homework.parameters.json
 * 2) Заполнить его значениями (как пример "parameters.v1.json")
 * 3) Считать получившийся homework.parameters.json в память
 * 4) Сериализовать его с помощью встроенного механиза сериализации в файл с именем homework.parameters.ser
 * 5) Сериализовать его с использованием интерфейса Externalizable в файл с именем homework.parameters.exter
 * 6) Считать данные из файла homework.parameters.ser в память и сохранить в формате JSON в файл с именем homework.result.ser.parameters.json
 * 7) Считать данные из файла homework.parameters.exter в память и сохранить в формате JSON в файл с именем homework.result.exter.parameters.json
 * 8) Убедиться, что файлы homework.result.ser.parameters.json и  homework.result.exter.parameters.json
 * совпадают с homework.parameters.json
 */
public class HomeWork {

    private static final String JSON_FILE_NAME = "homework.parameters.json";
    private static final String SERIALIZE_FILE_NAME = "homework.parameters.ser";
    private static final String EXTERNALIZE_FILE_NAME = "homework.parameters.exter";
    private static final String JSON_SERIALIZE_FILE_NAME = "homework.result.ser.parameters.json";
    private static final String JSON_EXTERNALIZE_FILE_NAME = "homework.result.exter.parameters.json";

    private static <T> void writeJson(File file, T json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, json);
    }

    private static <T> void writeJson(String fileName, T json) throws IOException {
        final File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, json);
    }


    private static <T> T readJson(File file, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, clazz);
    }

    private static void serialize(String fileName, Object obj) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fos);) {
            out.writeObject(obj);
        }
    }

    private static <T> T deserialize(String fileName, Class<T> clazz) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fis);) {
            return clazz.cast(in.readObject());
        }
    }

    private static void filesCompare() {
        FileComparator comparator = new FileComparator();
        File jsonFile = new File(JSON_FILE_NAME);
        File jsonSerializeFile = new File(JSON_SERIALIZE_FILE_NAME);
        File jsonExternalizeFile = new File(JSON_EXTERNALIZE_FILE_NAME);
        if (comparator.compare(jsonFile, jsonSerializeFile) == 0) {
            System.out.println(String.format("Файлы %s и %s совпадают.", JSON_FILE_NAME, JSON_SERIALIZE_FILE_NAME));
        } else {
            System.out.println(String.format("Файлы %s и %s не совпадают.", JSON_FILE_NAME, JSON_SERIALIZE_FILE_NAME));
        }
        if (comparator.compare(jsonFile, jsonExternalizeFile) == 0) {
            System.out.println(String.format("Файлы %s и %s совпадают.", JSON_FILE_NAME, JSON_EXTERNALIZE_FILE_NAME));
        } else {
            System.out.println(String.format("Файлы %s и %s не совпадают.", JSON_FILE_NAME, JSON_EXTERNALIZE_FILE_NAME));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File(JSON_FILE_NAME);
        //шаг 1 и 2
        if (!file.exists()) {
            homework.v3.entity.JsonFileClass jsonFile = JsonFileCreator.getInstance();
            System.out.println("Записываем данные в файла " + JSON_FILE_NAME);
            writeJson(file, jsonFile);
            System.out.println("Данные записаны на диск в файл " + JSON_FILE_NAME);
        }

        //шаг 3
        System.out.println("Чтение данных из файла " + JSON_FILE_NAME);
        homework.v3.entity.JsonFileClass jsonFile = readJson(file, homework.v3.entity.JsonFileClass.class);
        System.out.println("Данные считаны\n" + jsonFile.toString());
        //шаг 4
        System.out.println("Записываем данные в файла " + SERIALIZE_FILE_NAME);
        serialize(SERIALIZE_FILE_NAME, jsonFile);
        System.out.println("Данные записаны на диск в файл " + SERIALIZE_FILE_NAME);
        //шаг 5
        System.out.println("Чтение данных из файла " + JSON_FILE_NAME);
        JsonFileClass jsonFile2 = readJson(file, JsonFileClass.class);
        System.out.println("Данные считаны\n" + jsonFile.toString());

        System.out.println("Записываем данные в файла " + EXTERNALIZE_FILE_NAME);
        serialize(EXTERNALIZE_FILE_NAME, jsonFile2);
        System.out.println("Данные записаны на диск в файл " + EXTERNALIZE_FILE_NAME);
        //шаг 6
        System.out.println("Чтение данных из файла " + SERIALIZE_FILE_NAME);
        homework.v3.entity.JsonFileClass serializeFileClass = deserialize(SERIALIZE_FILE_NAME, homework.v3.entity.JsonFileClass.class);
        System.out.println("Данные считаны\n" + serializeFileClass.toString());
        //шаг 7
        System.out.println("Чтение данных из файла " + EXTERNALIZE_FILE_NAME);
        JsonFileClass externalizeFileClass = deserialize(EXTERNALIZE_FILE_NAME, JsonFileClass.class);
        System.out.println("Данные считаны\n" + externalizeFileClass.toString());
        //шаг8
        System.out.println("Записываем данные в файла " + JSON_SERIALIZE_FILE_NAME);
        writeJson(JSON_SERIALIZE_FILE_NAME, serializeFileClass);
        System.out.println("Данные записаны на диск в файл " + JSON_SERIALIZE_FILE_NAME);

        System.out.println("Записываем данные в файла " + JSON_EXTERNALIZE_FILE_NAME);
        writeJson(JSON_EXTERNALIZE_FILE_NAME, externalizeFileClass);
        System.out.println("Данные записаны на диск в файл " + JSON_EXTERNALIZE_FILE_NAME);
        //сравнение файлов
        filesCompare();
    }
}

package homework.v3;


import com.fasterxml.jackson.databind.ObjectMapper;
import homework.v3.entity.JsonFileClass;


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
    private static final String BASE_SERIALIZE_FILE_NAME = "homework.parameters.ser";

    public static void writeJson(File url, JsonFileClass clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(url, clazz);
    }

    public static <T> T readJson(File url, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(url, clazz);
    }

    public static void serialize(String fileName, Object obj) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fos);) {
            out.writeObject(obj);
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File(JSON_FILE_NAME);
        //шаг 1 и 2
        if (!file.exists()) {
            JsonFileClass jsonFile = JsonFileCreator.getInstance();
            System.out.println("Записываем данные в файла " + JSON_FILE_NAME);
            writeJson(file, jsonFile);
            System.out.println("Данные записаны на диск в файл " + JSON_FILE_NAME);
        }

        //шаг 3
        System.out.println("Чтение данных из файла " + JSON_FILE_NAME);
        JsonFileClass jsonFile = readJson(file, JsonFileClass.class);
        System.out.println("Данные считаны\n" + jsonFile.toString());
        //шаг 4
        System.out.println("Записываем данные в файла " + BASE_SERIALIZE_FILE_NAME);
        serialize(BASE_SERIALIZE_FILE_NAME, jsonFile);
        System.out.println("Данные записаны на диск в файл " + BASE_SERIALIZE_FILE_NAME);
    }
}

package classwork.step3;

import org.codehaus.jackson.map.ObjectMapper;
import classwork.step3.v3.Fallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * temp.step3.out -> result_parameters.step3.json
 * */

public class ExternalizeReadMapperWrite {
	
	public static final String RESULT_PARAMETERS_STEP_3_JSON = "result_parameters.step3.json";
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		String fileName = "temp.step3.out";
		
		System.out.println("Читаем из файла " + fileName);
		FileInputStream fileInputStream = new FileInputStream(fileName);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		Fallback business = (Fallback)objectInputStream.readObject();
		System.out.println("Данные считаны\n" + business.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Записываем данные в файла " + RESULT_PARAMETERS_STEP_3_JSON);
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RESULT_PARAMETERS_STEP_3_JSON), business);
		System.out.println("Данные записаны на диск в файл " + RESULT_PARAMETERS_STEP_3_JSON);
		
	}
	
}

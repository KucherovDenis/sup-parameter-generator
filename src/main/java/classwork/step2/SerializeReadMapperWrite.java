package classwork.step2;

import classwork.params.da.v2.JsonParameterSerializeReader;
import classwork.params.entity.template.v2.Fallback;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * читаем из temp.out
 *
 * serializeReader -> objectMapper
 *
 * temp.v2.out -> result_parameters.v2.json
 * */
public class SerializeReadMapperWrite {
	
	public static final String RESULT_PARAMETERS_V_2_JSON = "result_parameters.v2.json";
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		String fileName = "temp.v2.out";
		
		Fallback business = null;
		System.out.println("Значение business: " + business);
		JsonParameterSerializeReader myReader = new JsonParameterSerializeReader();
		System.out.println("Считываем данные из файла " + fileName);
		business = myReader.customSerializeReader(fileName);
		System.out.println("Данные считаны с диска из файл " + fileName);
		System.out.println("Значение business: " + business);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Записываем данные в файла " + RESULT_PARAMETERS_V_2_JSON);
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RESULT_PARAMETERS_V_2_JSON), business);
		System.out.println("Данные записаны на диск в файл " + RESULT_PARAMETERS_V_2_JSON);
	}
	
}

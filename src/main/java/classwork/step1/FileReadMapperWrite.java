package classwork.step1;

import classwork.params.da.v1.JsonParameterSerializeReader;
import classwork.params.entity.template.v1.Fallback;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * читаем из temp.out
 *
 * serializeReader -> objectMapper
 * temp.v1.out -> result_parameters.v1.json
 *
 * */
public class FileReadMapperWrite {
	
	public static final String RESULT_PARAMETERS_V_1_JSON = "result_parameters.v1.xml";
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		String fileName = "temp.v1.out";
		
		Fallback business = null;
		System.out.println("Значение business: " + business);
		JsonParameterSerializeReader myReader = new JsonParameterSerializeReader();
		System.out.println("Считываем данные из файла " + fileName);
		business = myReader.customSerializeReader(fileName);
		System.out.println("Данные считаны с диска из файл " + fileName);
		System.out.println("Значение business: " + business);
		
		//ObjectMapper mapper = new ObjectMapper();
		System.out.println("Записываем данные в файла " + RESULT_PARAMETERS_V_1_JSON);
		XmlMapper mapper  = new XmlMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RESULT_PARAMETERS_V_1_JSON), business);
		//mapper.writerWithDefaultPrettyPrinter().writeValue(new File(RESULT_PARAMETERS_V_1_JSON), business);
		System.out.println("Данные записаны на диск в файл " + RESULT_PARAMETERS_V_1_JSON);
	}
	
}

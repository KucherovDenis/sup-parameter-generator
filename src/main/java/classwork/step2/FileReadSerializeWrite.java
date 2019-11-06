package classwork.step2;

import classwork.params.da.v2.JsonParameterReader;
import classwork.params.da.v2.JsonParameterSerializeWriter;
import classwork.params.entity.template.v2.Fallback;

/**
 * @author Evgeni Korolev
 * @date 03.04.2018
 */
public class FileReadSerializeWrite {
	
	
	public static final String TEMP_V_2_OUT = "temp.v2.out";
	
	/**
     * Из файла parameters.json считываем данные
     * После сериазилуем в файл temp.out
     * Далее перегоняем потоком в result_parameters.json
     *
     * Смысл задания - сериализовать объект в файл.
     * Из файла считать - записать в новый файл и сравнить с исходным
	 *
	 * fileReader->serializeWriter
	 *
	 * parameters.v2.json -> temp.v2.out
	 *
	 * */
    public static void main(String... args) throws Exception {
        
        String fileName = "parameters.v2.json";
	
	    JsonParameterReader service = new JsonParameterReader();
        Fallback business = null;
	    System.out.println("Значение business: " + business);
	    System.out.println("Считываем данные из файла " + fileName);
        business = service.read(fileName);
	    System.out.println("Данные считаны:");
	    System.out.println(business);

        JsonParameterSerializeWriter mySerializer = new JsonParameterSerializeWriter();
	    System.out.println("Записываем данные в файла " + TEMP_V_2_OUT);
        mySerializer.customSerializeWriter(business, TEMP_V_2_OUT);
	    System.out.println("Данные записаны на диск в файл " + TEMP_V_2_OUT);
	    
    }
}

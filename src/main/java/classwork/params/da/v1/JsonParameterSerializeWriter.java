package classwork.params.da.v1;

import classwork.params.entity.template.v1.Fallback;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JsonParameterSerializeWriter {
	
	public <T> void customSerializeWriter(T parameters, String fileName) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(fileName)){
			try (ObjectOutputStream oos = new ObjectOutputStream(fos);){
				oos.writeObject(parameters);
			}
		}
	}
	
}

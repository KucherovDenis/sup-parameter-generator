package classwork.step3.v3;

import org.codehaus.jackson.annotate.JsonGetter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class Fallback implements Externalizable {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        return "Fallback{" +
                       "parameters=" + parameters +
                       '}';
    }
    
    public List<Parameter> parameters;

    public List<Parameter> getParameters() {
        return parameters;
    }
    @JsonGetter("parameters")
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Список параметров: " + this.getParameters().toString());
        out.writeObject(this.getParameters());
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setParameters((List<Parameter>)in.readObject());
    }
}

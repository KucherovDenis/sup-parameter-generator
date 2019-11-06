package classwork.step3.v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Path  implements Externalizable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String value;

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Path{" +
                       "name='" + name + '\'' +
                       ", value='" + value + '\'' +
                       '}' + "\n";
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Пишем поле с именем " + this.getName() + " и значением " + this.getValue());
        out.writeObject(this.getName());
        out.writeObject(this.getValue());
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setName((String)in.readObject());
        this.setValue((String)in.readObject());
    }
}
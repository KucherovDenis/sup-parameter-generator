package homework.v3.entity;

import java.io.Serializable;
import java.util.List;

public class Bundle implements Serializable {
    
    public static final long SerialVersionUID = 1L;
    
    private List<Path> path;
    private List<String> values;

    public List<Path> getPath() {
        return path;
    }
    public void setPath(List<Path> path) {
        this.path = path;
    }

    public List<String> getValues() {
        return values;
    }
    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return path.toString() + ",\n" +
                values.toString() + "\n";
    }
}

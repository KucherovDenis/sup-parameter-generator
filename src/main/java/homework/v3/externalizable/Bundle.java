package homework.v3.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class Bundle implements Externalizable {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getPath());
        out.writeObject(getValues());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        path = (List<Path>) in.readObject();
        values = (List<String>) in.readObject();
    }
}

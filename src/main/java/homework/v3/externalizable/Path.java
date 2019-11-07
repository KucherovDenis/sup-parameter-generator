package homework.v3.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Path implements Externalizable {

    public static final long SerialVersionUID = 1L;

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return code + " " + value;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(code);
        out.writeUTF(value);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        code = in.readUTF();
        value = in.readUTF();
    }
}

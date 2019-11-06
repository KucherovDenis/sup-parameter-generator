package homework.v3.entity;

import java.io.Serializable;

public class Value implements Serializable {
    
    public static final long SerialVersionUID = 1L;
    
    private String value;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}

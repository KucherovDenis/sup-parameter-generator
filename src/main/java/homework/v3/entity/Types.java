package homework.v3.entity;

import java.io.Serializable;

public enum Types  implements Serializable {
    
    STRING("STRING"),
    BOOLEAN("BOOLEAN"),
    LONG("LONG");
    
    private final String value;
    
    Types(String string) {
        this.value = string;
    }
    
    public static Types fromValue(String value) {
        if (value != null) {
            for (Types types : values()) {
                if (types.value.equals(value)) {
                    return types;
                }
            }
        }
        return getDefault();
    }
    
    public String toValue() {
        return value;
    }
    
    public static Types getDefault() {
        return STRING;
    }
}

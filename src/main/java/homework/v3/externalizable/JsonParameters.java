package homework.v3.externalizable;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

@JsonPropertyOrder({"name", "description", "isList", "roles", "type", "bundle"})
public class JsonParameters implements Externalizable {

    public static final long SerialVersionUID = 1L;

    private String name;
    private String description = "";
    private boolean isList;
    private List<String> roles;
    private String type;
    private List<Bundle> bundle;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getisList() {
        return isList;
    }

    public void setList(boolean isList) {
        this.isList = isList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Bundle> getBundle() {
        return bundle;
    }

    public void setBundle(List<Bundle> bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {

        String s = "";
        for (String s1: roles){
            s +=  s1 + " ";
        }

        return  name + "\n" +
                description + "\n" +
                isList + "\n" +
                type + "\n" +
                bundle.toString() + "\n" +
                s + "\n";

    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getDescription());
        out.writeBoolean(getisList());
        out.writeUTF(getName());
        out.writeObject(getRoles());
        out.writeUTF(getType());
        out.writeObject(getBundle());

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        description = in.readUTF();
        isList = in.readBoolean();
        name = in.readUTF();
        roles = (List<String>)in.readObject();
        type = in.readUTF();
        bundle = (List<Bundle>) in.readObject();

    }
}

package homework.v3;

import homework.v3.entity.Bundle;
import homework.v3.entity.JsonFileClass;
import homework.v3.entity.JsonParameters;
import homework.v3.entity.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JsonFileCreator {
    private static Map<String, Integer> names = new HashMap<>();

    private static String createString(String name) {
        int stringId = names.getOrDefault(name, 0);
        String result = name + (++stringId);
        names.put(name, stringId);
        return result;
    }

    private static void setRoles(JsonParameters param, int count) {
        List<String> roles = new ArrayList<>();
        param.setRoles(roles);
        for (int i = 0; i < count; i++) {
            roles.add(createString("role"));
        }
    }

    private static Path createPath() {
        Path path = new Path();
        path.setCode(createString("code"));
        path.setValue(createString("value"));
        return path;
    }

    private static void setPaths(Bundle bundle, int count) {
        List<Path> paths = new ArrayList<>();
        bundle.setPath(paths);
        for(int i = 0; i< count; i++) {
            paths.add(createPath());
        }
    }

    private static void setValues(Bundle bundle, int count) {
        List<String> values = new ArrayList<>();
        bundle.setValues(values);
        for (int i = 0; i < count; i++) {
            values.add(createString("value"));
        }
    }

    private static Bundle createBundle() {
        Bundle bundle = new Bundle();
        setValues(bundle, 2);
        setPaths(bundle, 2);
        return bundle;
    }

    private static void setBundles(JsonParameters param, int count) {
        List<Bundle> bundles = new ArrayList<>();
        param.setBundle(bundles);
        for (int i = 0; i < count; i++) {
            bundles.add(createBundle());
        }
    }

    private static JsonParameters createParam() {
        JsonParameters param = new JsonParameters();
        param.setName(createString("name"));
        param.setDescription(createString("description"));
        param.setType(createString("type"));
        param.setList(names.size() % 2 == 0);

        setRoles(param, 2);
        setBundles(param, 2);
        return param;
    }

    private static void setParams(JsonFileClass jsonFile, int count) {
        List<JsonParameters> params = new ArrayList<>();
        jsonFile.setParameters(params);
        for (int i = 0; i < count; i++) {
            params.add(createParam());
        }
    }

    public static JsonFileClass getInstance() {
        JsonFileClass jsonFile = new JsonFileClass();
        jsonFile.setVersion("1.0");
        setParams(jsonFile, 2);
        return jsonFile;
    }
}

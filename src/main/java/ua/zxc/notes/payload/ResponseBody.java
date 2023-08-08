package ua.zxc.notes.payload;

import java.util.HashMap;
import java.util.Map;

public class ResponseBody {

    private Map<String, Object> body = new HashMap<>();

    public void addValue(String key, Object value) {
        body.put(key, value);
    }
}

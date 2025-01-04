package org.model;

import java.util.HashMap;

public class JSONObject {

    HashMap<String, Object> json;

    public JSONObject() {
        json = new HashMap<>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(String key : json.keySet()) {
            sb.append(key)
                    .append(":")
                    .append(json.get(key).toString())
                    .append("\n");
        }

        return sb.toString();
    }
}

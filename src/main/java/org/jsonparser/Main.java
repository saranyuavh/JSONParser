package org.jsonparser;

import org.parser.JSONParser;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        JSONParser jsonParser = new JSONParser();
        String json = "{\"key\":\"value\", \"key2\":\"value2\", \"key3\":false, \"key4\":101, \"key5\":true}";
        try {
            jsonParser.parse(json, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
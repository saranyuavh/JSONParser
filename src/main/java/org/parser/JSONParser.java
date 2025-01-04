package org.parser;

import org.parser.ParserCreator.ParserCreator;

import java.util.Stack;

public class JSONParser implements IParser{

    Stack<String> parsed;
    Stack<String> parsing;

    public JSONParser(){
        parsed = new Stack<>();
        parsing = new Stack<>();
    }
    @Override
    public String parse(String value, int pos) throws Exception {

        int i = pos;
        try {
            while (i < value.length()) {
                if(value.charAt(i) == ' ') {
                    i++;
                    continue;
                }
                if( value.charAt(i) == '\n') {
                    i+=2;
                    continue;
                }
                if (value.charAt(i) == '"' && parsing.isEmpty()) {
                    String key = getKey(value, i);
                    parsing.push(key);
                    i+=(key.length()-1);
                } else if (value.charAt(i) == ':') {
                    String currVal = getParsedValue(value, i+1);
                    parsing.push(currVal);
                    i+=currVal.length();
                } else if(value.charAt(i) == ',' || value.charAt(i) == '}') {
                    collapse();
                }
                i++;
            }
        } catch (Exception e) {
            throw new Exception("invalid json "+ e.getMessage()+"\n");
        }

        while(!parsed.isEmpty()) {
            System.out.println(parsed.pop());
        }

        return null;
    }

    public String getParsedValue(String value, int pos)  throws Exception{
        if(pos >= value.length()) throw new Exception("reach end of string without a value"+"\n");
        IParser parser;
        String parsedValue = new String();
        try {
            if (value.charAt(pos) == '"') {
                parser = ParserCreator.getInstance().getParser("string");
                parsedValue = parser.parse(value, pos);
            } else if(value.charAt(pos) >= '0' && value.charAt(pos) <= '9') {
                parser = ParserCreator.getInstance().getParser("numeric");
                parsedValue = parser.parse(value, pos);
            } else if(value.charAt(pos) == 't' || value.charAt(pos) == 'f') {
                parser = ParserCreator.getInstance().getParser("boolean");
                parsedValue =  parser.parse(value, pos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return parsedValue;
    }

    public String getKey(String value, int  pos) throws Exception{
        int i=pos+1;
        while(i<value.length() && value.charAt(i) != '"') i++;
        if(i >= value.length() || value.charAt(i+1) != ':') throw new Exception("invalid key "+value.substring(pos)+"\n");
        return value.substring(pos, i+1);
    }
    private void collapse() {
        String value = parsing.pop();
        String key = parsing.pop();
        StringBuilder sb = new StringBuilder();
        sb.append(key)
                .append(":")
                .append(value.toString());
        parsed.push(sb.toString());
    }
}

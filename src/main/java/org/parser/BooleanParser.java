package org.parser;

public class BooleanParser implements IParser{
    @Override
    public String parse(String value, int pos) throws Exception{
        int i = pos;
        if(value.charAt(i) == 't') {
            if(value.substring(i, i+4).equals("true")) i+=4;
        } else if(value.charAt(i) == 'f') {
            if(value.substring(i, i+5).equals("false")) i+=5;
        }

        if(i >= value.length() || (value.charAt(i) != ',' && value.charAt(i)!='}'))
            throw new Exception("invalid boolean value "+value+"\n");

        return value.substring(pos, i);
    }
}

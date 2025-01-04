package org.parser;

public class NumericParser implements IParser{
    @Override
    public String parse(String value, int pos) throws Exception{
        int i = pos;

        while(i<value.length() && (value.charAt(i) >= '0' && value.charAt(i) <= '9')) {
            i++;
        }

        if(i >= value.length() || (value.charAt(i) != ',' && value.charAt(i)!='}'))
            throw new Exception("invalid numeric value "+value+"\n");


        return value.substring(pos, i);
    }
}

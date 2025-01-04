package org.parser;

import org.validator.IValidator;
import org.validator.StringValidator;

public class StringParser implements IParser{

    IValidator validator;
    String type;

    public StringParser() {
        validator = new StringValidator();
        type =  "string";
    }

    public String parse(String value, int pos) throws Exception{
        int i=pos+1;
        while(value.charAt(i) != '"')
            i++;
        if((i+1)>=value.length() || !(value.charAt(i+1) == ',' || value.charAt(i+1) == '}'))
            throw new Exception("invalid value " + value + "\n");

        return value.substring(pos, i+1);
    }
}

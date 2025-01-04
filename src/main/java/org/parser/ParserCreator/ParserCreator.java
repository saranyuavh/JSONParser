package org.parser.ParserCreator;

import org.parser.*;
import org.validator.*;

import java.util.HashMap;

public class ParserCreator {
    private static HashMap<String, IParser> parserMap;

    private static ParserCreator  parserCreator;

    private ParserCreator() {
        parserMap = new HashMap<>();
        parserMap.put("string", new StringParser());
        parserMap.put("numeric", new NumericParser());
        parserMap.put("boolean", new BooleanParser());
        parserMap.put("array", new ArrayParser());
        parserMap.put("json", new JSONParser());
    }

    public static ParserCreator getInstance() {
        if(parserCreator == null)
            parserCreator = new ParserCreator();
        return parserCreator;
    }

    public IParser getParser(String parserType) throws Exception{
        if(!parserMap.containsKey(parserType)) throw new Exception("parser not available");
        return parserMap.get(parserType);
    }
}

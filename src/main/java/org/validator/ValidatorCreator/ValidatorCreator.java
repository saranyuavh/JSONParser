package org.validator.ValidatorCreator;

import org.validator.*;

import java.util.HashMap;

public class ValidatorCreator {
    private static HashMap<String, IValidator> validatorMap;

    private static ValidatorCreator creator = null;

    public static ValidatorCreator getInstance() {
        if(creator == null) {
            creator = new ValidatorCreator();
        }
        return creator;
    }

    private ValidatorCreator() {
        validatorMap = new HashMap<>();
        validatorMap.put("string", new StringValidator());
        validatorMap.put("numeric", new NumericValidator());
        validatorMap.put("boolean", new BooleanValidator());
        validatorMap.put("array", new ArrayValidator());
        validatorMap.put("json", new JSONValidator());
    }

    public IValidator getValidator(String validatorType) throws Exception{
        validatorType = validatorType.toLowerCase();
        if(!validatorMap.containsKey(validatorType)) throw new Exception("Validator unaivalable");
        return validatorMap.get(validatorType);
    }
}

package com.bbva.pisd.lib.r601.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class FunctionUtils {

    private FunctionUtils(){}

    /**
     *  @allParametersNotNull(arguments,keys)
     *  return true -> all parameters save simulation not null
     *  return false -> parameters save simulation is null
    * */
    public static boolean parametersIsValid(Map<String, Object> arguments, String... keys) {
        return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
    }

}

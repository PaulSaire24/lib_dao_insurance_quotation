package com.bbva.pisd.lib.r601.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class FunctionUtils {
    /**
     *  @allParametersNotNull(arguments,keys)
     *  return true -> all parameters save simulation not null
     *  return false -> parameters save simulation is null
    * */
    public static boolean parametersIsValid(Map<String, Object> arguments, String... keys) {
        return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
    }

    public static String getDateTimeToString(Map<String, Object> data, String key) {
        String strDate = null;
        if (data.get(key) != null) {
            strDate = data.get(key).toString().trim();

        }
        return strDate;
    }

}

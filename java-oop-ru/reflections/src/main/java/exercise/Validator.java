package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        Class<?> aClass = address.getClass();
        Field[] fields = aClass.getDeclaredFields();
        List<String> result = new ArrayList<>();

        for (Field field : fields) {
            if (field.getAnnotation(NotNull.class) != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new HashMap<>();

        Class<?> aClass = address.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            List<String> list = new ArrayList<>();

            if (field.getAnnotation(NotNull.class) != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        list.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.getAnnotation(MinLength.class) != null) {
                field.setAccessible(true);
                int minLength = field.getAnnotation(MinLength.class).minLength();
                try {
                    if (field.get(address).toString().length() < minLength) {
                                list.add("length less than " + minLength);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (list.size() > 0) {
                result.put(field.getName(), list);
            }
        }

        return result;
    }
}
// END

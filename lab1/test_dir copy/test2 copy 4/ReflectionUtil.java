package edu.kpi;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class ReflectionUtil {
    public void invokeMethodsByAnnotation(Object object, Class<? extends Annotation> annotation)
            throws InvocationTargetException, IllegalAccessException {
        var methods = object.getClass().getMethods();
        for (var method : methods) {
            if (method.isAnnotationPresent(annotation))
                method.invoke(object);
        }
    }

    public void printAllFields(Class klass, PrintStream out) {
        var fields = klass.getDeclaredFields();
        for (var field : fields) {
            out.printf("modifiers: %s, type: %s, name: %s%n",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getName(),
                    field.getName()
            );
        }
    }

    public void printAllAnnotations(Class klass, PrintStream out) {
        var annotations = klass.getAnnotations();
        for (var annotation : annotations) {
            out.println(annotation.toString());
        }
    }

    public void printClassName(Class klass, PrintStream out) {
        out.println(klass.getName());
    }
}

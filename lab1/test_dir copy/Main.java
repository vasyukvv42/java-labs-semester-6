package edu.kpi;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final var poly = new Polynomial('y', Arrays.asList(1.0, 10.0, -1.5, 0.0, 2.0));

        // Reflection tasks
        final var reflection = new ReflectionUtil();
        try {
            reflection.invokeMethodsByAnnotation(poly, CallThis.class);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        reflection.printClassName(Polynomial.class, System.out);
        reflection.printAllFields(Polynomial.class, System.out);
        reflection.printAllAnnotations(Polynomial.class, System.out);

        System.out.println();
        System.out.println(poly);
        System.out.println();

        // Proxy
        RationalExpression expr = new ProxyRationalExpression(new Polynomial(Arrays.asList(228.0, 1337.0)), poly);
        expr.print(System.out);

        try {
            expr.setDenominator(poly);
        } catch (RuntimeException e) {
            System.err.println("Proxy works");
        }
    }
}

package edu.kpi;

import java.io.PrintStream;

public class ProxyRationalExpression implements RationalExpression {
    private RealRationalExpression rationalExpression;

    public ProxyRationalExpression(Polynomial numerator, Polynomial denominator) {
        rationalExpression = new RealRationalExpression(numerator, denominator);
    }

    @Override
    public void print(PrintStream out) {
        rationalExpression.print(out);
    }

    @Override
    public Polynomial getNumerator() {
        return rationalExpression.getNumerator();
    }

    @Override
    public void setNumerator(Polynomial numerator) {
        throw new RuntimeException();
    }

    @Override
    public Polynomial getDenominator() {
        return rationalExpression.getDenominator();
    }

    @Override
    public void setDenominator(Polynomial denominator) {
        throw new RuntimeException();
    }
}

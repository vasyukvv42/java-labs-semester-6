package edu.kpi;

import java.io.PrintStream;

public interface RationalExpression {
    void print(PrintStream out);

    Polynomial getNumerator();
    void setNumerator(Polynomial numerator);

    Polynomial getDenominator();
    void setDenominator(Polynomial denominator);

}

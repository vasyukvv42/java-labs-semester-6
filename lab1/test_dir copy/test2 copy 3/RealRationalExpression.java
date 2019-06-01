package edu.kpi;

import java.io.PrintStream;

public class RealRationalExpression extends Polynomial implements RationalExpression {
    private Polynomial denominator;

    public RealRationalExpression(Polynomial numerator, Polynomial denominator) {
        super(numerator.symbol, numerator.coefficients);
        this.denominator = denominator;
    }

    @Override
    public void print(PrintStream out) {
        out.println(toString());
    }

    @Override
    public Polynomial getNumerator() {
        return new Polynomial(symbol, coefficients);
    }

    @Override
    public void setNumerator(Polynomial numerator) {
        this.coefficients = numerator.coefficients;
        this.symbol = numerator.symbol;
    }

    @Override
    public Polynomial getDenominator() {
        return denominator;
    }

    @Override
    public void setDenominator(Polynomial denominator) {
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder(super.toString());
        sb.append('\n');

        var denominatorStr = denominator.toString();
        sb.append("–".repeat(denominatorStr.length()));
        sb.append('\n');

        sb.append(denominatorStr);

        return sb.toString();
    }
}

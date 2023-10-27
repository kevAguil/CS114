
package edu.njit.cs114;
/**
 * Author: Kevin Aguilar
 * Date created: 9/17/2022
 */
public class OperandToken implements ExpressionToken {
    private double value;
    public OperandToken(double val) {
        value = val;
    }
    public OperandToken(String str) {
        value = Double.parseDouble(str);
    }
    public double getValue() {
        return value;
    }
    public String toString() {
        return Double.toString(value);
    }
}

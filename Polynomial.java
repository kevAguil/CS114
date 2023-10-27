package edu.njit.cs114;
/**
 * Author: Kevin Aguilar
 * Date created: 9/12/2022
 */
public interface Polynomial {
    /**
     * Returns coefficient of the term with 'power'
     *
     * @param power
     * @return
     */
    public double coefficient(int power);
    /**
     * Returns the degree of polynomial (i.e. largest power of a non-zero term)
     *
     * @return
     */
    public int degree();
    /**
     * Add a term to the polynomial
     *
     * @param power
     * @param coefficient
     * @throws Exception when the power is invalid (e.g. negative)
     */
    public void addTerm(int power, double coefficient) throws Exception;
    /**
     * Remove and return the coefficient for the specified power,
     * @param power
     * @return coefficient of removed term if it exists else 0
     */
    public double removeTerm(int power);
    /**
     * Returns evaluation of the polynomial at 'point'
     *
     * @param point
     * @return
     */
    public double evaluate(double point);
    /**
     * Returns a new polynomial that is the result of addition of polynomial p to 
this polynomial
     *
     * @param p
     * @return
     */
    public Polynomial add(Polynomial p);
    /**
     * Returns a new polynomial that is the result of subtraction of polynomial p 
to this polynomial
     *
     * @param p
     * @return
     */
    public Polynomial subtract(Polynomial p);
    /**
     * Returns a new polynomial that is the result of multiplication of polynomial 
p with this polynomial
     *
     * @param p
     * @return
     */
    public Polynomial multiply(Polynomial p);
    /**
     * Returns the quotient polynomial that is the result of division of this 
polynomial
     * by polynomial p
     * @param p
     * @return
     * @throws Exception when the polynomial p is zero
     */
    public Polynomial divide(Polynomial p) throws Exception;
}
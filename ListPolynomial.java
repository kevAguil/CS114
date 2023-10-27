
package edu.njit.cs114;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * Author: Kevin Aguilar
 * Date created: 9/10/2022
 */
public class ListPolynomial extends AbstractPolynomial  {
    /**
     * To be completed for lab: Initialize the list
     */
    private List<PolynomialTerm> termList = new LinkedList<>();
    private class ListPolyIterator implements Iterator<PolynomialTerm> {
        private Iterator<PolynomialTerm> iter = termList.iterator();
        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }
        @Override
        public PolynomialTerm next() {
            PolynomialTerm term = iter.next();
            return new PolynomialTerm(term.getCoefficient(), term.getPower());
        }
    }
    // Default constructor
    public ListPolynomial() { }
    
    /**
     * Create a single term polynomial
     * @param power
     * @param coefficient
     * @throws Exception
     */
    public ListPolynomial(int power, double coefficient) throws Exception {
        if (power < 0) {
            throw new Exception("Invalid power for the term");
        }
        addTerm(power, coefficient);
        /*
         * Complete the code for lab
         */
    }
    /**
     * Create a new polynomial that is a copy of "another".
     * NOTE : you should use only the interface methods of Polynomial
     *
     * @param another
     */
    public ListPolynomial(PolynomialLab6 another) {
        Iterator<PolynomialTerm> iter = another.getIterator();
        while (iter.hasNext()) {
            PolynomialTerm term = iter.next();
            termList.add(new PolynomialTerm(term.getCoefficient(),term.getPower()));
        }
    }
    /**
     * Returns coefficient of power
     * @param power
     * @return
     */
    @Override
    public double coefficient(int power) {
        /*
         * Complete the code for homework
         */
    	for(PolynomialTerm term: termList)
    		if(term.getPower()==power)
    			return term.getCoefficient();
        return 0;
    }
    /**
     * Returns degree of the polynomial
     * @return
     */
    @Override
    public int degree() {
        /*
         * Complete the code for lab
         */
    	if(termList.isEmpty())
        return 0;
    	else
    		return termList.get(0).getPower();
    }
    /**
     * Adds polynomial term; add to existing term if power already exists
     * @param power
     * @param coefficient
     * @throws Exception if power < 0
     */
    @Override
    public void addTerm(int power, double coefficient) throws Exception {
        /*
         * Complete the code for lab
         */
    	Exception e = new Exception("Power less than 0");
    	if(power<0)
    		throw e;
    	if(coefficient==0)
    		return;
    	int index=0;
    	for(PolynomialTerm term: termList) {
    		int termPower = term.getPower();
    		if(termPower<= power) {
    			if(termPower < power) {
    				PolynomialTerm newTerm = new PolynomialTerm(coefficient,power);
    				termList.add(index, newTerm);
    				return;
    			}
    			else {
    				double newCoefficient = term.getCoefficient()+coefficient;
    				termList.remove(index);
    				if(newCoefficient!=0) {
    					PolynomialTerm newTerm = new PolynomialTerm(newCoefficient,power);
    					termList.add(index,newTerm);
    					return;
    				}
    			}
    				
    		}
    		index++;
    	}
		PolynomialTerm newTerm = new PolynomialTerm(coefficient,power);
		termList.add(newTerm); 
		return;
    }
    /**
     * Remove and return the term for the specified power,
     * @param power
     * @return removed term if it exists else zero term
     */
    @Override
    public PolynomialTerm removeTerm(int power) {
        /*
         * Complete the code for lab
         */
    	int index=0;
    	for(PolynomialTerm term: termList) {
    		if(power == term.getPower()) {
    			PolynomialTerm removedTerm= new PolynomialTerm(term.getCoefficient(),term.getPower());
    			termList.remove(index);
    			return removedTerm;
    		}
    		index++;	
    	}
    	return new PolynomialTerm(0, power);    		
    }
    /**
     * Evaluate polynomial at point
     * @param point
     * @return
     */
    @Override
    public double evaluate(double point) {
        /*
         * Complete the code for homework
         */
    	double total = 0;
    	for(PolynomialTerm term: termList) {
    		double coefficient = term.getCoefficient();
    		int power = term.getPower();
    		total += coefficient * Math.pow(point, power);  		
    	}
        return total;
    }
    /**
     * Add polynomial p to this polynomial and return the result
     * @param p
     * @return
     */
    @Override
    public PolynomialLab6 add(PolynomialLab6 p) {
        /**
         * Complete the code for homework
         */
    	ListPolynomial results = new ListPolynomial();
    	Iterator<PolynomialTerm> iter1 = this.getIterator();
        Iterator<PolynomialTerm> iter2 = p.getIterator();
        PolynomialTerm term1;
        PolynomialTerm term2;
        if(iter1.hasNext())
        	term1 = iter1.next();
        else
        	term1 = null;
        if(iter2.hasNext())
        	term2 = iter2.next();
        else
        	term2 = null;
        
        while(term1 != null && term2 != null) {
        	if(term1.getPower() > term2.getPower()) {
        		results.termList.add(term1);
        		if(iter1.hasNext())
        			term1 = iter1.next();
        		else
        			term1 = null;      		
        	}
        	if(term2.getPower() > term1.getPower()) {
        		results.termList.add(term2);
        		if(iter2.hasNext())
        			term2 = iter2.next();
        		else
        			term2 = null;      		
        	}
        	if(term1.getPower() == term2.getPower()) {
        		double newCoeff = term1.getCoefficient() + term2.getCoefficient();
        		if(newCoeff != 0) {
        			PolynomialTerm newTerm = new PolynomialTerm(newCoeff, term1.getPower()); 
        			results.termList.add(newTerm);
        		}
        		if(iter1.hasNext())
        			term1 = iter1.next();
        		else
        			term1 = null;
        		if(iter2.hasNext())
        			term2 = iter2.next();
        		else
        			term2 = null;         		
        	}

        }	
        while(term1 != null) {
        	results.termList.add(term1);
    		if(iter1.hasNext())
    			term1 = iter1.next();
    		else
    			term1 = null; 
        	
        }
        while(term2 != null) {
        	results.termList.add(term2);
    		if(iter2.hasNext())
    			term2 = iter2.next();
    		else
    			term2 = null; 
    		
        }
        	
    	return results;
    }
    /**
     * Substract polynomial p from this polynomial and return the result
     * @param p
     * @return
     */
    @Override
    public PolynomialLab6 subtract(PolynomialLab6 p) {
        /**
         * Complete the code for homework
         */
    	ListPolynomial results = new ListPolynomial();
    	Iterator<PolynomialTerm> iter1 = this.getIterator();
        Iterator<PolynomialTerm> iter2 = p.getIterator();
        PolynomialTerm term1;
        PolynomialTerm term2;
        if(iter1.hasNext())
        	term1 = iter1.next();
        else
        	term1 = null;
        if(iter2.hasNext())
        	term2 = iter2.next();
        else
        	term2 = null;
        
        while(term1 != null && term2 != null) {
        	if(term1.getPower() > term2.getPower()) {
        		results.termList.add(term1);
        		if(iter1.hasNext())
        			term1 = iter1.next();
        		else
        			term1 = null;      		
        	}
        	if(term2.getPower() > term1.getPower()) {
        		results.termList.add(term2);
        		if(iter2.hasNext())
        			term2 = iter2.next();
        		else
        			term2 = null;      		
        	}
        	if(term1.getPower() == term2.getPower()) {
        		double newCoeff = term1.getCoefficient() - term2.getCoefficient();
        		if(newCoeff != 0) {
        			PolynomialTerm newTerm = new PolynomialTerm(newCoeff, term1.getPower()); 
        			results.termList.add(newTerm);
        		}
        		if(iter1.hasNext())
        			term1 = iter1.next();
        		else
        			term1 = null;
        		if(iter2.hasNext())
        			term2 = iter2.next();
        		else
        			term2 = null;      		
        	}

        }	
        while(term1 != null) {
        	results.termList.add(term1);
    		if(iter1.hasNext())
    			term1 = iter1.next();
    		else
    			term1 = null; 
        	
        }
        while(term2 != null) {
        	results.termList.add(term2);
    		if(iter2.hasNext())
    			term2 = iter2.next();
    		else
    			term2 = null; 
    		
        }
        	
    	return results;
    }
    /**
     * Multiply polynomial p with this polynomial and return the result
     * @param p
     * @return
     */
    @Override
    public PolynomialLab6 multiply(PolynomialLab6 p) {
        /**
         * Complete the code for homework
         */
    	ListPolynomial results = new ListPolynomial();
    	for(PolynomialTerm term1: this.termList) {
            Iterator<PolynomialTerm> iter2 = p.getIterator();
            while(iter2.hasNext()) {
            	PolynomialTerm term2 = iter2.next();
            	try {
					results.addTerm(term1.getPower()+term2.getPower(), term1.getCoefficient()*term2.getCoefficient());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
    		
    		
    	}
    	
        return results;
    }
    @Override
    public Iterator<PolynomialTerm> getIterator() {
        return new ListPolyIterator();
    }
    public static void main(String [] args) throws Exception {
        /** Uncomment after you have implemented all the functions */
        PolynomialLab6 p1 = new ListPolynomial();
        System.out.println("p1(x) = " + p1);
        assert p1.degree() == 0;
        assert p1.coefficient(0) == 0;
        assert p1.coefficient(2) == 0;
        assert p1.equals(new ListPolynomial());
        PolynomialLab6 p2 = new ListPolynomial(5, -1.6);
        p2.addTerm(0,3.1);
        p2.addTerm(4,2.5);
        p2.addTerm(2,-2.5);
        System.out.println("p2(x) = " + p2);
        assert p2.degree() == 5;
        assert p2.coefficient(4) == 2.5;
        assert p2.toString().equals("-1.600x^5 + 2.500x^4 - 2.500x^2 + 3.100");
       System.out.println("p2(1) = " + p2.evaluate(1));
        assert Math.abs(p2.evaluate(1)-1.5) <= 0.001;
        PolynomialLab6 p3 = new ListPolynomial(0, -4);
        p3.addTerm(5,3);
        p3.addTerm(5,-1);
        System.out.println("p3(x) = " + p3);
        assert p3.degree() == 5;
        assert p3.coefficient(5) == 2;
        assert p3.coefficient(0) == -4;
        System.out.println("p3(2) = " + p3.evaluate(2));
        assert p3.evaluate(2) == 60;
        PolynomialLab6 p21 = new ListPolynomial(p2);
        System.out.println("p21(x) = " + p21);
        assert p21.equals(p2);
        PolynomialTerm t1 = p21.removeTerm(4);
        System.out.println("p21(x) = " + p21);
        assert !p21.equals(p2);
        assert p21.coefficient(4) == 0;
        assert t1.getPower() == 4;
        assert t1.getCoefficient() == 2.5;
        System.out.println("p2(x) = " + p2);
        PolynomialLab6 p22 = new ListPolynomial(p21);
        t1 = p21.removeTerm(1);
        System.out.println("p21(x) = " + p21);
        assert p21.equals(p22);
        assert t1.getPower() == 1;
        assert t1.getCoefficient() == 0;
        try {
            PolynomialLab6 p5 = new ListPolynomial(-5, 4);
            assert false;
        } catch (Exception e) {
            // Exception expected
            assert true;
        }
        System.out.println("p2(x) + p3(x) = " + p2.add(p3));
        PolynomialLab6 result = p2.add(p3);
        assert result.degree() == 5;
        assert Math.abs(result.coefficient(5) - 0.4) <= 0.0001;;
        System.out.println("p2(x) - p3(x) = " +p2.subtract(p3));
        result = p2.subtract(p3);
        assert result.degree() == 5;
        assert Math.abs(result.coefficient(5) - -3.6) <= 0.0001;
        assert Math.abs(result.coefficient(0) - 7.1) <= 0.0001;
        System.out.println("p2(x) * p3(x) = " +p2.multiply(p3));
        result = p2.multiply(p3);
        assert result.degree() == 10;
        assert Math.abs(result.coefficient(10) - -3.2) <= 0.0001;
        assert Math.abs(result.coefficient(5) - 12.6) <= 0.0001;
        assert Math.abs(result.coefficient(0) - -12.4) <= 0.0001;
        assert Math.abs(p2.evaluate(1) * p3.evaluate(1) - result.evaluate(1)) <= 0.0001;
    }
}

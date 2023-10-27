package edu.njit.cs114;
import java.util.Arrays;
/**
 * Author: Ravi Varadarajan
 * Date created: 9/28/22
 */
public class CashRegister {
    public static final int INFINITY = Integer.MAX_VALUE;
    private int [] denominations;
    /**
     * Constructor
     * @param denominations values of coin types not in any order
     * @throws Exception when a coin of denomination 1 does not exist
     */
    public CashRegister(int [] denominations) throws Exception  {
    	Exception exception= new Exception("No 1 value coin");
    	boolean found= false;
    	for(int i=0; i<denominations.length;i++)
    		if(denominations[i]==1) {
    			found = true;
    			break;
    		}
		if(found= false)
    		throw exception;
    	this.denominations = Arrays.copyOf(denominations, denominations.length);
    			
    }
    // make an array of same size as denominations array with each entry equal to nCoins
    private int [] makeChangeArray(int nCoins) {
        int [] changes = new int[denominations.length];
        for (int i=0; i < changes.length; i++) {
            changes[i] = nCoins;
        }
        return changes;
    }
    
    private int numCoins(int [] changes) {
        int sum = 0;
        for (int i=0; i < changes.length; i++) {
            if (changes[i] == INFINITY) {
                return INFINITY;
            }
            sum += changes[i];
        }
        return sum;
    }
    private int[] makeChange(int startDenomIndex, int remainingValue, int[]availableCoins) {
    	if(startDenomIndex==denominations.length)
    		if (remainingValue>0)
    			return makeChangeArray(INFINITY);
    		else
    			return makeChangeArray(0);
    	   	
    	int[] minTotCoins = makeChangeArray(INFINITY);
    	int maxDenomCoins = remainingValue / denominations[startDenomIndex];
    	if(availableCoins[startDenomIndex]<maxDenomCoins)
    		maxDenomCoins=availableCoins[startDenomIndex];
    	for(int i=0; i<=maxDenomCoins; i++) {
    		int remainingChange = remainingValue - i*denominations[startDenomIndex];
    		int[] remMinCoins = makeChange(startDenomIndex+1,remainingChange, availableCoins);
    		if(numCoins(remMinCoins)==INFINITY)
    			continue;
    		if(i+ numCoins(remMinCoins)<numCoins(minTotCoins)) {
    			minTotCoins= Arrays.copyOf(remMinCoins, remMinCoins.length);
    		minTotCoins[startDenomIndex]=i;
    		}
    	}

        return minTotCoins;
    }

    /**
     * Make change for value using minimum number of coins
     * using available coins of the different denominations
     * @param value amount to make change
     * @param availableCoins coins available in each denomination
     * @return array of same length as denominations array that specifies
     *         coins of each denomination (in the same order as specified in 
     *         denomination values array)
     *         to use in making given change with minimum number of coins. Each 
     *         entry in the returned
     *         array should not exceed the corresponding entry in the 
     *         availableCoins array
     */
    public int [] makeChange(int value, int [] availableCoins) {
    	return makeChange(0,value, availableCoins);

        /*
         * Complete code here
         */
    }
    /**
     * Make change for value using minimum number of coins
     * assuming unlimited availability of coins of each denomination
     * @param value
     * @return array of same length as denominations array that specifies
     *         coins of each denomination (in the same order as specified in 
     *         denomination values array)
     *         to use in making given change with minimum number of coins.
     */
    public int [] makeChange(int value) {
        return makeChange(0,value, makeChangeArray(INFINITY));
    }
    /**
     * Specifies description of change in coins if solution exists
     * @param coins
     * @return
     */
    public void printValues(int [] coins) {
        if (coins[0] == INFINITY) {
            System.out.println("Change not possible");
            return;
        }
        StringBuilder builder = new StringBuilder();
        int totalCoins = 0;
        for (int i=0; i < denominations.length; i++) {
            if (coins[i] > 0) {
                if (builder.length() > 0) {
                    builder.append(",");
                }
                builder.append(coins[i] + " coins of value " + denominations[i]);
                totalCoins += coins[i];
            }
        }
        builder.append(" for a total of " + totalCoins + " coins");
        System.out.println(builder.toString());
    }
    public static void main(String [] args) throws Exception {
        CashRegister reg = new CashRegister(new int [] {50, 25, 10, 5, 1});
        System.out.println("Change for " + 96 + ":");
        int [] change = reg.makeChange(96);
        // should have a total of 5 coins
        reg.printValues(change);
        assert Arrays.stream(change).sum() == 5;
        change = reg.makeChange(98, new int [] {2, 3, 1, 2, 100});
        System.out.println("Change for " + 98 + " with restricted coin availability: ");
        // should have a total of 8 coins
        reg.printValues(change);
        assert Arrays.stream(change).sum() == 8;
        System.out.println("Change for " + 58 + ":");
        change = reg.makeChange(58);
        // should have a total of 5 coins
        reg.printValues(change);
        assert Arrays.stream(change).sum() == 5;
        System.out.println("Change for " + 58 + " with restricted coin availability:");
        change = reg.makeChange(58, new int [] {2, 2, 2, 2, 2});
        // should have no solution
        reg.printValues(change);
        assert change[0] == INFINITY;
        reg = new CashRegister(new int [] {25, 10, 1});
        System.out.println("Change for " + 34 + ":");
        change = reg.makeChange(34);
        // should have a total of 7 coins
        reg.printValues(change);
        assert Arrays.stream(change).sum() == 7;
        reg = new CashRegister(new int [] {1, 7, 24, 42});
        System.out.println("Change for " + 48 + ":");
        change = reg.makeChange(48);
        // should have a total of 2 coins
        reg.printValues(change);
        assert Arrays.stream(change).sum() == 2;
        System.out.println("Change for " + 48 + " with restricted coin availability:");
        change = reg.makeChange(48, new int [] {100, 20, 1, 1});
        // should have a total of 7 coins
        reg.printValues(change);
        assert Arrays.stream(change).sum() == 7;
        reg = new CashRegister(new int [] {50, 1, 3, 16, 30});
        System.out.println("Change for " + 35 + ":");
        change = reg.makeChange(35);
        // should have a total of 3 coins
        reg.printValues(change);
        assert Arrays.stream(change).sum() == 3;
    }
}
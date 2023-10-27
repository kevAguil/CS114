package edu.njit.cs114;

import java.util.Arrays;

/**
 * Author: Ravi Varadarajan
 * Date created: 9/26/22
 */
public class LabCashRegister {
    private static final int INFINITY = Integer.MAX_VALUE;
    private int [] denominations;
    /**
     * Constructor
     * @param denominations values of coin types with no particular order
     * @throws Exception when a coin of denomination 1 does not exist
     */
    public LabCashRegister(int [] denominations) throws Exception {
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
    			
        /*
         * Complete code here
         */
    }
    /**
     * Recursive function that finds the minimum number of coins to make change for
the given
     * value using only denominations that are in indices
     * startDenomIndex, startDenomIndex+1,.....denomonations.length-1 of the 
denominations array
     * @param startDenomIndex
     * @param remainingValue
     * @return
     */
    private int minimumCoinsForChange(int startDenomIndex, int remainingValue) {
    	if(startDenomIndex==denominations.length)
    		if (remainingValue>0)
    			return INFINITY;
    		else
    			return 0;

    	int minTotCoins = INFINITY;
    	int maxDenomCoins = remainingValue / denominations[startDenomIndex];
    	for(int i=0; i<=maxDenomCoins; i++) {
    		int remainingChange = remainingValue - i*denominations[startDenomIndex];
    		int remMinCoins = minimumCoinsForChange(startDenomIndex+1,remainingChange);
    		if(remMinCoins==INFINITY)
    			continue;
    		int totCoins = i+remMinCoins;
    		if(totCoins<minTotCoins)
    			minTotCoins = totCoins;
    	}

        return minTotCoins;
    }
    /**
     * Wrapper function that finds the minimum number of coins to make change for 
the given value
     * @param value value for which to make change
     * @return
     */
    public int minimumCoinsForChange(int value) {
    	return minimumCoinsForChange(0,value);
        /*
         * Complete code here
         */
        
    }
    public static void main(String [] args) throws Exception {
        LabCashRegister reg = new LabCashRegister(new int [] {50, 25, 10, 5, 1});
        // should have a total of 5 coins
        int nCoins = reg.minimumCoinsForChange(96);
        System.out.println("Minimum coins to make change for " + 96
                + " from {50,25,10,5,1} = "+ nCoins);
        assert nCoins == 5;
        // should have a total of 5 coins
        nCoins = reg.minimumCoinsForChange(58);
        System.out.println("Minimum coins to make change for " + 58
                + " from {50,25,10,5,1} = "+ nCoins);
        assert nCoins == 5;
        reg = new LabCashRegister(new int [] {25, 10, 1});
        // should have a total of 7 coins
        nCoins = reg.minimumCoinsForChange(34);
        System.out.println("Minimum coins to make change for " + 34
                + " from {25,10,1} = "+ nCoins);
        assert nCoins == 7;
        reg = new LabCashRegister(new int [] {1, 9, 24, 42});
        // should have a total of 3 coins
        nCoins = reg.minimumCoinsForChange(49);
        System.out.println("Minimum coins to make change for " + 49
                + " from {1,7,24,42} = "+ nCoins);
        assert nCoins == 3;
        reg = new LabCashRegister(new int [] {50, 1, 3, 16, 30});
        // should have a total of 3 coins
        nCoins = reg.minimumCoinsForChange(62);
        System.out.println("Minimum coins to make change for " + 62
                + " from {50,1,3,16,30} = "+ nCoins);
        assert nCoins == 3;
    }
}
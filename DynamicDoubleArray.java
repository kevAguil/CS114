package edu.njit.cs114;
/**
 * Author: Kevin Aguilar
 * Date created: 10/3/2022
 */
public class DynamicDoubleArray {

    private static final int DEFAULT_INITIAL_CAPACITY = 1;

    private Double [] array;
    private int size;
    // keeps track of number of element copies made during array expansion or contraction
    private int nCopies;

    public DynamicDoubleArray(int initialCapacity) {
        array = new Double[initialCapacity];
    }

    private void expandArrIfNecessary(){
        if(size == array.length){
            Double[] newArr = new Double[size * 2];
            for(int i = 0; i < size; i++){
                nCopies++;
                newArr[i] = array[i];
            }
            array = newArr;
        }
    }
    private void shrinkArrIfNecessary(){
        if(size <= (1.0/4 * array.length)){
            Double[] newArr = new Double[array.length / 2];
            for(int i = 0; i < size;i++){
                nCopies++;
                newArr[i] = array[i];
            }
            array = newArr;
        }
    }

    public DynamicDoubleArray() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Add element at specified index position shifting to right elements at positions higher than
     *    or equal to index
     * @param index
     * @param elem
     * @throws IndexOutOfBoundsException if index < 0 or index > size()
     */
    public void add(int index, double elem) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        expandArrIfNecessary();

        for(int i = size; i > index; i--) {
           array[i] = array[i-1];
        }
        array[index] = elem;
        size++;
    }

    /**
     * Append element to the end of the array
     * @param elem
     */
    public void add(double elem) {
        expandArrIfNecessary();
        array[size] = elem;
        size++;
    }

    /**
     * Set the element at specified index position replacing any previous value
     * @param index
     * @param elem
     * @return previous value in the index position
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public double set(int index, double elem) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Double value = array[index];
        array[index] = elem;
        return value;
    }

    /**
     * Get the element at the specified index position
     * @param index
     * @return
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public double get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return array[index];
    }

    /**
     * Remove and return the element at the specified index position. The elements with positions
     *  higher than index are shifted to left
     * @param index
     * @return
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public double remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        double value = array[index];

        for(int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        size--;
        shrinkArrIfNecessary();

        return value;
    }

    /**
     * Remove and return the element at the end of the array
     * @return
     * @throws Exception if size() == 0
     */
    public double remove() throws Exception {
    	Exception e = new Exception("Array is empty");
        if (size == 0) 
            throw e;
        
        double value = array[size-1];
        array[size-1] = 0.0;
        size--;
        shrinkArrIfNecessary();

        return value;
    }

    /**
     * Returns the number of elements in the array
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Returns the total number of copy operations done due to expansion of array
     * @return
     */
    public int nCopies() {
        return nCopies;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(" + array.length + ")");
        builder.append("[");
        for (int i=0; i < size; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(array[i] == null ? "" : array[i]);
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String [] args) throws Exception {
        DynamicDoubleArray arr = new DynamicDoubleArray();
        arr.add(8.5);
        arr.add(12.1);
        arr.add(-5.7);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert arr.size() == 3;
        arr.add(1,4.9);
        arr.add(2,20.2);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert arr.size() == 5;
        double oldVal = arr.set(2,25);
        System.out.println("old value at index 2 after replacing it with 25 = " + oldVal);
        assert oldVal == 20.2;
        System.out.println("Element at position 2 = "+arr.get(2));
        assert arr.get(2) == 25;
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert arr.size() == 5;
        /*     Uncomment the following for homework 4 */
        double removedVal = arr.remove(0);
        System.out.println("Removed element at position 0 = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 8.5;
        assert arr.size() == 4;
        removedVal = arr.remove(2);
        System.out.println("Removed element at position 2 = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 12.1;
        assert arr.size() == 3;
        removedVal = arr.remove(2);
        System.out.println("Removed element at position 2 = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == -5.7;
        assert arr.size() == 2;
        removedVal = arr.remove();
        System.out.println("Removed element at end = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 25;
        assert arr.size() == 1;
        removedVal = arr.remove();
        System.out.println("Removed element at end = " + removedVal);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert removedVal == 4.9;
        assert arr.size() == 0;
        arr.add(67);
        arr.add(-14);
        arr.add(15);
        System.out.println("array of size " + arr.size()+ " : " + arr);
        assert arr.size == 3;
        int [] nItemsArr = new int [] {0, 100000, 200000, 400000, 800000, 1600000, 3200000};
        DynamicDoubleArray arr1 = new DynamicDoubleArray();
        long totalTime = 0;
        for (int k=1; k < nItemsArr.length; k++) {
            for (int i = 0; i < nItemsArr[k]-nItemsArr[k-1]; i++) {
                long startTime = System.currentTimeMillis();
                arr1.add(i + 1);
                long stopTime = System.currentTimeMillis();
                totalTime += (stopTime - startTime);
            }
            System.out.println("copy cost for inserting " + nItemsArr[k] + " items = " +
                    + arr1.nCopies());
            System.out.println("total time(ms) for inserting " + nItemsArr[k] + " items = " +
                    + totalTime);
        }
        /* Uncomment the following for homework 4 */
        totalTime = 0;
        for (int k=1; k < nItemsArr.length; k++) {
            for (int i = 0; i < nItemsArr[k]-nItemsArr[k-1]; i++) {
                long startTime = System.currentTimeMillis();
                arr1.remove();
                long stopTime = System.currentTimeMillis();
                totalTime += (stopTime - startTime);
            }
            System.out.println("total time(ms) for deleting " + nItemsArr[k] + " items = " +
                    + totalTime);
        }

    }

}


package edu.njit.cs114;
/**
 * Author: Kevin Aguilar
 * Date created: 12/4/2022
 */
public class GraphException extends Exception {
    public GraphException(String msg) {
        super(msg);
    }
    public GraphException(String msg, Throwable t) {
        super(msg, t);
    }
}

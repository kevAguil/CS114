package edu.njit.cs114;
import java.awt.*;
/**
 * Class that solves maze problems with backtracking.
 *
 * @author Koffman and Wolfgang
 **/
public class Maze {
    public static Color PATH = Color.green;
    public static Color BACKGROUND = Color.white;
    public static Color NON_BACKGROUND = Color.red;
    public static Color TEMPORARY = Color.black;
    /** The maze */
    private TwoDimGrid maze;
    public Maze(TwoDimGrid m) {
        maze = m;
    }
    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }
    /**
     * PROBLEM 1
     * Attempts to find a path through point (x, y).
     *
     * @pre Possible path cells are initially in NON_BACKGROUND color
     * @post If a path is found, all cells on it are set to the PATH color; all
     *       cells that were visited but are not on the path are in the TEMPORARY
     *       color.
     * @param x
     *            The x-coordinate of current point
     * @param y
     *            The y-coordinate of current point
     * @return If a path through (x, y) is found, true; otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        if (y >= maze.getNRows() || x >= maze.getNCols() || x < 0 || y < 0) {
        	// beyond boundary of the grid
            // no path from (x,y) to destination
            return false;
        }
        if(maze.getColor(x, y)!= NON_BACKGROUND) {
        	return false;
        }
        if(x==maze.getNCols()-1 && y== maze.getNRows()-1) {
        	maze.recolor(x, y, PATH);
        	return true;
        }
        // if (x+1,y) is not a valid maze cell || or has not been visited
        // and true for other neighboring cells
        // color it black
        // return false
        /*
         * To BE COMPLETED (base cases)
         */
        boolean found = false;
        
        /**
         * TO BE COMPLETED (recursion case)
         */
        if(maze.getColor(x, y)!=NON_BACKGROUND) {
        	return false;
        }
        else
        	 maze.recolor(x, y, TEMPORARY);
        if(findMazePath(x+1,y)) {
        	maze.recolor(x, y, PATH);
        	return true;
        }
        else if(findMazePath(x-1,y)) {
        	maze.recolor(x, y, PATH);
        	return true;
        }
        else if(findMazePath(x,y+1)) {
        	maze.recolor(x, y, PATH);
        	return true;
        }
        else if(findMazePath(x,y-1)) {
        	maze.recolor(x, y, PATH);
        	return true;
        }
 
        return found;
    }
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
}
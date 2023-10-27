
package edu.njit.cs114;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
/**
 * Author: Kevin Aguilar
 * Date created: 9/17/2022
 */
public class ExpressionEvaluator {
    // Complete this function which can be called by postfixEval()
    private static double applyOp(OperatorToken opType, double op1, double op2) {
    	switch(opType) {
    		case ADD:
    			return op1 + op2;
    		case SUBTRACT:
    			return op1 - op2;
    		case MULTIPLY:
    			return op1 * op2;
    		case DIVIDE:
    			return op1 / op2;
    		case MOD:
    			return op1 % op2;
    		case EXP:
    			return Math.pow(op1, op2);   		
    		default:
    			return 0.0;
    		
    	}		

    }
    /**
     * Parse an expression into a list of operator and operand tokens
     * @param str
     * @return
     */
    public static List<ExpressionToken> parseExpr(String str) {
        List<ExpressionToken> expr = new ArrayList<>();
        String [] strTokList = str.split("\\s+");
        for (String strTok : strTokList) {
            String str1 = strTok.trim();
            if (str1.isEmpty()) {
                continue;
            }
            OperatorToken operToken = OperatorToken.opType(str1);
            expr.add(operToken == null ? new OperandToken(str1) : operToken);
        }
        return expr;
    }
    /**
     * Convert Infix expression given as a list of operator and operand tokens to
     * a postfix expression as a list of operator and operand tokens
     * @param infixExpr
     * @return
     * @throws Exception when the expression is not valid
     *    such as insufficient number of operators or operands e.g. 4 * 2 5, 4 * 2 +
     *    or not having balanced parentheses e.g. (4 * ( 5 + 3 )
     */
    public static List<ExpressionToken> convertToPostFix(List<ExpressionToken> infixExpr) throws Exception {
        /**
         * Complete the code here only for homework.
         * After completing the code here, remove the following return statement
         */
    	List<ExpressionToken> results = new ArrayList<>();
    	Stack<OperatorToken> operatorTokens = new Stack<>();
    	
    	for(ExpressionToken token: infixExpr) {
    		if(token instanceof OperandToken) {
    			results.add(token);
    		}
    		else {
    			OperatorToken OpToken = (OperatorToken)token;
                if(OpToken.equals(OperatorToken.OPENPAR)){
                    operatorTokens.push(OpToken);
                }
                else if(OpToken.equals(OperatorToken.CLOSEDPAR)){
                    try {
                        while (operatorTokens.peek() != OperatorToken.OPENPAR) {
                            results.add(operatorTokens.pop());
                        }
                        operatorTokens.pop();
                    }
                    catch(Exception exception){
                        throw exception;
                    }
                }
                else{
                    while(!operatorTokens.empty()){
                        if(!OpToken.precedes(operatorTokens.peek())){
                            results.add(operatorTokens.pop());
                        }
                        else{
                            break;
                        }
                    }
                    operatorTokens.push(OpToken);
                }

            }
        }
        while(!operatorTokens.empty()){
            results.add(operatorTokens.pop());
        }
        return results;
    }
                
    /**
     * Evaluate post fix expression given as a list of operator and operand tokens
     * and return the result
     * @param postfixExpr
     * @return
     * @throws Exception when the expression is not valid
     *      such as insufficient number of operators or operands e.g. 4 5 2 *, 4 *
     */
    public static double postFixEval(List<ExpressionToken> postfixExpr) throws Exception {
        /*
         * Complete this code for lab
         * After completing the code here, remove the following return statement
         */
    	Stack<Double> stack= new Stack<>();
    	for(ExpressionToken token: postfixExpr) {
    		if(token instanceof OperandToken) {
    			stack.push(((OperandToken) token).getValue());
    		}
    		//operator token
    		else {
    			if(stack.empty())
    				throw new Exception("Insufficient number of operands in post fix expression "+ postfixExpr);
    			double value2 = stack.pop();
    			if(stack.empty())
    				throw new Exception("Insufficient number of operands in post fix expression "+ postfixExpr);
    			double value1 = stack.pop();
    			stack.push(applyOp((OperatorToken)token, value1, value2));
    			
    		}
    	}
    	double result = stack.pop();
    	if(!stack.empty())
    		throw new Exception("Insufficient number of Operators in postfix expression " + postfixExpr);    	
        return result; 
    }
    /**
     * Evaluate an infix expression string using postfix
     * @param str
     * @return
     * @throws Exception when the expression is not valid (e.g 2 + 3 5)
     */
    public static double eval(String str) throws Exception {
        return postFixEval(convertToPostFix(parseExpr(str)));
    }
    /**
     * Evaluate an infix expression string directly
     * @param str
     * @return
     * @throws Exception
     */
    public static double evalDirect(String str) throws Exception {
        List<ExpressionToken> tokens = parseExpr(str);
        /**
         * For homework extra credit only!!
         */
        return 0;
    }
    public static void main(String [] args) throws Exception {
        /** Uncomment commented lines below after you have finished the homework 
implementations **/
        System.out.println(String.format("expr. %s evaluated as %.4f", "( 8 - 6 )* ( 6 / 2 ) + 3",
                eval("( 8 - 6 ) * ( 6 / 2 ) + 3")));
        System.out.println(String.format("postfix notation for %s : %s", "-8.5",
                convertToPostFix(parseExpr("-8.5"))));
        double result = postFixEval(parseExpr("-8.5"));
        System.out.println(String.format("postfix expr. %s evaluated as %.4f", "-8.5",
                result));
        assert Math.abs(result-(-8.5)) <= 0.00001;
        System.out.println(String.format("%s evaluated as %.4f", "-8.5",
                eval("-8.5")));
        System.out.println(String.format("postfix notation for %s : %s", " 5 * -2",
                convertToPostFix(parseExpr(" 5 * -2"))));
        result = postFixEval(parseExpr(" 5 -2 *"));
        System.out.println(String.format("postfix expr. %s evaluated as %.4f", " 5 -2 *",
                result));
        assert Math.abs(result-(-10.0)) <= 0.00001;
        System.out.println(String.format("%s evaluated as %.4f", " -2 * 5",
                eval(" -2 * 5")));
        System.out.println(String.format("postfix notation for %s : %s", "( 4 + -2 ) * 7",
                convertToPostFix(parseExpr("( 4 + -2 ) * 7"))));
        System.out.println(String.format("%s evaluated as %.4f", "( 4 + -2 ) * 7",
                eval("( 4 + -2 ) * 7")));
        try {
            System.out.println(String.format("postfix notation for %s : %s", " 4 * ( 3 - 2 ) ) ** -2 ",
                    convertToPostFix(parseExpr(" 4 * ( 3 - 2 ) ) ** -2 "))));
        } catch (Exception e) {
            System.out.println("Invalid infix expression : 4 * ( 3 - 2 ) ) ** -2 ");
        }
        try {
            System.out.println(String.format("%s evaluated as %.4f", " 4 * ( 3 - 2 ) ) ** -2",
                    eval(" 4 * ( 3 - 2 ) ) ** -2")));
        } catch (Exception e) {
                System.out.println("Invalid infix expression : 4 * ( 3 - 2 ) ) **-2 ");
        }
        System.out.println(String.format("postfix notation for %s : %s",
                " ( ( 2.5 + 3.1 ) ** 2  - 6 ) * -3.4 ",
                convertToPostFix(parseExpr(" ( ( 2.5 + 3.1 ) ** 2  - 6 ) * -3.4 "))));
        result = postFixEval(parseExpr("2.5 3.1 + 2 ** 6 - -3.4 *"));
        System.out.println(String.format("postfix expr. %s evaluated as %.4f",
           "2.5 3.1 + 2 ** 6 - -3.4 *", result));
        assert Math.abs(result-(-86.224)) <= 0.00001;
        System.out.println(String.format("%s evaluated as %.4f",
                " ( ( 2.5 + 3.1 ) ** 2  - 6 ) * -3.4",
                eval(" ( ( 2.5 + 3.1 ) ** 2  - 6 ) * -3.4")));
        result = postFixEval(parseExpr("4 -2 5 * +"));
        System.out.println(String.format("postfix expr. %s evaluated as %.4f", "4 -2 5 * +",result));
        assert Math.abs(result-(-6.0)) <= 0.00001;
        result = postFixEval(parseExpr("3.5 2 3 + /"));
        System.out.println(String.format("postfix expr. %s evaluated as %.4f", "3.52 3 + /",result));
        assert Math.abs(result-(0.7)) <= 0.00001;
        System.out.println(String.format("%s evaluated as %.4f", "3.5 / ( 2 + 3 )",
                eval("3.5 / ( 2 + 3 )")));
        System.out.println(String.format("postfix notation for %s : %s", "2 ** 3 ** 2",
                convertToPostFix(parseExpr("2 ** 3 ** 2"))));
        System.out.println(String.format("%s evaluated as %.4f", "2 ** 3 ** 2",
                eval("2 ** 3 ** 2")));
        System.out.println(String.format("postfix expr. %s evaluated as %.4f","46 3 8 * 5 - % 10 -",postFixEval(parseExpr("46 3 8 * 5 - % 10 -"))));
        System.out.println(String.format("postfix notation for %s : %s", "46 % ( 3 * 8 - 5 ) - 10",
                convertToPostFix(parseExpr("46 % ( 3 * 8 - 5 ) - 10"))));
        System.out.println(String.format("%s evaluated as %.4f", "46 % ( 3 * 8 - 5 ) - 10",
                eval("46 % ( 3 * 8 - 5 ) - 10")));
        try {
            System.out.println(String.format("postfix expr. %s evaluated as %.4f", "2 2.5 + 3 4 *",postFixEval(parseExpr("2 2.5 + 3 4 *"))));
            assert false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert true;
        }
        try {
            System.out.println(String.format("postfix expr. %s evaluated as %.4f", "2 5 * + ",postFixEval(parseExpr("2 5 * + "))));
            assert false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert true;
        }
    }
}

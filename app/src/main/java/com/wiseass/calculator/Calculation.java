package com.wiseass.calculator;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

/**
 * This can be thought of as a "Service"
 * Created by Ryan on 15/11/2016.
 */

public class Calculation {

    private final Symbols symbols;
    private CalculationResult calculationResult;
    private static String currentExpression;

    interface CalculationResult {
        void onExpressionChanged(String result, boolean successful);
    }

    public void setCalculationResultListener(CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
        currentExpression = "";
    }

    public Calculation() {
        symbols = new Symbols();
    }

    /**
     * Delete a single character from currentExpression, unless empty
     * "" - invalid
     * 543 - valid
     * 45*65 - valid
     */
    public void deleteCharacter() {
        if (currentExpression.length() > 0) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            calculationResult.onExpressionChanged(currentExpression, true);
        } else {
            calculationResult.onExpressionChanged("Invalid Input", false);
        }
    }

    /**
     * Delete entire expression unless empty
     * "" - invalid
     */
    public void deleteExpression() {
        if (currentExpression.equals("")) {
            calculationResult.onExpressionChanged("Invalid Input", false);
        }
        currentExpression = "";
        calculationResult.onExpressionChanged(currentExpression, true);
    }

    /**
     * Append number to currentExpression if valid:
     * "0" & number is 0 - invalid
     * "12345678909876543" - invalid
     *
     * @param number
     */
    public void appendNumber(String number) {
        if (currentExpression.startsWith("0") && number.equals("0")) {
            calculationResult.onExpressionChanged("Invalid Input", false);
        } else {
            if (currentExpression.length() <= 16) {
                currentExpression += number;
                calculationResult.onExpressionChanged(currentExpression, true);
            } else {
                calculationResult.onExpressionChanged("Expression Too Long", false);
            }
        }
    }

    /**
     * Append an Operator to currentExpression, if valid:
     * 56 - valid
     * 56* - invalid
     * 56*2 - valid
     * "" - invalid
     *
     * @param operator one of:
     *                 - "*"
     *                 - "/"
     *                 - "-"
     *                 - "+"
     */
    public void appendOperator(String operator) {
        if (validateExpression(currentExpression)) {
            currentExpression += operator;
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    /**
     * See type comment for appendOperator
     */
    public void appendDecimal() {
        if (validateExpression(currentExpression)) {
            currentExpression += ".";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }


    /**
     * If currentExpression passes checks, pass currentExpression to symbols object, \
     * then return the result.
     */
    public void performEvaluation() {
        if (validateExpression(currentExpression)) {
            try {
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                calculationResult.onExpressionChanged(currentExpression, true);
            } catch (SyntaxException e) {
                calculationResult.onExpressionChanged("Invalid Input", false);
                e.printStackTrace();
            }
        }
    }

    /**
     * Helper function to validate expressions against the following checks:
     * "" - invalid;
     * 8765 - valid
     *
     * @param expression
     * @return
     */
    public boolean validateExpression(String expression) {
        if (expression.endsWith("*") ||
                expression.endsWith("/") ||
                expression.endsWith("-") ||
                expression.endsWith("+")
                ) {
            calculationResult.onExpressionChanged("Invalid Input", false);
            return false;
        } else if (expression.equals("")) {
            calculationResult.onExpressionChanged("Empty Expression", false);
            return false;
        } else if (expression.length() > 16) {
            calculationResult.onExpressionChanged("Expression Too Long", false);
            return false;
        } else {
            return true;
        }
    }

}

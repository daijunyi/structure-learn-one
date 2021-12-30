package com.daijunyi.structure.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

class PolandNotationCalculatorMain{
    public static void main(String[] args) {
        BigDecimal compute = PolandNotationCalculator.computePostfix("3 4 + 5 X 6 -");
        System.out.println("计算的值:"+compute.toString());
        String s = PolandNotationCalculator.infixToPostfixExpression("(3+4)*5-6");
        System.out.println("后缀表达式："+s);
        BigDecimal bigDecimal = PolandNotationCalculator.computeInfix("(3+4)*5-6");
        System.out.println("计算值："+bigDecimal);

    }
}

/**
 * @author djy
 * @createTime 2021/12/30 下午2:05
 * @description 逆波兰计算器
 */
public class PolandNotationCalculator {

    /**
     * 中缀转后缀表达式
     * 1+((2+3)X4)-5
     * 1 2 3 + 4 X + 5 -
     * @param infixExpression
     * @return
     */
    public static String infixToPostfixExpression(String infixExpression){
        //结果
        ArrayList<String> resultArray = new ArrayList<>();
        //符号
        Stack<String> operatorStackStack = new Stack<>();
        char[] chars = infixExpression.toCharArray();
        int index = 0;
        String numberCache = "";
        for (char item : chars){
            String s = String.valueOf(item);
            if (checkIsNumber(s)){
                numberCache += s;
                //数字
                //判断下一个是不是数字
                if (index+1 != chars.length && checkIsNumber(String.valueOf(chars[index+1]))){

                }else{
                    resultArray.add(numberCache);
                    numberCache="";
                }
            }else if (s.matches("^\\({1}$")){
                operatorStackStack.push(s);
            }else if(s.matches("^\\){1}$")){
                //符号栈直到第一个是（
                while (operatorStackStack.size() > 0 && !operatorStackStack.peek().matches("^\\({1}$")){
                    resultArray.add(operatorStackStack.pop());
                }
                if (operatorStackStack.size() > 0){
                    operatorStackStack.pop();
                }
            }else{
                while (operatorStackStack.size() > 0
                        && !operatorStackStack.peek().matches("^\\({1}$")
                        && priority(s) <= priority(operatorStackStack.peek())){
                    resultArray.add(operatorStackStack.pop());
                }
                operatorStackStack.push(s);
            }
            index++;
        }
        //符号栈一次入结果栈
        while (operatorStackStack.size() > 0){
            resultArray.add(operatorStackStack.pop());
        }
        //拼接后缀表达式 空格隔开
        String expression = "";
        for (String item : resultArray){
            if (expression.length() > 0){
                expression += " ";
            }
            expression += item;
        }
        return expression;
    }

    /**
     * 判断是不是数字
     * @param s
     * @return
     */
    private static boolean checkIsNumber(String s) {
        return s.matches("^\\d{1}$") || s.matches("^\\.{1}$");
    }

    /**
     * 中缀计算
     * @param expression
     * @return
     */
    public static BigDecimal computeInfix(String expression){
        String s = infixToPostfixExpression(expression);
        return computePostfix(s);
    }

    /**
     * 后缀计算
     * @param expression
     * @return
     */
    public static BigDecimal computePostfix(String expression){
        if (expression == null || expression.length() == 0){
            return BigDecimal.valueOf(0);
        }
        String[] s = expression.split(" ");
        Stack<BigDecimal> stack = new Stack<>();
        for (String item : s){
            if (item.matches("\\d")){
                //数字
                stack.push(new BigDecimal(item));
            }else{
                BigDecimal rear = stack.pop();
                BigDecimal front = stack.pop();
                stack.push(CalculatorInfixBracketAndDecimals.compute(front, rear, item));
            }
        }
        return stack.pop();
    }

    private static int priority(String operator){
        if ("*".equals(operator) || "/".equals(operator)) {
            return 3;
        }else if ("-".equals(operator) || "+".equals(operator)){
            return 2;
        }else {
            return 0;
        }
    }

}

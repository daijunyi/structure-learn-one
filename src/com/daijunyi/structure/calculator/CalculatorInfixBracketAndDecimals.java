package com.daijunyi.structure.calculator;

import com.daijunyi.structure.stack.ArrayStack;

import java.math.BigDecimal;


class CalculatorInfixBracketAndDecimalsMain{
    public static void main(String[] args) {
        BigDecimal compute = CalculatorInfixBracketAndDecimals.compute("6.2-8*(5+2)/2");
        System.out.println("结果"+compute.toString());
        BigDecimal compute1 = CalculatorInfixBracketAndDecimals.compute("10.55-1002.3/2*(3-2)+100.2*400+2");
        System.out.println("结果"+compute1.toString());
    }
}
/**
 * @author djy
 * @createTime 2021/12/29 下午4:57
 * @description 计算器中缀表达式 处理 括号，处理小数
 */
public class CalculatorInfixBracketAndDecimals {

    /**
     *
     ● 创建两个栈，一个保存数字，一个保存符号栈
     ● 通过一个index值，来遍历我们的表达式，扫描字符串
     ● 如果发现是一个数字，缓存追加当前数字
     ○ 下一位是数字，就继续循环，并且缓存当前值
     ○ 下一位不是数字，直接把缓存值入数字栈
     ● 如果扫描到一个符号，就分如下情况
     ○ 当前符号栈为空，直接入栈
     ○ 符号栈有操作符，进行比较，
     ■ 当前的操作符优先级小于或者等于栈中操作符，就需要从数栈中pop出两个数，在从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，并重复刚刚的步骤，直到符号栈为空，或者当前符号优先级大于栈中符号，
     ■ 如果当前的操作符优先级大于栈中的操作符，直接入符号栈
     ○ 当前表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算，值再存入数字栈
     ○ 最后在数栈中只有一个数字，就是表达式的结果
     ● 如果扫描到（符号，是直接入栈
     ● 如果扫描到）是要进行计算直到扫描到符号栈中最上面一个是（
     ○ 计算结果直接入数栈，并且舍弃）和pop出符号栈的（舍弃
     * @param expression
     * @return
     */
    public static BigDecimal compute(String expression){
        //数字栈
        ArrayStack<BigDecimal> numberStack = new ArrayStack<>(100);
        //符号栈
        ArrayStack<String> operatorStack = new ArrayStack<>(100);
        //扫描中缀表达式进栈
        scanExpression(expression, numberStack, operatorStack);
        //进行计算
        while (!operatorStack.isEmpty()){
            BigDecimal value2 = numberStack.pop();
            BigDecimal value1 = numberStack.pop();
            numberStack.push(compute(value1,value2,operatorStack.pop()));
        }
        return numberStack.pop();
    }

    private static void scanExpression(String expression, ArrayStack<BigDecimal> numberStack, ArrayStack<String> operatorStack) {
        int index = 0;
        String s;
        String numberCache = "";
        while (true){
            if (index == expression.length()){
                break;
            }
            s = expression.substring(index, index+1);
            //匹配数字或者小数点.
            if (checkIsNumber(s)){
                numberCache += s;
                //数字
                if (index+1 <expression.length()){
                    String nextV = expression.substring(index + 1, index+2);
                    if (checkIsNumber(nextV)){

                    }else{
                        numberStack.push(new BigDecimal(numberCache));
                        numberCache = "";
                    }
                }else{
                    numberStack.push(new BigDecimal(numberCache));
                    numberCache = "";
                }
            }else if(s.matches("^\\({1}$")) {
                operatorStack.push(s);
            }else if (s.matches("^\\){1}$")){
                //判断是不是(的话就继续计算
                while (!operatorStack.peek().matches("^\\({1}$")){
                    BigDecimal value2 = numberStack.pop();
                    BigDecimal value1 = numberStack.pop();
                    numberStack.push(compute(value1,value2,operatorStack.pop()));
                }
                //舍弃掉(
                operatorStack.pop();
            }else{
                if (operatorStack.isEmpty()){
                    //符号栈为空直接入栈
                    operatorStack.push(s);
                }else{
                    //符号
                    while (operatorStack.peek() != null && priority(s) <= priority(operatorStack.peek())){
                        //需要pop两个数进行计算
                        BigDecimal value2 = numberStack.pop();
                        BigDecimal value1 = numberStack.pop();
                        //计算并入栈
                        numberStack.push(compute(value1,value2,operatorStack.pop()));
                    }
                    operatorStack.push(s);
                }
            }
            index++;
        }
    }

    private static boolean checkIsNumber(String s) {
        return s.matches("^\\d+$") || s.matches("^\\.{1}$");
    }

    private static BigDecimal compute(BigDecimal front, BigDecimal rear, String operator){
        switch (operator){
            case "*":
                return front.multiply(rear);
            case "/":
                return front.divide(rear);
            case "-":
                return front.subtract(rear);
            case "+":
                return front.add(rear);
            default:
                return BigDecimal.valueOf(0);
        }
    }

    private static int priority(String operator){
        if ("*".equals(operator) || "/".equals(operator)) {
            return 3;
        }else if ("-".equals(operator)){
            return 2;
        }else if ("+".equals(operator)){
            return 1;
        }else {
            return 0;
        }
    }
}

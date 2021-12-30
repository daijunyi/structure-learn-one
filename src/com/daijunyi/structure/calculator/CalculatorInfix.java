package com.daijunyi.structure.calculator;

import com.daijunyi.structure.stack.ArrayStack;


class CalculatorInfixMain{
    public static void main(String[] args) {
        int compute = CalculatorInfix.compute("6-8*5+2");
        System.out.println("结果"+compute);
        int compute1 = CalculatorInfix.compute("10-1002-2+100*400+2");
        System.out.println("结果"+compute1);
    }
}

/**
 * @author djy
 * @createTime 2021/12/27 下午8:48
 * @description 中缀计算器，不考虑（）不考虑小数，考虑加减乘除
 */
public class CalculatorInfix {

    /**
     *
     * ● 创建两个栈，一个保存数字，一个保存符号栈
     * ● 通过一个index值，来遍历我们的表达式，扫描字符串
     * ● 如果发现是一个数字
     *  如果下一位也是数字就继续循环，缓存当前的数值
     *  如果下一位不是数字/空就直接把当前的值入栈
     * ● 如果扫描到一个符号，就分如下情况
     *   ○ 当前符号栈为空，直接入栈
     *   ○ 符号栈有操作符，进行比较，
     *     ■ 当前的操作符优先级小于或者等于栈中操作符，就需要从数栈中pop出两个数，在从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，再次重复刚刚的比较。直到操作符栈为空，或者优先级大于操作符栈第一个
     *     ■ 如果当前的操作符优先级大于栈中的操作符，直接入符号栈
     *   ○ 当前表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算，值再存入数字栈
     *   ○ 最后在数栈中只有一个数字，就是表达式的结果
     * 计算中缀表达式 不包含（）整数，加减乘除
     * @param expression
     * @return
     */
    public static int compute(String expression){
        //数字栈
        ArrayStack<Integer> numberStack = new ArrayStack<>(100);
        //符号栈
        ArrayStack<String> operatorStack = new ArrayStack<>(100);
        //扫描中缀表达式进栈
        scanExpression(expression, numberStack, operatorStack);
        //进行计算
        while (!operatorStack.isEmpty()){
            Integer value2 = numberStack.pop();
            Integer value1 = numberStack.pop();
            numberStack.push(compute(value1,value2,operatorStack.pop()));
        }
        return numberStack.pop();
    }

    private static void scanExpression(String expression, ArrayStack<Integer> numberStack, ArrayStack<String> operatorStack) {
        int index = 0;
        String s;
        String numberCache = "";
        while (true){
            if (index == expression.length()){
                break;
            }
            s = expression.substring(index, index+1);
            if (s.matches("^\\d+$")){
                numberCache += s;
                //数字
                if (index+1 <expression.length() && expression.substring(index + 1, index+2).matches("^\\d$")){
                }else{
                    numberStack.push(Integer.valueOf(numberCache));
                    numberCache = "";
                }
            }else{
                if (operatorStack.isEmpty()){
                    //符号栈为空直接入栈
                    operatorStack.push(s);
                }else{
                    //符号
                    while (operatorStack.peek() != null && priority(s) <= priority(operatorStack.peek())){
                        //需要pop两个数进行计算
                        Integer value2 = numberStack.pop();
                        Integer value1 = numberStack.pop();
                        //计算并入栈
                        numberStack.push(compute(value1,value2,operatorStack.pop()));
                    }
                    operatorStack.push(s);
                }
            }
            index++;
        }
    }

    private static int compute(Integer front,Integer rear,String operator){
        switch (operator){
            case "*":
                return front*rear;
            case "/":
                return front/rear;
            case "-":
                return front-rear;
            case "+":
                return front+rear;
            default:
                return 0;
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

package com.daijunyi.structure.calculator;

import java.math.BigDecimal;
import java.util.Stack;

class PolandNotationCalculatorMain{
    public static void main(String[] args) {
        BigDecimal compute = PolandNotationCalculator.compute("3 4 + 5 * 6 -");
        System.out.println("计算的值:"+compute.toString());
    }
}

/**
 * @author djy
 * @createTime 2021/12/30 下午2:05
 * @description 逆波兰计算器
 */
public class PolandNotationCalculator {

    public static BigDecimal compute(String expression){
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

}

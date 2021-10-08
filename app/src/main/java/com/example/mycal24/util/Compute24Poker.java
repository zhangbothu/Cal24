package com.example.mycal24.util;

import java.util.ArrayList;
import java.util.List;

public class Compute24Poker {
    final int N = 4;// 生成卡牌的数量
    Character op[] = {'+', '*', '-', '/'};// 待打印的操作符
    Integer cards[] = {3, 8, 6, 2};// 四个放牌的空位
    List<String> results = new ArrayList<>();
    boolean pan = false;// 看能不能生成24点
    int resAmount = 0; // 记录有多少种答案
    final static String[] COLORTYPE = {"红桃", "黑桃", "方块", "梅花"};// 花色
    static List<String> pokers = new ArrayList<>();// 储存卡牌

	public Integer[] getCards() {
		return cards;
	}

	public List<String> getResults() {
		return results;
	}

	public void compute(Integer[] cards4)
	{

		this.cards=cards4;
		PermuationNum<Integer> q1 = new PermuationNum<>();
		q1.makeQuan(this.cards);
		List<Integer[]> quan = q1.getQuanPaiLie();
		PermuationOperator<Character> q2 = new PermuationOperator<>();
		q2.makeQuan(this.op);
		List<Character[]> operators = q2.getOperator3();
		this.cal24(quan, operators);
	}


	public static void main(String[] args) {
    }// end function main

    public void cal24(List<Integer[]> nums, List<Character[]> operators) {
        results.clear();
        for (int ii = 0; ii < nums.size(); ii++) {
            Integer[] tcards = nums.get(ii);
            for (int jj = 0; jj < operators.size(); jj++) {
                Character[] top = operators.get(jj);
                boolean pan2 = cal24one(tcards, top);
                if (!pan) {
                    pan = pan2;
                }

            }
        }

        if (!pan) {// 当不能生成24点时打印这个语句
        	results.add("这四张牌无法凑成24点");
            System.out.println("这四张牌无法凑成24点");
        } // end if
    }

    private boolean cal24one(Integer[] tcards, Character[] top) {
        boolean pan = false;
        if (fun2Num(fun2Num(fun2Num(tcards[0], tcards[1], top[0]), tcards[2], top[1]), tcards[3], top[2]) == 24) {
            String temp2= String.format("第%d种: ( ( %d%c%d )%c%d )%c%d = 24\n", // 格式化输出递增式运算情况
					++resAmount, tcards[0], top[0], tcards[1], top[1], tcards[2], top[2], tcards[3]);
        	results.add(temp2);
            System.out.printf(temp2);
            pan = true;
        } // end if
        else if (fun2Num(fun2Num(tcards[0], tcards[1], top[0]), fun2Num(tcards[2], tcards[3], top[2]), top[1]) == 24) {
            String temp3= String.format("第%d种: ( %d%c%d ) %c ( %d%c%d ) = 24\n", // 格式化输出两边先运算的情况
					++resAmount, tcards[0], top[0], tcards[1], top[1], tcards[2], top[2], tcards[3]);
            results.add(temp3);
            System.out.printf(temp3);
            pan = true;
        } // end else if
        return pan;
    }

    /*
     * 从已经生成的52张扑克牌里面随机抽选四张 并且展现所抽选的扑克
     *
     * @para int x[]: 将四张扑克牌装进去的数组
     */

    /*
     * //计算每个两个整数的运算
     *
     * @para int x: 第一个整数
     *
     * @para int y: 第二个整数
     *
     * @para int opK: 对应的操作
     *
     * @return int: 返回运算结果
     */
    int fun2Num(int x, int y, char opK) {
        if (opK == '+')
            return x + y;// 对应加法
        if (opK == '*')
            return x * y;// 对应乘法
        if (opK == '-')
            return x - y < 0 ? -10000 : x - y;// 对应减法
        return (y == 0 || x % y != 0) ? -1000 : x / y;// 对应除法
    }// end function f

}// end class Compute24
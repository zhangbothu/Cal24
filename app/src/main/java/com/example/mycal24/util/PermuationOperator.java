package com.example.mycal24.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuationOperator<T> {
    private List<T[]> quan = new ArrayList<>();
    private int i = 0;
    private int total = 0;

    public static void main(String[] args) {
    }

    public void makeQuan(T[] aa) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    T[] temp = Arrays.copyOf(aa, 3);
                    temp[0] = aa[i];
                    temp[1] = aa[j];
                    temp[2] = aa[k];
                    quan.add(temp);
                }
            }
        }
        total = quan.size();
    }

    public List<T[]> getOperator3() {
        return quan;
    }

    public static class QuanPai2<T> {
        private List<T[]> quan = new ArrayList<>();
        private int i = 0;
        private int total = 0;

        public static void main(String[] args) {
        }

        public void makeQuan(T[] aa) {
            nextPer(aa, 0);
            total = quan.size();
        }

        public List<T[]> getQuanPaiLie() {
            return quan;
        }

        // start代表的是每一个子序列的第一个位置，
        private void nextPer(T[] arr, int start) {
            // 当start==arr.length-1时，说明子序列的长度为1，就不用再往下分子序列了
            if (start == arr.length - 1) {
                quan.add(Arrays.copyOf(arr, arr.length));
            }
            // 枚举该层子序列第一个位置可以取的值
            for (int i = start; i < arr.length; i++) {
                swap(arr, start, i);
                // 该层递归的子序列第一个位置已经确定了，所以又可以往下再分
                nextPer(arr, start + 1);
                // 递归结束回到当前层的时候，把之前交换的数字换回来，将序列恢复到初始情况
                swap(arr, start, i);
            }
        }

        // 交换数组中i和j对应位置的值
        private void swap(T[] arr, int i, int j) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }


}

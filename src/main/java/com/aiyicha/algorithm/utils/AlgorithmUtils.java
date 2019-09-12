package com.aiyicha.algorithm.utils;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmUtils {
    /**
     * 范围内某数字出现的次数统计
     * @param k
     * @param n
     * @return
     */
    public static int digitCount(int k, int n) {
        List<Integer> list = new ArrayList<>();
        int left = n, right = 0, s = 0, t, i = 0;
        int f = k == 0 ? 0 : 1;
        while ((t = left % 10) != 0) {
            right = n - left * (int)Math.pow(10, i);
            left = left / 10;

            // 主逻辑
            if (t < k && left > 0 - 1) {
                s += (left + f - 1) * (int)Math.pow(10, i);
            } else if (t == k) {
                s += (left + f - 1) * (int)Math.pow(10, i) + (right + 1);
            } else if (t > k){
                s += (left + f) * (int)Math.pow(10, i);
            }
            i++;
        }
        return s;
    }

}

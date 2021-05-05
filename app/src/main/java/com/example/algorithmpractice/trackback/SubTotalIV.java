package com.example.algorithmpractice.trackback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hufei on 2021/4/24.
 * 654267767@qq.com
 * des:
 *
 *
 * 可以通过动态规划的方法计算可能的方案数，用dp[x] 表示选取的元素之和等于 x 的方案数，目标是求 dp[target].
 * 动态规划的边界是 dp[0] = 1. 只有当不选取任何元素时，元素之和才为0， 因此只有 1种方案。
 * 当 1 ≤ i ≤ target 时，如果存在一种排列， 其中的元素之和等于i， 则排列的最后一个元素一定是数组nums中的一个元素，
 * 假设该排列的最后一个元素时numm 则一定有 num ≤1, 对于元素之和等于i-num 的每一种排列，在最后添加num之后
 * 即可得到一个元素之和等于 i 的排列， 因此在计算 do[i] 时， 应该计算所有的 dp[i- num]之和。
 *
 * 由此可以得到动态规划的做法：
 * 1.初始化 dp[0] =1;
 * 2.遍历 i 从1到target,对于每个i, 进行如下操作:
 *      遍历数组nums 中的每个元素 num， 当 num ≤ i 时，将 dp[i- num] 的值加到 dp[i].
 *
 * 3.最终得到 dp[target] 的值即为答案。
 *
 * 上述做法是否考虑到选取元素的顺序？答案是肯定的。因为外层循环时遍历从 1 到 target的值， 内层循环时遍历数组 nums 的值，
 * 在计算 dp[i] 的值时， nums中的每个小于等于 i 的元素都可能作为元素之和，
 * 等于 i 的排列的最后一个元素， 例如： 1和3 都在数组 nums 中，计算do[4]的时候，排列的最后一个元素
 * 可以是 1 也可以是  3， 因此 dp[1] 和 dp[3] 都会被考虑到， 即不同的顺序都会被考虑到。
 */
class SubTotalIV {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] =1;

        for (int i = 0; i <= target ; i++) {
            for (int num : nums) {
                if(num <= i) {
                    dp[i] += dp[i-num];
                }
            }
        }

        return dp[target];
    }



    public static void main(String[] args) {
        SubTotalIV test = new SubTotalIV();
        int[] arr = {1,2,3};
        System.out.println("结果:" + test.combinationSum4(arr, 4));
    }
}

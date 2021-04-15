package com.example.algorithmpractice.DP;

/**
 * Created by hufei on 2021/4/15.
 * 654267767@qq.com
 * des: 题目：一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 *      动态转移方程： dp[i] = max(dp[i - 2] + nums[i], dp[i-1]);
 *    边界: 只有1间： dp[start] = nums[start]
 *          只有2间： dp[start+1] = max(nums[start], nums[start+1])
 *
 *   分别取 (start, end) = (0, n-2) 和 (start, end) = (1, n-1) 进行计算，取两个dp[end]中的最大值即为结果。
 */
class RobRangII {

    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 偷第1间就不能偷最后一间，范围为 0，len -2
        // 偷最后一间就不能偷第一间，范围为 1, len -1
        return Math.max(robRange(nums, 0, len -2), robRange(nums, 1, len -1));
    }

    /**
     * 单独计算一个区间内的最大和，
     * 每次比较 当前值+ 往前2间的和 与 前一间的和的最大值
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start+1]);
        for (int i = start+2; i <= end; i++) {
            int tmp = second;
            second = Math.max(first + nums[i], second);
            first = tmp;
        }

        return second;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,1};
        RobRangII solution = new RobRangII();
        solution.rob(nums);
    }
}

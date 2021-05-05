package com.example.algorithmpractice.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hufei on 2021/5/5.
 * 654267767@qq.com
 * des:给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxTotal740 {

    /**
     * 方法一：动态规划
     * 根据题意，在选择了元素x之后，该元素以及所有等于x-1或x+1 的元素会从数组中删去，
     * 若还有多个值为x 的元素，由于所有等于 x-1 或 x+1 的元素已经被删除， 我们可以直接删除x并获取其点数。
     * 因此若选择了x,所有等于x 的元素也应一同被选择，以尽可能多的获得点数。
     * 记元素 x 在数组中出现的次数为 cx， 我们可以用一个数组 sum 记录数组 nums 中所有相同元素之和，
     * 即 sum[x] = x * cx。 若选择了x，则可以获取 sum[x] 的点数，且无法再选择 x -1 和 x + 1.
     * 接下来比较 sum[i] 和 sum[i+1]较大值，累加
     *
     * 时间复杂度 O(N+M), 其中 N 是数组 nums的长度， M是nums中元素的最大值。
     * 空间复杂度 O(M).
     */
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int val : nums) {
            maxVal = Math.max(maxVal, val);
        }
        int[] sum = new int[maxVal + 1];
        for (int val : nums) {
            sum[val] += val;
        }
        return rob(sum);
    }

    public int rob(int[] nums) {
        int size = nums.length;
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < size; i++) {
            int tmp = second;
            second = Math.max(first+nums[i], second);
            first = tmp;
        }
        return second;
    }


    /**
     * 方法二： 排序 + 动态规划
     * 注意到若 nums 中不存在某个元素x， 则选择任一小于x 的元素不会影响到大于x 的元素的选择。
     * 因此我们可以将 nums 排序后，将其划分成若干连续子数组，子数组内任意相邻元素只差不超过1.
     * 对每个子数组按照方法一的动态规划过程计算出结果，累加所有结果即为答案。
     *
     * 时间复杂度 O(N logN),其中 N是数组 nums的长度，对nums排序需要花费 O(NlogN),遍历计算需要花费 O(N).
     * 空间复杂度 O(N).
     */
    public int deleteAndEarn2(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Arrays.sort(nums);
        List<Integer> sum = new ArrayList<>();
        sum.add(nums[0]);
        int size =1;
        for (int i = 1; i < n; i++) {
            int val = nums[i];
            if(val == nums[i-1]) {
                sum.set(size-1, sum.get(size-1) + val);
            } else if (val == nums[i-1] + 1) {
                sum.add(val);
                ++size;
            } else {
                ans += rob2(sum);
                sum.clear();
                sum.add(val);
                size=1;
            }
        }
        ans+=rob2(sum);
        return ans;
    }

    private int rob2(List<Integer> nums) {
        int size = nums.size();
        if(size ==1){
            return nums.get(0);
        }
        int first = nums.get(0), second = Math.max(nums.get(0), nums.get(1));
        for (int i = 2; i < size; i++) {
            int tmp = second;
            second = Math.max(first+ nums.get(i), second);
            first = tmp;
        }
        return second;
    }
}

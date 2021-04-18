package com.example.algorithmpractice.arr;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by hufei on 2021/4/17.
 * 654267767@qq.com
 * des:
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 */
class ExistedArrIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 桶排序
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        // 比如 t == 3, 那么 0,1,2,3 应该是一个桶， 所以桶大小为：t+1.
        long w = t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if(map.containsKey(id)) {
                return true;
            }
            // 检查相邻两个桶的元素范围
            if(map.containsKey(id -1) && Math.abs(nums[i] - map.get(id -1)) < w) {
                return true;
            }
            if(map.containsKey(id +1) && Math.abs(nums[i] - map.get(id +1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if(i >=k) { // 删除不在范围内的桶
                map.remove(getID(nums[i- k], w));
            }
        }

        return false;
    }

    private long getID(int x, long w) {
        if(x >=0) {
            return x /w;
        }
        // 比如 -4,-3,-2,-1要划入同一个桶 ,整体左移一个桶。
        return (x+1) / w -1;
    }

}

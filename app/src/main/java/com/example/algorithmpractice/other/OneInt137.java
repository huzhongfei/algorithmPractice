package com.example.algorithmpractice.other;

import java.util.HashMap;
import java.util.Map;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/30
 * desc:给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 链接：https://leetcode-cn.com/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/
 */
class OneInt137 {

    /**
     * 哈希表法
     * 时间复杂度 O(n), 其中 n 是数组的长度
     * 空间复杂度 O(n), 哈希映射中最多 [n/3] + 1个元素，即需要的空间为 O(n)
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    /**
     * 一次确定每一个的二进制位
     * 由于数组中的元素都在int(即32位整数) 范围内，因此我们可以依次计算答案的每一个二进制位是 0 还是1.
     * 具体地，考虑答案的第 i 个二进制位（i从0开始编号），它可能为 0 或1.
     * 对于数组中 非答案的元素，每一个元素都出现了3次，对应着第i个二进制位的 3个0 或 3个1，无论是哪一种情况，它们的和都是3的倍数（即为0 或3）。
     * 因此： 答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以3的余数。
     * 这样一来，对于数组中的每一个元素 x， 我们使用位运算（x>>i) & 1 得到 x 的第i个二进制位，
     * 并将他们相加再对 3 取余，得到的结果一定为 0 或1，即为答案的第 i 个二进制位。
     *
     * 时间复杂度:O(n logC), 其中n是数组的长度，C是元素的数据范围。
     * 空间复杂度: O(1).
     */
    public int singleNumber2(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }

            if(total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * 方法三： 数字电路设计
     *
     * 时间复杂度:O(n)
     * 空间复杂度: O(1)
     */
    public int singleNumber3(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int aNext = (~a & b & num) | (a & ~b & ~num), bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;

    }

}

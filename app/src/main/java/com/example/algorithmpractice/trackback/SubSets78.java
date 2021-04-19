package com.example.algorithmpractice.trackback;

import java.util.ArrayList;
import java.util.List;


/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/19
 * desc: 求子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 */
class SubSets78 {
    List<Integer> tmp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    private void dfs(int cur, int[] nums) {
        if(cur == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }

        tmp.add(nums[cur]);
        dfs(cur+1, nums);
        tmp.remove(tmp.size()-1);
        dfs(cur+1, nums);
    }

    public static void main(String[] args) {
        SubSets78 test = new SubSets78();
        int[] nums = {1,2,3,4,5};
        List<List<Integer>> subsets = test.subsets(nums);
        for (List<Integer> subset : subsets) {
            System.out.println("result-->" + subset.toString());
        }

    }

}

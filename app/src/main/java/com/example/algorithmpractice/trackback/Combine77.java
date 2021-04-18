package com.example.algorithmpractice.trackback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hufei on 2021/4/18.
 * 654267767@qq.com
 * des: 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class Combine77 {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    private void dfs(int cur, int n, int k) {
        // 剪枝： temp 长度加上区间 [cur, n] 的长度小于k, 不可能构造出长度为 k 的 temp
        if(temp.size() + (n-cur + 1) <k) {
            return;
        }
        // 记录符合要求的答案
        if(temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur+1, n, k);
        temp.remove(temp.size()-1); // 比如 1,2 已经满足长度，就会返回来，删除2， 继续向下走， 1,3
        // 考虑不选择当前位置
        dfs(cur+1, n, k);
    }

    public static void main(String[] args) {
        Combine77 combine77 = new Combine77();
        List<List<Integer>> combine = combine77.combine(4, 2);
        System.out.println(combine.toString());
    }
}

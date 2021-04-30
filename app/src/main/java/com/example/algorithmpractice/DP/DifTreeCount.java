package com.example.algorithmpractice.DP;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/27
 * desc:给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 【序号:96】
 *         链接：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
 */
class DifTreeCount {
    /**
     *  使用动态规划
     *  思路：
     *  给定一个有序序列 1..n， 为了构建出一颗二叉搜索树，我们可以遍历每个数字i ，将该数字作为树根，
     *  将 1...（i-1) 序列作为左子树，将(i+1)..n 序列左右右子树。
     *  接着我们可以按照同样的方式递归构建左子树和右子树。
     *  在上述构建的过程中，由于根的值不同，因此我们能保证，每颗二叉搜索树是唯一的。
     *  由此可见，原问题可以分解成规模较小的两个子问题，且子问题的解可以复用。因此，我们可以想到使用动态规划。
     *
     *  算法：
     *  题目要求是计算不同二叉搜索树的个数。为此，我们可以定义两个函数：
     *      1.G(n): 长度为n的序列能构成的不同二叉搜索树的个数。
     *      2.F(i,n): 以i为根、序列长度为n的不同二叉搜索树个数(1≤ i ≤ n)。
     *  可见，G(n)是我们求解需要的函数。
     * G(n) 是所有 F（i,n) 的和。
     * 边界情况，当序列长度为1（只有根)或为0（空数)时， 只有一种情况，即 G(0) =1, G(1) =1.
     * 给定序列 1...n,我们选择数字i作为根，则根为i 的所有二叉搜索树的集合是左子树和右子树集合的笛卡尔积，
     * 对于笛卡尔积中的每个元素，加上根节点之后兴城完整的二叉搜索树。
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];

    }
    /**
     *  使用数学
     * @param n
     * @return
     */
    public int numTrees2(int n) {
        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
}

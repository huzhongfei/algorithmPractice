package com.example.algorithmpractice.DP;

import java.util.Arrays;

/**
 * desc: 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 */
public class FindPathIISolution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 滚动数组
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];


//        obstacleGrid[0][0] =1;
//        for(int i = 0; i < obstacleGrid.length; i++) {
//            for(int j = 0; j < obstacleGrid[i].length;j++) {
//                if(i == 0 || j==0) {
//                    if(i != 0) {
//                        if(obstacleGrid[i-1][0] == -1) {
//                            obstacleGrid[i][0] = -1;
//                            continue;
//                        }
//                    } else {
//                        if(j != 0) {
//                            if(obstacleGrid[0][j-1] == -1) {
//                                obstacleGrid[0][j] = -1;
//                            } else {
//
//                                if (obstacleGrid[0][j] != -1) {
//                                    obstacleGrid[0][j] = obstacleGrid[0][j-1];
//                                }
//
//                            }
//                            continue;
//                        }
//                    }
//
//                    if(obstacleGrid[i][j] == 0) {
//                        obstacleGrid[i][j] = 1;
//                    }
//
//                    continue;
//                }
//
//                if(obstacleGrid[i][j] == 1) {
//                    obstacleGrid[i][j] = -1;
//                    continue;
//                }
//                if (obstacleGrid[i-1][j] == -1) {
//                    obstacleGrid[i][j] = obstacleGrid[i][j-1];
//                } else if (obstacleGrid[i][j-1] == -1) {
//                    obstacleGrid[i][j] = obstacleGrid[i-1][j];
//                } else {
//                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
//                }
//
//            }
//        }
//
//        return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }


    public static void main(String[] args) {
        FindPathIISolution solution = new FindPathIISolution();
        int[][] arr = new int[][] {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("结果:" + solution.uniquePathsWithObstacles(arr));

    }
}

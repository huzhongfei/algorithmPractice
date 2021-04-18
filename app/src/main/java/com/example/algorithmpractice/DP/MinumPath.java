package com.example.algorithmpractice.DP;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.channels.Channel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttributeView;

/**
 * Created by hufei on 2021/4/15.
 * 654267767@qq.com
 * des:给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 */
class MinmumPath {
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];


//        for(int i =0 ; i < grid.length; i++) {
//            for(int j = 0; j < grid[0].length; j++) {
//                if(i == 0) {
//                    if(j > 0) {
//                        grid[i][j] = grid[i][j-1] + grid[i][j];
//                    } else {
//                        continue;
//                    }
//                } else {
//                    if(j == 0) {
//                        grid[i][j] = grid[i-1][j] + grid[i][j];
//                    } else {
//                        grid[i][j] = Math.min(grid[i][j-1] + grid[i][j], grid[i-1][j]+ grid[i][j]);
//                    }
//                }
//            }
//        }
//
//        return grid[grid.length-1][grid[0].length-1];
    }


    public static void main(String[] args) {
        int[][] nums = new int[][] {{1,3,1},{1,5,1},{4,2,1}};
        MinmumPath.minPathSum(nums);
    
    }
}

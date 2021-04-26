package com.example.algorithmpractice.arr.binerySearch;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/26
 * desc:传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class ShipWithDay {


    public static int shipWithinDays(int[] weights, int D) {
        // 确定二分查找左右边界
        int left = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            left = Arrays.stream(weights).max().getAsInt();
        }
        int right = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            right = Arrays.stream(weights).sum();
        }
        System.out.println("边界: left:" + left + ", right:" + right);
        while(left < right) {
            int mid = (left + right) /2;
            // need 为需要运送的天数
            // cur 为当前这一天一斤运送的包裹重量之和
            int need = 1, cur = 0;
            for (int weight : weights) {
                if(cur +weight > mid) {
                    ++need;
                    cur = 0;
                }
                cur += weight;
            }
            if(need <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
//        ShipWithDay shipWithDay = new ShipWithDay();
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println("运行结果:" + ShipWithDay.shipWithinDays(arr,5));

    }
}

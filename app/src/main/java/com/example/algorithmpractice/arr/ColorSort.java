package com.example.algorithmpractice.arr;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;

/**
 * Created by hufei on 2021/4/17.
 * 654267767@qq.com
 * des:
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ColorSort {

    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }




//        if(nums.length == 1) {
//            return;
//        } else if (nums.length == 2) {
//            if(nums[0] >nums[1]) {
//                int tmp = nums[0];
//                nums[0] = nums[1];
//                nums[1] = tmp;
//            }
//            return;
//        }
//
//        int cur = 0, idx = 0;
//        while(idx < nums.length) {
//            for(int i = idx ; i <nums.length; i++) {
//                if(nums[i] == cur) {
//                    int tmp = nums[idx];
//                    nums[idx] = nums[i];
//                    nums[i] = tmp;
//                    idx++;
//                }
//            }
//            cur++;
//        }

    }


    public void test() {
        int[] arr = new int[] {2, 0, 2, 1,1, 0, 2,1,0};
        sortColors(arr);
        System.out.println(arr);
    }


}

package com.ani.ccyl.leg;

import javafx.scene.effect.SepiaTone;
import org.junit.Test;

import java.util.*;

/**
 * Created by zhanglina on 18-1-25.
 */
public class RandomTest {
    @Test
    public void testRandom(){
       HashSet<Integer> set=new HashSet<>();
//        Random random=new Random();
//        for (int i=0;i<10;i++){
//            int a=random.nextInt(76);
//            set.add(a);
//        }
//        System.out.println(set);
        int n=20;
        int max=76;
        this.randomSet(76,20,set);
       System.out.println(set);

    }
    public void randomSet(int max, int n, Set<Integer> set){

        for (int i = 0; i < n; i++) {

            if (set.size()<n){
                Random random = new Random();
                int num = random.nextInt(max);
                set.add(num);
            }else {
                break;
            }


        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(max, n, set);// 递归
        }
    }
    @Test
    public void getList(){
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        System.out.println(list.get(list.size()));
    }
}

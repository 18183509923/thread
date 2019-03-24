package forkjoin.sum;

import java.util.Random;

//产生整形数组
public class MarkArray {
    //数组长度
    public static final int ARRAY_LENGTH = 4000;

    public static int[] makeArray(){
        Random r = new Random();
        //定义一个数组
        int[] result = new int[ARRAY_LENGTH];
        for (int i =0; i < ARRAY_LENGTH; i++){
            //用随机数填充数组
            result[i] = r.nextInt(ARRAY_LENGTH*2);
        }
        return result;
    }
}

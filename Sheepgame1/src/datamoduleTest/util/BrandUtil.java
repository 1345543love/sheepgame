package datamoduleTest.util;

import datamoduleTest.module.Brand;

import java.util.Random;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 16:21
 */
public class BrandUtil {
    public static Random random = new Random();
    public static String[] brandNames ={
            "刷子","剪刀","叉子","手套","奶瓶","小草","日落",
            "木墩","棉花","水盆","火堆","玉米","白菜","稻草",
            "线球","胡萝卜","铃铛","落日"
    };
    //随机获取一张牌的名称
    public static String getBrandname(){
        int randomindex = random.nextInt(brandNames.length);
        return brandNames[randomindex];
    }
    //创建随机牌的数组
    public static Brand[] buildBrands(Integer capacity){
        Brand[] brands = new Brand[capacity];//数组的容量等于图层的容量

        for (int i = 0; i < brands.length; i = i + 3){
            String randombrandname = getBrandname();

            Brand brand1 = new Brand(randombrandname);
            Brand brand2 = new Brand(randombrandname);
            Brand brand3 = new Brand(randombrandname);

            brands[i] = brand1;
            brands[i + 1] = brand2;
            brands[i + 2] = brand3;

        }

        for (int i = 0; i < brands.length; i++) {
            //当前位置 A 的变量拿到
            Brand brandA = brands[i];
            //交换位置的B
            int randomindex = random.nextInt(brands.length);
            Brand brandB = brands[randomindex];

            Brand temp = brandA;
            brands[i] = brandB;
            brands[randomindex] = temp;

        }
        return brands;
    }
}

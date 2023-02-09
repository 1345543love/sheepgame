package datamoduleTest.test;

import datamoduleTest.module.Brand;
import datamoduleTest.module.Cell;
import datamoduleTest.module.Layer;

import java.util.Random;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 14:30
 * 测试图形构建
 */
public class LayerBuilderTest {
    public static Random random = new Random();
    public static String[] brandNames ={
            "刷子","剪刀","叉子","手套","奶瓶","小草","日落",
            "木墩","棉花","水盆","火堆","玉米","白菜","稻草",
            "线球","胡萝卜","铃铛"
    };
    //
    public static String getBrandname(){
        int randomindex = random.nextInt(brandNames.length);
        return brandNames[randomindex];
    }

    public static void main(String[] args) {
        Layer layer = new Layer(6,6);

        Brand[] brands = new Brand[new Layer().getCapacity()];//数组的容量等于图层的容量

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
        Cell[][] cells = layer.getCells();

        int flag = 0;

        for( int row = 0; row < cells.length;row++){
            for (int col = 0;col < cells[row].length; col++){
                Cell cell = new Cell();
                cell.setState(1);
                cell.setBrand(brands[flag++]);
                cells[row][col] = cell;
            }
        }

    }
}

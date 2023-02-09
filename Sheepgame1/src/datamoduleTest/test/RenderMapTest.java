package datamoduleTest.test;

import datamoduleTest.module.*;

import datamoduleTest.util.BrandUtil;
import datamoduleTest.util.MapUtil;

import javax.swing.*;

import java.util.List;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 17:09
 * 测试 渲染一个地图的数据
 */
public class RenderMapTest extends JFrame{
    private Brand brand = new Brand("草地背景2");
    public static void main(String[] args) {

        new RenderMapTest();


    }
    public static Map map = MapUtil.buildMap(3);
    public RenderMapTest(){

        brand.setGrey(true);

        new Music().music("D:\\Sheepgame\\music\\sheep.mp3");
        //初始化窗口
        init();
        //渲染 将地图加到窗口
        List<Layer> list = map.getList();
        for (int i = 0; i < list.size(); i++) {
           renderLayer(list.get(i));
        }

        map.compareAll();
        brand.setBounds(0,0,500,900);
        this.getContentPane().add(brand);
        //自动刷新
        autoRefresh();

    }

    private void init(){

        this.setTitle("羊了个羊小游戏模拟");
        this.setSize(500,900);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置布局
        this.setLayout(null);
        this.setBounds(0,0,500,900);
        this.setLocationRelativeTo(null);//居中

        this.setVisible(true);

    }

    private void autoRefresh(){
        JFrame start = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    start.repaint();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private void renderLayer(Layer layer){
        Cell[][] cells = layer.getCells();

        for (int row = 0; row < cells.length; row++) {

            for (int col = 0;col < cells[row].length;col++){

                Brand brands1 = cells[row][col].getBrand();
                int x = col * 50 + layer.getOffsetx();
                int y = row * 50 + layer.getOffsety() + 200;
                brands1.setBounds(x,y,50,50);
                this.getContentPane().add(brands1);
            }
        }
    }
}

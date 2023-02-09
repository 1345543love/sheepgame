package datamoduleTest.view;

import datamoduleTest.module.*;
import datamoduleTest.util.MapUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 9:45
 */
public class Start extends JFrame {
    private Integer width = 500;
    private Integer height = 900;
    private Brand brand = new Brand("草地背景2");
    public static Map map = MapUtil.buildMap(3);
    public  Start() throws HeadlessException {
        brand.setGrey(true);

        init();

        new Music().music("D:\\Sheepgame\\music\\sheep.mp3");



        java.util.List<Layer> list = map.getList();
        for (int i = 0; i < list.size(); i++) {
            renderLayer(list.get(i));
        }
        map.compareAll();
        autoRefresh();//启动自动刷新线程
        //添加自定义组件到当前窗口
        brand.setBounds(0,0,500,900);
        this.getContentPane().add(brand);


    }
    private void autoRefresh(){
        Start start = this;
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
    private void init(){
        this.setVisible(true);

        this.setTitle("羊了个羊小游戏");
        this.setSize(width,height);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置布局
        this.setLayout(null);
        this.setBounds(0,0,500,900);
        this.setLocationRelativeTo(null);//居中


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
    public static void main(String[] args) {
        new Start();
    }
}

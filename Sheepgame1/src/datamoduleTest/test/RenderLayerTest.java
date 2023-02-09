package datamoduleTest.test;

import datamoduleTest.module.Brand;
import datamoduleTest.module.Cell;
import datamoduleTest.module.Layer;
import datamoduleTest.util.LayerUtil;
import datamoduleTest.view.Start;

import javax.swing.*;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 17:09
 * 测试 渲染一个图层的数据
 */
public class RenderLayerTest extends JFrame{
    public static void main(String[] args) {
        new RenderLayerTest();
    }
    private Layer layer = LayerUtil.buildLayer(14,9);

    public RenderLayerTest(){
        //初始化窗口
        init();
        //渲染 将图层加到窗口
        renderLayer();
        //自动刷新
        autoRefresh();
    }

    private void init(){
        this.setTitle("羊了个羊小游戏");
        this.setSize(470,850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置布局
        this.setLayout(null);
        this.setBounds(0,0,470,850);
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
    private void renderLayer(){
        Cell[][] cells = layer.getCells();

        for (int row = 0; row < cells.length; row++) {

            for (int col = 0;col < cells[row].length;col++){

                Brand brands1 = cells[row][col].getBrand();
                int x = col * 50;
                int y = row * 50;
                brands1.setBounds(x,y,50,50);
                this.getContentPane().add(brands1);
            }
        }
    }
}

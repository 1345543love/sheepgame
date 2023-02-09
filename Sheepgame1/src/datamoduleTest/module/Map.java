package datamoduleTest.module;

import datamoduleTest.util.MapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 17:49
 * 有多个图层,层层遮盖
 */
public class Map {
    private Integer floorHeight; //层高

    private List<Layer> list = new ArrayList<>(); //存放图层数据

    public Integer getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(Integer floorHeight) {
        this.floorHeight = floorHeight;
    }

    public List<Layer> getList() {
        return list;
    }

    public void setList(List<Layer> list) {
        this.list = list;
    }
    /*
    1.第一次进入需要调用compareAll()
    2.点击后需要调用compareAll()
     */

    //判断牌是否置灰
    public void compareAll(){
        for (int i = 1; i < list.size(); i++) {
            Layer layer = list.get(i);
            Cell[][] cells = layer.getCells();
            for (int row = 0; row < cells.length; row++) {

                for (int col = 0;col < cells[row].length;col++){

                    Cell cell = cells[row][col];
                    if (cell.getState() == 1){
                        Brand brand = cell.getBrand();
                        boolean compare = MapUtil.compare(brand, layer.getLastLayer());
                        brand.setGrey(compare);

                    }
                }
            }
        }
    }
}

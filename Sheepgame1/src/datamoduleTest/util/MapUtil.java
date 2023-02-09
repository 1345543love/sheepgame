package datamoduleTest.util;

import datamoduleTest.module.Brand;
import datamoduleTest.module.Cell;
import datamoduleTest.module.Layer;
import datamoduleTest.module.Map;

import java.awt.*;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 17:57
 */
public class MapUtil {
    public static Map buildMap(Integer floorHeight){
        Map map = new Map();
        map.setFloorHeight(floorHeight);

        Layer layer1 = LayerUtil.buildLayer(5,6);
        Layer layer2 = LayerUtil.buildLayer(7,9);
        Layer layer3 = LayerUtil.buildLayer(6,6);

        //构建图层链式关系

        layer3.setLastLayer(layer2);
        layer2.setLastLayer(layer1);
        layer1.setLastLayer(null);//递归结束

        map.getList().add(layer1);//在绝对布局中,同样位置,先加入的组件显示在最上层。
        map.getList().add(layer2);
        map.getList().add(layer3);

        return map;
    }
    public static boolean compare(Brand brand,Layer layer){
        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {

            for (int col = 0;col < cells[row].length;col++){

                Cell cell = cells[row][col];
                if (cell.getState() == 1){
                    Rectangle tem = cell.getBrand().getBounds();
                    Rectangle rec = brand.getBounds();
                    boolean result = rec.intersects(tem);

                    if (result){
                        //有遮盖
                        return result;
                    }
                }
            }
        }
        //当前上一层比较完,比较再上一层
        if (layer.getLastLayer() != null){
            return compare(brand,layer.getLastLayer());
        }
        else {
            return false;
        }
    }
}

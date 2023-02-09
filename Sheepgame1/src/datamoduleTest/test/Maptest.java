package datamoduleTest.test;

import datamoduleTest.module.Layer;
import datamoduleTest.module.Map;
import datamoduleTest.util.LayerUtil;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 17:52
 * 测试创建一个地图类
 */
public class Maptest {
    public static void main(String[] args) {
        Map map = new Map();
        map.setFloorHeight(3);

        Layer layer1 = LayerUtil.buildLayer(3,3);
        Layer layer2 = LayerUtil.buildLayer(3,3);
        Layer layer3 = LayerUtil.buildLayer(3,3);

        map.getList().add(layer1);
        map.getList().add(layer2);
        map.getList().add(layer3);
    }
}

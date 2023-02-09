package datamoduleTest.util;

import datamoduleTest.module.Brand;

import datamoduleTest.module.Cell;

import datamoduleTest.module.Layer;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 16:39
 */
public class LayerUtil {
    public static Layer buildLayer(Integer rownum,Integer colnum){
        Layer layer = new Layer(rownum,colnum);

        Cell[][] cells = layer.getCells();

        Brand[] brands = BrandUtil.buildBrands(layer.getCapacity());

        int flag = 0;

        for( int row = 0; row < cells.length;row++){
            for (int col = 0;col < cells[row].length; col++){
                 Brand brands1 = brands[flag++];

                Cell cell = new Cell();
                cell.setState(1);
                cell.setBrand(brands1);
                brands1.setCell(cell);
                cells[row][col] = cell;
            }
        }
        return layer;
    }
}

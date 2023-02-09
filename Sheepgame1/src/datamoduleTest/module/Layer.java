package datamoduleTest.module;

import java.util.Random;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 14:18
 * 图层类
 */
public class Layer {
    private Layer lastLayer;
    private Integer offsetx;
    private Integer offsety;
    private Integer rownum;
    private Integer colnum;

    private static Integer capacity;//图层最多容量
    private Integer size = 0;//当前图层牌数

    private Cell[][] cells = null;

    private Cell cell = null;

    public Layer() {
    }

    public Layer(Integer rownum, Integer colnum) {
        this.rownum = rownum;

        this.colnum = colnum;

        this.capacity = this.colnum * this.rownum;
        if (this.capacity % 3 != 0){
            try {
                throw new Exception("容量不是3的倍数!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.cells = new Cell[this.rownum][this.colnum];

        this.size = 0;//实时变化

        this.offsetx = new Random().nextInt(100);
        this.offsety = new Random().nextInt(100);
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Layer getLastLayer() {
        return lastLayer;
    }

    public void setLastLayer(Layer lastLayer) {
        this.lastLayer = lastLayer;
    }

    public Integer getOffsety() {
        return offsety;
    }

    public void setOffsety(Integer offsety) {
        this.offsety = offsety;
    }

    public Integer getOffsetx() {
        return offsetx;
    }

    public void setOffsetx(Integer offsetx) {
        this.offsetx = offsetx;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    public Integer getColnum() {
        return colnum;
    }

    public void setColnum(Integer colnum) {
        this.colnum = colnum;
    }

    public  Integer getCapacity() {
        return  capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

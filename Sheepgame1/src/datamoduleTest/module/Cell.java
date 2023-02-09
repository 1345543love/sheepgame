package datamoduleTest.module;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 14:16
 * 单元格类,有两种状态
 */
public class Cell {

    private Integer state = 0;
    private Brand brand;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}

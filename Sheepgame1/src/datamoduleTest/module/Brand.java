package datamoduleTest.module;

import datamoduleTest.test.RenderMapTest;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author whale-fall
 * @create 2023 - 02 - 08 10:09
 * 游戏中的每一张牌
 */
public class Brand extends Component{
    private Cell cell;

    private String name;

    private Boolean isGrey;//是否灰色

    private java.awt.Image image;

    private java.awt.Image greyimage;

    private Integer image_x;//左上角坐标

    private Integer image_y;

    private Integer image_w;

    private Integer image_h;

    NameSame nameSame = new NameSame();

    //输入图片名称
    public Brand(String name) {
        this.name = name;

        this.image = Toolkit.getDefaultToolkit().getImage("imges\\"+ name +".png");
        this.greyimage = Toolkit.getDefaultToolkit().getImage("imges\\"+ name +"_grey.png");

        this.isGrey = false;

        this.image_w = 50;
        this.image_h = 50;

        this.image_x = 0;
        this.image_y = 0;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Brand brand = (Brand) e.getSource();//获取当前组件
                if (brand.getGrey()){
                    return;
                }
                else {
//                    brand.getParent().remove(brand);//通过父类容器删自己
                    nameSame.addSlot(brand);
                    cell.setState(0);
                    cell.setBrand(null);
                    RenderMapTest.map.compareAll();
                }

            }
        });

    }

    @Override
    public void paint(Graphics g) {
        if (this.isGrey == true){
            //绘制灰色图片
            g.drawImage(this.greyimage,this.image_x,this.image_y,null);
        }
        else {
            //绘制正常图片
            g.drawImage(this.image,this.image_x,this.image_y,null);
        }
    }

    public Brand() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGrey() {
        return isGrey;
    }

    public void setGrey(Boolean grey) {
        isGrey = grey;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getGreyimage() {
        return greyimage;
    }

    public void setGreyimage(Image greyimage) {
        this.greyimage = greyimage;
    }

    public Integer getImage_x() {
        return image_x;
    }

    public void setImage_x(Integer image_x) {
        this.image_x = image_x;
    }

    public Integer getImage_y() {
        return image_y;
    }

    public void setImage_y(Integer image_y) {
        this.image_y = image_y;
    }

    public Integer getImage_w() {
        return image_w;
    }

    public void setImage_w(Integer image_w) {
        this.image_w = image_w;
    }

    public Integer getImage_h() {
        return image_h;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setImage_h(Integer image_h) {
        this.image_h = image_h;
    }
}

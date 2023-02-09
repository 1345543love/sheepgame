# sheep a sheep
# 羊了个羊小游戏模拟

### 1.代码逻辑：

#### 1.1`Map`类：

最顶层的数据模型类`Map`，称之为地图类。包含了所有元素，比如多个图层，消除框，道具之类的内容。采用集合`list`来存储多个图层，并对添加的图层进行排序，提供了`compareAll()`方法，为实现遮盖算法做准备；提供对应的`MapUtil`类创建存储组件的`Map`(采用**链式关系存储**，有利于实现遮盖算法)，提供`compare()方法`，来对产生的`Brand`组件进行判断；

`compareAll()`:

- 时间复杂度和空间复杂度较大，需加强学习，进行优化；
- 第一次启动程序，需调用；
- 点击移动组件后，需进行调用；

#### 1.2`Layer`类：

一个Map中有多个图层Layer,层层遮盖，被盖住的牌为灰色，不能点击。图层是二维表格，每个单元格是一个Cell类的对象。Layer类中提供三个重要参数：

```java
private Integer rownum;//行数
private Integer colnum;//列数
private static Integer capacity;//图层最多容量
```

其中`capacity`为图层最大容量,需控制其为3的倍数，否则会影响游戏正常进行，报异常：

```java
this.capacity = this.colnum * this.rownum;
if (this.capacity % 3 != 0){
    try {
        throw new Exception("容量不是3的倍数!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

Layer图层建立时，同时建立链式结构，提供`lastLayer`属性，实现compare方法，比较当前图层与上一层图像是否重叠。

**采用递归调用，实现多图层依次比较。**有遮盖的牌显示灰色；

#### 1.3`Cell`类：

Cell类 ，单元格，一个图层当中不是所有单元格都有图案。单元格分为两种状态：有图案和没有图案；

#### 1.4`Brand`类：

- brand类 ，牌，被盖住的牌是灰色的，不能点击。所以牌brand也有两种状态：灰色和正常色。

第一个属性是String类型的name；

第一个作用：在消除框内需要根据name属性来判断是否有三张相同的牌来进行消除，所以不同图案的牌不能有相同的name属性；

第二个作用：在文件流中，需要通过name属性来找到对应的牌，进行相关操作。在`str`目录下的image包中来存放图片。

消除牌后

### 2.功能和`API`:

#### 2.1遮盖算法：

每个图层之间采用链式关系连接，利用递归，对每个图层进行依次比较，如果组件被上一层遮盖，则显示灰色图片，

否则，显示正常图片。

```java
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
```

#### 2.2消除算法：

三个名称相同的组件，进行消除。创建一个集合，并将三个组件依次添加到集合List中，添加时，按照名称进行排序，当list的size为3时，清空list，达到消除的效果；

```java
this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Brand brand = (Brand) e.getSource();//获取当前组件
                if (brand.getGrey()){
                    return;
                }
                else {
                    nameSame.addSlot(brand);
                    cell.setState(0);
                    cell.setBrand(null);
                    RenderMapTest.map.compareAll();
                }

            }
        });
```

#### 2.3自动刷新线程：

启动一个线程，重写repaint()方法，设置刷新间隔，刷新游戏界面；

```java
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
```

#### 2.4添加背景图：

生成背景组件，调用如下两个方法添加背景

```java
brand.setBounds();
this.getContentPane().add(brand);
```

#### 2.5组件偏移实现：

利用随机数随机生成偏移量，并添加到单个组件生成；设置默认的偏移量添加到一个图层，调整到一个舒适的位置；

```java
private void renderLayer(Layer layer){
        Cell[][] cells = layer.getCells();

        for (int row = 0; row < cells.length; row++) {

            for (int col = 0;col < cells[row].length;col++){

                Brand brands1 = cells[row][col].getBrand();
                int x = col * 50 + layer.getOffsetx() + 150;
                int y = row * 50 + layer.getOffsety() + 200;
                brands1.setBounds(x,y,50,50);
                this.getContentPane().add(brands1);
            }
        }
    }
}
```

#### 2.6`music`类：

提供程序使用的音频：

```java
public class Music {
    public void music(String str){
        new Thread(){
            @Override
            public void run() {
                //声明一个File对象
                File mp3 = new File(str);
                //创建一个输入流
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(mp3);
                    //创建一个缓冲流
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    //创建播放器对象，把文件的缓冲流传入进去
                    Player player = new Player(fileInputStream);
                    //调用播放方法进行播放
                    player.play();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
```



#### 2.7打乱随机组件：

将所有组件存储到添加到一个list中，调用list中带有的方法打乱组件；

```java
for (int i = 0; i < brands.length; i++) {
    //当前位置 A 的变量拿到
    Brand brandA = brands[i];
    //交换位置的B
    int randomindex = random.nextInt(brands.length);
    Brand brandB = brands[randomindex];

    Brand temp = brandA;
    brands[i] = brandB;
    brands[randomindex] = temp;

}
```

#### 2.8调用`API`:

```java
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.*;
import java.awt.*;
```



#### 总结：

一个map有多个layer，一个layer有多个cell，一个cell有0张或者1张brand，一张brand有两种状态。

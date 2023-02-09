package datamoduleTest.module;

import javax.swing.*;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 20:21
 * 消除3张相同牌
 */
public class NameSame {
    static int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private static List<Brand> slot = new ArrayList<>();

    public void addSlot(Brand brand){
        slot.add(brand);
        //牌的排序
        slot.sort(Comparator.comparing(Brand::getName));
        //牌的消除
        Map<String, List<Brand>> collect = slot.stream().collect(Collectors.groupingBy(Brand::getName));
        Set<String> key = collect.keySet();
        for (String s : key){
            List<Brand> brands = collect.get(s);
            if (brands.size() == 3){
                deleteBrand(s);
                count += 3;

                break;
            }
        }
        Brandpaint();
        victory(brand);
        gameover(brand);
    }

    void Brandpaint(){
        for (int i = 0; i < slot.size(); i++) {
            Brand brand = slot.get(i);
            int x = i * brand.getImage_w() + 25;
            brand.setBounds(x,758,50,50);


        }
    }

    void deleteBrand(String name){
        Iterator<Brand> iterator = slot.iterator();
        while (iterator.hasNext()){
            Brand next = iterator.next();
            if (next.getName().equals(name)){
                next.getParent().remove(next);
                iterator.remove();
            }
        }

    }

    public void victory(Brand brand){
        if(count == 27){
            new Music().music("D:\\Sheepgame\\music\\v.mp3");
            System.out.println("胜利!");

        }

    }
    void gameover(Brand brand){
        if (slot.size() >= 7){
            new Music().music("D:\\Sheepgame\\music\\d.mp3");
            JOptionPane.showMessageDialog(brand,"游戏结束!");
            System.exit(0);
        }

    }

}

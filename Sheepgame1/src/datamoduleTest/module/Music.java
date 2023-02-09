package datamoduleTest.module;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;

/**
 * @author whale-fall
 * @create 2023 - 02 - 08 20:58
 */
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

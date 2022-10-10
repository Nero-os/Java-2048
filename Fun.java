package KeShe5;
import java.awt.*;
import java.io.*;
import javax.swing.*;

import javazoom.jl.player.Player;

public class Fun {
    public Player player;

    public void music(String filename){
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
            player = new Player(buffer);
            player.play();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void overframe(){
        JLabel message = new JLabel("没有可移动的方向了----请鼠标点击'!'方块重新开始游戏");
        JFrame frame = new JFrame();
        frame.setTitle("game over");
        frame.add(message);
        frame.setSize(350,180);
        frame.setLocation(720,400);
        frame.setBackground(Color.gray);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void pframe1(){
        JLabel message = new JLabel("Yes,yes!Come on,scan this qr code and you will win!");
        JFrame frame = new JFrame();
        frame.setTitle("pay for win");
        frame.add(message);
        frame.setSize(350,180);
        frame.setLocation(680,320);
        frame.setBackground(new Color(210,180,100));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void bframe(){
        JLabel message = new JLabel("按'↑↓←→'移动方块,and 按回车键进入充值系统");
        JFrame frame = new JFrame();
        frame.setTitle("How to play");
        frame.add(message);
        frame.setSize(380,180);
        frame.setLocation(660,320);
        frame.setBackground(new Color(210,180,100));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}

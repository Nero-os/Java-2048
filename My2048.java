package KeShe5;

import javax.swing.*;
import java.awt.*;

public class My2048 extends JFrame {
    public My2048() { //构造方法
        setTitle("2048"); //设置标题
        setSize(500, 500); //设定窗口大小
        setLocation(600, 150); //设定窗口初始位置
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(5, 5, 5, 5)); //设定布局方式为GridLayout型
        new Operation(this);
        this.setVisible(true); //设为可视
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true); //设定Frame的缺省外观
        new My2048();
        Fun.bframe();
        System.out.println("# ฅʕ•̫͡•ʔฅ");
        System.out.println("# @Author:沛川");
        System.out.println("# @Time:2048/1/1");
    }

}

package KeShe5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Operation implements KeyListener {
    Block[] block; //译：块；用于存储16——25个数据
    JPanel panel; //译：面板
    public boolean up,down,left,right;
    int moveFlag; //用于累计移动的次数
    boolean numFlag; //判断是否还能移动
    Threading th1 = new Threading("music"); //创建music线程对象
    // Object lock = new Object();   //创建同步锁
    Fun fun = new Fun();

    public Operation(JFrame frame){
        this.panel = (JPanel) frame.getContentPane(); //构造出panel
        block = new Block[25]; //构造出长度为25的数组
        numFlag = true; //初始化
        moveFlag = 0;
        up = true; down = true; left = true; right = true;
        addBlock();
        for (int i=0;i<2;i++)
            appearBlock();
        frame.addKeyListener(this);
        th1.start(); //开始运行music线程
    }

    private void addBlock(){
        for (int i=0;i<25;i++){ //往panel里加入block
            block[i] = new Block();
            block[i].setHorizontalAlignment(JLabel.CENTER); //不透明的标签
            block[i].setOpaque(true);
            panel.add(block[i]);
        }
    }

    public void appearBlock(){
        while (numFlag){ //当还能加入随机的一个新值的时候
            int index = (int)(Math.random()*25); //取一个0到15的随机整数，这个数作为随机加入盘中的2或4的数字
            if (block[index].getValue()==0){ //如果这个数所在的block数组中值为0，即在为空的时候，加入一个2或4的数字
                if (Math.random()<0.5)
                {
                    block[index].setValue(2);
                }
                else
                {
                    block[index].setValue(4);
                }
                break; //跳出while
            }
        }
    }

    public void judgeAppear(){ //统计block数组中是否含有值为0的元素，若没有，则numFlag变为false
        int sum = 0; //非0元素的个数
        for (int i=0;i<25;i++){
            if (block[i].getValue()!=0) {
                sum++;
            }
        }
        if (sum == 25)
            numFlag = false;

    }

    public int Find(int i,int j,int a,int b){
        while( i < b && i >= a ){
            if (block[i].getValue()!=0){
                return i;
            }
            i = i+j;
        }
        return -1;
    }

    public void upBlock(){
        int i=0,j=0; int t=0; int valueJ=0; int valueI=0; int index=0;
        for (i=0;i<5;i++)
        {
            index = i;
            for (j=i+5;j<25;j+=5)
            {
                valueJ = 0; valueI = 0;
                if (block[index].getValue()==0)
                {
                    t = Find(index, 5, 0, 25);
                    if (t != -1){
                        block[index].setValue(block[t].getValue()); //啊啊啊
                        block[t].setValue(0);
                    }
                    else {
                        break;
                    }
                }
                valueI = block[index].getValue();
                if (block[j].getValue()==0)
                {
                    t = Find(j,5,0,25);
                    if (t != -1)
                    {
                        block[j].setValue(block[t].getValue());
                        block[t].setValue(0);
                    }
                    else
                    {
                        break;
                    }
                }
                valueJ = block[j].getValue();
                if ( valueI==valueJ && valueI != 0 && valueJ != 0 )
                {
                    block[index].setValue(valueI+valueJ);
                    block[j].setValue(0);
                    numFlag = true;
                }
                index = j;
            }
        }
    }

    public void downBlock(){
        int i=0,j=0; int t=0; int valueJ=0; int valueI=0; int index=0;
        for (i=15;i<25;i++)
        {
            index = i;
            for (j=i-5;j>=0;j-=5)
            {
                valueJ=0; valueI=0;
                if (block[index].getValue()==0)
                {
                    t = Find(index,-5,0,25);
                    if ( t != -1 )
                    {
                        block[index].setValue(block[t].getValue());
                        block[t].setValue(0);
                    }
                    else
                    {
                        break;
                    }
                }
                valueI = block[index].getValue();
                if (block[j].getValue()==0)
                {
                    t = Find(j,-5,0,25);
                    if (t != -1)
                    {
                        block[j].setValue(block[t].getValue());
                        block[t].setValue(0);
                    }
                    else
                    {
                        break;
                    }
                }
                valueJ = block[j].getValue();
                if ( valueI==valueJ && valueI!=0 && valueJ!=0 )
                {
                    block[index].setValue(valueI+valueJ);
                    block[j].setValue(0);
                    numFlag = true;
                }
                index = j;
            }
        }
    }

    public void rightBlock(){
        int i=0,j=0; int t=0; int valueJ=0; int valueI=0; int index=0;
        for (i=4;i<25;i+=5)
        {
            index = i;
            for (j=i-1;j>i-5;j--)
            {
                valueJ=0; valueI=0;
                if (block[index].getValue()==0)
                {
                    t = Find(index,-1,i-4,index+1);
                    if ( t != -1 )
                    {
                        block[index].setValue(block[t].getValue());
                        block[t].setValue(0);
                    }
                    else
                    {
                        break;
                    }
                }
                valueI = block[index].getValue();
                if (block[j].getValue()==0)
                {
                    t = Find(j,-1,i-4,j+1);
                    if (t != -1)
                    {
                        block[j].setValue(block[t].getValue());
                        block[t].setValue(0);
                    }
                    else
                    {
                        break;
                    }
                }
                valueJ = block[j].getValue();
                if ( valueI==valueJ && valueI!=0 && valueJ!=0 )
                {
                    block[index].setValue(valueI+valueJ);
                    block[j].setValue(0);
                    numFlag = true;
                }
                index = j;
            }
        }
    }

    public void leftBlock(){
        int i=0,j=0; int t=0; int valueJ=0; int valueI=0; int index=0;
        for (i=0;i<25;i+=5)
        {
            index = i;
            for (j=i+1;j<i+5;j++)
            {
                valueJ=0; valueI=0;
                if (block[index].getValue()==0)
                {
                    t = Find(index,1,index,i+5);
                    if ( t != -1 )
                    {
                        block[index].setValue(block[t].getValue());
                        block[t].setValue(0);
                    }
                    else
                    {
                        break;
                    }
                }
                valueI = block[index].getValue();
                if (block[j].getValue()==0)
                {
                    t = Find(j,1,j,i+5);
                    if (t != -1)
                    {
                        block[j].setValue(block[t].getValue());
                        block[t].setValue(0);
                    }
                    else
                    {
                        break;
                    }
                }
                valueJ = block[j].getValue();
                if ( valueI==valueJ && valueI!=0 && valueJ!=0 )
                {
                    block[index].setValue(valueI+valueJ);
                    block[j].setValue(0);
                    numFlag = true;
                }
                index = j;
            }
        }
    }

    public void over(){
        //当不能添加元素，并且不可移动的步数超过36就数了，输了的时候在盘中央显示GAMEOVER
        if ( numFlag==false && up==false && down==false && left==false && right==false )
        {
            block[5].setText("L");
            block[6].setText("O");
            block[7].setText("V");
            block[8].setText("E");
            block[9].setText("-");
            block[10].setText("");
            block[11].setText("Y");
            block[12].setText("O");
            block[13].setText("U");
            block[14].setText("");
            block[14].addMouseListener(new MouseAdapter() {public void
            mousePressed(MouseEvent e){reStart();}});
            Fun.overframe();

            //Fun laugh = new Fun();
            //laugh.music("C:\\Users\\86131\\Downloads\\Music\\Laugh.mp3");
        }
    }

    public void win(){
        for (int i = 0; i < 25; i++) {
            if (block[i].getValue() == 2048) {
                    for (int j = 0; j < 25; j++) {
                        block[j].setValue(6);
                    }
                    block[6].setText("Y");
                    block[6].setBackground(new Color(255,120,57));
                    block[7].setText("O");
                    block[7].setBackground(new Color(255,120,57));
                    block[8].setText("U");
                    block[8].setBackground(new Color(255,120,57));
                    block[11].setText("W");
                    block[11].setBackground(new Color(255,120,57));
                    block[12].setText("I");
                    block[12].setBackground(new Color(255,120,57));
                    block[13].setText("N");
                    block[13].setBackground(new Color(255,120,57));
                block[13].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e){reStart();}});
                break;
            }
        }
    }

    public void reStart(){
        numFlag = true;
        moveFlag = 0;
        up=true; down=true; left=true; right=true;
        for (int i=0;i<25;i++)
            block[i].setValue(0);
        for (int i=0;i<2;i++)
            appearBlock();
    }

    public void keyPressed(KeyEvent e){
        //判断按的上下左右键，并依次调用移动函数、判断函数、添加函数、判断是否输掉的函数
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (up) {
                    upBlock();}
                fun.music("C:\\FFOutput\\泡泡啵.mp3");
                judgeAppear();
                appearBlock();
                over();
                win();
                if (numFlag==false)
                {
                    up = false;
                }
                else
                {
                    up=true; down=true; left=true; right=true;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (down){
                    downBlock();}
                fun.music("C:\\FFOutput\\泡泡啵.mp3");
                judgeAppear();
                appearBlock();
                over();
                win();
                if (numFlag==false)
                {
                    down = false;
                }
                else
                {
                    up=true; down=true; left=true; right=true;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (left){
                    leftBlock();}
                fun.music("C:\\FFOutput\\泡泡啵.mp3");
                judgeAppear();
                appearBlock();
                over();
                win();
                if (numFlag==false)
                {
                    left = false;
                }
                else
                {
                    up=true; down=true; left=true; right=true;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (right){
                    rightBlock();}
                fun.music("C:\\FFOutput\\泡泡啵.mp3");
                judgeAppear();
                appearBlock();
                over();
                win();
                if (numFlag==false)
                {
                    right = false;
                }
                else
                {
                    up=true; down=true; left=true; right=true;
                }
                break;
            case KeyEvent.VK_SPACE:
                th1.bgm.player.close();
                /*synchronized (lock) {
                    if (th1.flag) {
                        System.out.println(th1.getName() + "暂停");
                        th1.pause();
                    } else {
                        System.out.println(th1.getName() + "播放");
                        th1.goOn();
                    }
                }*/
                break;
            case KeyEvent.VK_ENTER:
                new Java_JFrame();
                Fun.pframe1();
                /*try {
                    Thread.sleep(5000);
                }catch (Exception e){
                    System.out.println("???");
                }*/
                System.out.println("en en");
        }
    }

    public void keyTyped(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){

    }

}

package tank5;

import javax.swing.*;

public class frank extends  JFrame {//定义自己的画框
    public Mypaint mypaint = null;
    public static void main(String[] args) {
        new frank();
    }

    public frank(){
        this.mypaint = new Mypaint();//获取自己的画布
        this.add(mypaint);
        //设置画布的大小
        this.setSize(1000,750);
        //可见和关闭

        this.setDefaultCloseOperation(3);
        this.setVisible(true);

        //把画板加入到键盘LISTER监听,这里有多态
        this.addKeyListener(this.mypaint);

    }
}



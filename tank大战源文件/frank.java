package tank5;

import javax.swing.*;

public class frank extends  JFrame {//�����Լ��Ļ���
    public Mypaint mypaint = null;
    public static void main(String[] args) {
        new frank();
    }

    public frank(){
        this.mypaint = new Mypaint();//��ȡ�Լ��Ļ���
        this.add(mypaint);
        //���û����Ĵ�С
        this.setSize(1000,750);
        //�ɼ��͹ر�

        this.setDefaultCloseOperation(3);
        this.setVisible(true);

        //�ѻ�����뵽����LISTER����,�����ж�̬
        this.addKeyListener(this.mypaint);

    }
}



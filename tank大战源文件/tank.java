package tank5;

import java.awt.*;
import java.util.Vector;

public class tank {
    public int x;//̹��x����
    public int y;//̹��Y����
    private int direction;
    private int speed;
    public boolean isLive = true;

    public tank() {
        this.x = 100;
        this.y = 100;
        this.speed = 2;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    //�޸�̹������
    //����
    public void TankUp() {
        if (this.y - this.speed < 0)
            this.y += this.speed;
        else
            this.y -= this.speed;
    }

    //�� ��
    public void TankRight() {
        if (this.x + this.speed > 890)
            this.x -= this.speed;
        else
            this.x += this.speed;
    }

    //����
    public void TankDown() {
        if (this.y + this.speed > 650)
            this.y -= this.speed;
        else
            this.y += this.speed;
    }

    //����
    public void TankLeft() {
        if (this.x - this.speed < 0)
            this.x += this.speed;
        else
            this.x -= this.speed;
    }

    public static void drawTank(int x, int y, Graphics g, int type, int direction) {
        //������������͵�̹��
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
            default:
                System.out.println("other color");
        }
        // 0 �� 1 �ұ� 2�� 3��
        switch (direction) {
            case 0://��ʼ����Ļ�ͼ ������������ϵ�
                g.fill3DRect((int) x, (int) y, 10, 60, false);
                g.fill3DRect((int) x + 30, (int) y, 10, 60, false);
                g.fill3DRect((int) x + 10, (int) y + 10, 20, 40, false);
                g.fillOval((int) x + 10, (int) y + 20, 20, 20);
                g.fill3DRect((int) x + 18, (int) y, 4, 20, false);


                break;
            case 1:
                g.fill3DRect((int) x, (int) y, 60, 10, false);
                g.fill3DRect((int) x, (int) y + 30, 60, 10, false);
                g.fill3DRect((int) x + 10, (int) y + 10, 40, 20, false);
                g.fillOval((int) x + 20, (int) y + 10, 20, 20);
                g.fill3DRect((int) x + 40, (int) y + 18, 20, 4, false);
                break;
            case 2:
                g.fill3DRect((int) x, (int) y, 10, 60, false);
                g.fill3DRect((int) x + 30, (int) y, 10, 60, false);
                g.fill3DRect((int) x + 10, (int) y + 10, 20, 40, false);
                g.fillOval((int) x + 10, (int) y + 20, 20, 20);
                g.fill3DRect((int) x + 18, (int) y + 40, 4, 20, false);

                break;
            case 3:
                g.fill3DRect((int) x, (int) y, 60, 10, false);
                g.fill3DRect((int) x, (int) y + 30, 60, 10, false);
                g.fill3DRect((int) x + 10, (int) y + 10, 40, 20, false);
                g.fillOval((int) x + 20, (int) y + 10, 20, 20);
                g.fill3DRect((int) x, (int) y + 18, 20, 4, false);
                break;
            default:
                System.out.println("other direction");
        }

    }


    protected Bullet bullet = null;
    public Vector<Bullet> bullets = new Vector<>();


    ///---------------------------------------------------
    public void drawBullet(Graphics g, int type, Bullet AnyTankBullet) {
        switch (type) {
            case 0:
                g.setColor(Color.red);
                break;
            case 1:
                g.setColor(Color.BLUE);
                break;
        }
        g.fill3DRect(AnyTankBullet.x, AnyTankBullet.y, 5, 5, false);
        g.setColor(Color.cyan);
    }


}

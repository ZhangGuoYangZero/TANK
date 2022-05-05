package tank5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

@SuppressWarnings("all")
public class Mypaint extends JPanel implements KeyListener {
    public heroTank hero = null;
    public Vector<enemy> vector = new Vector<>();
    public rep r;
    public Vector<Boom> booms = new Vector<>();
    Image p1 = Toolkit.getDefaultToolkit().getImage("./src/1.jpg");
   


    public Mypaint(int a) {

    }

    public Mypaint() {
        this.hero = new heroTank();//英雄坦克初始坐标为100,100
        //匿名创建三个敌方坦克对象，让enemy坦克自己创建
        vector = new enemy().creat();

    }

    //repaint一次 就d一次 paint方法
    @Override
    public void paint(Graphics g) {
        //初始化画笔
        super.paint(g);
        r = new rep(this, g);
        r.setDaemon(true);
        r.start();

        //给整个图版设置背景
        g.fillRect(0, 0, 1000, 750);

        //画英雄坦克
        this.hero.drawHeroTank(g, this.hero.getX(), this.hero.getY(), 0, this.hero);
        //画敌方坦克
        enemy.drawEnemyTank(vector, 1, g);
        //画英雄坦克子弹
        for (int i = 0; i < this.hero.bullets.size(); ++i) {
            Bullet bullet = this.hero.bullets.get(i);
            if (bullet.isLive) {
                this.hero.drawBullet(g, 0, bullet);
            } else this.hero.bullets.remove(i);
        }

        //画敌方坦克的子弹


        for (int i = 0; i < this.vector.size(); i++) {
            enemy e = this.vector.get(i);
            for (int j = 0; j < e.bullets.size(); j++) {
                Bullet bullet = e.bullets.get(j);
                if (!(bullet.isLive)) this.vector.get(i).bullets.remove(j);
                else e.drawBullet(g, 1, bullet);
            }
        }


        //击杀效果
        for (int i = 0; i < this.booms.size(); i++) {
            Boom boom = null;
            boom = this.booms.get(i);
            if (boom.isLive == false) {
                booms.remove(i);
                continue;
            }

            if (boom.LifeTime > 0) {
                g.drawImage(p1, boom.x, boom.y, 120, 120, this);
                boom.displayTime();
            }
        }
    }

    //分开判断，一起不好写，先判断英雄坦克是不是击中了敌方坦克
    public void tankAttach(Bullet bullet, tank en) {
        switch (en.getDirection()) {
            case 1:
            case 3:
                if (bullet.x > en.x && bullet.x < en.x + 60 && bullet.y == en.y) {
                    this.booms.add(new Boom(bullet.x, bullet.y, true));
                    bullet.isLive = false;
                    en.isLive = false;
                    System.out.println("击中1");
                }
                break;
            case 2:
            case 4:
                if (bullet.x == en.x && bullet.y > en.y && bullet.y < en.y + 60) {
                    this.booms.add(new Boom(bullet.x, bullet.y, true));
                    bullet.isLive = false;
                    en.isLive = false;
                    System.out.println("击中2");

                }
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }


    public void enemyAttach(Bullet bullet, heroTank en) {
        switch (en.getDirection()) {
            case 1:
            case 3:
                if (bullet.x > en.x && bullet.x < en.x + 60 && bullet.y == en.y) {
                    this.booms.add(new Boom(bullet.x, bullet.y, true));
                    bullet.isLive = false;
                    en.isLive = false;
                    System.out.println("击中1");
                }
                break;
            case 2:
            case 4:
                if (bullet.x == en.x && bullet.y > en.y && bullet.y < en.y + 60) {
                    this.booms.add(new Boom(bullet.x, bullet.y, true));
                    bullet.isLive = false;
                    en.isLive = false;
                    System.out.println("击中2");

                }
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }


    //这是 implements keyListener的重写 字符串
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //这是 implements keyListener的重写 键按下
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: {
                //设置方向
                this.hero.setDirection(0);
                //设置移动
                this.hero.TankUp();
                //设置弹头方向

                break;
            }
            case KeyEvent.VK_DOWN: {
                this.hero.TankDown();
                this.hero.setDirection(2);

                break;
            }
            case KeyEvent.VK_LEFT: {
                this.hero.TankLeft();
                this.hero.setDirection(3);

                break;
            }
            case KeyEvent.VK_RIGHT: {
                this.hero.TankRight();
                this.hero.setDirection(1);

                break;
            }

            case KeyEvent.VK_X: {
                //最多存在5发
                if (this.hero.bullets.size() == 5) {
                    return;

                }

                this.hero.creatBullet();


            }
        }
    }

    //这是 implements keyListener的重写 键放开
    @Override
    public void keyReleased(KeyEvent e) {

    }
}

class rep extends Thread {
    public Mypaint mypaint;
    public Graphics g;
    public boolean loop = true;

    public rep(Mypaint mypaint, Graphics g) {
        this.mypaint = mypaint;
        this.g = g;
    }

    @Override
    public void run() {

        while (loop) {
            //原本的改变

            this.mypaint.repaint();
            //子弹是否已经不可使用
            for (int i = 0; i < this.mypaint.hero.bullets.size(); ++i) {
                Bullet bullet = this.mypaint.hero.bullets.get(i);
                if (!(bullet.isLive)) {
                    if (bullet != null)
                        this.mypaint.hero.bullets.remove(i);
                    continue;
                }

                //敌军是否还存活
                for (int j = 0; j < this.mypaint.vector.size(); ++j) {
                    enemy en = null;
                    en = this.mypaint.vector.get(j);
                    if (!(en.isLive)) {
                        continue;
                    }
                    this.mypaint.tankAttach(bullet, en);
                }

            }
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!(this.mypaint.hero.isLive)) continue;
            for (int i = 0; i < this.mypaint.vector.size(); i++) {
                enemy en = this.mypaint.vector.get(i);
                if (en.isLive == false)
                    continue;
                for (int j = 0; j < en.bullets.size(); j++) {
                    Bullet bullet = en.bullets.get(j);
                    if (bullet.isLive == false)
                        continue;
                    this.mypaint.tankAttach(bullet, this.mypaint.hero);
                }
            }

            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
package tank5;

import java.awt.*;
import java.util.Vector;

public class enemy extends tank implements Runnable {
    public static int enemyNumTank = 1;

    public enemy() {
        super();
    }

    public enemy(int x, int y, int speed, boolean isLive) {
        //创建一个默认子弹
        this.x = x;
        this.y = y;
        this.setSpeed(speed);
        this.setDirection(2);
    }

    //自己创建足够数量的坦克
    public Vector<enemy> creat() {
        Vector<enemy> vector = new Vector<>();
        for (int i = 0; i < enemyNumTank; i++) {
            enemy en = new enemy(this.getX() + 60 * (i + 1), this.getY(), 2, true);
            vector.add(en);
            Thread thread = new Thread(en);
            thread.start();
        }
        return vector;
    }


    public static void drawEnemyTank(Vector<enemy> vector, int type, Graphics g) {
        //画一辆敌方坦克
        for (int i = 0; i < vector.size(); i++) {
            enemy e = vector.get(i);
            if (!(e.isLive)) {
                if (e != null)
                    vector.remove(i);
            } else {
                tank.drawTank(e.getX(), e.getY(), g, type, e.getDirection());

            }
        }
    }


    @Override
    public void run() {
        while (this.isLive) {
            switch (4) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        this.TankUp();
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {

                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        this.TankRight();
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {

                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        this.TankDown();
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {

                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        this.TankLeft();
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {

                        }
                    }
                    break;
            }
            //this.setDirection((int) (Math.random() * 4));

            if (!(this.bullets.size() < 1)) continue;

            //由于上面是EN开启的独立线程，所以这个对应每一个敌方坦克
            bullet = new Bullet(this.getX(), this.getY(), this.getDirection());
            bullets.add(bullet);
            bullet.setDaemon(true);
            bullet.start();

        }
        System.out.println("敌方坦克线程结束");
    }
}

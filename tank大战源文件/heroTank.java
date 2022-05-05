package tank5;

import java.awt.*;

public class heroTank extends tank {
    public heroTank() {
        super();
        this.setSpeed(5);
    }

    public  void creatBullet(){
        Bullet bullet = new Bullet(this.getX(),this.getY(),this.getDirection());
        this.bullets.add(bullet);
        bullet.setDaemon(true);
        bullet.start();

    }

    public  void  drawHeroTank(Graphics g,int x, int y, int type,tank Tank){
        if(Tank.isLive)
            tank.drawTank(Tank.getX(), Tank.getY(), g,type,Tank.getDirection());

    }
}

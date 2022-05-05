package tank5;


public class Bullet extends Thread {
    protected int x;
    protected int y;
    protected boolean isLive;
    private final int direction;

    public Bullet(int x, int y, int direction) {
        this.isLive = true;
        this.direction = direction;

        switch (direction) {
            case 0:
                x += 18;
                break;
            case 1:
                x += 60;
                y += 18;
                break;
            case 2:
                x += 18;
                y += 60;
                break;
            case 3:
                y += 18;
                break;
        }
        this.x = x;
        this.y = y;

    }

    public void bulletXmin(int tempX) {
        x += tempX;
    }

    public void bulletYmin(int tempY) {
        y += tempY;
    }


    @Override
    public void run() {
        //这里画
        while (this.isLive) {

            switch (direction) {
                case 0:
                    this.bulletYmin(-10);
                    break;
                case 1:
                    this.bulletXmin(+10);
                    break;
                case 2:
                    this.bulletYmin(+10);
                    break;
                case 3:
                    this.bulletXmin(-10);
                    break;

            }


            if (x >= 1000 || x <= 0 || y <= 0 || y >= 750) {
                this.isLive = false;
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("子弹线程释放");
    }
}
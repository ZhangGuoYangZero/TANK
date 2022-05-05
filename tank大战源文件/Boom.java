package tank5;

public class Boom {
    public  int x;
    public  int y;
    public  boolean isLive;
    public int LifeTime = 50;
    public  Boom(int x, int y , boolean isLive){
        this.x = x;
        this.y = y;
        this.isLive = isLive;
    }

    public  void  displayTime(){
        if(this.LifeTime >= 0)
            LifeTime--;
        else
            isLive = false;
    }
}

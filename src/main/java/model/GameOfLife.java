package model;

/**
 * Created by etere on 20.12.2015.
 */
public class GameOfLife {
    private int width, hight, updateTime;

    public void setPercentageLiving(double percentageLiving) {
        this.percentageLiving = percentageLiving;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private double percentageLiving;
    public GameOfLife(){

    }
}

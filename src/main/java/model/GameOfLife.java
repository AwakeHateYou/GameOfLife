package model;

/**
 * Created by etere on 20.12.2015.
 */
public class GameOfLife {
    private int width, height, updateTime;

    public void setPercentageLiving(double percentageLiving) {
        this.percentageLiving = percentageLiving;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    private double percentageLiving;
    private byte[] mainField;
    private byte[] backField;
    private int[] neighborOffsets;
    private int[][] neighborXYOffsets;
    public GameOfLife(){
        initFieldContainers();
    }
    private void initFieldContainers(){
        mainField = new byte[width * height];
        backField = new byte[width * height];
        neighborOffsets = new int[] { -width - 1, -width, -width + 1, -1, 1, width - 1, width, width + 1 };
        neighborXYOffsets = new int[][] { { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
    }
}

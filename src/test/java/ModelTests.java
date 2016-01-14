import model.GameOfLifeModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModelTests{
    private GameOfLifeModel model;
    @Before
    public void setUp(){
        model = new GameOfLifeModel(50, 50);
        model.setPercentageLiving(20);
    }
    @Test
    public void testRandomize(){
        Assert.assertFalse("Randomize is not correct", isNotRandomized());
    }
    public boolean isNotRandomized(){
        model.randomizeByPercent();
        int live = 0;
        for(byte i : model.getMainField()){
            if(i == 1) {
                live++;
            }
        }
        if(live != 480)
        {
            return true;
        }else {
            return false;
        }
    }
    @Test
    public void testCountNeighbors(){
        model.getMainField()[67] = 1;
        Assert.assertFalse("Count is not correct", isNotCorrectCount());
    }
    public boolean isNotCorrectCount(){
        if(model.countNeighbors(66) == 1) {
            return false;
        }else
            return true;
    }
    @Test
    public void testSimulateCell(){
        Assert.assertFalse("Count is not correct", isNotSimulate());
    }
    public boolean isNotSimulate(){
        if(model.simulateCell((byte)0, (byte)3) == 1) {
            return false;
        }else
            return true;
    }

}

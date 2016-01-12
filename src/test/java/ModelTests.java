import model.GameOfLifeModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class ModelTests{
    private GameOfLifeModel model;
    @Before
    public void setUp(){
        model = new GameOfLifeModel(50, 50);
        model.setPercentageLiving(20);
    }
    @Test
    public void testRandomize(){
        Assert.assertFalse("Building is not correct", isNotRandomized());
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

}

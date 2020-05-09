import java.util.ArrayList;
import java.util.List;

public class MainTest {


    public static void main(String[] args) {


    InputHandler inputHandler=new InputHandler();
    BuildStrategy buildStrategy=new BuildStrategy(inputHandler.getProject(),inputHandler.getPriceList(),inputHandler.getStrategy());
    OutputHandler outputHandler=new OutputHandler(buildStrategy.getView());

    }




}

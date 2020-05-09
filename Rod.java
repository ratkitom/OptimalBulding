import java.util.ArrayList;
import java.util.List;

public class Rod {


    private   List<Integer> content=new ArrayList<>();
    private int cut=0;

    public Rod(){}





    public Rod(int rod,int content,int cut){

        this.content.add(rod);
        this.content.add(content);
        this.cut=cut;


    }



    public void add(int rod){

        this.content.add(rod);

    }

    public List<Integer> getContent(){

        return content;

    }

    public int getCut() {
        return this.cut;
    }

    public void setCut(int cut){
        this.cut=cut;
    }
}

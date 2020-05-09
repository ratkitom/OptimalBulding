import java.util.ArrayList;
import java.util.List;

// This strategy always choose longest rod from price list

public class Maximal implements StrategyView {

    // A list that stores bought rod and rods into which it was divided
    List<Rod> rods=new ArrayList<>();
    // Total price of bought rods
    private int price;
    // Total length of wasted rods
    private int cutRods;

    private int[] project;
    private int[][] priceList;

    public List<Rod> getRods(){

        return rods;
    }
    public int getPrice(){

        return price;
    }

    public int getCutRods(){

        return cutRods;
    }



    public Maximal(int[] project,int[][] priceList){

        this.project=project;
        this.priceList=priceList;
        BuildStrategy();






    }

    private void BuildStrategy(){

        matchRod(project[project.length-1]);

        for (int i=project.length-2;i>=0;i--){


            if(matchCut(project[i])) {



            }
            else {

                matchRod(project[i]);
            }




        }

        CountCuts();

    }

    private void matchRod(int rod){



        int cut=priceList[0][priceList[0].length-1]-rod;
        int boughtRod=priceList[0][priceList[0].length-1];;
        int boughtRodIndex=priceList[0].length-1;



        rods.add(new Rod(boughtRod, rod, cut));

        price=price+priceList[1][boughtRodIndex];







    }

    private boolean matchCut(int rod){

        boolean check=false;
        int i=0;




        while (check==false && i<=rods.size()-1) {
            int ratio = rods.get(i).getCut() - rod;

            if (ratio >= 0) {

                rods.get(i).setCut(ratio);
                rods.get(i).add(rod);
                check = true;


            }
            i++;
        }




        return check;


    }

    private void CountCuts(){



        for (int i=0;i<rods.size();i++){

            this.cutRods=this.cutRods+ rods.get(i).getCut();

        }

    }



}

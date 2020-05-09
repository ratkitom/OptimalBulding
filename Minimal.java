import java.util.ArrayList;
import java.util.List;

// This strategy always choose the shortest rod from priceList which contain longest rod from project

public class Minimal implements StrategyView {

   // List containing rods from price list and way in which they are cut.
    private List<Rod> rods=new ArrayList<>();
    // Total price of bought rods
    private int price;
    // Total length of wasted rods
    private int wasteRods;

    private int[] project;
    private int[][] priceList;

    public List<Rod> getRods(){

        return rods;
    }



    public int getPrice(){

        return price;
    }

    public int getCutRods(){

        return wasteRods;

    }






    public Minimal(int[] project,int[][] priceList){

        this.project=project;
        this.priceList=priceList;
       buildStrategy();
       countWaste();





    }

    private void buildStrategy(){

        matchRod(project[project.length-1]);

        for (int i=project.length-2;i>=0;i--) {


            if (!matchCut(project[i])) {

                matchRod(project[i]);

            }
        }



    }


    private void matchRod(int rod){



        int cut=priceList[0][priceList[0].length-1]-rod;
        int boughtRod=0;
        int boughtRodIndex=priceList[0].length-1;

        for(int i=priceList[0].length-2;i>=0;i--){


            if(cut>0) {
                if ((priceList[0][i] - rod < cut) & priceList[0][i] - rod>=0) {

                    cut = priceList[0][i] - rod;
                    boughtRod = priceList[0][i];
                    boughtRodIndex = i;


                }
            }




        }




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

    private void countWaste(){



        for (int i=0;i<rods.size();i++){

            this.wasteRods=this.wasteRods+ rods.get(i).getCut();

        }

    }






}

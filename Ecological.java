package app;

import java.util.ArrayList;
import java.util.List;

// This strategy  minimize length of waste

public class Ecological implements StrategyView  {




    private int[][] priceList;

    private int cutRods=Integer.MAX_VALUE;
    private int price=0;

    private List<List<Integer>> best;
    private List<Rod> rod;






     public Ecological(int[] project, int[][] List){

         rod = new ArrayList<>();
         best = new ArrayList<>();

         this.priceList=List;
         findBestSolution(project,new ArrayList<>(),0,0);
         setRod();













     }




     // Recursive Knapsack algorithm which finds the best way to cut  particular rod from price list to minimize amount of waste.
     private int bestCut (int index,int capacity,int[] project,boolean[] temp){

         if(capacity==0||index==0){

             return 0;

         }


        if(project[index-1]>capacity){

            return bestCut(index-1,capacity,project,temp);

        }
        else  {



                boolean[] tab1=new boolean[temp.length];
                System.arraycopy(temp,0,tab1,0,tab1.length);
                boolean[] tab2=new boolean[temp.length];
                System.arraycopy(temp,0,tab2,0,tab2.length);
                tab1[index-1]=true;

                int val1=project[index-1]+bestCut(index-1,capacity-project[index-1],project,tab1);
                int val2=bestCut(index-1,capacity,project,tab2);


                if(val1>=val2){

                    System.arraycopy(tab1,0,temp,0,tab1.length);
                    return val1;
                }

                else {

                    System.arraycopy(tab2,0,temp,0,tab2.length);
                    return val2;
                }







        }


     }


     private void findBestSolution(int[] project,List<List<Integer>> list,int waste,int price) {





         for(int i=0;i<priceList[0].length;i++){


                if (checktab(project,priceList[0][i])) {

                    // this variable stores amount of rods left

                    int counter=0;

                    // this variable stores the amount of waste left after rod cut

                    int cut=0;

                    // this variable stores the current purchase cost

                    int cost;

                    //   this list stores the rods currently purchased and their division into parts

                    List<List<Integer>> copyList = new ArrayList<>(list);

                    // this list stores the rod from price list currently under consideration  and how it was cut

                    List<Integer> newList=new ArrayList<>();

                    newList.add(priceList[0][i]);

                    // this array holds mapping of rods which were and weren't taken from project

                    boolean[] temp = new boolean[project.length];

                    // this array holds rods from project that weren't included

                    int[] extant;

                    bestCut(project.length, priceList[0][i], project, temp);

                    for (int j=0;j<temp.length;j++){

                            if (temp[j]==false) counter=counter+1;

                            else {
                                newList.add(project[j]);
                                cut=cut+project[j];

                            }


                    }

                    copyList.add(newList);

                    cost=price+priceList[1][i];

                    cut=priceList[0][i]-cut+waste;
                    if (counter==0){

                        if (cut<cutRods){
                            this.price=cost;
                            cutRods=cut;
                            best.removeAll(best);
                            best=new ArrayList<>(copyList);

                        }



                        return;

                    }

                    else {


                        extant=new int[counter];

                        counter=0;

                        for (int k=0;k<temp.length;k++){

                                if (temp[k]==false){

                                    extant[counter]=project[k];
                                    counter=counter+1;

                                }



                        }

                        findBestSolution(extant,copyList,cut,cost);



                    }

                }

             }
     }

    // Check if any of left elements fits in rod form price list
     private boolean checktab(int[] tab,int rod){

         boolean check=false;

        for (int i=0;i<tab.length;i++){

            if (tab[i]<=rod){
                check=true;
            }

        }

        return check;

    }


     private void setRod(){

         for (List<Integer> temp:best) {

             Rod rod=new Rod();


             for (int i=0;i<temp.size();i++){


                rod.add(temp.get(i));

             }



             this.rod.add(rod);

         }

     }


    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public int getCutRods() {
        return this.cutRods;
    }

    @Override
    public List<Rod> getRods() {
        return this.rod;
    }
}

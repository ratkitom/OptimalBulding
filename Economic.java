import java.util.ArrayList;
import java.util.List;

// This minimize price of bought rods

public class Economic implements StrategyView{

        private int[][] priceList;

        int cutRods=0;
        int price=Integer.MAX_VALUE;

        List<List<Integer>> best=new ArrayList<>();
        List<Rod> rod=new ArrayList<>();






        public Economic(int[] project,int[][] List){



            this.priceList=List;
            throwRod(project,new ArrayList<>(),0,0);
            setRod();













        }





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


        private void throwRod(int[] project,List<List<Integer>> list,int waste,int price) {





            for(int i=0;i<priceList[0].length;i++){


                if (checktab(project,priceList[0][i])) {

                    int counter=0;
                    int cut=0;
                    int cost=price;
                    List<List<Integer>> copyList = new ArrayList<>(list);
                    List<Integer> newList=new ArrayList<>();
                    newList.add(priceList[0][i]);

                    boolean[] temp = new boolean[project.length];
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

                        if (cost<this.price){
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

                        throwRod(extant,copyList,cut,cost);



                    }



                }
            }

        }


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

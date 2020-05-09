import java.util.Scanner;

public class InputHandler {


    private int priceListSize;
    private int projectSize;
    private Scanner reader=new Scanner(System.in);
    private int [][] priceList;
    private int [] project;
    private String strategy;








   public InputHandler()
   {
    setPriceListSize();
    EnterPriceList();
    setProjectSize();
    EnterProject();
    setStrategy();

    priceListSort(priceList,0,priceListSize-1);
    projectsort(project,0,projectSize-1);



   }



    private void setPriceListSize() {
        this.priceListSize = Integer.parseInt(reader.nextLine());
    }

    private void EnterPriceList(){

        String input;
        String[] temp;
        priceList= new int[2][priceListSize];






        for (int j=0;j<priceList[0].length;j++){

            input=reader.nextLine();
            temp=input.split(" ");



            priceList[0][j]=Integer.parseInt(temp[0]);
            priceList[1][j]=Integer.parseInt(temp[1]);





        }







    }


    public void setProjectSize() {
        this.projectSize = Integer.parseInt(reader.nextLine());
    }

    private void EnterProject(){
        project=new int[projectSize];
        String input=reader.nextLine();
        String[] temp;
        temp=input.split(" ");

        for (int i=0;i<projectSize;i++){

            project[i]=Integer.parseInt(temp[i]);


        }






    }

    private void setStrategy() {
        String strategy = reader.nextLine();
        switch (strategy){
            case "minimalistyczna": this.strategy=strategy;
                break;
            case "maksymalistyczna": this.strategy=strategy;
                break;
            case "ekonomiczna": this.strategy=strategy;
                break;
            case "ekologiczna": this.strategy=strategy;
                break;
            default:
                System.out.println("Invalid input !!!");
                System.exit(0);



        }
    }


    private void projectsort(int[] tab,int left,int right) {

        int i,j,v,temp;

        i=left;
        j=right;
        v=tab[(left+right) / 2];
        do {
            while (tab[i]<v)
                i++;
            while (v<tab[j])
                j--;
            if (i<=j) {
                temp=tab[i];
                tab[i]=tab[j];
                tab[j]=temp;
                i++;
                j--;
            }
        }
        while (i<=j);
        if (left<j)
            projectsort(tab,left,j);
        if (i<right)
            projectsort(tab,i,right);
    }

    private void priceListSort(int [][] tab,int left, int right){

        int i,j,v,temp;

        i=left;
        j=right;
        v=tab[0][(left+right) / 2];
        do {
            while (tab[0][i]<v)
                i++;
            while (v<tab[0][j])
                j--;
            if (i<=j) {
                temp=tab[0][i];
                tab[0][i]=tab[0][j];
                tab[0][j]=temp;
                temp=tab[1][i];
                tab[1][i]=tab[1][j];
                tab[1][j]=temp;
                i++;
                j--;
            }
        }
        while (i<=j);
        if (left<j)
            priceListSort(tab,left,j);
        if (i<right)
            priceListSort(tab,i,right);




    }


    public int[][] getPriceList(){

       return priceList;
    }

    public int[] getProject(){

       return project;
    }

    public String getStrategy() {
        return strategy;
    }


}

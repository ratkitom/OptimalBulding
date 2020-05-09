public class OutputHandler {

    public OutputHandler (StrategyView view){


        showRods(view);





    }


    private void showRods(StrategyView view){


        System.out.println(view.getPrice());
        System.out.println(view.getCutRods());

        for (int i=0;i<view.getRods().size();i++){

            for (int j=0;j<view.getRods().get(i).getContent().size();j++){

                System.out.print(view.getRods().get(i).getContent().get(j)+" ");

            }

            System.out.println("");

        }





    }



}

public class BuildStrategy {

    private StrategyView view;

    public BuildStrategy(int[] project,int[][] priceList,String strategy){

        switch (strategy) {
            case "minimalistyczna":
                view=new Minimal(project,priceList);

                break;
            case "maksymalistyczna":
                view=new Maximal(project,priceList);

                break;
            case "ekonomiczna":

                view=new Economic(project,priceList);
                break;
            case "ekologiczna":
                view=new Eco(project,priceList);


        }




    }


    public StrategyView getView() {
        return view;
    }
}

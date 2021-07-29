package sim;



public class EinfügeRunner implements Runnable{
    private SimLogic sim;

    public EinfügeRunner(SimLogic sim) {
        this.sim = sim;
    }
    @Override
    public void run() {
        while(true) {

            this.sim.addRandomKuchen();

        }
    }
}

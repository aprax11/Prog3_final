package sim;



public class KuchenLöschenRunner implements Runnable {
    private SimLogic sim;

    public KuchenLöschenRunner(SimLogic sim) {
        this.sim = sim;
    }

    @Override
    public void run() {
        while (true) {
            this.sim.removeRandomKuchen();

        }
    }
}

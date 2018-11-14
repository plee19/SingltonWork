package singlton.work;

public class ThreadSafeCalcTool {
    public static int totalBMICalculated=0;
    public static int numberOfCalculations=0;
    enum MeasurementSystem {METRIC, ENGLISH}

    private static ThreadSafeCalcTool inst = null;
    private ThreadSafeCalcTool(){}
    public static ThreadSafeCalcTool getInstance() {
        synchronized (ThreadSafeCalcTool.class) {
            if(inst == null) {
                inst = new ThreadSafeCalcTool();
            }
        }
        return inst;
    }

    public static double calcBMI(double height, double weight, ThreadSafeCalcTool.MeasurementSystem measurementSystem) {
        double bmi = weight / Math.pow(height,2);
        if (measurementSystem == ThreadSafeCalcTool.MeasurementSystem.ENGLISH) {
            bmi *= 703;
        }
        totalBMICalculated+=bmi;
        numberOfCalculations++;

        return bmi;
    }

    public static double averageBMI() {
        return totalBMICalculated / numberOfCalculations;
    }
}

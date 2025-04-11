package in.edu.fitnessapplication.model;
public class DailyStats {
    private int totalWaterMl;
    private int totalCaloriesIn;
    private int totalExerciseMin;
    private int totalCaloriesOut;

    public DailyStats() { }

    public int getTotalWaterMl() {
        return totalWaterMl;
    }

    public void setTotalWaterMl(int totalWaterMl) {
        this.totalWaterMl = totalWaterMl;
    }

    public int getTotalCaloriesIn() {
        return totalCaloriesIn;
    }

    public void setTotalCaloriesIn(int totalCaloriesIn) {
        this.totalCaloriesIn = totalCaloriesIn;
    }

    public int getTotalExerciseMin() {
        return totalExerciseMin;
    }

    public void setTotalExerciseMin(int totalExerciseMin) {
        this.totalExerciseMin = totalExerciseMin;
    }

    public int getTotalCaloriesOut() {
        return totalCaloriesOut;
    }

    public void setTotalCaloriesOut(int totalCaloriesOut) {
        this.totalCaloriesOut = totalCaloriesOut;
    }
}
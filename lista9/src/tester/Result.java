package tester;

public class Result{
    String setName;
    int size;
    double averageInsertTime;
    double insertStdDev;

    double averageSearchTime;
    double searchStdDev;

    double averageRemoveTime;
    double removeStdDev;

    public Result(String setName, int size, double averageInsertTime, double insertStdDev, double averageSearchTime, double searchStdDev, double averageRemoveTime, double removeStdDev) {
        this.setName = setName;
        this.size = size;
        this.averageInsertTime = averageInsertTime;
        this.insertStdDev = insertStdDev;
        this.averageSearchTime = averageSearchTime;
        this.searchStdDev = searchStdDev;
        this.averageRemoveTime = averageRemoveTime;
        this.removeStdDev = removeStdDev;
    }

    public String getSetName() {
        return setName;
    }

    public int getSize() {
        return size;
    }

    public double getAverageInsertTime() {
        return averageInsertTime;
    }

    public double getInsertStdDev() {
        return insertStdDev;
    }

    public double getAverageSearchTime() {
        return averageSearchTime;
    }

    public double getSearchStdDev() {
        return searchStdDev;
    }

    public double getAverageRemoveTime() {
        return averageRemoveTime;
    }

    public double getRemoveStdDev() {
        return removeStdDev;
    }
}
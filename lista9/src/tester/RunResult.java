package tester;

public class RunResult {
    long insertTime;
    long searchTime;
    long removeTime;

    public RunResult(long insertTime, long searchTime, long removeTime) {
        this.insertTime = insertTime;
        this.searchTime = searchTime;
        this.removeTime = removeTime;
    }
}
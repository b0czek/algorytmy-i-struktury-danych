package testing;

public class Result {
	private double avgTime;
	private double timeStdDev;
	
	private double avgComparisons;
	private double comparisonsStdDev;
	
	private double avgSwaps;
	private double swapsStdDev;
	
	private double avgAdds;
	private double addsStdDev;

	private boolean srted;
	private boolean stble;
	
	public Result(double avgTime, double timeStdDev, double avgComparisons, double comparisonsStdDev, 
			double avgSwaps, double swapsStdDev, double avgAdds, double addsStdDev, boolean sorted, boolean stable) {
		this.avgTime = avgTime;
		this.timeStdDev = timeStdDev;
		
		this.avgComparisons = avgComparisons;
		this.comparisonsStdDev = comparisonsStdDev;
		
		this.avgSwaps = avgSwaps;
		this.swapsStdDev = swapsStdDev;

		this.avgAdds = avgAdds;
		this.addsStdDev = addsStdDev;
		
		this.srted = sorted;
		this.stble = stable;
	}
	
	public double averageTimeInMilliseconds() {
		return avgTime;
	}
	
	public double timeStandardDeviation() {
		return timeStdDev;
	}
	
	public double averageComparisons() {
		return avgComparisons;
	}
	
	public double comparisonsStandardDeviation() {
		return comparisonsStdDev;
	}
	
	public double averageSwaps() {
		return avgSwaps;
	}
	
	public double swapsStandardDeviation() {
		return swapsStdDev;
	}

	public double averageAdds() {
		return avgAdds;
	}
	public double addsStandardDeviation() {
		return addsStdDev;
	}

	public boolean sorted() {
		return srted;
	}
	
	public boolean stable() {
		return stble;
	}
}

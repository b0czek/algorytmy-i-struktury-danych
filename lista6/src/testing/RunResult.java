package testing;

public class RunResult {
	private long timeMillis;
	
	private long comps;
	private long swps;
	private long adds;
	
	private boolean srted;
	private boolean stble;
	
	public RunResult(long timeMillis, long comparisons, long swaps, long adds, boolean sorted, boolean stable) {
		this.timeMillis = timeMillis;
		this.comps = comparisons;
		this.swps = swaps;
		this.adds = adds;
		this.srted = sorted;
		this.stble = stable;
	}
	
	public long timeInMilliseconds() {
		return timeMillis;
	}
	
	public long comparisons() {
		return comps;
	}
	
	public long swaps() {
		return swps;
	}

	public long adds() {
		return adds;
	}
	
	public boolean sorted() {
		return srted;
	}
	
	public boolean stable() {
		return stble;
	}
}

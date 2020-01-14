package blu3flux.qlearn;

public class QEnvironment {
	
	
	// Parameters
	public static double learningRate = 0.1;
	public static double discountFactor = 0.95;
	public static double explorationDecay = 0.00000005;
	public static double explorationRate = 0.80;
	public static double explorationFloor = 0.025;
	
	private static double[] os_low;
	private static double[] os_high;
	private static int os_size = 1;
	private static int[] num_states;
	
	// Environment Information
	public static int episodeNumber = 0;
	
	public static void setObservationSpaceSize(int size) {
		if(size < 0) {
			throw new IllegalArgumentException("Size must be greater than zero");
		}else {
			os_size = size;
		}
	}
	
	public static void setHighObservationSpace(double[] low) {
		if(low.length != os_size) {
			throw new IllegalArgumentException("Length of array must be " + os_size);
		}else {
			os_low = low;
		}
	}
	
	public static void setLowObservationSpace(double[] high) {
		if(high.length != os_size) {
			throw new IllegalArgumentException("Length of array must be " + os_size);
		}else {
			os_high = high;
		}
	}
	
	public static void setNumberOfStates(int [] numStates) {
		if(numStates.length != os_size) {
			throw new IllegalArgumentException("Length of array must be " + os_size);
		}else {
			num_states = numStates;
		}
	}
	
	public static void incrementEpisode() {
		episodeNumber++;
	}
	
	public static int getObservationSpaceSize() {
		return os_size;
	}
	
	public static double[] getHighObservationSpace() {
		return os_high;
	}
	
	public static double[] getLowObservationSpace() {
		return os_low;
	}
	
	public static int[] getNumOfStates() {
		return num_states;
	}
}

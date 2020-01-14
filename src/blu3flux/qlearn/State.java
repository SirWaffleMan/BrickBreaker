package blu3flux.qlearn;

import java.util.Arrays;

public class State {
	
	int data[];
	
	public State(double [] d) {
		
		int os_size = QEnvironment.getObservationSpaceSize();
		
		if(d.length != os_size) {
			throw new IllegalArgumentException("Length of array must be " + os_size +
			". You can change the size by calling QEnviornment.setObservationSpaceSize(int)");
		}
		data = new int[os_size];
		
		double low[] = 	QEnvironment.getLowObservationSpace();
		double high[] =	QEnvironment.getHighObservationSpace();
		int numStates[] = QEnvironment.getNumOfStates();
		double stepSize[] = new double[os_size];
		
		for(int i = 0; i < os_size; i++) {
			stepSize[i] = (high[i] - low[i]) / numStates[i];
			data[i] = (int)(d[i] / stepSize[i]);
		}
		
		
	}
	
	// Returns index of state
	public int[] fetch() {
		return this.data;
	}

	@Override
	public String toString() {
		return Arrays.toString(data);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}
}

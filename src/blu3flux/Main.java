package blu3flux;

import blu3flux.qlearn.QEnvironment;

public class Main {
	
	public static void main(String[] args) {
		
		// Setup QLearn Environment
		QEnvironment.setObservationSpaceSize(5);
		QEnvironment.setNumberOfStates(new int[] {100, 100, 20, 4, 4});
		QEnvironment.setLowObservationSpace(new double[] {0, 0, 0, -5, -3});
		QEnvironment.setHighObservationSpace(new double[] {1600, 1600, 900, 5, 3});
		
		BrickBreaker game = new BrickBreaker();
		game.start();
	}

}

package blu3flux;

import blu3flux.qlearn.QEnvironment;

public class Player {
	public static int points = 0;
	public static int scores[] = new int[100];
	public static int highScore = 0;
	public static int lives = 3;
	public static final int brickHitPoints = 100;
	
	public static int getAverageScore() {
		int size = QEnvironment.episodeNumber;
		if(QEnvironment.episodeNumber > 100) {
			size = 100;
		}
		
		
		int sum = 0;
		for(int i = 0; i < (size); i++) {
			sum += scores[i];
		}
		
		if(QEnvironment.episodeNumber == 0) {
			return 0;
		}
		
		return sum/size;
	}
}

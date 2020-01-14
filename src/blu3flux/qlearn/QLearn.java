package blu3flux.qlearn;

import java.util.Random;

import blu3flux.BrickBreaker;

public class QLearn {
	
	BrickBreaker game;
	QTable table;
	State state;
	
	public QLearn(BrickBreaker g) {
		game = g;
		
		double px = g.getPaddle().getX();
		double bx = g.getBall().getX();
		double by = g.getBall().getY();
		double bsx = g.getBall().getXSpeed();
		double bsy = g.getBall().getYSpeed();
		
		state = new State(new double[] {px, bx, by, bsx, bsy});
		table = new QTable();
	}
	
	public double calculateReward() {
		double r = 0.0;
		if(game.getBall().collidingWithBricks()) {
			r = 25;
		}
		
		if(game.getPaddle().ballCollision()) {
			r += 1000;
		}
		
		
		if(game.getBall().outOfBounds()) {
			r = -1000;
			double diff = Math.abs(game.getBall().getCenterX() - game.getPaddle().getCenterX());
			r *= (diff/1600);
		}
		
		return r;
	}

	public void tick() {
		// Retrieve info
		double lr = QEnvironment.learningRate;
		double discount = QEnvironment.discountFactor;
		int action;
		
		if(Math.random() < QEnvironment.explorationRate)
			action = new Random().nextInt(3);
		else
			action = table.getMaxAction(state);
		
		State new_state = takeAction(action);
		double reward = calculateReward();
		double q = table.get(state)[action];
		double max_future_q = table.getMaxQ(new_state);
		
		// Calculate new q value and update table
		double q_new = (1-lr) * q + lr * (reward + discount * max_future_q);
		table.replace(state, action, q_new);
		state = new_state; // Might pose problem, new_state null after
		
		if(QEnvironment.explorationRate > QEnvironment.explorationFloor)
			QEnvironment.explorationRate -= QEnvironment.explorationDecay;
		else
			QEnvironment.explorationRate = QEnvironment.explorationFloor;
	}

	private State takeAction(int action) {
		switch(action) {
		case 0:
			game.getPaddle().moveLeft();
			break;
		case 1:
			game.getPaddle().detachBall();
			break;
		case 2:
			game.getPaddle().moveRight();
			break;
		}
		double px = game.getPaddle().getX();
		double bx = game.getBall().getX();
		double by = game.getBall().getY();
		double bxs = game.getBall().getXSpeed();
		double bys = game.getBall().getYSpeed();
		return new State(new double[] {px,bx,by, bxs, bys});
	}

}

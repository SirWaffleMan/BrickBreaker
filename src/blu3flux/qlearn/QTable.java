package blu3flux.qlearn;

import java.util.HashMap;

public class QTable {
	
	HashMap<State, double[]> table;
	
	public QTable() {
		table = new HashMap<State, double[]>();
	}
	
	public double[] get(State s) {
		double [] row = table.get(s);
		if(row == null) {
			double [] temp = {0,0,0,0,0};
			table.put(s, temp);
			return temp;
		}
		return row;
	}
	
	public void replace(State s, int action, double new_q) {
		double [] old_row = table.get(s);
		old_row[action] = new_q;
		table.replace(s, old_row);
	}
	
	public double getMaxQ(State s) {
		int index = getMaxAction(s);
		return table.get(s)[index];
	}
	
	public int getMaxAction(State s) {
		
		double row[] = get(s);
		int largestIndex = 0;
		
		for(int i = 0; i < row.length; i++) {
			if(row[i] > row[largestIndex]) {
				largestIndex = i;
			}
		}
		return largestIndex;
	}
	
	public int size() {
		return table.size();
	}
	
}

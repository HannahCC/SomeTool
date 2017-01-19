package temp;
import java.util.ArrayList;
import java.util.List;

public class Node {
	int id = -1;
	boolean labeled = false;
	List<Integer> adjacents = null;
	int degree = 0;

	public Node(int id, boolean labeled) {
		this.id = id;
		this.labeled = labeled;
		if (labeled) {
			this.adjacents = new ArrayList<>();
		}
	}

	public void addAdjacent(int idx) {
		if (!adjacents.contains(idx)) {
			degree++;
			adjacents.add(idx);
		}

	}

	public void setLabeled(boolean labeled) {
		this.labeled = labeled;
	}

}

package temp;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import utils.FileUtils;

public class RemoveNode {

	public static void main(String args[]) throws IOException {
		String graphFile = "";
		String labelFile = "";
		int dataSize = 0;
		Set<Integer> idSet = new HashSet<>();
		FileUtils.readLine(labelFile, idSet);

		Node[] nodes = new Node[dataSize];
		for (int i = 0; i < dataSize; i++) {
			nodes[i] = new Node(i + 1, idSet.contains(i + 1));
		}
		FileUtils.readGraph(graphFile, nodes, idSet);

		boolean flag = isLianTong(nodes, idSet);

	}

	private static boolean isLianTong(Node[] nodes, Set<Integer> idSet) {
		boolean[] visited = new boolean[nodes.length];
		dfs(nodes, visited, 0);
		for (Integer id : idSet) {
			if (!visited[id])
				return false;
		}
		return true;
	}

	private static void dfs(Node[] nodes, boolean[] visited, int i) {
		visited[i] = true;
		Iterator<Integer> it = nodes[i].adjacents.iterator();
		while (it.hasNext()) {
			int next = it.next();
			if (!visited[next]) {
				dfs(nodes, visited, next);
			}
		}
	}
}

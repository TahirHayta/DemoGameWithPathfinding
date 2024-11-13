package askerler;

import java.util.List;

public class Deneme {

	public static void main(String[] args) {
        byte[][] grid = {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0}
            };

            Node start = new Node(0, 0, null);
            Node goal = new Node(4, 4, null);

            List<Node> path = AStarPathfinding.findPath(grid, start, goal);

            for (Node node : path) {
                System.out.println("Node: (" + node.x + ", " + node.y + ")");
            }
        }
    }

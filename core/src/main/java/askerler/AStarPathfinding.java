package askerler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.badlogic.gdx.ai.msg.PriorityQueue;

import savaşAlanları.SavasAlaniMatrixi;


class Node {
    int x, y;
    int gCost, hCost;
    Node parent;

    Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.gCost = 0;
        this.hCost = 0;
    }

    int getFCost() {
        return gCost + hCost;
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        return this.x == node.x && this.y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    public int[] nodeToCoordinat(int blocksize) {
    	int[] t={this.x*blocksize,this.y*blocksize};
    	return t;
    }
}

public class AStarPathfinding {

    private static final int[] ROW = {-1, 1, 0, 0, -1, 1, -1, 1};
    private static final int[] COL = {0, 0, -1, 1, -1, 1, 1, -1};

    public static List<Node> findPath(byte[][] grid, Node start, Node goal) {
        int rows = grid.length;
        int cols = grid[0].length;

        List<Node> openList = new ArrayList<>();
        Set<Node> closedList = new HashSet<>();

        start.gCost = 0;
        start.hCost = calculateHeuristic(start, goal);
        openList.add(start);

        while (!openList.isEmpty()) {
            // Sort openList to get the node with the lowest fCost
            openList.sort(Comparator.comparingInt(Node::getFCost));
            Node current = openList.remove(0);

            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            closedList.add(current);

            for (int i = 0; i < 8; i++) {
                int newRow = current.x + ROW[i];
                int newCol = current.y + COL[i];

                if (isValidMove(newRow, newCol, rows, cols, grid, closedList)) {
                    Node neighbor = new Node(newRow, newCol, current);
                    neighbor.gCost = current.gCost + 1;
                    neighbor.hCost = calculateHeuristic(neighbor, goal);

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    } else {
                        // Update the costs if a cheaper path is found
                        for (Node n : openList) {
                            if (n.equals(neighbor) && neighbor.gCost < n.gCost) {
                                n.gCost = neighbor.gCost;
                                n.parent = current;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return Collections.emptyList(); // Return empty path if no path is found
    }

    private static boolean isValidMove(int row, int col, int rows, int cols, byte[][] grid, Set<Node> closedList) {
        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && closedList.stream().noneMatch(n -> n.x == row && n.y == col);
    }

    private static int calculateHeuristic(Node a, Node b) {
    	// Using Euclidean distance heuristic
    	return (int) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));    }

    private static List<Node> reconstructPath(Node goal) {
        List<Node> path = new ArrayList<>();
        Node current = goal;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;}
    }



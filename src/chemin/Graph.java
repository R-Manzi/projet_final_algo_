package chemin;
import java.util.List;

import static chemin.Node.State.UNVISITED;

public class Graph<T> {

    private final DoubleLinkedList<Node<T>> nodes = new DoubleLinkedList<>();
    private final Heuristic<T> heuristic;

    public Graph(Heuristic<T> heuristic) {
        this.heuristic = heuristic;
    }

    public void addNode(Node<T> n) {
        nodes.push(n);
    }

    public DoubleLinkedList<Node<T>> getNodes() {
        return nodes;
    }

    public Heuristic<T> getHeuristic() {
        return heuristic;
    }
    public void link(Node a, Node b, double cost) {
        Edge edge = new Edge(cost, a, b);
        a.addEdge(edge);
        b.addEdge(edge);
    }

    public void findPath(Node<T> start, Node<T> target, List<Node<T>> path) {
        nodes.forEach(node -> {
            node.setState(UNVISITED);
            node.setBackPathNode(null);
            node.setG(Double.MAX_VALUE);
        });

        start.setG(0);
        start.setH(heuristic.calculate(start, target, start));

        PriorityQueue<Node<T>> activeNodes = new PriorityQueue<>();
        activeNodes.add(start);

        while (!activeNodes.isEmpty()) {
            Node<T> currentNode = activeNodes.poll();
            currentNode.setState(CLOSED);

            // target node found !
            if (currentNode == target) {
                path.clear();
                target.retrievePath(path);
                return;
            }

            currentNode.getEdges().forEach((edge) -> {
                Node<T> neighborNode = edge.getOppositeNode(currentNode);
                double neighborG = currentNode.getG() + edge.getG();
                if (!neighborNode.isBlocked()
                        && neighborG < neighborNode.getG()) {

                    neighborNode.setBackPathNode(currentNode);
                    neighborNode.setG(neighborG);
                    double h = heuristic.calculate(start, target, neighborNode);
                    neighborNode.setH(h);
                    if (!activeNodes.contains(neighborNode)) {
                        activeNodes.add(neighborNode);
                        neighborNode.setState(OPEN);
                    }
                }
            });
        }
    }

}
package com.pddm.game.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AiPathFInding {

    public final AiMap map;
    private final PathFinder<Node> pathfinder;
    private final Heuristic<Node> heuristic;
    private final GraphPath<Connection<Node>> connectionPath;

    public AiPathFInding(AiMap map) {
        this.map = map;
        this.pathfinder = new IndexedAStarPathFinder<Node>(createGraph(map));
        this.connectionPath = new DefaultGraphPath<Connection<Node>>();
        this.heuristic = new Heuristic<Node>() {
            @Override
            public float estimate (Node node, Node endNode) {
                return Math.abs(endNode.x - node.x) + Math.abs(endNode.y - node.y);
            }
        };
    }

    public Node findNextNode(Vector2 source, Vector2 target) {
        int sourceX = MathUtils.floor(source.x);
        int sourceY = MathUtils.floor(source.y);
        int targetX = MathUtils.floor(target.x);
        int targetY = MathUtils.floor(target.y);
        Node sourceNode = map.getNodeAt(sourceX, sourceY);
        Node targetNode = map.getNodeAt(targetX, targetY);
        connectionPath.clear();
        pathfinder.searchConnectionPath(sourceNode, targetNode, heuristic, connectionPath);

        return connectionPath.getCount() == 0 ? null : connectionPath.get(0).getToNode();
    }

    public Node findAlternativeWay(Vector2 source){
        return findNextNode(source, new Vector2(MathUtils.random(19), MathUtils.random(14)));
    }

    private static final int[][] NEIGHBORHOOD = new int[][] {
            new int[] {-1,  0},
            new int[] { 0, -1},
            new int[] { 0,  1},
            new int[] { 1,  0}
    };

    public static MyGraph createGraph (AiMap map) {
        final int height = map.getHeight();
        final int width = map.getWidth();
        MyGraph graph = new MyGraph(map);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = map.getNodeAt(x, y);
                for (int offset = 0; offset < NEIGHBORHOOD.length; offset++) {
                    int neighborX = node.x + NEIGHBORHOOD[offset][0];
                    int neighborY = node.y + NEIGHBORHOOD[offset][1];
                    if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                        Node neighbor = map.getNodeAt(neighborX, neighborY);
                        if (!neighbor.isBody && !neighbor.isPoisonApple) {
                            node.getConnections().add(new DefaultConnection<Node>(node, neighbor));
                        }
                    }
                }
            }
        }
        return graph;
    }

    private static class MyGraph implements IndexedGraph<Node> {

        AiMap map;

        public MyGraph (AiMap map) {
            this.map = map;
        }

        @Override
        public int getIndex(Node node) {
            return node.getIndex();
        }

        @Override
        public Array<Connection<Node>> getConnections(Node fromNode) {
            return fromNode.getConnections();
        }

        @Override
        public int getNodeCount() {
            return map.getHeight() * map.getWidth();
        }

    }
}

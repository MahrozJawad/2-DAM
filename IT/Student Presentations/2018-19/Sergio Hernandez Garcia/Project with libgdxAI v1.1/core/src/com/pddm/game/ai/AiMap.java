package com.pddm.game.ai;

public class AiMap {
    private Node[][] map;

    private final int width;
    private final int height;

    public AiMap(int width, int height) {
        this.width = width;
        this.height = height;

        map = new Node[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = new Node(this, x, y);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Node getNodeAt(int x, int y) {
        return map[y][x];
    }

    public void clearMap(){
        for (Node[] n2 : map){
            for (Node n : n2){
                n.isBody = false;
                n.isPoisonApple = false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 1; height >= y; y++) {
            for (int x = 0; x < width; x++) {
                stringBuilder.append(map[height-y][x].isBody ? "#" : map[height-y][x].isPoisonApple ? "@" : " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

package other;

import java.util.ArrayList;
import java.util.List;

public class P174 {

    private static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int calculateMinimumHP(int[][] dungeon) {
        List<Node> list = new ArrayList<>();
        int lenX = dungeon.length;
        int lenY = dungeon[0].length;

        if (dungeon[lenX - 1][lenY - 1] < 0)
            dungeon[lenX - 1][lenY - 1] = Math.abs(dungeon[lenX - 1][lenY - 1]) + 1;
        else
            dungeon[lenX - 1][lenY - 1] = 1;

        if (lenX - 2 >= 0)
            list.add(new Node(lenX - 2, lenY - 1));
        if (lenY - 2 >= 0)
            list.add(new Node(lenX - 1, lenY - 2));

        while (list.size() > 0) {
            List<Node> tList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);
                int x = node.x;
                int y = node.y;
                int need = Integer.MAX_VALUE;
                if (x + 1 < lenX)
                    need = dungeon[x + 1][y];
                if (y + 1 < lenY)
                    need = dungeon[x][y + 1] < need ? dungeon[x][y + 1] : need;
                if (dungeon[x][y] < 0)
                    dungeon[x][y] = need + Math.abs(dungeon[x][y]);
                else if (dungeon[x][y] >= need)
                    dungeon[x][y] = 1;
                else
                    dungeon[x][y] = need - dungeon[x][y];
                if (x - 1 >= 0)
                    tList.add(new Node(x - 1, y));
                if (i == list.size() - 1 && y - 1 >= 0)
                    tList.add(new Node(x, y - 1));
            }
            list = tList;
        }
        return dungeon[0][0];
    }

}

package other;

import java.util.HashSet;
import java.util.Set;

public class P547 {

    static class UnionFindSet {
        private int[] arr;

        public UnionFindSet(int n) {
            this.arr = new int[n];
            for (int i = 0; i < n; i++)
                this.arr[i] = i;
        }

        private int findRoot(int x) {
            if (this.arr[x] != x)
                this.arr[x] = findRoot(this.arr[x]);
            return this.arr[x];
        }

        private void merge(int x, int y) {
            int xRoot = findRoot(x);
            int yRoot = findRoot(y);
            if (xRoot != yRoot)
                this.arr[xRoot] = yRoot;
//                if (xRoot > yRoot)
//                    this.arr[xRoot] = yRoot;
//                else
//                    this.arr[yRoot] = xRoot;
        }

        private int getRootLen() {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < this.arr.length; i++)
                set.add(findRoot(i));
            return set.size();
        }
    }


    public int findCircleNum(int[][] M) {
        int len = M.length;
        UnionFindSet unionFindSet = new UnionFindSet(len);

        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++)
                if (M[i][j] == 1)
                    unionFindSet.merge(i, j);
        return unionFindSet.getRootLen();
    }

    public static void main(String[] args) {
        int[][] M = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        P547 p547 = new P547();
        System.out.println(p547.findCircleNum(M));
    }

}

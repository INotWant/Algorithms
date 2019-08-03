package other;

/**
 * @author iwant
 * @date 19-6-26 19:54
 * @desc
 */
public class P223 {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A <= E && B <= F && C >= G && D >= H)
            return area(A, B, C, D);
        else if (A >= E && B >= F && C <= G && D <= H)
            return area(E, F, G, H);
        else if (F >= D || H <= B || G <= A || E >= C)
            return area(A, B, C, D) + area(E, F, G, H);
        else
            return area(A, B, C, D) + area(E, F, G, H) -
                    area(Math.max(A, E), Math.max(B, F), Math.min(C, G), Math.min(D, H));

    }


    private int area(int A, int B, int C, int D) {
        return (C - A) * (D - B);
    }

}

package other;

public class P165 {

    public int compareVersion(String version1, String version2) {
        String[] splits1 = version1.split("\\.");
        String[] splits2 = version2.split("\\.");
        int i = 0;
        for (; i < Math.min(splits1.length, splits2.length); i++) {
            int n1 = Integer.parseInt(splits1[i]);
            int n2 = Integer.parseInt(splits2[i]);
            if (n1 > n2)
                return 1;
            else if (n1 < n2)
                return -1;
        }
        if (i < splits1.length) {
            for (; i < splits1.length; i++)
                if (Integer.parseInt(splits1[i]) != 0)
                    return 1;
            return 0;
        } else {
            for (; i < splits2.length; i++)
                if (Integer.parseInt(splits2[i]) != 0)
                    return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        P165 p165 = new P165();
        System.out.println(p165.compareVersion("1", "1.1"));
        System.out.println(p165.compareVersion("1.0", "1.0.0"));
        System.out.println(p165.compareVersion("1.01", "1.0.0"));
    }

}

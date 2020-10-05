package other;

public class Pair<K, T> {

    private K first;
    private T second;

    public Pair(K first, T second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}

package chapter10;

/**
 * @author kissx on 2017/10/6.
 *         <p>
 *         队列的数组实现
 *         要点：判断队列为空（head == tail）或队列满（“tail+1” == head）时的条件
 */
public class Queue<T> {

    private T[] array;
    private int head = 0;
    private int tail = 0;

    /**
     * 构造一个队列
     *
     * @param size 初始队列大小（容量）
     */
    @SuppressWarnings("unchecked")
    public Queue(int size) {
        array = (T[]) new Object[size];
    }

    /**
     * 将一个新元素入队
     *
     * @param element 新元素
     * @throws OutOfSpaceException 超出队列容量异常
     */
    public void enQueue(T element) throws OutOfSpaceException {
        int newTail = tail == array.length - 1 ? 0 : tail + 1;
        if (newTail != head) {
            array[tail] = element;
            tail = newTail;
        } else
            throw new OutOfSpaceException("空间已满！");
    }

    static class OutOfSpaceException extends Exception {
        OutOfSpaceException() {
        }

        OutOfSpaceException(String message) {
            super(message);
        }
    }

    /**
     * @return 出队
     * @throws EmptyQueueException 队列为空异常
     */
    public T deQueue() throws EmptyQueueException {
        if (head != tail) {
            T element = array[head];
            head = head == array.length - 1 ? 0 : head + 1;
            return element;
        } else
            throw new EmptyQueueException("队列为空！");
    }

    static class EmptyQueueException extends Exception {
        EmptyQueueException() {
        }

        EmptyQueueException(String message) {
            super(message);
        }
    }

    /**
     * @return 返回队列已用大小
     */
    public int size() {
        if (tail > head)
            return tail - head;
        else if (tail == head)
            return 0;
        else {
            return array.length - head + tail;
        }
    }

    public static void main(String[] args) throws OutOfSpaceException, EmptyQueueException {
        Queue<Integer> queue = new Queue<>(5);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.deQueue();
        queue.deQueue();
        System.out.println("队列大小为：" + queue.size());
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        System.out.println("队列大小为：" + queue.size());
        while (queue.size() > 0)
            System.out.println("当前元素为：" + queue.deQueue());
    }
}

package chapter16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 贪心算法（拟阵） ---- 任务调度问题
 *
 * @author kissx on 2017/11/5.
 */
public class TaskSchedulingProblem {


    private List<DataNode> dataNodeList = new ArrayList<>();    // 待调度任务集信息

    public TaskSchedulingProblem(List<DataNode> dataNodeList) {
        this.dataNodeList = dataNodeList;
    }

    public TaskSchedulingProblem() {
    }

    public static class DataNode {
        public final Integer label;     // 标号
        public final Integer deadline;  // 截止时间
        public final Integer fineTime;  // 罚时

        public DataNode(Integer label, Integer deadline, Integer fineTime) {
            this.label = label;
            this.deadline = deadline;
            this.fineTime = fineTime;
        }
    }

    /**
     * 计算任务调度顺序以使得罚时最小
     *
     * @return 返回任务的调度顺序
     */
    public Integer[] getSchedules() {
        List<DataNode> schedules = new ArrayList<>();
        // 注意：逆序
        TreeSet<DataNode> dataNodes = new TreeSet<>((o1, o2) -> o2.fineTime.compareTo(o1.fineTime));
        dataNodes.addAll(dataNodeList);
        List<DataNode> remainList = new ArrayList<>();
        for (DataNode dataNode : dataNodes) {
            if (isAlone(schedules, dataNode.deadline))
                schedules.add(dataNode);
            else
                remainList.add(dataNode);

        }
        Integer[] results = new Integer[dataNodes.size()];
        int i = 0;
        for (DataNode dataNode : schedules)
            results[i++] = dataNode.label;
        for (DataNode dataNode : remainList)
            results[i++] = dataNode.label;
        return results;
    }

    // 判断是否仍为独立集
    private boolean isAlone(List<DataNode> currentSchedules, Integer currentDeadline) {
        int[] taskArray = new int[currentSchedules.size() + 2];
        for (DataNode dataNode : currentSchedules) {
            if (dataNode.deadline <= currentSchedules.size() + 1)
                taskArray[dataNode.deadline]++;
        }
        if (currentDeadline <= currentSchedules.size() + 1)
            taskArray[currentDeadline]++;
        int sum = 0;
        for (int i = 1; i < taskArray.length; i++) {
            sum += taskArray[i];
            if (sum > i)
                return false;
        }
        return true;
    }

    public void setDataNodeList(List<DataNode> dataNodeList) {
        this.dataNodeList = dataNodeList;
    }

    public List<DataNode> getDataNodeList() {
        return dataNodeList;
    }


    //    --------------- TEST ---------------
    public static void main(String[] args) {
        List<DataNode> dataNodes = new ArrayList<>();
        dataNodes.add(new DataNode(1, 4, 70));
        dataNodes.add(new DataNode(3, 4, 50));
        dataNodes.add(new DataNode(4, 3, 40));
        dataNodes.add(new DataNode(2, 2, 60));
        dataNodes.add(new DataNode(5, 1, 30));
        dataNodes.add(new DataNode(6, 4, 20));
        dataNodes.add(new DataNode(7, 6, 10));
        TaskSchedulingProblem taskSchedulingProblem = new TaskSchedulingProblem(dataNodes);
        Integer[] schedules = taskSchedulingProblem.getSchedules();
        System.out.println(Arrays.toString(schedules));
    }
}
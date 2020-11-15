import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    //使用堆解决
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return count.get(o2) - count.get(o1);
            }
        });
        heap.addAll(count.keySet());
        int[] result = new int[k];
        for(int i = 0; i < k; i ++){
            result[i] = heap.poll();
        }
        return result;
    }

    //使用堆解决，优化方案
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for(Map.Entry<Integer, Integer> entry : count.entrySet()){
            int num = entry.getKey();
            int times = entry.getValue();
            if(heap.size() == k){
                if(heap.peek()[1] < times){
                    heap.poll();
                    heap.offer(new int[]{num, times});
                }
            }else{
                heap.add(new int[]{num, times});
            }
        }
        int[] result = new int[k];
        for(int i = 0; i < k; i ++){
            result[i] = heap.poll()[0];
        }
        return result;
    }

    //使用排序解决


}

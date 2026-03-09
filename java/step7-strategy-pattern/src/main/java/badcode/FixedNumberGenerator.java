package badcode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class FixedNumberGenerator implements NumberGenerator{
    private Queue<Integer> queue;

    public FixedNumberGenerator(Integer... numbers){
        queue = new ArrayDeque<>(List.of(numbers));
    }

    public int generate() {
        if (queue.isEmpty()){
            return 0;
        }
        return queue.poll();
     }
}
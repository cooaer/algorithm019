import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class TestDeque {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new TestDeque().testDeque();
    }

    public void testDeque(){
        Deque<String> deque = new LinkedList<String>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addLast("d");
        System.out.println(deque);

        String str = deque.element();
        System.out.println(str);

        while(!deque.isEmpty()){
            System.out.println(deque.remove());
        }
        System.out.println(deque);

    }


}




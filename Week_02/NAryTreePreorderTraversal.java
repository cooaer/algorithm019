import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NAryTreePreorderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    //递归实现
    public List<Integer> preorderTraversal(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node node, List<Integer> result){
        if(node == null){
            return;
        }
        result.add(node.val);
        for(Node child : node.children){
            preorder(child, result);
        }
    }

    //迭代实现
    public List<Integer> preorderTraversal2(Node root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node current = stack.pop();
            result.add(current.val);
            for(int i = current.children.size() - 1; i >= 0; i --){
                stack.push(current.children.get(i));
            }
        }
        return result;
    }

}

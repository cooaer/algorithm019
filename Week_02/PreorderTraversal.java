import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal {

    public class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode node, List<Integer> result){
        if(node == null){
            return;
        }
        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    public List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while(current != null || !stack.isEmpty()){
            while(current != null){
                result.add(current.val);
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            current = current.right;
        }
        return result;
    }


}

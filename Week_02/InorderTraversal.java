import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {

    public class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 中序遍历：递归实现
    public List<Integer> inorderTraversal1(TreeNode root){
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode node, List<Integer> result){
        if(node == null){
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }

    // 中序遍历：迭代实现
    public List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> result = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }


}




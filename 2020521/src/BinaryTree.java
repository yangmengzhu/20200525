import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @program: 2020521
 * @description
 * @author: mrs.yang
 * @create: 2020 -05 -21 16 :01
 */
class Node{
    public char val;
    public Node left;
    public Node right;
    public Node(char val){
        this.val=val;
    }
}
public class BinaryTree {
    public Node build(){
        Node A=new Node('A');
        Node B=new Node('B');
        Node C=new Node('C');
        Node D=new Node('D');
        Node E=new Node('E');
        Node F=new Node('F');
        Node G=new Node('G');
        Node H=new Node('H');
        A.left=B;
        A.right=C;
        B.left=D;
        B.right=E;
        E.right=H;
        C.left=F;
        C.right=G;
        return A;
    }
    //层序遍历
    public List<List<Character>> levelorderTraversal(Node root){
        List<List<Character>> ret=new ArrayList<>();
        Queue<Node> queue=new LinkedList<>();
        if(root==null){
            return ret;
        }
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Character> list=new ArrayList<>();
            int size=queue.size();
                while(size>0){
                    Node cur=queue.poll();
                    list.add(cur.val);
                    if(cur!=null){
                        if(cur.left!=null){
                            queue.offer(cur.left);
                        }
                        if(cur.right!=null){
                            queue.offer(cur.right);
                        }
                    }
                    size--;
            }
                ret.add(list);
        }
        return ret;
    }
    //二叉树的最近公共祖先
    public Node lowestCommonAncestor(Node root,Node p,Node q){
      if(root==null){
          return null;
      }
      if(root==p||root==q){
          return root;
      }
       Node left= lowestCommonAncestor(root.left,p,q);
        Node right=lowestCommonAncestor(root.right,p,q);
        //1.p.q在同一边，左边
        if(left!=null) {
            return left;
        }else if(right!=null){
            return right;
        }else{
            return root;
        }
        //2.p.q分别在不同边
    }
    //根据前序字符串构建二叉树
    int i=0;
    public Node buildTree(String str){
        Node root=null;
        if(str.charAt(i)!='#'){
            root=new Node(str.charAt(i));
            i++;
            root.left=buildTree(str);
            root.right=buildTree(str);
        }else{
            i++;
        }
        return root;
    }
    public int widthOfBinaryTree(Node root) {
        if(root == null) return 0;
        Queue<Node> q = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        q.offer(root);
        list.add(1);
        int res = 1;
        while (!q.isEmpty()) {
            int count = q.size();
            for(int i =count; i> 0; i--) {
                Node cur = q.poll();
                Integer curIndex = list.removeFirst();
                if(cur.left != null) {
                    q.offer(cur.left);
                    list.add(curIndex * 2);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                    list.add(curIndex * 2 +1);
                }
            }
            // list 中 size 为 1 的情况下，宽度也为 1，没有必要计算。
            if(list.size() >= 2) {
                res = Math.max(res, list.getLast() - list.getFirst() + 1);
            }
        }

        return res;
    }
}

import java.util.Arrays;

/*
 * @program: 2020521
 * @description
 * 给定数组，创建一个大根堆
 * @author: mrs.yang
 * @create: 2020 -05 -21 19 :18
 */
public class Heap {
    public int[] elem;
    public int usedSize;
    public Heap() {
        this.elem = new int[10];
    }

    /**
     *
     * @param root 每棵子树的开始位置
     * @param len  结束位置
     */
    public void adjustDown(int root,int len) {//一次调整
        int parent = root;
        int child = 2*parent+1;
        while (child < len) {
            //0、判断是否有左右孩子  有的话 找到最大值 C下标表示最大值下标
            if(child+1<len&&this.elem[child]<this.elem[child+1]) {//有左右孩子
                   child=child+1;
            }
            //代码指向到这里，c表示最大值下标
            if(this.elem[child] > this.elem[parent]) {
                //交换
                int tmp=this.elem[child];
                this.elem[child]=this.elem[parent];
                this.elem[parent]=tmp;
                parent=child;
                child=2*parent+1;
            }else {
                break;
            }
        }
    }
    public void createHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }
        //i：每棵子树的根节点下标
        //调整5次
        for (int i = (this.usedSize-1-1)/2; i >= 0 ; i--) {
            adjustDown(i,this.usedSize);
        }
    }
    private Boolean isFull(){
        if(this.usedSize==this.elem.length){
            return true;
        }
        return false;
    }
    public void adjustUp(int child){
        int parent=(child-1)/2;
        //判断c的值是否大于p的值
        while(child>0){
            if(this.elem[child]>this.elem[parent]){
                int tmp=this.elem[child];
                this.elem[child]=this.elem[parent];
                this.elem[parent]=tmp;
                child=parent;
                parent=(child-1)/2;
            }else{
                break;
            }
        }
    }
    public void push(int val){
        //1.判断堆是否已满
        //2.满了扩容
        if(isFull()){
         this.elem= Arrays.copyOf(this.elem ,2*this.elem.length);
        }
        //将val放到数组最后一个位置
        this.elem[this.usedSize]=val;
        //调整
        this.usedSize++;
        adjustUp(this.usedSize-1);
    }
    private boolean isEmpty(){
        return this.usedSize==0;
    }
    public void pop(){
        //1.判断是否是空的
        if(isEmpty()){
            return;
        }
        //2.交换堆顶元素和数组最后一个元素
        int tmp=this.elem[0];
        this.elem[0]=this.elem[this.usedSize-1];
        this.elem[this.usedSize-1]=tmp;
        this.usedSize--;
        //3.调用向下调整函数
        adjustDown(0,this.usedSize);
    }
    //大根堆进行从小到大排序
    public void headSort(){
        int end=this.usedSize-1;
        while(end>0){
            //交换0下标和end下标的值
            int tmp=this.elem[0];
            this.elem[0]=this.elem[end];
            this.elem[end]=tmp;
            //调整
            adjustDown(0,end);
            end--;
        }
    }
}


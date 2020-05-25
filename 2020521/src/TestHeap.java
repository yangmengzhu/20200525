/*
 * @program: 2020521
 * @description
 * @author: mrs.yang
 * @create: 2020 -05 -22 14 :40
 */

import java.util.Arrays;

public class TestHeap {
    int[] elem;
    int usedSize;
    public TestHeap(){
        this.elem=new int[10];
    }
    //创建堆
  public void creatHead(int[] arr){
      for (int i = 0; i < arr.length; i++) {
          this.elem[i]=arr[i];
          this.usedSize++;
      }
      for (int i = (this.usedSize-1-1)/2; i >=0 ; i--) {
          adjustDown(i,this.usedSize-1);
      }
  }
  //创建一个大根堆
  public void adjustDown(int root,int len){
        int parent=root;
        int child=parent*2+1;
      while(child<=len){
          //判断是否有右孩子并且右孩子值大于左孩子
          if(child+1<len&&this.elem[child]<this.elem[child+1]){
              child=child+1;
          }
          //比较根节点和child值得大小
          if(this.elem[root]<this.elem[child]){
              //交换
              int tmp=this.elem[child];
              this.elem[child]=this.elem[parent];
              this.elem[parent]=tmp;
              parent=child;
              child=2*parent+1;
          }else{
              break;
          }
      }
  }
  public void display(){
      for (int i = 0; i <this.usedSize  ; i++) {
          System.out.print(this.elem[i]+" ");
      }
      System.out.println();
  }
 //增加元素
    private boolean isFull(){
        return this.usedSize==this.elem.length;
    }
    public void adjustUp(int child){
        int parent=(child-1)/2;
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
    //添加元素
    public void push(int val){
        //判断数组是否已满，满了就扩容
        if(isFull()){
            this.elem= Arrays.copyOf(this.elem,2*this.elem.length);
        }
        //将val放在最后一个位置，向上调整
        this.elem[this.usedSize]=val;
        this.usedSize++;
        adjustUp(this.usedSize-1);
    }
    //删除堆顶元素
    private boolean isEmpty(){
        return this.usedSize==0;
    }
    public void pop(){
        //判断数组是否为空
        if(isEmpty()){
            return;
        }
        //将0下标位置元素和最后一个位置元素交换
        int tmp=this.elem[0];
        this.elem[0]=this.elem[this.usedSize-1];
        this.elem[this.usedSize-1]=tmp;
        //删除最后一个元素
        this.usedSize--;
        //进行调整0号下标位置
        adjustDown(0,this.usedSize) ;
    }
    //拿到堆顶元素
    public int peek(){
        if(isEmpty()){
            return -1;
        }
        return this.elem[0];
    }
    public void lastStoneWeight(int[] stones){
        for (int i = 0; i < stones.length; i++) {
            this.elem[i]=stones[i];
            this.usedSize++;
        }
        for (int i =(this.usedSize-1-1)/2; i >=0 ; i--) {
            adjustDown(i,this.usedSize-1);
        }

    }
    //排序
    public void HeadSort(){
        int end=this.usedSize-1;
        while(end>0){
            int tmp=this.elem[0];
            this.elem[0]=this.elem[end];
            this.elem[end]=tmp;
            adjustDown(0,end);
            end--;
        }
    }
    public static void main(String[] args) {
        TestHeap testHeap=new TestHeap();
        int[] arr={ 2,7,4,1,8,1};
        testHeap.creatHead(arr);
        testHeap.display();
        /*testHeap.push(80);
        testHeap.display();*/
        System.out.println(testHeap.peek());

    }
}

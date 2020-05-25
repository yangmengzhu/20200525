/*
 * @program: 2020521
 * @description
 * @author: mrs.yang
 * @create: 2020 -05 -22 15 :47
 */

import java.util.*;

public class TestDemo {
    //直接插入排序，越接近有序，越快
    // 时间复杂度：最坏情况O(n^2),最好情况：O(n),空间复杂度O(1)
    // 是稳定排序，如果一个排序是稳定的，那么它就可以变成不稳定的排序，不稳定的排序不会变成稳定的
    //一个稳定排序，在比较过程中没有跳跃式的交换
    public static void insertSort(int[] arr){
        for (int i = 1; i <arr.length ; i++) {
            int tmp=arr[i];
            int j;
            for (j = i-1; j >=0 ; j--) {
                if(arr[j]>tmp){//此处加=就变成不稳定排序的
                    arr[j+1]=arr[j];
                }else{
                    break;
                }
            }
            arr[j+1]=tmp;
        }
    }
    //希尔排序
    public static void shell(int[] arr,int gap){
        int tmp=0;
        for (int i = gap; i < arr.length; i++) {
             tmp=arr[i];
            int j;
            for (j =i-gap; j >=0 ; j-=gap) {
                if(arr[j]>tmp){
                    arr[j+gap]=arr[j];
                }else{
                    break;
                }
            }
            arr[j+gap]=tmp;
        }
    }
    public static void shellSort(int[] arr){
        int[] drr={5,3,1};
        for (int i = 0; i <drr.length; i++) {
            shell(arr,drr[i]);
        }
    }
    //选择排序时间复杂度最好最坏都是O(N^2) 空间复杂度O(1)  不稳定排序
    public void selectSort(int[] arr){
        for (int i = 0; i <arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[i]){
                    int tmp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
    }

    public static void adjustDown(int[] arr,int root,int len){
        int parent=root;
        int child=2*parent+1;
        while(child<len){
            if(child+1<len&&arr[child]<arr[child+1]){
                child++;
            }
            if(arr[child]>arr[parent]){
                int tmp=arr[child];
                arr[child]=arr[parent];
                arr[parent]=tmp;
                child=parent;
                parent=(child-1)/2;
            }else{
                break;
            }
        }
    }
    //堆排序 时间复杂度 O(n*logn)
    public static void creatHead(int[] arr){
        //创建堆
        for (int i = (arr.length-1-1)/2; i >=0 ; i--) {
            adjustDown(arr,i,arr.length);
        }
    }
    public static void headSort(int[] arr){
        creatHead(arr);
        int end=arr.length-1;
        while(end>0){
          if(arr[end]>arr[0]){
              int tmp=arr[0];
              arr[0]=arr[end];
              arr[end]=tmp;
              adjustDown(arr,0,end);
              end--;
          }
        }
    }
    //冒泡排序 时间复杂度O(n^2) 优化后代码最好情况可以达到O(n) 空间复杂度O(n)  稳定排序
    public void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {//i表示趟数
            boolean flag=false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                    flag=true;
                }
            }
            if(flag==false){
                return;
            }
        }
    }
    public boolean isPopOrder(int[] pushA,int[] popA){
        Stack<Integer> stack=new Stack<>();
        int tmp=0;
        for (int i = 0; i <pushA.length ; i++) {
            stack.push(pushA[i]);
            while(!stack.empty()&&stack.peek()==popA[tmp]){
                stack.pop();
                tmp++;
            }
        }
        if(tmp==popA.length){
            return true;
        }
       return false;
    }
    static class MinSum{
        List<Integer> list;
        public MinSum(List<Integer> list){
            this.list=list;
        }
        public int sum(){
            return list.get(0)+list.get(1);
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1,int[] nums2,int k){
        List<List<Integer>> ret=new ArrayList<>();
        if(k<=0||nums1.length==0||nums2.length==0){
            return ret;
        }
        PriorityQueue<MinSum> queue=new PriorityQueue<>();
        for (int i = 0; i <nums1.length ; i++) {
            for (int j = 0; j <nums2.length ; j++) {
                queue.offer(new MinSum(Arrays.asList(nums1[i],nums2[j])));
            }
        }
        while(k>0&&!queue.isEmpty()){
            ret.add(queue.poll().list);
            k--;
        }
        return ret;
    }
    public int lastStoneWeight(int[] stones) {
        //排序 从大到小
        if(stones.length==1){
            return stones[0];
        }
        int index=stones.length-1;
        Arrays.sort(stones);
        while(stones[index-1]!=0){
            int x=stones[index-1];
            int y=stones[index];
            if(x!=y){
                stones[index-1]=0;
                stones[index]=y-x;
            }else{
                stones[index]=stones[index-1]=0;
            }
            Arrays.sort(stones);
        }
        return stones[stones.length-1];
    }
    public int partition(int[] array,int low,int high){
        int tmp=array[low];
        while(low<high){
            while(low<high&&array[high]>=tmp){
                high--;
            }
            array[low]=array[high];
            while(low<high&&array[low]<=tmp){
                low++;
            }
            array[high]= array[low];
        }
        array[high]=tmp;
        return tmp;
    }
    public void three_num_mid(int[] array,int left,int right){
        //array[mid]<=array[left]<=array[right]
        int mid=(left+right)/2;
        if(array[mid]>array[left]){
            int tmp=array[mid];
            array[mid]=array[left];
            array[left]=tmp;
        }
        if(array[mid]>array[right]){
            int tmp=array[mid];
            array[mid]=array[right];
            array[right]=tmp;
        }
        if(array[left]>array[right]){
            int tmp=array[left];
            array[left]=array[right];
            array[right]=tmp;
        }
    }
    //快排优化一
    public static void insertSort1(int[] arr,int left,int right){
        for (int i =left; i < right; i++) {
            int tmp=arr[i];
            int j;
            for (j = i-1; j >left ; j--) {
                if(arr[j]>tmp){//此处加=就变成不稳定排序的
                    arr[j+1]=arr[j];
                }else{
                    break;
                }
            }
            arr[j+1]=tmp;
        }
    }
    public void quick(int[] array,int left,int right){
        if(left>=right){
            return;
        }
        //优化方式一
        if(left+right-1<=100){
            insertSort1(array,left,right);
            return;
        }
        //优化方式二
        three_num_mid(array,left,right);
        int par=partition(array,left,right);
        //递归左边 和右边
        quick(array,0,par-1);
        quick(array,par+1,right);
    }
    //快速排序
    public  void quickSort(int[] array){
        quick(array,0,array.length-1);
    }
    //非递归快速排序
    public void quickSort1(int[] arr){
        Stack<Integer> stack=new Stack<>();
        int left=0;
        int right=arr.length-1;
        int par=partition(arr,left,right);
        if(par<left+1){
            stack.push(left);
            stack.push(par-1);
        }
        if(par<right-1){
            stack.push(par+1);
            stack.push(right);
        }
        while(!stack.empty()){
            right=stack.pop();
            left=stack.pop();
            par=partition(arr,left,right);
            if(par<left+1){
                stack.push(left);
                stack.push(par-1);
            }
            if(par<right-1){
                stack.push(par+1);
                stack.push(right);
            }
        }
    }
    //归并排序
    public void merge(int[] arr,int mid,int low,int high){
        //归并
        int s1=low;
        int s2=mid+1;
        int[] ret=new int[low+high-1];
        int i=0;
        while(s1<=mid&&s2<=high){
            //两个段都有数据
            if(arr[s1]<arr[s2]){
                ret[i++]=arr[s1++];
                //i++;
                //s1++;
            }else{
                ret[i++]=arr[s2++];
            }
        }
        while(s1<=mid){
            //s1有数据
            ret[i++]=arr[s1++];
        }
        while(s2<=high){
            //s2有数据
            ret[i++]=arr[s2++];
        }
        //将ret中元素拷贝到arr中对应的位置
        for (int j = 0; j < ret.length; j++) {
            arr[j+low]=ret[j];
        }
    }
    public void mergeSortInternal(int[] arr,int low,int high){//分解
        if(low>=high){
            return;
        }
        int mid=(low+high)>>>1;
        mergeSortInternal(arr,low,mid);
        mergeSortInternal(arr,mid+1,high);
        merge(arr,mid,low,high);
    }
    public void mergeSort(int[] arr){
        //1.分解
        //2.合并
        mergeSortInternal(arr,0,arr.length-1);
    }
}

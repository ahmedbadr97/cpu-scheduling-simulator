package main.java.com.cpuschedular.datastructure;

public class MinHeap {
   private int size;
    private Process heap[];
    private int counter;

    public MinHeap(int size) {
        this.size = size;
        this.heap=new Process[size];
        this.counter=0;

    }
    public void Insert(Process process)
    {
        if(counter==size)
            return;
        if(counter==0)//insert  at parent
        {
            heap[counter]=process;
        }
        else
        {
            heap[counter]=process;
            ShiftUp(counter);
        }
        counter ++;
    }
    public Process Top()
    {
        return heap[0];
    }
    public Process Remove()
    {
        Process process;
        if(isEmpty())
            return null;
        process=heap[0];
        heap[0]=heap[counter-1];
        counter--;
        ShiftDown(0);
        return  process;

    }
    public void ShiftDown(int index)
    {

        int l=leftChild(index),r=rightChild(index),p=parent(index);
        int minindex= MinChilds(index);//return the index of max child if it has
        if(minindex!=-1)//it has childern
        {
            if(heap[minindex].compareTo(heap[index])==-1)//if the current index smaller than the min of it's children this swap with min
            {
                Swap(index,minindex);
                ShiftDown(minindex);

            }
        }

    }
    public int MinChilds(int root)
    {//return index of max
        int l=leftChild(root),r=rightChild(root),childs=noOfChilds(root);
        if(childs==2)
            return  heap[l].compareTo(heap[r])==-1 ? l:r;
        else if(childs==1)return l;
        else
            return -1;
    }
    private  int noOfChilds(int root)
    {
        if(leftChild(root)>this.counter)
            return 0;
        else if (rightChild(root)>=this.counter)
            return 1;
        return 2;
    }

    public void ShiftUp(int index)
    {
        int p=parent(index);
        if(heap[index].compareTo(heap[p])==-1&&index!=0) //if current node smaller than or equal it's parent then shift it up
        {
            Swap(p,index);
            ShiftUp(p);
        }
    }
    private void Swap(int x,int y)
    {
        Process Temp=heap[x];
        heap[x]=heap[y];
        heap[y]=Temp;

    }

    private int leftChild(int i)
    {
        return (i*2)+1;
    }
    private int rightChild(int i)
    {
        return (i*2)+2;
    }
    private int parent(int i)
    {
        if(i==0)
            return 0;
        int p=(int)((i-1)/2);
        if(p<0)
            p*=-1;
        return p;
    }
    public boolean isEmpty()
    {
       return counter==0;
    }

    public int getSize() {
        return size;
    }


}

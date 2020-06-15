#include "BinaryHeap.h"

BinaryHeap::BinaryHeap(int capacity){
    this->capacity = capacity;
    heap = new int[capacity+1];
    size = 0;
}

BinaryHeap::~BinaryHeap(){
    delete [] heap;
}

void BinaryHeap::insert(int element){
    if(size < capacity){
     
        size++;
        int tmp = size;
        heap[tmp] = element;
        percolateUp(tmp);
       
    }
    else return;
}

void BinaryHeap::percolateUp(int num){
    
    int parent = num/2;
    int current = num;
    while(current > 0 && heap[parent] > heap[current]){
        
        swap(current, parent);
        current = parent;
        parent = parent/2;
    }
}

void BinaryHeap::deleteMin(){
    
    if(size < 1) return;
    
    else {
        heap[1] = heap[size];
        heap[size] = 0;
        percolateDown(1);
        size--;
    }
}

int BinaryHeap::getMin(){
    
    if(size < 1) return -1;
    
    else return heap[1];
    
}

void BinaryHeap::percolateDown(int hole){
    
    int min = hole;
    int left = (2 * hole);
    int right = (2 * hole) + 1;
    if(left < size && heap[min] > heap[left]){
        min = left;
    }
    if(right < size && heap[min] > heap[right]){
        min = right;
    }
    if(min != hole){
        swap(hole, min);
        percolateDown(min);
    }
}

void BinaryHeap::swap(int i, int j){
    int t = heap[i];
    heap[i] = heap[j];
    heap[j] = t;
}


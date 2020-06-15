#include "AlgorithmSortHeap.h"
#include "BinaryHeap.h"
#include <stdio.h>

AlgorithmSortHeap::AlgorithmSortHeap(int k){
    this->k = k;
}

int AlgorithmSortHeap::select(){
    
    BinaryHeap heap(k);
    int N;
    cin >> N;
    int *arr;
    arr = new int[k];
    
    for(int i=0; i<k; i++){
        cin >> arr[i];
        heap.insert(arr[i]);
    }
    
    int outer;
    for(int i=0; i<N-k; i++){
        cin >> outer;
        if(outer > heap.getMin()){
            heap.deleteMin();
            heap.insert(outer);
        }
    }
    delete []arr;
    return heap.getMin();
}

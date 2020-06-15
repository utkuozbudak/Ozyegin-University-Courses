#include <stdio.h>
#include "AlgorithmSortQuick.h"

AlgorithmSortQuick::AlgorithmSortQuick(int k){
    this->k = k;
}

void swap(int arr[],int a, int b){
    int t = arr[a];
    arr[a] = arr[b];
    arr[b] = t;
}

int insertionSort(int arr[],int left,int right,int k){
    int size = right-left+1;
    int i, t, j;
    for (i = 1; i < size; i++){
        t = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > t){
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = t;
    }
    return arr[size-k];
}

int partition(int arr[], int left, int right){
    int leftP = left;
    int pivot = arr[right];
    for (int i = left; i <= right - 1; i++){
        if(arr[i] >= pivot) {
            swap(arr,leftP,i);
            leftP++;
        }
    }
    swap(arr,leftP,right);
    return leftP;
}

int AlgorithmSortQuick::quickselect(int* arr, int left, int right, int k){
   
    int pi = partition(arr,left,right);
    if(pi - left == k -1)
        return arr[pi];
    if(pi - left > k-1)
        return quickselect(arr, left, pi-1, k);
    return quickselect(arr, pi + 1, right, k - pi + left - 1);
}

int AlgorithmSortQuick::select(){
    
    int N;
    cin >> N;
    int* pNums = new int[N];
    for (int i=0; i<N; i++){
        cin >> pNums[i];
    }
    
    if(N<=10){
        delete[] pNums;
        return insertionSort(pNums, 0, N-1, k);
    }
    else{
        delete[] pNums;
        return quickselect(pNums, 0, N-1, k);
    }
}

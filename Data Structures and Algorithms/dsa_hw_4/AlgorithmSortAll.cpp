#include <stdio.h>
#include "AlgorithmSortAll.h"


AlgorithmSortAll::AlgorithmSortAll(int k){
    this-> k = k;
}

int AlgorithmSortAll::select(){
   
    int N;
    cin >> N;
    int *pNums = new int[N];
    for (int i=0; i<N; i++){
        cin >> pNums[i];
    }
    
    int i, value, index;
    for(i=1; i<N; i++){
        value = pNums[i];
        index = i-1;
        while(index >= 0 && pNums[index] < value){
            pNums[index+1] = pNums[index];
            index = index-1;
            
        }
        pNums[index+1] = value;
    }
    
    int result = pNums[k-1];
    
       
    delete [] pNums;
    pNums = 0;
    return result;
}

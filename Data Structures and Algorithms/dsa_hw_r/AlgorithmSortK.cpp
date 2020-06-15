#include <stdio.h>
#include "AlgorithmSortK.h"

AlgorithmSortK::AlgorithmSortK(int k){
    this-> k = k;
}

int AlgorithmSortK::select(){
    int N;
    cin >> N;
    int *pNums = new int[k];
    for (int i=0; i<k; i++){
        cin >> pNums[i];
    }
    int i, value, index;
    for(i=1; i<k; i++){
        value = pNums[i];
        index = i-1;
        while(index >= 0 && pNums[index] < value){
            pNums[index+1] = pNums[index];
            index = index-1;
            
        }
        pNums[index+1] = value;
    }
 
    int j,outer, value2, myindex;
    for(j=0; j<N-k; j++){
        cin >> outer;
        myindex = k-1;
        value2 = outer;
        while(myindex >= 0 && pNums[myindex] < value2){
            pNums[myindex+1] = pNums[myindex];
            myindex = myindex -1;
        }
        pNums[myindex+1] = value2;
    
    }
    
    int result = pNums[k-1];
    
       
    delete [] pNums;
    pNums = 0;
    return result;
    
}

#include "SelectionAlgorithm.h"
#include <iostream>
#ifndef AlgorithmSortHeap_h
#define AlgorithmSortHeap_h

using namespace std;

class AlgorithmSortHeap : public SelectionAlgorithm{
public:
    int select();
    AlgorithmSortHeap(int);
    
};
#endif /* AlgorithmSortHeap_h */

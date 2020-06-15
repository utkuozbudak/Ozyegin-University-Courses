#include <iostream>
#include "SelectionAlgorithm.h"
#ifndef AlgorithmSortK_h
#define AlgorithmSortK_h

using namespace std;

class AlgorithmSortK : public SelectionAlgorithm{
public:
    int select();
    AlgorithmSortK(int);    
};

#endif /* AlgorithmSortK_h */

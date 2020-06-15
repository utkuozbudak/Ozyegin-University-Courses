#include <iostream>
#include "SelectionAlgorithm.h"



#ifndef AlgorithmSortAll_h
#define AlgorithmSortAll_h

using namespace std;

class AlgorithmSortAll : public SelectionAlgorithm{
public:
    int select();
    AlgorithmSortAll(int);
};
#endif /* AlgorithmSortAll_h */

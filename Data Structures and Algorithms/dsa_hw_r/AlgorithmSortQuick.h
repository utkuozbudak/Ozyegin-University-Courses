#include <iostream>
#include "SelectionAlgorithm.h"

#ifndef AlgorithmSortQuick_h
#define AlgorithmSortQuick_h

using namespace std;

class AlgorithmSortQuick : public SelectionAlgorithm{
public:
    int select();
    int quickselect(int*,int,int,int);
    AlgorithmSortQuick(int);   
};


#endif /* AlgorithmSortQuick_h */

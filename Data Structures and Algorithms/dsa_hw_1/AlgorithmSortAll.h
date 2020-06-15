
#include <iostream>
#include "selectionAlgorithm.h"



#ifndef AlgorithmSortAll_h
#define AlgorithmSortAll_h

using namespace std;

class AlgorithmSortAll : public selectionAlgorithm{
public:
    int select();
    AlgorithmSortAll(int);
};
#endif /* AlgorithmSortAll_h */

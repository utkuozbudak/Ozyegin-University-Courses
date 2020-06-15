
#include <iostream>
#include "selectionAlgorithm.h"
#ifndef AlgorithmSortK_h
#define AlgorithmSortK_h

using namespace std;

class AlgorithmSortK : public selectionAlgorithm{
public:
    int select();
    AlgorithmSortK(int);
    
};

#endif /* AlgorithmSortK_h */

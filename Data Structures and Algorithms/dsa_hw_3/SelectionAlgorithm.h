#ifndef _SELECTIONALGORITHM_
#define _SELECTIONALGORITHM_
#include <iostream>


    
using namespace std;

class SelectionAlgorithm{
public:
    int virtual select();
    SelectionAlgorithm();
protected:
    int k;
};
#endif /* selectionAlgorithm_h */

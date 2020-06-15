#ifndef TestBed_h
#define TestBed_h

#include "SelectionAlgorithm.h"
#include "AlgorithmSortK.h"
#include "AlgorithmSortAll.h"
#include "AlgorithmSortHeap.h"
#include "AlgorithmSortQuick.h"
#include <iostream>

using namespace std;

class TestBed{
public:
    void execute();
    void setAlgorithmType(int,int);
    TestBed();
    ~TestBed();
private:
    SelectionAlgorithm *algorithm;
    
};
#endif /* TestBed_h */

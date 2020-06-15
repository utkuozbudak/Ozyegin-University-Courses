#ifndef TestBed_h
#define TestBed_h

#include "selectionAlgorithm.h"
#include "AlgorithmSortK.h"
#include "AlgorithmSortAll.h"
#include <iostream>

using namespace std;

class TestBed{
public:
    void execute();
    void setAlgorithmType(int,int);
    TestBed();
    ~TestBed();
private:
    selectionAlgorithm *algorithm;
    
};
#endif /* TestBed_h */

#include <stdio.h>
#include <ctime>
#include "TestBed.h"

TestBed::TestBed(){
    
}
TestBed::~TestBed(){
    delete algorithm;
    
}
void TestBed::setAlgorithmType(int type, int k){
    if (type == 1) {
        algorithm = new AlgorithmSortAll(k);
        
    }
    
    if (type == 2){
        algorithm = new AlgorithmSortK(k);
        
    }
}
    
void TestBed::execute(){
   
    clock_t start = clock();
    
    cout << "Result: " << algorithm -> select() << endl;
    
    
    clock_t end = clock();
    
    double cpu_time = static_cast<double>( end - start ) /CLOCKS_PER_SEC;
    cout << "Duration(sec): "<< cpu_time << endl;
    
}

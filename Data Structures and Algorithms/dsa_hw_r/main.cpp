#include <iostream>
#include <fstream>
#include "string"
#include "TestBed.h"
#include "SelectionAlgorithm.h"
#include "AlgorithmSortAll.h"

using namespace std;

int main(int argc, const char * argv[]) {
    string testfile;
    if(argc < 2){
        cout << "Enter a test file name:" << endl;
        cin >> testfile;
    }
    else {
        testfile = argv[1];
    }
    ifstream file(testfile.c_str());

    if(file.is_open()){
    cin.rdbuf(file.rdbuf());
    
    }
    else{
    cout << "Error: cannot read the test file!"<< endl;
    return -1;
    }
    
    int algorithmType = 0;
    int k = 0;
   
    cin >> algorithmType;
    cin >> k;
  
    TestBed *pTestbed = new TestBed();
    
    pTestbed -> setAlgorithmType(algorithmType, k);
    pTestbed -> execute();
        
    delete pTestbed;
    return 0;
}

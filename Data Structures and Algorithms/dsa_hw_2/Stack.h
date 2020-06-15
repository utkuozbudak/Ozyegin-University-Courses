#ifndef H_STACK
#define H_STACK
#include "StackItem.h"

using namespace std;

class Stack{
public:
    Stack();
    ~Stack();
    void push(StackItem*);
    StackItem* pop();
    StackItem* top();
    bool isEmpty();
private:
    StackItem *head;
    
};
#endif /* Stack_h */

#include "Stack.h"
#include <stdio.h>
#include <iostream>

using namespace std;

Stack::Stack(){
    head = NULL;
}
Stack::~Stack(){
    delete head;
}

void Stack::push(StackItem *item){
    StackItem *tmp = item;
    if(isEmpty()){
        tmp->next = NULL;
    }
    else{
        tmp->next = head;
    }
    head = tmp;
}

StackItem* Stack::top(){
    return head;
}

StackItem* Stack::pop(){
    if (isEmpty()) {
        cout << "Stack is empty.";
        return NULL;
    }
    else
    {
        StackItem* tmp = head;
        head = tmp->next;
        return tmp;
    }
}

bool Stack::isEmpty(){
    return head == NULL;
}












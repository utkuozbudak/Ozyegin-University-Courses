#ifndef __CALCULATOR__
#define __CALCULATOR__

#include <string>
#include "Stack.h"


using namespace std;

class Calculator {
public:
    Calculator(string); // constructor that takes an arithmetic expression in infix representation
    ~Calculator(); // destructor
    string getPostfix(); // returns the postfix representation of the arithmetic expression
    int calculate(); // evaluates and returns the result of the arithmetic expression
private:
    Stack *stack; // stack to be used for infix-postfix conversion and evaluation of an arithmetic expression
    string postfixExpression; // keeps a postfix representation of the arithmetic expression
    string infixExpression; // keeps an infix representation of the arithmetic expression
};
 #endif

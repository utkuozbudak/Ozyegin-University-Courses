#include "Calculator.h"
#include <stdlib.h>
#include <vector>
#include <sstream>

int prec(int k){
    if(k==4 || k==5){
        return 2;
    }
    if(k==3 || k==2){
        return 1;
    }
    return 0;
}

Calculator::Calculator(string infix) {
    this->infixExpression = infix;
    stack = new Stack();
    
    vector<string> tokens;
    string token;
    istringstream iss(infixExpression);
    iss>>token;
    while(token.compare(";")!=0){
        tokens.push_back(token);
        iss>>token;
    }
    for(string s:tokens){
        StackItem *temp = new StackItem(s);
        if(!temp->isOperator){
            postfixExpression +=temp->toString();
            postfixExpression += " ";
        }
        else{
             if (temp->op == 0) this->stack->push(temp);
             else if(temp->op == 1){
                 while(stack->isEmpty()==false && stack->top()->op != 0){
                     StackItem *tmp = stack->pop();
                     postfixExpression += tmp->toString();
                     postfixExpression += " ";
                 }
                 if(stack->isEmpty()==false){
                     stack->pop();
                 }
             }
             else{
                 while(stack->isEmpty() == false && prec(temp->op)<=prec(stack->top()->op)){
                     StackItem *tmp = stack->pop();
                     if(tmp->op != 0){
                         postfixExpression += tmp->toString();
                         postfixExpression += " ";
                     }
                 }
                 stack->push(temp);
        }
    }
}
    while(stack->isEmpty()==false){
        StackItem *tmp = stack->pop();
        postfixExpression += tmp->toString();
        postfixExpression += " ";
    }
    postfixExpression += ";";
}

int Calculator::calculate() {
        vector<string> tokens ;
        string token;
        istringstream iss(postfixExpression);
        iss>>token;
        while(token.compare(";")!=0){
            tokens.push_back(token);
            iss>>token;
        }

        for(string s: tokens){
            StackItem *temp = new StackItem(s);
            
            if(!temp->isOperator){
                stack->push(temp);
            
        }
        else {
            if (stack->isEmpty() == false && s == "/" ) {
                int res = (stack->top()->next->n) / (stack->top()->n);
                stack->pop();
                stack->pop();
                
                StackItem* newResult = new StackItem(false,res);
                stack->push(newResult);
            }
            else if (stack->isEmpty() == false && s == "*") {
                int res = (stack->top()->next->n) * (stack->top()->n);
                stack->pop();
                stack->pop();
                
                StackItem* newResult = new StackItem(false, res);
                stack->push(newResult);
            }
            else if (stack->isEmpty() == false && s == "+" ) {
                int res = (stack->top()->next->n) + (stack->top()->n);
                stack->pop();
                stack->pop();
                
                StackItem* newResult = new StackItem(false, res);
                stack->push(newResult);
            }
            else if (stack->isEmpty() == false && s == "-") {
                int res = (stack->top()->next->n) - (stack->top()->n);
                stack->pop();
                stack->pop();
                
                StackItem* newResult = new StackItem(false, res);
                stack->push(newResult);
            }
        }
    }
    return stack->pop()->n;
}

string Calculator::getPostfix() {
    return postfixExpression;
}

Calculator::~Calculator() {
    delete stack;
}

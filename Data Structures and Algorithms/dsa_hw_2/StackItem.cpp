#include "StackItem.h"

StackItem::StackItem(bool isOp, int i) {

    init(isOp, i);
    next = 0;
}

StackItem::StackItem(string item) {

    if(item.compare("+") == 0)
        init(true, OPERATOR_PLUS);
    else if(item.compare("-") == 0)
        init(true, OPERATOR_MINUS);
    else if(item.compare("*") == 0)
        init(true, OPERATOR_MULTIPLICATION);
    else if(item.compare("/") == 0)
        init(true, OPERATOR_DIVISION);
    else if(item.compare("(") == 0)
        init(true, OPERATOR_LEFTPAR);
    else if(item.compare(")") == 0)
        init(true, OPERATOR_RIGHTPAR);
    else
        init(false, atoi(item.c_str()));
}

void StackItem::init(bool isOp, int i) {
    isOperator = isOp;
    if(isOp)
        op = i;
    else
        n = i;
}

string StackItem::toString() {

    stringstream ss;

    if(!isOperator) {
        ss << n;
    } else {
        switch(op) {
            case OPERATOR_MINUS:
                ss << "-";
                break;
            case OPERATOR_PLUS:
                ss << "+";
                break;
            case OPERATOR_DIVISION:
                ss << "/";
                break;
            case OPERATOR_MULTIPLICATION:
                ss << "*";
                break;
            case OPERATOR_LEFTPAR:
                ss << "(";
                break;
            case OPERATOR_RIGHTPAR:
                ss << ")";
                break;
        }
    }
    return ss.str();
}

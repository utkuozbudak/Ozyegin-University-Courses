#include <iostream>
#include <string>
#include <sstream>
#include "Calculator.h"

int main ()
{
    string infixExpression;
    cout << "Enter an arithmetic expression: " << endl;
    getline(cin, infixExpression);

    Calculator *calc = new Calculator(infixExpression);

    cout << "Input expression in postfix form: " << calc->getPostfix() << endl;
    cout << "The result is: " <<calc->calculate()<<endl;

    delete calc;

    return 0;
}

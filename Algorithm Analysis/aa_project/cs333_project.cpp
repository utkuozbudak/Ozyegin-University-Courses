#include <iostream>
#include <climits>
#include <math.h>
using namespace std;

static const size_t k=50;

long findR(long long n) {
    long even = (n % 2 == 0) ? n : n - 1;
    long result = 0;
    while (even % 2 == 0) {
        even /= 2;
        result++;
    }
    return result;
}

long findD(long long n) {
    long even = (n % 2 == 0) ? n : n - 1;
    while (even %2==0) {
        even /= 2;
    }
    return even;
}



bool probabilistic_test(long long n) {
    long r = findR(n);  // r's and d's in formula (2^r)*d + 1
    long d = findD(n);
    //cout << "r:" << r << " d:" << d << endl;
    for (int i = 0; i < k; i++) {
        
        
        //randomnumber
        long long randNum = (rand() << 16 | rand()) % (n-2)+2;
        long long x = randNum; 
        
        // W hold or not
        for (long j = 1; j < d ; j++) {
            x = (randNum * x) %  n;
        }

        if (x == 1 || x == (n - 1)) continue; 
        for (long j = 1; j < r; j++) {
            x = (x * x) % n;
            if (x == (n - 1)) goto continuer;
        }
        return false;
        continuer:;
    }
    return true;
}

int main()
{
   
    
    long long n;
    cin >> n;
    n = pow(2, 62) - 3;
        if (probabilistic_test(n))
            std::cout <<n << ": most probably prime"<<endl;
        else
            std::cout <<n<< ": its composite"<<endl;
    
}


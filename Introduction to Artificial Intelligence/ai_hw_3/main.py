import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.naive_bayes import BernoulliNB
from sklearn.naive_bayes import MultinomialNB
from sklearn.naive_bayes import ComplementNB
from sklearn.metrics import accuracy_score
from sklearn.metrics import confusion_matrix


all_data = pd.read_csv("mnist_train.csv")
test_data= pd.read_csv("mnist_test.csv")
x_train= np.asarray(all_data)[:,1:]
y_train= np.asarray(all_data)[:,0:1]
x_test= np.asarray(test_data)[:,1:]
y_test= np.asarray(test_data)[:,0:1]

train_x = pd.DataFrame(data = x_train, index= range(len(x_train)))
train_y = pd.DataFrame(data = y_train, index= range(len(y_train)))
test_x = pd.DataFrame(data = x_test, index= range(len(x_test)))
test_y = pd.DataFrame(data = y_test, index= range(len(y_test)))

#     Split the train data
from sklearn.model_selection import train_test_split
x_train, x_test, y_train, y_test = train_test_split(train_x,train_y, test_size= 0.33, random_state = 20)


#######      ALL ALGORITHMS DEFINED WITH THEIR DEFAULT PARAMETERS             ########
#######      PARAMETER TUNING AND ITS RESULTS DISCUSSED IN REPORT             ########

while True:
        print("\n-------Select An Algorithm-------\n"
              "1. KNeighbors Classification\n"
              "2. Decision Tree Classification\n"
              "3. Naive Bayes Classification\n"
              "4. Exit\n")

        opt = int(input())
        
        if(opt == 1):
            #KNeighbor Classifier
            print("\nK Neighbors Classifier working...\n")
            KNG = KNeighborsClassifier() 
            KNG.fit(x_train,y_train.values.ravel())
            print("\nAccuracy Score:",accuracy_score(y_test,KNG.predict(x_test)),"\n ")
            print("Confusion Matrix:\n")
            print(confusion_matrix(y_test,KNG.predict(x_test)))
           
            
        if(opt == 2):
             #Decision Tree Classifier
            print("\nDecision Tree Classifier working...\n")
            DTR = DecisionTreeClassifier()
            DTR.fit(x_train,y_train.values.ravel())
            print("\nAccuracy Score:",accuracy_score(y_test,DTR.predict(x_test)),"\n")
            print("Confusion Matrix:\n")
            print(confusion_matrix(y_test,DTR.predict(x_test)))
            
        if(opt == 3):
            print("\n-------Select An Naive Bayes Algorithm-------\n"
                  "1. GaussianNB\n"
                  "2. BernoulliNB\n"
                  "3. MultinomialNB\n"
                  "4. ComplementNB\n")
                
            sel = int(input())
                
            if(sel == 1):
                #GaussianNB Classifier
                print("\nGaussianNB working...\n")
                GNB = GaussianNB()
                GNB.fit(x_train,y_train.values.ravel())
                print("\nAccuracy Score:",accuracy_score(y_test,GNB.predict(x_test)))
                print("Confusion Matrix:\n")
                print(confusion_matrix(y_test,GNB.predict(x_test)))
                        
            if(sel == 2):
                #BernoulliNB Classifier
                print("\nBernoulliNB working...\n")
                BNB = BernoulliNB()
                BNB.fit(x_train,y_train.values.ravel())
                print("\nAccuracy Score:",accuracy_score(y_test,BNB.predict(x_test)))
                print("Confusion Matrix:\n")
                print(confusion_matrix(y_test,BNB.predict(x_test)))
                
            if(sel == 3):
                #MultinomialNB Classifier
                print("\nMultinomialNB working...\n")
                MNB = MultinomialNB()
                MNB.fit(x_train,y_train.values.ravel())
                print("\nAccuracy Score:",accuracy_score(y_test,MNB.predict(x_test)))
                print("Confusion Matrix:\n")
                print(confusion_matrix(y_test,MNB.predict(x_test)))
                    
                         
            if(sel==4):
                #ComplementNB Classifier
                print("\nComplementNB working...\n")
                CNB = ComplementNB()
                CNB.fit(x_train,y_train.values.ravel())
                print("\nAccuracy Score:",accuracy_score(y_test,CNB.predict(x_test)))
                print("Confusion Matrix:\n")
                print(confusion_matrix(y_test,CNB.predict(x_test)))
                    
        if(opt == 4):
            break
                       

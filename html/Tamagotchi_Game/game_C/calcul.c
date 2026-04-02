#include <stdio.h>
#include <string.h>
#include "calcul.h"

int calcul(const char* Opp, int val1, int val2){
    if(!strcmp(Opp, "MUL")){
        return val1 * val2;
    } else if(!strcmp(Opp, "ADD")){
        return val1 + val2;
    } else if(!strcmp(Opp, "SOU")){
        return val1 - val2;
    } else if(!strcmp(Opp, "DIV")){
        if(val2 != 0){
            return val1 / val2;
        } else {
            return 0;
        }
    } else{
        return 0;
    }
}
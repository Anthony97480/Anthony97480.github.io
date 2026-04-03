#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "tableau.h"

typedef struct MyTable{
    const char* Name;
    struct MyTable* next;
} MyTable;


MyTable* NameTable = NULL;
int index_NameTable = 0;

void add_element(char* val){
    MyTable* nextElement = (MyTable*) malloc(sizeof(MyTable));
    nextElement->Name = strdup(val);
    nextElement->next = NULL;
    if(NameTable == NULL){
        NameTable = nextElement;
    } else{
        MyTable* tmp = NameTable;
        while (tmp->next != NULL){
            tmp = tmp->next;
        }
        tmp->next = nextElement;
    }
    index_NameTable++;
}

char* get_element(int idx){
    MyTable* tmp = NameTable;
    int tmp_idx = 0;

    while(tmp != NULL && tmp_idx != idx){
        tmp = tmp->next;
        tmp_idx++;
    }

    if(tmp != NULL){
        return tmp->Name;
    } else{
        printf("erreur, élément rechercher non existant");
        exit(1);
    }
}
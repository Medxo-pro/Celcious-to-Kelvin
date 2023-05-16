//
//  main.c
//
//  Created by Mehdi Atmani on 5/11/20.
//

#include <stdio.h>

int main(){
   
    int n1,n2, suma=0;
    n2 = 273;
    printf("Digite Temperatura en C°: ");
    scanf("%i",&n1);
    
    suma= n1 + n2;
    printf("La conversión de C° a K es de: %i",suma);
    
    return 0;
}

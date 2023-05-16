//
//  main.c
//  Mehdi Xcode Episodio 13
//
//  Created by Mehdi Atmani on 5/11/20.
// Areas de trapecios

#include <stdio.h>

int main(){
    int B,b,area,altura;
    printf("Digite la Base mayor:");scanf("%i",&B);
    printf("Digite la Base menor:");scanf("%i",&b);
    printf("Digite la altura:");scanf("%i",&altura);
    
    area = ( (B + b)*altura)/2;
    printf("LA Area del trapecio es de: %i",area);
    
    return 0;
}



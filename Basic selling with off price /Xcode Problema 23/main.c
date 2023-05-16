//
//  main.c
//  Xcode Problema 23
//
//  Created by Mehdi Atmani on 6/11/20.
//

#include <stdio.h>
#include <string.h>

#define Honda 0.05
#define Yamaha 0.08
#define Suzuki 0.10
#define OM 0.02

int main() {
    char marca[30];
    float precio,total,descuento;
    printf("Digite Su marca de moto:\n");
    gets(marca);
    printf("Digite precio de moto:\n");
    scanf("%f",&precio);
    
    
    if(strcmp(marca,"Honda")==0){
    descuento=precio*Honda;
    total=precio-descuento;
    printf("El costo de La moto es de:$%.2f",total);
    }
        
    if(strcmp(marca,"Yamaha")==0){
    descuento = precio * Yamaha;
    total = precio - descuento;
    printf("El costo de La moto es de:$%.2f",total);
    }
        
    if(strcmp(marca,"Suzuki")==0){
    descuento = precio * Suzuki;
    total = precio - descuento;
    printf("El costo de La moto es de:$%.2f",total);
    }
        
    if(strcmp(marca,"OM")==0){
    descuento = precio * OM;
    total = precio - descuento;
    printf("El costo de La moto es de:$%.2f",total);
    }
        
    return 0;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoprogra3;


/**
 *
 * @author CrisA
 */
public class PilArbol {
    private NodoPil tope;
    
    public void Insertar (NodoAr elemento){
        NodoPil nuevo;
        nuevo = new NodoPil(elemento);
        nuevo.siguiente = tope;
        tope = nuevo;
    }
    
    public boolean pilVacia(){
        return tope == null;
    }
    
    public NodoAr topePil(){
        return tope.dato;
    }
    
    public void pilReiniciada(){
        tope = null;
    }
    
    public NodoAr quitar(){
        NodoAr aux = null;
        if(!pilVacia ()){
            aux = tope.dato;
            tope = tope.siguiente;
        }
        return aux;
        
    }
}

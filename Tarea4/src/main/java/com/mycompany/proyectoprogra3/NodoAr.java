/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoprogra3;

/**
 *
 * @author CrisA
 */
public class NodoAr {
    Object dato;
    NodoAr izq;
    NodoAr der;
    
    public NodoAr (Object x){
        dato = x;
        izq = null;
        der = null;
    }
}

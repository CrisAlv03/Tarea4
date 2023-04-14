/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoprogra3;


/**
 *
 * @author CrisA
 */
public class ArbolExp {
    NodoAr raiz;
    
    public ArbolExp (){
        raiz = null;
    }
    
    public ArbolExp (String cadena){
        raiz = crearAr(cadena);
    }
    
    public void reiniciaAr(){
        raiz = null;
    }
    
    public void crearNodo(Object dato){
        raiz = new NodoAr(dato);
    }
    
    public NodoAr crearSubAr(NodoAr dato1, NodoAr dato2, NodoAr oper){
        oper.izq = dato1;
        oper.der = dato2;
        return oper;
    }
    
    public boolean arVacio(){
        return raiz == null;
    }
    
    public String toString(int a){
        String cadena ="";
        switch (a){
                case 0:
                    cadena = preorden(raiz, cadena);
                    break;
                case 1:
                    cadena = posorden(raiz, cadena);
                    break;
        } 
        return cadena;
    }
    
    private String preorden (NodoAr subAr, String c){
        String cadena;
        cadena = "";
        if (subAr != null){
            cadena = c + subAr.dato.toString() + "\n" + preorden(subAr.izq, c)+ preorden (subAr.der, c); 
        }
        return cadena;
    }
    
    
    private String posorden (NodoAr subAr, String c){
        String cadena;
        cadena = "";
        if (subAr != null){
            cadena = c + posorden(subAr.izq, c)+ posorden (subAr.der, c) + subAr.dato.toString() + "\n" ; 
        }
        return cadena;
    }
    
    private int prioridad (char c){
        int p = 3;
        switch (c){
            case '^':
                p = 3;
                break;
            case '/':
            case '*':
                p = 2;
                break;
            case '+':
            case '-':
                p = 1;
                break;
            default:
                p = 0;
        }
        return p;         
    }
    
    private boolean operador( char c){
        boolean resul;
        
        switch (c){
            case'(':
            case')':
            case'^':
            case'/':
            case'*':
            case'+':
            case'-':
                resul = true;
                break;
            default:
                resul = false;
        }
        return resul;
    }
    private NodoAr crearAr (String cadena){
        PilArbol PilOper;
        PilArbol PilExpre;
        NodoAr  token;
        NodoAr op1;
        NodoAr op2;
        NodoAr op;
        PilOper = new PilArbol();
        PilExpre = new PilArbol();
        char caracterEval;
        for (int i = 0; i< cadena.length(); i++){
            caracterEval = cadena.charAt(i);
            token = new NodoAr(caracterEval);
            if(!operador(caracterEval)){
                PilExpre.Insertar(token);
            }
            else {
                switch(caracterEval){
                    case'(':
                        PilOper.Insertar(token);
                        break;
                    case ')':
                        while (!PilOper.pilVacia() && !PilOper.topePil().dato.equals('(')){
                            op2 = PilExpre.quitar();
                            op1 = PilExpre.quitar();
                            op = PilOper.quitar();
                            op = crearSubAr(op2, op1, op);
                            PilExpre.Insertar(op);
                        }
                        PilOper.quitar();
                        break;
                    default:
                        while (!PilOper.pilVacia() && prioridad(caracterEval) <= prioridad(PilOper.topePil().dato.toString().charAt(0))){
                            op2 = PilExpre.quitar();
                            op1 = PilExpre.quitar();
                            op = PilOper.quitar();
                            op = crearSubAr(op2, op1, op);
                            PilExpre.Insertar(op);
                        }
                        PilOper.Insertar(token);
                }
            }
        }
        while (!PilOper.pilVacia()){
            op2 = PilExpre.quitar();
            op1 = PilExpre.quitar();
            op = PilOper.quitar();
            op = crearSubAr(op2, op1, op);
            PilExpre.Insertar(op);
        }
        op = PilExpre.quitar();
        return op;
    }
    
    public double EvaluaExpre(){
        return evalua(raiz);
    }
    
    private double evalua(NodoAr subAr){
        double temp = 0;
        if (!operador(subAr.dato.toString().charAt(0))){
            return Double.parseDouble(subAr.dato.toString());
        }
        else{
            switch(subAr.dato.toString().charAt(0)){
                case '^':
                    temp = temp + Math.pow(evalua(subAr.izq), evalua(subAr.der));
                    break;
                case '*':
                    temp = temp + evalua(subAr.izq) * evalua(subAr.der);
                    break;
                case '/':
                    temp = temp + evalua(subAr.izq) / evalua(subAr.der);
                    break;
                case '+':
                    temp = temp + evalua(subAr.izq) + evalua(subAr.der);
                    break;
                case '-':
                    temp = temp + evalua(subAr.izq) - evalua(subAr.der);
                    break;
            }
        }
    return temp;
    }    
}

package cr.ac.uned.estructurasdedatos.proyecto2.logica;

import java.awt.Graphics;

/**
 * Implementación de un árbol binario de busqueda simple (no balanceado).
 * 
 * @author Mayarling Martínez.
 */
public class ArbolBinario {
    // Atributo que representa el nodo raíz del árbol.
    private Nodo nodoRaiz;
    // Atributo que guarda la cantidad de elementos del árbol para no tener que recorrerlo y calcularlo
    private int cantidadElementos;

    /**
     * Constructor por defecto.
     */
    public ArbolBinario() {
        this.nodoRaiz = null;
        this.cantidadElementos = 0;
    }
    
    /**
     * Método para agregar un elemento al árbol.
     * 
     * @param elemento El elemento a agregar.
     */
    public void agregarElemento(String elemento){
        if(!this.contieneElemento(elemento)){
            if(this.estaVacio()){
                this.nodoRaiz = new Nodo(elemento);
                this.cantidadElementos++;
            } else {
                Nodo temporal = this.nodoRaiz;
                Nodo padre = null;
                while(temporal != null){
                    padre = temporal;
                    if (elemento.compareToIgnoreCase(temporal.valor) < 0) {
                        temporal = temporal.nodoIzquierdo;
                    } else if(elemento.compareToIgnoreCase(temporal.valor) > 0) {
                        temporal = temporal.nodoDerecho;
                    }
                }
                Nodo nuevoNodo = new Nodo(elemento);
                nuevoNodo.nodoPadre = padre;
                if(elemento.compareToIgnoreCase(padre.valor) < 0){
                    padre.nodoIzquierdo = nuevoNodo;
                } else if(elemento.compareToIgnoreCase(padre.valor) > 0) {
                    padre.nodoDerecho = nuevoNodo;
                }
                this.cantidadElementos++;
            }
        }
    }
    
    /**
     * Método para eliminar un nodo hoja sin hijos.
     * @param elemento El elemento a eliminar.
     */
    public void eliminarNodoHoja(String elemento){
        Nodo nodoEncontrado = this.obtenerNodo(elemento);
        if(nodoEncontrado != null) {
            if(nodoEncontrado.esHoja()){
                if(nodoEncontrado.nodoPadre == null){
                    //El nodo es la raíz.
                    this.nodoRaiz = null;
                    this.cantidadElementos--;
                } else {
                    if(elemento.compareToIgnoreCase(nodoEncontrado.nodoPadre.valor) < 0){
                        nodoEncontrado.nodoPadre.nodoIzquierdo = null;
                        nodoEncontrado.nodoPadre = null;
                    } else if(elemento.compareToIgnoreCase(nodoEncontrado.nodoPadre.valor) > 0){
                        nodoEncontrado.nodoPadre.nodoDerecho = null;
                        nodoEncontrado.nodoPadre = null;
                    }
                    this.cantidadElementos--;
                }
            }
        }
    }
    
    /**
     * Método que nos indica si un elemento ya esta o no en el árbol.
     * 
     * @param elemento El elemento a buscar.
     * @return true si ya el elemento existe, false si no.
     */
    public boolean contieneElemento(String elemento){
        boolean contieneElemento = false;
        if(!this.estaVacio()){
            Nodo temporal = this.nodoRaiz;
            while(temporal != null && !contieneElemento) {
                if(elemento.equalsIgnoreCase(temporal.valor)){
                    contieneElemento = true;
                } else {
                    if(elemento.compareToIgnoreCase(temporal.valor) < 0){
                        temporal = temporal.nodoIzquierdo;
                    } else if (elemento.compareToIgnoreCase(temporal.valor) > 0) {
                        temporal = temporal.nodoDerecho;
                    }
                }
            }
        }
        return contieneElemento;
    }
    
    /**
     * Método para obtner el nodo donde esta un elemento.
     * @param elemento El elemento.
     * @return El nodo que contiene el elemento o null si el elemento no esta en el arbol.
     */
    private Nodo obtenerNodo(String elemento){
        Nodo nodoEncontrado = null;
        if(!this.estaVacio()){
            Nodo temporal = this.nodoRaiz;
            while(temporal != null && nodoEncontrado == null) {
                if(elemento.equalsIgnoreCase(temporal.valor)){
                    nodoEncontrado = temporal;
                } else {
                    if(elemento.compareToIgnoreCase(temporal.valor) < 0){
                        temporal = temporal.nodoIzquierdo;
                    } else if (elemento.compareToIgnoreCase(temporal.valor) > 0) {
                        temporal = temporal.nodoDerecho;
                    }
                }
            }
        }
        return nodoEncontrado;
    }
    
    /**
     * Método para saber si el elemento es hoja.
     * @param elemento El elemento.
     * @return true si el elemento es hoja, false si el elemento no es hoja o no existe.
     */
    public boolean esHoja(String elemento){
        boolean hoja = false;
        Nodo nodo = this.obtenerNodo(elemento);
        if(nodo != null){
            hoja = nodo.esHoja();
        }
        return hoja;
    }
    
    /**
     * Método para determinar si el árbol esta vacio o no.
     * 
     * @return true si el árbol esta vacío, false de lo contrario.
     */
    public boolean estaVacio(){
        return this.nodoRaiz == null;
    }
    
    /**
     * Método para obtener la cantidad de elementos.
     * 
     * @return La cantidad de elementos.
     */
    public int obtenerCantidadElementos(){
        return this.cantidadElementos;
    }
    
    /**
     * Método para dibujar el árbol binario dadas las coordenadas X y Y de inicio
     * y el objeto Graphics donde se va a pintar el árbol.
     * 
     * @param x El punto de inicio en el eje X
     * @param y El punto de inicio en el eje Y
     * @param graphics El objeto Graphics.
     */
    public void dibujar(int x, int y, Graphics graphics){
        if(this.nodoRaiz != null) {
            this.nodoRaiz.dibujar(x, y, graphics);
        }
    }
}

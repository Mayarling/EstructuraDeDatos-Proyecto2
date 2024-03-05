package cr.ac.uned.estructurasdedatos.proyecto2.logica;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que define un nodo para un árbol binario de busqueda.
 * 
 * @author Mayarling Martinez
 */
public class Nodo {
    // Atributo que contiene el valor del nodo
    public String valor = null;
    //Atributo que hace una referencia al nodo padre.
    public Nodo nodoPadre = null;
    // Atributo que hace referencia al nodo izquierdo
    public Nodo nodoIzquierdo = null;
    // Atributo que hace referencia al nodo izquierdo
    public Nodo nodoDerecho = null;

    // Constantes para determinar el largo y ancho del dibujo del nodo
    private int LARGO = 30;
    private int ANCHO = 30;
    
    /**
     * Método estático y recursivo para obtener la cantidad de nodos completos
     * (con ambos nodos hijos) que hay dentro de un nodo.
     * 
     * @param nodo El nodo al que se le va a calcular la cantidad de nodos completos.
     * @return La cantidad de nodos completos que contiene este nodo (inclusive)
     */
    private static int cantidadDeNodosCompletos(Nodo nodo) {
        if(nodo == null) {
            return 0;
        } else {
            if(nodo.nodoIzquierdo != null && nodo.nodoDerecho != null) {
                return 1 + cantidadDeNodosCompletos(nodo.nodoIzquierdo) + cantidadDeNodosCompletos(nodo.nodoDerecho);
            } else {
                return cantidadDeNodosCompletos(nodo.nodoIzquierdo) + cantidadDeNodosCompletos(nodo.nodoDerecho);
            }
        }
    }
    
    /**
     * Método que cuenta la cantidad de nodos que hay en un nodo.
     * 
     * @param nodo El nodo
     * @return La cantidad de nodos que hay, incluyendo al nodo, 0 cero si no hay o el nodo es nulo
     */
    private static int cantidadDeNodos(Nodo nodo) {
        int cantidad = 0;
        if(nodo != null) {
            cantidad++;
            cantidad += cantidadDeNodos(nodo.nodoIzquierdo);
            cantidad += cantidadDeNodos(nodo.nodoDerecho);
        }
        return cantidad;
    }

    /**
     * Constructor por defecto.
     * 
     * @param valor El valor que va a contener el nodo.
     */
    public Nodo(String valor) {
        this.valor = valor;
    }

    /**
     * Método para determinar si un nodo es hoja (no tiene hijos).
     * 
     * @return true si el nodo no tiene hijos, false de lo contrario.
     */
    public boolean esHoja() {
        return this.nodoIzquierdo == null && this.nodoDerecho == null;
    }

    /**
     * Método para dibujar el árbol binario dadas las coordenadas X y Y de inicio
     * y el objeto Graphics donde se va a pintar el árbol.
     * 
     * @param x El punto de inicio en el eje X
     * @param y El punto de inicio en el eje Y
     * @param graphics El objeto Graphics.
     */
    public void dibujar(int x, int y, Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, LARGO, ANCHO);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, LARGO, ANCHO);

        graphics.setColor(Color.BLACK);
        graphics.drawString(valor, x - 5 + (LARGO / 2), y + 5 + (ANCHO / 2));

        int nodos = (this.nodoPadre == null)? cantidadDeNodos(this)/2 : cantidadDeNodosCompletos(this);
        int margen = nodos * ANCHO;

        if(this.nodoIzquierdo != null) {
            //int nodosCompletos = cantidadDeNodosCompletos(this.nodoIzquierdo);
            //int margen = nodosCompletos * ANCHO;
            graphics.setColor(Color.BLACK);
            graphics.drawLine(
                    x + (LARGO / 2),
                    y + ANCHO,
                    x - LARGO - margen,
                    y + ANCHO * 2
            );
            this.nodoIzquierdo.dibujar(
                    x - LARGO - margen - (LARGO / 2),
                    y + ANCHO * 2,
                    graphics
            );
        }

        if (this.nodoDerecho != null) {
            //int nodosCompletos = cantidadDeNodosCompletos(this.nodoDerecho);
            //int margen = nodosCompletos * ANCHO;

            graphics.setColor(Color.BLACK);
            graphics.drawLine(
                    x + (LARGO / 2),
                    y + ANCHO,
                    x + (LARGO * 2) + margen,
                    y + ANCHO * 2
            );
            this.nodoDerecho.dibujar(
                    x + (LARGO * 2) + margen - (LARGO / 2),
                    y + ANCHO * 2,
                    graphics
            );
        }
    }
}

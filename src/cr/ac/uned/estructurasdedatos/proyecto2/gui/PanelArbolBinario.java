package cr.ac.uned.estructurasdedatos.proyecto2.gui;

import cr.ac.uned.estructurasdedatos.proyecto2.logica.ArbolBinario;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Clase que hereda de JPanel que contiene un arbol binario que se pinta
 * dentro del panel.
 * 
 * @author Mayarling Martinez
 */
public class PanelArbolBinario extends JPanel{
    // Atributo que contiene el árbol binario
    private ArbolBinario arbol;

    /**
     * Constructor por parametro, recibe el arbol binario.
     * 
     * @param arbol El árbol binario.
     */
    public PanelArbolBinario(ArbolBinario arbol) {
        super();
        this.arbol = arbol;
    }

    /**
     * Sobreescribimos el método paint para que este JPanel pinte el árbol binario.
     * 
     * @param graphics EL objeto Graphics.
     */
    @Override
    public void paint(Graphics graphics) {
        graphics.drawString("Cantidad de elementos en el arbol: "+this.arbol.obtenerCantidadElementos(), 5, 10);
        int x = (new Double(this.getBounds().getWidth()).intValue() / 2) - 15;
        int y = new Double(this.getBounds().getHeight() * 0.05).intValue();
        this.arbol.dibujar(x, y, graphics);
    }
}

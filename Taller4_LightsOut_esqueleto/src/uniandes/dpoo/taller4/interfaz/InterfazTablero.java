package uniandes.dpoo.taller4.interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class InterfazTablero extends JPanel implements MouseListener {
    private Tablero tablerito;
    private JLabel lblContador;
    private Top10 top10;
    private String nombreJugador;
    static File top_10 = new File("data/top10.csv");

    public InterfazTablero(Tablero tablero, JLabel lblContador) {
        this.tablerito = tablero;
        this.setLblContador(lblContador);
        addMouseListener(this);
        top10 = new Top10();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tam = tablerito.darTablero().length;
        int altoPanelTablero = getHeight();
        int anchoPanelTablero = getWidth();
        int altoCasilla = altoPanelTablero / tam;
        int anchoCasilla = anchoPanelTablero / tam;

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (tablerito.darTablero()[i][j]) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.black);
                }
                g.fillRect(j * anchoCasilla, i * altoCasilla, anchoCasilla, altoCasilla);
                g.setColor(Color.red);
                g.drawRect(j * anchoCasilla, i * altoCasilla, anchoCasilla, altoCasilla);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int click_x = e.getX();
        int click_y = e.getY();
        int[] casilla = convertirCoordenadasACasilla(click_x, click_y);
        tablerito.jugar(casilla[0], casilla[1]);
        lblContador.setText(Integer.toString(tablerito.darJugadas()));
        repaint();
        
        if (tablerito.tableroIluminado()) {
            int puntaje = tablerito.calcularPuntaje();
            JOptionPane.showMessageDialog(null, "Ha completado el juego, su puntaje es: " + puntaje);
            
            if (top10.esTop10(puntaje)) {
                String nombre = nombreJugador;
                top10.agregarRegistro(nombre, puntaje);
                try {
                    top10.salvarRecords(top_10);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public void actualizarTablero(Tablero nuevoTablero) {
        this.tablerito = nuevoTablero;
        lblContador.setText(Integer.toString(tablerito.darJugadas()));
        repaint();
    }
    


    private int[] convertirCoordenadasACasilla(int x, int y) {
        int ladoTablero = tablerito.darTablero().length;
        int altoPanelTablero = getHeight();
        int anchoPanelTablero = getWidth();
        int altoCasilla = altoPanelTablero / ladoTablero;
        int anchoCasilla = anchoPanelTablero / ladoTablero;
        int fila = (int) (y / altoCasilla);
        int columna = (int) (x / anchoCasilla);
        return new int[]{fila, columna};
    }
    
    public void setNombreJugador(String nombre) {
        this.nombreJugador = nombre;
    }
	public JLabel getLblContador() {
		return lblContador;
	}

	public void setLblContador(JLabel lblContador) {
		this.lblContador = lblContador;
	}
    
}

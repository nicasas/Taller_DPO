package uniandes.dpoo.taller4.interfaz;
import javax.swing.*;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;

import java.awt.*;
import java.io.File;


public class PanelTop10 extends JFrame {
    private static final long serialVersionUID = 1L;
    private Top10 top10;
    static File top_10 = new File("data/top10.csv");
    public PanelTop10() {
        setTitle("Top 10");
        setSize(200, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        top10 = new Top10();
        top10.cargarRecords(top_10);

        JPanel panelEtiquetas = new JPanel(new GridLayout(1, 3));
        panelEtiquetas.add(new JLabel("Posición"));
        panelEtiquetas.add(new JLabel("Nombre"));
        panelEtiquetas.add(new JLabel("Puntaje"));

        add(panelEtiquetas, BorderLayout.NORTH);

        JPanel panelResultados = new JPanel(new GridLayout(10, 3));
        actualizarPanelResultados(panelResultados);
        add(panelResultados, BorderLayout.CENTER);
    }

    private void actualizarPanelResultados(JPanel panelResultados) {
        int posicion = 1;
        for (RegistroTop10 registro : top10.darRegistros()) {
            panelResultados.add(new JLabel(String.valueOf(posicion)));
            panelResultados.add(new JLabel(registro.darNombre()));
            panelResultados.add(new JLabel(String.valueOf(registro.darPuntos())));
            posicion++;
        }
    }

    public static void main(String[] args) {
        PanelTop10 frame = new PanelTop10();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this;
    }
}
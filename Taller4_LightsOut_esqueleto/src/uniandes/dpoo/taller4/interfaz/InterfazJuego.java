package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.File;

import uniandes.dpoo.taller4.modelo.Tablero;

public class InterfazJuego extends JFrame {
	private Tablero tablerito;
    private String nombre = "";
    private JLabel lblJugador;
    private InterfazTablero panelTablero;

    public InterfazJuego() {
        setTitle("LightsOut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());

        JLabel etiquetaTamano = new JLabel("Tamaño:");
        panelSuperior.add(etiquetaTamano);

        String[] tamanos = {"3x3", "4x4", "5x5","6x6","7x7","8x8"};
        JComboBox<String> Tamano = new JComboBox<>(tamanos);
        panelSuperior.add(Tamano);

        JLabel etiquetaDificultad = new JLabel("Dificultad:");
        panelSuperior.add(etiquetaDificultad);

        ButtonGroup btnDificultad = new ButtonGroup();
        JRadioButton btnFacil = new JRadioButton("Fácil");
        JRadioButton btnMedio = new JRadioButton("Medio");
        JRadioButton btnDificil = new JRadioButton("Difícil");
        btnFacil.setSelected(true);
        btnDificultad.add(btnFacil);
        btnDificultad.add(btnMedio);
        btnDificultad.add(btnDificil);
        panelSuperior.add(btnFacil);
        panelSuperior.add(btnMedio);
        panelSuperior.add(btnDificil);

        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(4, 1));

        JButton btnNuevo = new JButton("Juego Nuevo");
        JButton btnReiniciar = new JButton("Reiniciar");
        JButton btnTop10 = new JButton("TOP-10");
        JButton btnCambiarJugador = new JButton("Cambiar jugador");
        panelDerecho.add(btnNuevo);
        panelDerecho.add(btnReiniciar);
        panelDerecho.add(btnTop10);
        panelDerecho.add(btnCambiarJugador);

        add(panelDerecho, BorderLayout.EAST);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        JLabel lblJugadas = new JLabel("Jugadas:");
        JLabel lblcontador = new JLabel("0");
        lblJugador = new JLabel("Jugador: " + nombre);
        panelInferior.add(lblJugadas);
        panelInferior.add(lblcontador);
        panelInferior.add(lblJugador);
        add(panelInferior, BorderLayout.SOUTH);


        int tamanoInicial = Integer.parseInt(((String) Tamano.getSelectedItem()).substring(0, 1));
        tablerito = new Tablero(tamanoInicial);
        panelTablero = new InterfazTablero(tablerito, lblcontador);
        panelTablero.setNombreJugador(nombre);
        add(panelTablero, BorderLayout.CENTER);


        Tamano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tamano = Integer.parseInt(((String) Tamano.getSelectedItem()).substring(0, 1));
                tablerito = new Tablero(tamano);
                panelTablero.actualizarTablero(tablerito);
                int dificultad = btnFacil.isSelected() ? 5 : (btnMedio.isSelected() ? 8 : 10);
                tablerito.desordenar(dificultad);
            }
        });

        ActionListener dificultadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dificultad = btnFacil.isSelected() ? 5 : (btnMedio.isSelected() ? 8 : 10);
                tablerito.desordenar(dificultad);
                panelTablero.repaint(); 
            }
        };
        btnFacil.addActionListener(dificultadListener);
        btnMedio.addActionListener(dificultadListener);
        btnDificil.addActionListener(dificultadListener);

        btnNuevo.addActionListener(e -> {
            int dificultad = btnFacil.isSelected() ? 5 : (btnMedio.isSelected() ? 8 : 10);
            tablerito.desordenar(dificultad);
            lblcontador.setText(Integer.toString(tablerito.darJugadas()));
            panelTablero.repaint();
        });


        btnReiniciar.addActionListener(e -> {
            tablerito.reiniciar();
            lblcontador.setText(Integer.toString(tablerito.darJugadas()));
            panelTablero.repaint();
        });

        btnTop10.addActionListener(e -> {
            PanelTop10 top10Frame = new PanelTop10();
            top10Frame.setVisible(true);
        });

        btnCambiarJugador.addActionListener(e -> {
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese su nombre:");
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                nombre = nuevoNombre;
                lblJugador.setText("Nombre Jugador: " + nombre);
                panelTablero.setNombreJugador(nombre);
            }
        });
        getContentPane().setBackground(new Color(230, 230, 230));


        panelSuperior.setBackground(new Color(200, 200, 250));
        panelDerecho.setBackground(new Color(200, 200, 150));
        panelInferior.setBackground(new Color(200, 200, 230));


        etiquetaTamano.setForeground(new Color(50, 50, 50));
        etiquetaDificultad.setForeground(new Color(50, 50, 50));
        lblJugadas.setForeground(new Color(50, 50, 50));
        lblcontador.setForeground(new Color(50, 50, 50));
        lblJugador.setForeground(new Color(50, 50, 50));


        btnNuevo.setBackground(new Color(100, 149, 237));
        btnNuevo.setForeground(new Color(255, 255, 255));
        btnReiniciar.setBackground(new Color(100, 149, 237));
        btnReiniciar.setForeground(new Color(255, 255, 255));
        btnTop10.setBackground(new Color(100, 149, 237));
        btnTop10.setForeground(new Color(255, 255, 255));
        btnCambiarJugador.setBackground(new Color(100, 149, 237));
        btnCambiarJugador.setForeground(new Color(255, 255, 255));


        btnFacil.setBackground(new Color(200, 200, 200));
        btnFacil.setForeground(new Color(50, 50, 50));
        btnMedio.setBackground(new Color(200, 200, 200));
        btnMedio.setForeground(new Color(50, 50, 50));
        btnDificil.setBackground(new Color(200, 200, 200));
        btnDificil.setForeground(new Color(50, 50, 50));
    }

    public static void main(String[] args) {
        InterfazJuego frame = new InterfazJuego();
        frame.setVisible(true);
    }
}
	



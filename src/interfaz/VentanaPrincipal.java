package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import controlador.ControladorPlanificador;
import modelo.AristaConPeso;
import modelo.Localidad;
import modelo.Observador;
import modelo.ResultadoPlanificacion;
import modelo.ServicioPlanificacion;

public class VentanaPrincipal implements Observador {

    private JFrame frame;
    private ServicioPlanificacion modelo;
    private ControladorPlanificador controlador;
    private JTextField textNombreLocalidad;
    private JTextField textProvincia;
    private JTextField textLatitud;
    private JTextField textLongitud;
    private JMapViewer mapa;
    private JLabel lblLocalidadesCargadas;
    private JLabel lblCostoTotal;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal window = new VentanaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaPrincipal() {
        this.modelo = new ServicioPlanificacion();
        this.controlador = new ControladorPlanificador(this.modelo);
        this.modelo.agregarObservador(this);
        
        initialize();
        this.modelo.cargarDesdeArchivo();
    }

    @Override
    public void actualizar() {
        mapa.removeAllMapMarkers();
        mapa.removeAllMapPolygons();
        
        List<Localidad> localidades = modelo.getLocalidades();
        String textoLocalidades = "<html>Localidades cargadas:<br>";
        
        if(localidades.isEmpty()) {
            textoLocalidades += "ninguna";
        } else {
            for (int i = 0; i < localidades.size(); i++) {
                Localidad loc = localidades.get(i);
                MapMarkerDot marcador = new MapMarkerDot(loc.getLatitud(), loc.getLongitud());
                marcador.setName(loc.getNombre());
                
                if(i == localidades.size() - 1) {
                    marcador.getStyle().setBackColor(Color.blue);
                    marcador.getStyle().setColor(Color.blue);
                }
                mapa.addMapMarker(marcador);
                
                textoLocalidades += loc.getNombre();
                if (i < localidades.size() - 1) textoLocalidades += "<br>";
            }
        }
        textoLocalidades += "</html>";
        lblLocalidadesCargadas.setText(textoLocalidades);
        mapa.repaint();
    }

    private void initialize() {
        mapa = new JMapViewer();
        DefaultMapController mapController = new DefaultMapController(mapa);
        mapController.setMovementMouseButton(java.awt.event.MouseEvent.BUTTON1);
        frame = new JFrame();
        frame.setTitle("Conectando Ciudades");
        frame.setBounds(100, 100, 850, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    controlador.guardarEnArchivo();
                } catch (Exception ex) {
                    System.err.println("No se pudo guardar: " + ex.getMessage());
                }
            }
        });
        
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(600, 600));
        panelIzquierdo.setLayout(null);
                
        JLabel lblNombreLocalidad = new JLabel("Nombre:");
        lblNombreLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombreLocalidad.setBounds(10, 55, 65, 14);
        panelIzquierdo.add(lblNombreLocalidad);
        
        JLabel lblProvincia = new JLabel("Provincia:");
        lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProvincia.setBounds(10, 95, 92, 14);
        panelIzquierdo.add(lblProvincia);
        
        JLabel lblLatitud = new JLabel("Latitud:");
        lblLatitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLatitud.setBounds(10, 136, 92, 14);
        panelIzquierdo.add(lblLatitud);
        
        JLabel lblLongitud = new JLabel("Longitud:");
        lblLongitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLongitud.setBounds(10, 171, 92, 20);
        panelIzquierdo.add(lblLongitud);
        
        lblCostoTotal = new JLabel("Costo total: $ -");
        lblCostoTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCostoTotal.setBounds(112, 350, 360, 32);
        panelIzquierdo.add(lblCostoTotal);
        
        lblLocalidadesCargadas = new JLabel("Localidades cargadas: ninguna");
        lblLocalidadesCargadas.setVerticalAlignment(SwingConstants.TOP);
        lblLocalidadesCargadas.setBounds(302, 53, 288, 140);
        panelIzquierdo.add(lblLocalidadesCargadas);
        
        JLabel lblNewLabel_4 = new JLabel("Planifica el costo de tu conexión:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_4.setBounds(149, 11, 247, 32);
        panelIzquierdo.add(lblNewLabel_4);
        
        textNombreLocalidad = new JTextField();
        textNombreLocalidad.setBounds(112, 54, 180, 20);
        panelIzquierdo.add(textNombreLocalidad);
        textNombreLocalidad.setColumns(10);
            
        textProvincia = new JTextField();
        textProvincia.setBounds(112, 94, 180, 20);
        panelIzquierdo.add(textProvincia);
        textProvincia.setColumns(10);
        
        textLatitud = new JTextField();
        textLatitud.setBounds(112, 135, 180, 20);
        panelIzquierdo.add(textLatitud);
        textLatitud.setColumns(10);
        
        textLongitud = new JTextField();
        textLongitud.setBounds(112, 173, 180, 20);
        panelIzquierdo.add(textLongitud);
        textLongitud.setColumns(10);
            
        JButton btnAgregarLocalidad = new JButton("Agregar localidad");
        btnAgregarLocalidad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.agregarLocalidad(
                            textNombreLocalidad.getText(),
                            textProvincia.getText(),
                            textLatitud.getText(),
                            textLongitud.getText());

                    limpiarCampos();
                } catch (IllegalArgumentException ex) {
                     JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnAgregarLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAgregarLocalidad.setBounds(80, 216, 195, 23);
        panelIzquierdo.add(btnAgregarLocalidad);
        
        JButton btnPlanificar = new JButton("Planificar conexiones");
        btnPlanificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPlanificar.setEnabled(false);
                SwingWorker<ResultadoPlanificacion, Void> worker = new SwingWorker<ResultadoPlanificacion, Void>() {
                    @Override
                    protected ResultadoPlanificacion doInBackground() throws Exception {
                        return controlador.planificar();
                    }

                    @Override
                    protected void done() {
                        try {
                            ResultadoPlanificacion resultado = get();
                            lblCostoTotal.setText("Costo total: $ " + resultado.getCostoTotal());
                            dibujarConexiones(resultado);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error al planificar: " + ex.getMessage());
                        } finally {
                            btnPlanificar.setEnabled(true);
                        }
                    }
                };
                worker.execute();
            }

            private void dibujarConexiones(ResultadoPlanificacion resultado) {
                for(AristaConPeso arista : resultado.getConexiones()) {
                    Localidad origen = modelo.getLocalidades().get(arista.getOrigen());
                    Localidad destino = modelo.getLocalidades().get(arista.getDestino());

                    List<Coordinate> coords = new ArrayList<>();
                    coords.add(new Coordinate(origen.getLatitud(), origen.getLongitud()));
                    coords.add(new Coordinate(destino.getLatitud(), destino.getLongitud()));
                    coords.add(new Coordinate(destino.getLatitud(), destino.getLongitud()));

                    MapPolygon linea = new MapPolygonImpl(coords);
                    mapa.addMapPolygon(linea);
                }
                mapa.repaint();
            }
        });
        
        btnPlanificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnPlanificar.setBounds(160, 290, 245, 23);
        panelIzquierdo.add(btnPlanificar);
        
        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.reiniciar();
                limpiarCampos();
                lblCostoTotal.setText("Costo total: $ -");
            }
        });
            
        btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnReiniciar.setBounds(280, 216, 195, 23);
        panelIzquierdo.add(btnReiniciar);
        
        mapa.setBounds(430, 50, 300, 330);
        mapa.setZoomControlsVisible(false);
        mapa.setDisplayPosition(new Coordinate(-38.4161, -63.6167), 4);
        
        frame.getContentPane().add(panelIzquierdo, BorderLayout.WEST);
        frame.getContentPane().add(mapa, BorderLayout.CENTER);
    }
    
    private void limpiarCampos() {
        textNombreLocalidad.setText("");
        textProvincia.setText("");
        textLatitud.setText("");
        textLongitud.setText("");
    }
}
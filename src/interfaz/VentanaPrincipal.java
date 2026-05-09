package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import controlador.ControladorPlanificador;
import modelo.AristaConPeso;
import modelo.Localidad;
import modelo.ResultadoPlanificacion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame frame;
	private ControladorPlanificador controlador;
	private JTextField textNombreLocalidad;
	private JTextField textProvincia;
	private JTextField textLatitud;
	private JTextField textLongitud;

	
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
		controlador = new ControladorPlanificador();
		initialize();
	}

	private void initialize() {
		JMapViewer mapa = new JMapViewer();
		frame = new JFrame();
		frame.setTitle("Conectando Ciudades");
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
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
		
		JLabel lblCostoTotal = new JLabel("Costo total: $ -");
		lblCostoTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCostoTotal.setBounds(112, 350, 360, 32);
		panelIzquierdo.add(lblCostoTotal);
		
		JLabel lblLocalidadesCargadas = new JLabel("Localidades cargadas: ninguna");
		lblLocalidadesCargadas.setBounds(80, 250, 397, 23);
		panelIzquierdo.add(lblLocalidadesCargadas);
		
		JLabel lblNewLabel_4 = new JLabel("Planifica el costo de tu conexi\u00F3n:");
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
			public void actionPerformed(ActionEvent e) 
			{
				String nombre = textNombreLocalidad.getText();
				String provincia = textProvincia.getText();
				String latitud = textLatitud.getText();
				String longitud = textLongitud.getText();
				try {

				    controlador.agregarLocalidad(
				            nombre,
				            provincia,
				            latitud,
				            longitud);

				    actualizarLocalidadesCargadas();
				    limpiarCampos();
				    
				    Localidad ultima =controlador.getLocalidades().get(controlador.getLocalidades().size() - 1);
				    MapMarkerDot marcador =new MapMarkerDot(ultima.getLatitud(), ultima.getLongitud());
				    marcador.getStyle().setBackColor(Color.blue);
				    marcador.getStyle().setColor(Color.blue);
					
				    marcador.setName(ultima.getNombre());

					mapa.addMapMarker(marcador);

				} catch (IllegalArgumentException ex) {

					 JOptionPane.showMessageDialog(
					            frame,
					            ex.getMessage(),
					            "Error",
					            JOptionPane.ERROR_MESSAGE);
				}
					
			}

			private void actualizarLocalidadesCargadas() {
				String texto = "Localidades cargadas: ";
				if(controlador.getLocalidades().isEmpty()) {
					texto += "ninguna";
				} else {
					for(int i = 0; i <controlador.getLocalidades().size(); i++)
					{
						texto += controlador.getLocalidades().get(i).getNombre();
						if(i < controlador.getLocalidades().size()-1) texto += ", ";
					}
				}
				lblLocalidadesCargadas.setText(texto);
			}
		});
		btnAgregarLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAgregarLocalidad.setBounds(80, 216, 195, 23);
		panelIzquierdo.add(btnAgregarLocalidad);
		
		JButton btnPlanificar = new JButton("Planificar conexiones");
		btnPlanificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ResultadoPlanificacion resultado = controlador.planificar();
				
				dibujarConexiones(mapa, resultado);
				
				double costo = resultado.getCostoTotal();
				lblCostoTotal.setText("Costo total: $" + costo);
			}

			private void dibujarConexiones(JMapViewer mapa, ResultadoPlanificacion resultado) {
				for(AristaConPeso arista : resultado.getConexiones()) {
					
					Localidad origen = controlador.getLocalidades().get(arista.getOrigen());
					Localidad destino = controlador.getLocalidades().get(arista.getDestino());

				    List<Coordinate> coords =new ArrayList<>();

				    coords.add(new Coordinate(origen.getLatitud(),origen.getLongitud()));
				    coords.add(new Coordinate(destino.getLatitud(),destino.getLongitud()));
				    //Como la funcion del poligono dibuja una figura cerrada, la fuerzo repitiendo una de las coordenadas
				    coords.add(new Coordinate(destino.getLatitud(),destino.getLongitud()));

				    MapPolygon linea = new MapPolygonImpl(coords);
				    mapa.addMapPolygon(linea);
				    mapa.repaint();
				}
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

				mapa.removeAllMapMarkers();
				mapa.removeAllMapPolygons();
				mapa.repaint();
				
				lblLocalidadesCargadas.setText("Localidades cargadas: ninguna");
		        lblCostoTotal.setText("Costo total: $ -");				
			}
		});
			
		btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReiniciar.setBounds(280, 216, 195, 23);
		panelIzquierdo.add(btnReiniciar);	
		
		mapa.setBounds(430,50,300,330);
		mapa.setZoomControlsVisible(false);
		mapa.setDisplayPosition(new Coordinate(-38.4161,-63.6167),4); // Para centrar el mapa en Argentina
		
		frame.getContentPane().add(panelIzquierdo,BorderLayout.WEST);
		frame.getContentPane().add(mapa,BorderLayout.CENTER);
	}
	
	 private void limpiarCampos() {
		textNombreLocalidad.setText("");
		textProvincia.setText("");
		textLatitud.setText("");
		textLongitud.setText("");
	}
}

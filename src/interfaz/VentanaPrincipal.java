package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textNombreLocalidad;
	private JTextField textProvincia;
	private JTextField textLatitud;
	private JTextField textLongitud;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Conectando Ciudades");
		frame.setBounds(100, 100, 580, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombreLocalidad = new JLabel("Nombre:");
		lblNombreLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreLocalidad.setBounds(10, 55, 65, 14);
		frame.getContentPane().add(lblNombreLocalidad);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProvincia.setBounds(10, 95, 92, 14);
		frame.getContentPane().add(lblProvincia);
		
		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLatitud.setBounds(10, 136, 92, 14);
		frame.getContentPane().add(lblLatitud);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLongitud.setBounds(10, 171, 92, 20);
		frame.getContentPane().add(lblLongitud);
		
		textNombreLocalidad = new JTextField();
		textNombreLocalidad.setBounds(112, 54, 225, 20);
		frame.getContentPane().add(textNombreLocalidad);
		textNombreLocalidad.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Planifica el costo de tu conexi\u00F3n:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(149, 11, 247, 32);
		frame.getContentPane().add(lblNewLabel_4);
		
		textProvincia = new JTextField();
		textProvincia.setBounds(112, 94, 225, 20);
		frame.getContentPane().add(textProvincia);
		textProvincia.setColumns(10);
		
		textLatitud = new JTextField();
		textLatitud.setBounds(112, 135, 225, 20);
		frame.getContentPane().add(textLatitud);
		textLatitud.setColumns(10);
		
		textLongitud = new JTextField();
		textLongitud.setBounds(112, 173, 225, 20);
		frame.getContentPane().add(textLongitud);
		textLongitud.setColumns(10);
		
		JButton btnAgregarLocalidad = new JButton("Agregar localidad");
		btnAgregarLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAgregarLocalidad.setBounds(80, 216, 195, 23);
		frame.getContentPane().add(btnAgregarLocalidad);
		
		JButton btnPlanificar = new JButton("Planificar conexiones");
		btnPlanificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPlanificar.setBounds(282, 216, 195, 23);
		frame.getContentPane().add(btnPlanificar);
	}
}

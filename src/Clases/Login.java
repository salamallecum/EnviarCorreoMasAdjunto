package Clases;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;
import java.awt.Toolkit;

public class Login {

	private JFrame frame;
	private URL fondo;
    private Image imagen1;
    private Container miFramePane;
    private String dominio;
    private Validacion validacion = new Validacion();
    public static  String usuario, user;
    public static String contra;
    private JRadioButton rdHotmail;
    private JRadioButton rbGmail;
    private JRadioButton rbYahoo;
    private JRadioButton rbOulook;
    private JRadioButton rbLive;
    private JButton btnIngresar;
    private boolean conexion;
    public static int servidor;
    public JPanel miPanel=new JPanel(){
        

		public void paintComponent(Graphics g){
            g.drawImage(imagen1, 0, 0, getWidth(), getHeight(), null);
    
   }
	};
    private JTextField txtCorreo;
    private JPasswordField passwordField;
    private JButton btnActualizarConexionA;
    private JLabel lblConexion;
	public Login() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Imagenes/Logo MultiBox.jpg")));
		rbGmail = new JRadioButton("Gmail");
		rdHotmail = new JRadioButton("Hotmail");
		
        fondo=frame.getClass().getResource("/Imagenes/LoginNuevo.jpg");
		
		imagen1=new ImageIcon(fondo).getImage();
	    miFramePane = frame.getContentPane();
		initialize();
		verificarConexion();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		miFramePane.add(miPanel);
		miPanel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Correo Electr\u00F3nico:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setForeground(Color.LIGHT_GRAY);
		lblUsuario.setBounds(57, 274, 150, 19);
		miPanel.add(lblUsuario);
		
		txtCorreo = new JTextField();
		txtCorreo.setEnabled(false);
		txtCorreo.setForeground(Color.WHITE);
		txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCorreo.setBackground(Color.DARK_GRAY);
		txtCorreo.setBounds(57, 293, 362, 33);
		miPanel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setForeground(Color.LIGHT_GRAY);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasena.setBounds(57, 323, 150, 19);
		miPanel.add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setEnabled(false);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBackground(Color.DARK_GRAY);
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(57, 343, 362, 33);
		miPanel.add(passwordField);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setEnabled(false);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				usuario= txtCorreo.getText().trim();
				contra= passwordField.getText().trim();
				
				user=usuario;
				
				StringTokenizer st = new StringTokenizer(usuario, "@");
				 if(validacion.validateEmail(usuario) && contra.length()!=0)
				   {
				   while(st.hasMoreTokens()) {

				    String correo = st.nextToken();

				     dominio = st.nextToken();
				     dominio= dominio.toLowerCase();

				   }
				   
				
				   if(rbGmail.isSelected())
				   {
						   if(dominio.equals("gmail.com")  )
						   {
							   frame.dispose();
						       EmailInterfaz ventana= new EmailInterfaz();
						   }
						   else
						   {
							   JOptionPane.showMessageDialog(null,"EL DOMINIO SELECCIONADO ES INCORRECTO","ERROR	",JOptionPane.PLAIN_MESSAGE);
							   
						   }
				   }
				   
				   if(rbLive.isSelected())
				   {
						   if(dominio.equals("live.com")  )
						   {
							   frame.dispose();
						       EmailInterfaz ventana= new EmailInterfaz();
						   }
						   else
						   {
							   JOptionPane.showMessageDialog(null,"EL DOMINIO SELECCIONADO ES INCORRECTO","ERROR	",JOptionPane.PLAIN_MESSAGE);
							   
						   }
				   }
				   if(rdHotmail.isSelected())
				   {
						   
						   if(dominio.equals("hotmail.com") )
						   {
							   frame.dispose();
						       EmailInterfaz ventana= new EmailInterfaz();
						   }
						   else
						   {
							   JOptionPane.showMessageDialog(null,"EL DOMINIO SELECCIONADO ES INCORRECTO","ERROR	",JOptionPane.PLAIN_MESSAGE);
						   }
				   }
				   
				   if(rbYahoo.isSelected())
				   {
						   
						   if(dominio.equals("yahoo.es") || dominio.equals("yahoo.com"))
						   {
							   frame.dispose();
						       EmailInterfaz ventana= new EmailInterfaz();
						   }
						   else
						   {
							   JOptionPane.showMessageDialog(null,"EL DOMINIO SELECCIONADO ES INCORRECTO","ERROR	",JOptionPane.PLAIN_MESSAGE);
						   }
				   }
				   if(rbOulook.isSelected())
				   {
						   if(dominio.equals("outlook.com") )
						   {
							   frame.dispose();
						       EmailInterfaz ventana= new EmailInterfaz();
						   }
						   else
						   {
							   JOptionPane.showMessageDialog(null,"EL DOMINIO SELECCIONADO ES INCORRECTO","ERROR	",JOptionPane.PLAIN_MESSAGE);
						   }
				   }
						 
						  
					   }
					
		
				   else
				   {
					   txtCorreo.setText("");
					   passwordField.setText("");
					   txtCorreo.requestFocus();
					   JOptionPane.showMessageDialog(null,"LA CUENTA NO ES UN CORREO ELECTRONICO","ERROR	",JOptionPane.PLAIN_MESSAGE);
				   }
			}
		});
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setBackground(Color.DARK_GRAY);
		btnIngresar.setBounds(79, 379, 150, 37);
		miPanel.add(btnIngresar);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"GRACIAS, LOS SALUDA JF TUTORIALES :)","SALIDA DEL PROGRAMA",JOptionPane.PLAIN_MESSAGE);
				frame.dispose();
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setBounds(239, 379, 150, 37);
		miPanel.add(btnSalir);
		
		JLabel lblServidor = new JLabel("Servidor:");
		lblServidor.setForeground(Color.LIGHT_GRAY);
		lblServidor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblServidor.setBounds(43, 221, 74, 19);
		miPanel.add(lblServidor);
		
		
		rbGmail.setBackground(Color.DARK_GRAY);
		rbGmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbGmail.setForeground(Color.LIGHT_GRAY);
		rbGmail.setBounds(117, 219, 63, 23);
		rbGmail.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
			
			if(conexion)
			{
				if (Mouse_evt.getClickCount() == 1) {

					if(rbGmail.isSelected()== true)
					{
						rdHotmail.setSelected(false);
						rbOulook.setSelected(false);
						rbYahoo.setSelected(false);
						rbLive.setSelected(false);
						txtCorreo.setEnabled(false);
						passwordField.setEnabled(false);
						btnIngresar.setEnabled(false);
						
					}
					else
					{
						rdHotmail.setSelected(false);
						rbOulook.setSelected(false);
						rbYahoo.setSelected(false);
						rbLive.setSelected(false);
						servidor = 0;
						txtCorreo.setEnabled(true);
						passwordField.setEnabled(true);
						btnIngresar.setEnabled(true);
						
					}
					
						
					
						}
					}
			}
				});
		miPanel.add(rbGmail);
		
		
		rdHotmail.setForeground(Color.LIGHT_GRAY);
		rdHotmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdHotmail.setBackground(Color.DARK_GRAY);
		rdHotmail.setBounds(211, 219, 81, 23);
		rdHotmail.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
			
				if(conexion)
				{
				if (Mouse_evt.getClickCount() == 1) {

					if(rdHotmail.isSelected()== true )
					{
						rbGmail.setSelected(false);
						rbOulook.setSelected(false);
						rbYahoo.setSelected(false);
						txtCorreo.setEnabled(false);
						rbLive.setSelected(false);
						passwordField.setEnabled(false);
						btnIngresar.setEnabled(false);
						
					}
					else
					{
						rbGmail.setSelected(false);
						rbOulook.setSelected(false);
						rbYahoo.setSelected(false);
						txtCorreo.setEnabled(true);
						rbLive.setSelected(false);
						passwordField.setEnabled(true);
						btnIngresar.setEnabled(true);
						servidor = 1;
						
					}
					
						
					
						}
					}
			}
				});
		miPanel.add(rdHotmail);
		
		rbYahoo = new JRadioButton("Yahoo");
		rbYahoo.setForeground(Color.LIGHT_GRAY);
		rbYahoo.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbYahoo.setBackground(Color.DARK_GRAY);
		rbYahoo.setBounds(278, 246, 74, 23);
		rbYahoo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
			
				if(conexion)
				{
				if (Mouse_evt.getClickCount() == 1) {

					if(rbYahoo.isSelected()== true )
					{
						rbGmail.setSelected(false);
						rbOulook.setSelected(false);
						rdHotmail.setSelected(false);
						rbLive.setSelected(false);
						txtCorreo.setEnabled(false);
						passwordField.setEnabled(false);
						btnIngresar.setEnabled(false);
						
					}
					else
					{
						rbGmail.setSelected(false);
						rbOulook.setSelected(false);
						rdHotmail.setSelected(false);
						rbLive.setSelected(false);
						txtCorreo.setEnabled(true);
						passwordField.setEnabled(true);
						btnIngresar.setEnabled(true);
						servidor = 2;
						
					}
					
						
					
						}
					}
			}
				});
		miPanel.add(rbYahoo);
		
		rbOulook = new JRadioButton("OutLook");
		rbOulook.setForeground(Color.LIGHT_GRAY);
		rbOulook.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbOulook.setBackground(Color.DARK_GRAY);
		rbOulook.setBounds(334, 219, 85, 23);
		rbOulook.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
				if(conexion)
				{
				if (Mouse_evt.getClickCount() == 1) {

					if(rbOulook.isSelected()== true )
					{
						rbGmail.setSelected(false);
						rbYahoo.setSelected(false);
						rdHotmail.setSelected(false);
						rbLive.setSelected(false);
						txtCorreo.setEnabled(false);
						passwordField.setEnabled(false);
						btnIngresar.setEnabled(false);
						
					}
					else
					{
						rbGmail.setSelected(false);
						rbYahoo.setSelected(false);
						rdHotmail.setSelected(false);
						rbLive.setSelected(false);
						txtCorreo.setEnabled(true);
						passwordField.setEnabled(true);
						btnIngresar.setEnabled(true);
						servidor = 1;
					}
					
						
					
						}
					}
			}
				});
		miPanel.add(rbOulook);
		
		btnActualizarConexionA = new JButton("Actualizar Conexion a Internet");
		btnActualizarConexionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				verificarConexion();
				
			}
		});
		btnActualizarConexionA.setBackground(Color.DARK_GRAY);
		btnActualizarConexionA.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnActualizarConexionA.setForeground(Color.WHITE);
		btnActualizarConexionA.setBounds(57, 427, 221, 23);
		miPanel.add(btnActualizarConexionA);
		
		lblConexion = new JLabel("");
		lblConexion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConexion.setForeground(Color.WHITE);
		lblConexion.setBounds(94, 457, 150, 14);
		miPanel.add(lblConexion);
		
		rbLive = new JRadioButton("Live");
		rbLive.setForeground(Color.LIGHT_GRAY);
		rbLive.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbLive.setEnabled(true);
		rbLive.setBackground(Color.DARK_GRAY);
		rbLive.setBounds(163, 245, 66, 23);
		rbLive.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
				if(conexion)
				{
				if (Mouse_evt.getClickCount() == 1) {

					if(rbLive.isSelected()== true )
					{
						rbGmail.setSelected(false);
						rbYahoo.setSelected(false);
						rdHotmail.setSelected(false);
						rbOulook.setSelected(false);
						txtCorreo.setEnabled(false);
						passwordField.setEnabled(false);
						btnIngresar.setEnabled(false);
						
					}
					else
					{
						rbGmail.setSelected(false);
						rbYahoo.setSelected(false);
						rdHotmail.setSelected(false);
						rbOulook.setSelected(false);
						txtCorreo.setEnabled(true);
						passwordField.setEnabled(true);
						btnIngresar.setEnabled(true);
						servidor = 1;
					}
					
						
					
						}
					}
			}
				});
		miPanel.add(rbLive);
		frame.setTitle("Acceso al programa");
		frame.setSize(485, 511);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	    frame.setResizable(false); 
	}
	
	public void verificarConexion()
	  {
	    ComprobarConexionInternet google = new ComprobarConexionInternet();
	    conexion = google.test();
	    System.out.println(conexion);
	    rbGmail.setEnabled(conexion);
	    rdHotmail.setEnabled(conexion);
	    rbYahoo.setEnabled(conexion);
	    rbOulook.setEnabled(conexion);
	   
	    if (conexion)
	    {
	      System.out.println("hay conexion");
	      lblConexion.setText("");
	     
	    }
	    else
	    {
	      System.out.println("No hay conexion");
	      lblConexion.setText("Sin conexión a internet");
	      txtCorreo.setEnabled(false);
			passwordField.setEnabled(false);
			btnIngresar.setEnabled(false);
	  
	    }
	  }
}

package Clases;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Toolkit;

public class EmailInterfaz {

	private JFrame frame;
	private URL fondo;
    private Image imagen1;
    private Container miFramePane;
    public static String path;
    private static  JTextArea textArea;
    public static String destinatario, asunto, mensaje;
    
    public static String adjunto = "";
    public static String[] adjuntos;
    public static String[] destinatarios;
    public static int servidor = 0;
    public static JButton btnEnviar;
    public static JLabel lblEnviando;
    private JLabel lblConexion;
    Validacion email= new Validacion();

    public JPanel miPanel=new JPanel(){
        

		public void paintComponent(Graphics g){
            g.drawImage(imagen1, 0, 0, getWidth(), getHeight(), null);
    
   }
	};
    private static  JTextField txtPara;
    public static JTextField txtAsunto;

	public EmailInterfaz() {

		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EmailInterfaz.class.getResource("/Imagenes/Apps-Email-Chat-Metro-icon.png")));
		lblEnviando = new JLabel("");
		lblEnviando.setVisible(false);
        fondo=frame.getClass().getResource("/Imagenes/Email.jpg");
		
		imagen1=new ImageIcon(fondo).getImage();
	    miFramePane = frame.getContentPane();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		miFramePane.add(miPanel);
		miPanel.setLayout(null);
		
		JLabel lblPara = new JLabel("Para: ");
		lblPara.setForeground(Color.WHITE);
		lblPara.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPara.setBounds(31, 103, 53, 25);
		miPanel.add(lblPara);
		
		txtPara = new JTextField();
		txtPara.setBackground(Color.DARK_GRAY);
		txtPara.setForeground(Color.WHITE);
		txtPara.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPara.setBounds(104, 97, 306, 31);
		miPanel.add(txtPara);
		txtPara.setColumns(10);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setForeground(Color.WHITE);
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAsunto.setBounds(31, 139, 63, 25);
		miPanel.add(lblAsunto);
		
		JLabel lblAdjunto = new JLabel("Archivo Adjunto:");
		lblAdjunto.setForeground(Color.WHITE);
		lblAdjunto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdjunto.setBounds(30, 175, 144, 25);
		miPanel.add(lblAdjunto);
		
		JButton btnAdjunto = new JButton("");
		btnAdjunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
			    fc.setFileSelectionMode(0);
			   
			    int respuesta = fc.showSaveDialog(frame);
			    if (respuesta == 0)
			    {
			      File archivoElegido = fc.getSelectedFile();
			      try
			      {
			       // adjunto = adjunto + archivoElegido.getCanonicalPath() + ",";
			        adjunto =  archivoElegido.getCanonicalPath();
			        adjunto = adjunto.replace("\\", "/");
			        System.out.println("Adjuntos" + adjunto);
			        actualizarAdjunto();
			      }
			      catch (IOException ex)
			      {
			       
			      }
			    }
			}
			
			
		});
		btnAdjunto.setIcon(new ImageIcon(EmailInterfaz.class.getResource("/Imagenes/paperclip-icon.png")));
		btnAdjunto.setBounds(173, 164, 43, 36);
		miPanel.add(btnAdjunto);
		
		 textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setBounds(31, 235, 422, 202);
		miPanel.add(textArea);
		
		btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   
			    destinatario = txtPara.getText().trim();
			    asunto = txtAsunto.getText();
			    mensaje = textArea.getText();
		
			    if (verificarDatos())
			    {
			      
			      
			      destinatarios = destinatario.split(",\\s*");
			      
			      notificarEnvio(true);
			      Hilo2 miHilo = new Hilo2();
			      miHilo.start();
			    
			      if(Hilo2.envioExito)
			      {
			    	  System.out.println();
			    	  vaciar(false);
			      }
			      
			      
			    }
			
				
			}
		});
		btnEnviar.setBackground(Color.DARK_GRAY);
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEnviar.setBounds(128, 448, 106, 23);
		miPanel.add(btnEnviar);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adjunto= "";
				frame.dispose();
				Login login = new Login();
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setBounds(244, 448, 89, 23);
		miPanel.add(btnSalir);
		
		JLabel lblMensaje = new JLabel("Mensaje:");
		lblMensaje.setForeground(Color.WHITE);
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMensaje.setBounds(31, 207, 89, 25);
		miPanel.add(lblMensaje);
		
		txtAsunto = new JTextField();
		txtAsunto.setForeground(Color.WHITE);
		txtAsunto.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtAsunto.setColumns(10);
		txtAsunto.setBackground(Color.DARK_GRAY);
		txtAsunto.setBounds(104, 133, 306, 31);
		miPanel.add(txtAsunto);
		
		 
		lblEnviando.setBackground(Color.DARK_GRAY);
		lblEnviando.setIcon(new ImageIcon(EmailInterfaz.class.getResource("/Imagenes/enviando-mensaje.gif")));
		lblEnviando.setBounds(244, 176, 200, 67);
		miPanel.add(lblEnviando);
		
		lblConexion = new JLabel("");
		lblConexion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConexion.setForeground(Color.RED);
		lblConexion.setBounds(87, 67, 156, 15);
		miPanel.add(lblConexion);
		
		JButton btnActualizarConexinA = new JButton("Actualizar Conexi\u00F3n a Internet");
		btnActualizarConexinA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				verificarConexion();
			}
		});
		btnActualizarConexinA.setBounds(253, 64, 200, 23);
		miPanel.add(btnActualizarConexinA);
		
		
		frame.setTitle("Enviar Email");
		frame.setSize(485, 511);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	    frame.setResizable(false); 
	}
	
	private boolean verificarDatos()
	  {
	    if (!email.validateEmail(destinatario))
	    {
	      JOptionPane.showMessageDialog(null, new Object[] { "El destinatario es incorrecto" }, "Faltan Datos", 1, null);
	      return false;
	    }
	    if (asunto.trim().equalsIgnoreCase(""))
	    {
	      JOptionPane.showMessageDialog(null, new Object[] { "Ingrese el asunto de su mensaje\n, evitará que se considere Spam" }, "Faltan Datos", 1, null);
	      return false;
	    }
	    if (mensaje.trim().equalsIgnoreCase(""))
	    {
	      JOptionPane.showMessageDialog(null, new Object[] { "Ingrese el contenido de su mensaje\n" }, "Faltan Datos", 1, null);
	      return false;
	    }
	    return true;
	  }
	
	 public static void notificarEnvio(boolean flag)
	  {
	    lblEnviando.setVisible(flag);
	    btnEnviar.setEnabled(!flag);
	  }
	 
	 public void actualizarAdjunto()
	  {
	    adjuntos = adjunto.split(",\\s*");
	   
	  }
	 
	 public void verificarConexion()
	  {
	    ComprobarConexionInternet google = new ComprobarConexionInternet();
	    boolean conexion = google.test();
	    


	    btnEnviar.setEnabled(conexion);
	    if (conexion)
	    {
	      lblConexion.setVisible(false);
	    }
	    else
	    {
	      lblConexion.setText("Sin conexion a internet");
	      lblConexion.setVisible(true);
	    }
	  }
	 
	 public static void vaciar(boolean bandera)
	 {
		 if(bandera)
		 {
			 
		 
		 txtPara.setText("");
		 txtAsunto.setText("");
		 textArea.setText("");
		 adjunto= "";
	
		 }
	 }
}

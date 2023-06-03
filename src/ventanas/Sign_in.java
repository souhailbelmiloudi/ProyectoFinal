package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase Sign_in que representa la ventana de inicio de sesion.
 */
public class Sign_in extends JFrame {

	private static final long serialVersionUID = -7354044334709280302L;
	private JTextField entradaUsu;
	int xMouse, yMouse;
	private JPasswordField entradaPasswd;
	private JLabel crearCuenta;
	Conexion conx = new Conexion();
	Connection con;
	public static String usuario;
	static String id;
	static String nombre;
	/**
	 * Metodo principal que crea una instancia de la ventana de inicio de sesion y la hace visible.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign_in frame = new Sign_in();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la clase Sign_in. 
	 */
	public Sign_in() {
		getContentPane().setEnabled(false);
		setUndecorated(true);

		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setType(Type.POPUP);
		getContentPane().setBackground(new Color(255, 127, 80));
		getContentPane().setLayout(null);

		JLabel Titulo_Signin = new JLabel("Iniciar sesión en SpicyApp");
		Titulo_Signin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Titulo_Signin.setBounds(10, 34, 469, 71);
		Titulo_Signin.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 24));
		Titulo_Signin.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(Titulo_Signin);

		JLabel labelUsuariooCorreo = new JLabel("Usuario o Correo Electrónico");
		labelUsuariooCorreo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelUsuariooCorreo.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
		labelUsuariooCorreo.setBounds(32, 122, 247, 39);
		getContentPane().add(labelUsuariooCorreo);

		JLabel labelpasswd = new JLabel("Contraseña");
		labelpasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelpasswd.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
		labelpasswd.setBounds(32, 208, 114, 39);
		getContentPane().add(labelpasswd);

		entradaUsu = new JTextField();
		entradaUsu.setBounds(30, 159, 413, 39);
		getContentPane().add(entradaUsu);
		entradaUsu.setColumns(10);

		entradaPasswd = new JPasswordField();
		entradaPasswd.setBounds(30, 245, 413, 39);
		getContentPane().add(entradaPasswd);

		JLabel RecuperarPasswd = new JLabel("¿Has olvidado tu contraseña?");
		RecuperarPasswd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				con = conx.conectar();
				String correo = JOptionPane.showInputDialog("Introduce tu correo electrónico");
				String consulta = conx.consulta("SELECT clave FROM jugadores where correo ='" + correo + "'", "clave");
				JOptionPane.showMessageDialog(null, "tu clave es : " + consulta);
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		RecuperarPasswd.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		RecuperarPasswd.setHorizontalAlignment(SwingConstants.CENTER);
		RecuperarPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		RecuperarPasswd.setForeground(Color.BLUE);
		RecuperarPasswd.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		RecuperarPasswd.setBounds(252, 209, 181, 39);
		getContentPane().add(RecuperarPasswd);

		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario = entradaUsu.getText();
				@SuppressWarnings("deprecation")
				String clave = entradaPasswd.getText();

				if (usuario.isEmpty() || clave.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe completar los datos");
					return;
				}

				try {
					con = conx.conectar();
					String consulta = "SELECT nombre, clave, correo FROM jugadores WHERE correo=? OR TO_CHAR(usuario)=?";
					PreparedStatement ps = con.prepareStatement(consulta);
					ps.setString(1, usuario);
					ps.setString(2, usuario);

					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						nombre = rs.getString("nombre");
						String claveDB = rs.getString("clave");
						String correoBd = rs.getString("correo");

						if (correoBd.equalsIgnoreCase(usuario) || claveDB.equalsIgnoreCase(clave)) {
							SpicyApp app = new SpicyApp();
							app.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
					}
					con.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

		btnSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignIn.setAutoscrolls(true);
		btnSignIn.setBackground(new Color(34, 139, 34));
		btnSignIn.setBounds(129, 308, 221, 45);
		getContentPane().add(btnSignIn);

		crearCuenta = new JLabel("¿Nuevo en SpicyApp? Crear una cuenta");
		crearCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Registrartee re = new Registrartee();
				re.setVisible(true);
				dispose();
			}
		});
		crearCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		crearCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		crearCuenta.setForeground(new Color(0, 0, 0));
		crearCuenta.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		crearCuenta.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		crearCuenta.setBounds(20, 365, 259, 39);
		getContentPane().add(crearCuenta);

		JLabel quizasLuegoBtn = new JLabel("¡quizás luego!");
		quizasLuegoBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quizasLuegoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SpicyApp app = new SpicyApp();
				app.setVisible(true);
				dispose();
			}
		});
		quizasLuegoBtn.setHorizontalAlignment(SwingConstants.CENTER);
		quizasLuegoBtn.setForeground(new Color(0, 0, 139));
		quizasLuegoBtn.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		quizasLuegoBtn.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		quizasLuegoBtn.setBounds(297, 365, 158, 39);
		getContentPane().add(quizasLuegoBtn);

		/** Panel para permitir arrastrar la ventana**/
		
		JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				yMouse = e.getY();
				xMouse = e.getX();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xMouse, y - yMouse);
			}
		});
		panel.setBounds(0, 0, 469, 28);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel SALIR = new JLabel("X");
		SALIR.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SALIR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		SALIR.setBounds(212, 0, 42, 28);
		SALIR.setForeground(SystemColor.desktop);
		SALIR.setFont(new Font("Verdana", Font.BOLD, 18));
		panel.add(SALIR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 427);
	}
	
	/**
	 * Metodo que obtiene el nombre de usuario.
	 *
	 * @return el nombre de usuario.
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * Metodo que obtiene el ID del usuario.
	 *
	 * @return el ID del usuario.
	 */
	public String getId() {
		return id;
	}
}

package tusPreguntas;


import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ventanas.TUS_PREGUNTAS;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * La clase BuscarPregunta representa una ventana que muestra una tabla con las preguntas buscadas.
 * Esta clase extiende JFrame.
 */
public class BuscarPregunta extends JFrame {

	private static final long serialVersionUID = 4621762969700703946L;
	private JPanel contentPane;
	
	int xMouse, yMouse;
	List<Map<String, Object>> prebu = TUS_PREGUNTAS.preguntas;

	/**
	 * Constructor de la clase BuscarPregunta. Inicializa la interfaz grafica de la ventana.
	 */
	public BuscarPregunta() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 473);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				yMouse = e.getY();
				xMouse = e.getX();
			}
		});
		
		// Para permitir arrastrar la ventana
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xMouse, y - yMouse);
			}
		});
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		scrollPane.setBackground(new Color(250, 235, 215));
		
		JLabel lblNewLabel = new JLabel("Buscar Preguntas");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Para cerrar la ventana
		JLabel lblCerrar = new JLabel("Cerrar");
		lblCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(new Font("Verdana", Font.PLAIN, 19));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(305, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(290))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(306, Short.MAX_VALUE)
					.addComponent(lblCerrar, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(289))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 741, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCerrar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		
		DefaultTableModel modelo = new DefaultTableModel();
		JTable table = new JTable(modelo);
		table.setBackground(new Color(250, 240, 230));
		table.setFont(new Font("Verdana", Font.BOLD, 12));
		table.setGridColor(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PREGUNTA", "ID"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(394);
		table.getColumnModel().getColumn(1).setPreferredWidth(184);
		
		// Agregar filas a la tabla utilizando los datos de la lista de preguntas
		for (Map<String, Object> filas : prebu) {
		    Object[] fila = new Object[2]; // Crear un arreglo de tamaño 2 para dos columnas

		    int columnIndex = 0; // Indice de la columna

		    for (String columna : filas.keySet()) {
		        Object valor = filas.get(columna);
		        fila[columnIndex] = valor; // Asignar el valor a la columna correspondiente
		        columnIndex++; // Incrementar el índice de la columna
		    }

		    ((DefaultTableModel) table.getModel()).addRow(fila);
		}

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}

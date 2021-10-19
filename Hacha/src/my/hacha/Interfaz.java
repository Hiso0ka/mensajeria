package my.hacha;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JFileChooser fileSelected;
	private JFileChooser fileSelectedXML;
	private JSpinner numSplits;
	private long size;
	private JTextField textField_2;
	private MessageDigest md5Digest;
	private String checksum;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Seleccionar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileSelected = new JFileChooser();
				fileSelected.showOpenDialog(btnNewButton_1);
				String archivoS = fileSelected.getSelectedFile().getName();
				String archNoEx = archivoS.substring(0, archivoS.lastIndexOf("."));
				textField.setText(archNoEx);
				
				size = fileSelected.getSelectedFile().length()/1024;
				textField_1.setText(Long.toString(size) + "KB");
			}
		});
		
		numSplits = new JSpinner();
		numSplits.setBounds(420, 87, 38, 20);
		contentPane.add(numSplits);
		btnNewButton_1.setBounds(543, 40, 103, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Archivo para particionar: ");
		lblNewLabel.setBounds(57, 44, 122, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(189, 41, 305, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tama\u00F1o: ");
		lblNewLabel_1.setBounds(133, 90, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(189, 87, 138, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Particionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XMLCreator.XMLGenerator(fileSelected.getSelectedFile(), size, (Integer)numSplits.getValue(), FileManagement.arreglarRuta(fileSelected.getSelectedFile()));
				XMLCreator.XMLperfile(fileSelected.getSelectedFile(), size, (Integer)numSplits.getValue(), FileManagement.arreglarRuta(fileSelected.getSelectedFile()));
				//, hashCode
				try {
					FileManagement.partitionFile((Integer)numSplits.getValue(), fileSelected.getSelectedFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(543, 86, 103, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Unir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				UnirFicheros.unirFicheros(fileSelectedXML.getSelectedFile(), FileManagement.arreglarRuta(fileSelectedXML.getSelectedFile()));
			}
		});
		btnNewButton_2.setBounds(276, 315, 103, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Mostrar HashCode");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String checksum;
			try {
				md5Digest = MessageDigest.getInstance("MD5");
				checksum = ObtenerHashCode.obtenerHC(md5Digest, fileSelected.getSelectedFile());
				textField_2.setText(checksum);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}
		});
		btnNewButton_3.setBounds(508, 146, 138, 23);
		contentPane.add(btnNewButton_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(189, 147, 202, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("N\u00BA de partes:");
		lblNewLabel_2.setBounds(336, 90, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("HashCode:");
		lblNewLabel_3.setBounds(126, 150, 53, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PARTICIONAR");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(265, 11, 104, 19);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("UNIR");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(287, 207, 70, 30);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Archivo para unir:");
		lblNewLabel_6.setBounds(83, 272, 96, 14);
		contentPane.add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(189, 269, 305, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Seleccionar XML");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileSelectedXML = new JFileChooser();
				fileSelectedXML.showOpenDialog(btnNewButton_4);
				String archivoS = fileSelectedXML.getSelectedFile().getName();
				String archNoEx = archivoS.substring(0, archivoS.lastIndexOf("."));
				textField_3.setText(archNoEx);		
			}
		});
		btnNewButton_4.setBounds(543, 263, 103, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_7 = new JLabel("Para unir escoja un archivo XML.");
		lblNewLabel_7.setBounds(83, 385, 208, 14);
		contentPane.add(lblNewLabel_7);
	}
}
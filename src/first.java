import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class first extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField orlogoTextFeild;
	private JTextField zarlagaTextFeild;
	private static int id;
	private static String type;
	private static String cash;
	private static Date date;
	private static String date_string;
	private static JLabel ashigDun;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					first frame = new first();
					// database-ees datag haruulna
					showData("Select * from sanhuu");
					tentsel();
					
					date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					date_string =  sdf.format(date);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//
	// ӨГӨГДЛИЙН САНГААС ДАТАГ ХУУЛЖ ХАРУУЛНА
	//
	private static void showData(String query)
	{
		try
		{
			Connection conn = getConnection();
			Statement st = conn.createStatement();

			// Student table-с датаг хуулна
			ResultSet rs = st.executeQuery(query);

			// table-ийн model-ийн дахин зааж өгсөнөөр өмнөх датануудын арилгана
			table.setModel(new DefaultTableModel(null, new String[] {"ID","type","cash","date"}));
			DefaultTableModel dt = (DefaultTableModel) table.getModel();

			dt.addRow(new Object[] {"ID","type","cash","date"});
			if(rs.next())
			{
				do
				{
					id =  rs.getInt("ID");
					type = rs.getString("type");
					cash = rs.getString("cash");
					date_string = rs.getString("date");


					// data table-д мөр нэмнэ
					dt.addRow(new Object[] {id,type,cash,date_string});
				}while(rs.next());
			}
			conn.close();
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	//
	//	DATABASE-ТЭЙ CONNECTION ХИЙНЭ
	//	$return: CONNECTION
	//
	private static Connection getConnection()
	{
		try {
			String dbFile = "C://Users//Ariunsanaa//Desktop//New folder//dadlaga//sanhuu.accdb";
			String dbUrl = "jdbc:ucanaccess://" + dbFile.trim();
			Connection conn = DriverManager.getConnection(dbUrl);
			return conn;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	//
	// МЭДЭЭЛЭЛ ОРУУЛАХ TEXTFIELD-ҮҮДИЙГ ХООСОН ЭСЭХИЙГ ШАЛГАНА
	// @return: true- АЛЬ НЭГ TEXTFIELD ХООСОН БОЛ
	// 			false- БҮХ TEXTFIELD-Д МЭДЭЭЛЛЭЭ ОРУУЛСАН БОЛ
	//
	private boolean isTxtEmpty()
	{
	
		if(orlogoTextFeild.getText().isEmpty() && zarlagaTextFeild.getText().isEmpty())
			return true;
		
		return false;
	}
	//
	// DATABASE-Д ДАТА ОРУУЛНА
	//
	private void insertData()
	{
		// мэдээлэл хоосон болон алдаа байгаа эсэхийг шалгана
		if(!isTxtEmpty())
		{
		
				try
				{
					Connection conn = getConnection();
					String query = "INSERT INTO sanhuu (type,cash,date) VALUES (?,?,?)";
					// Parameter addWithValue
					PreparedStatement st= conn.prepareStatement(query);
//					st.setInt(1, id);
					st.setString(1, type);
					st.setString(2, cash);
					st.setString(3, date_string);
		

					st.execute();
					JOptionPane.showMessageDialog(null, "Inserted succesfully");
					conn.close();

					showData("Select * from sanhuu"); // Table-дэх датануудыг дахин өгөгдлийн сангаас хуулж харуулна
					
				}
				catch(Exception ex)
				{
					//
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			
		}
		else
		{
			// TextField hooson baival medeelne
			JOptionPane.showMessageDialog(null, "Та орлого, зарлагаа оруулна уу.");
		}
	}
	//
	// DATABASE-ЭЭС ДАТА УСТГАНА
	//
	private void deleteData()
	{

			try
			{
				Connection conn = getConnection();
				String query = "DELETE FROM sanhuu WHERE ID = ?";

				PreparedStatement st= conn.prepareStatement(query);
				st.setInt(1, id);

				// үнэхээр устгах эсэхийг лавлана.Заавал шалга гэж багш хэлсэн.Тийм бол 0-г буцаана
				int result = JOptionPane.showConfirmDialog(null, "Та үнэхээр '" + id + "' id-тай бичвэрийг устгах уу?",
						"Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				if(result == 0)
				{
					st.execute();
					JOptionPane.showMessageDialog(null, "Амжилттай устгагдлаа");
					conn.close();
				
				}
				showData("Select * from sanhuu");
		
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
	

	}
	private static void tentsel() {
	
		int sum = 0;
		
		for(int i=1; i<table.getRowCount(); i++) {
			sum += Integer.parseInt((String) table.getValueAt(i, 2));
		}
		ashigDun.setText(sum + "₮");
		
	}
	private void reset() {
		orlogoTextFeild.setText("");
		zarlagaTextFeild.setText("");
	}
	/**
	 * Create the frame.
	 */
	public first() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(first.class.getResource("/com/sun/javafx/scene/web/skin/UnorderedListBullets_16x16_JFX.png")));
		setTitle("ENKHBOLD B180910819");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 417);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel garchig = new JLabel("\u0425\u0423\u0412\u0418\u0419\u041D \u0421\u0410\u041D\u0425VV\u0413\u042D\u042D \u0411V\u0420\u0422\u0413\u042D\u0425");
		garchig.setFont(new Font("Century Gothic", Font.BOLD, 15));
		garchig.setForeground(Color.WHITE);
		garchig.setBounds(160, 22, 358, 19);
		contentPane.add(garchig);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(46, 88, 472, 38);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel orlogoLabel = new JLabel("\u041E\u0440\u043B\u043E\u0433\u043E");
		orlogoLabel.setBounds(10, 11, 69, 14);
		panel.add(orlogoLabel);
		
		JButton orlogoButton = new JButton("\u041D\u044D\u043C\u044D\u0445");
		orlogoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "Орлого";
				cash = orlogoTextFeild.getText();
				
				insertData();
				reset();
				tentsel();
				
			}
		});
		orlogoButton.setBounds(373, 7, 89, 23);
		panel.add(orlogoButton);
		
		orlogoTextFeild = new JTextField();
		orlogoTextFeild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(c >= '0' && '9' >= c) {
					orlogoTextFeild.setEditable(true);
				}else {
					if(e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE)
						orlogoTextFeild.setEditable(true);
					else
						orlogoTextFeild.setEditable(false);
				}
				
			}
		});
		orlogoTextFeild.setBounds(89, 8, 275, 20);
		panel.add(orlogoTextFeild);
		orlogoTextFeild.setColumns(10);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				id = (int) table.getValueAt(table.getSelectedRow(), 0);
	
			}
		});
		table.setBounds(46, 186, 472, 127);
		contentPane.add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(46, 137, 472, 38);
		contentPane.add(panel_1);
		
		JLabel zarlagaLabel = new JLabel("\u0417\u0430\u0440\u043B\u0430\u0433\u0430");
		zarlagaLabel.setBounds(10, 11, 68, 14);
		panel_1.add(zarlagaLabel);
		
		JButton zarlagaButton = new JButton("\u041D\u044D\u043C\u044D\u0445");
		zarlagaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "Зарлага";
				cash = "-"+zarlagaTextFeild.getText();
				
				insertData();
				reset();
				tentsel();
			}
		});
		zarlagaButton.setBounds(373, 7, 89, 23);
		panel_1.add(zarlagaButton);
		
		zarlagaTextFeild = new JTextField();
		zarlagaTextFeild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(c >= '0' && '9' >= c) {
					zarlagaTextFeild.setEditable(true);
				}else {
					if(e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE)
						zarlagaTextFeild.setEditable(true);
					else
						zarlagaTextFeild.setEditable(false);
				}
			}
		});
		zarlagaTextFeild.setBounds(88, 8, 275, 20);
		panel_1.add(zarlagaTextFeild);
		zarlagaTextFeild.setColumns(10);
		
		JButton hasahButton = new JButton("\u041C\u044D\u0434\u044D\u044D\u043B\u043B\u0438\u0439\u0433 \u0445\u0430\u0441\u0430\u0445");
		hasahButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteData();
				tentsel();
			}
		});
		hasahButton.setBounds(368, 324, 150, 23);
		contentPane.add(hasahButton);
		
		JLabel tentsel = new JLabel("\u0422\u044D\u043D\u0446\u044D\u043B:");
		tentsel.setForeground(Color.WHITE);
		tentsel.setBounds(46, 63, 46, 14);
		contentPane.add(tentsel);
		
		ashigDun = new JLabel("1,000,000");
		ashigDun.setFont(new Font("Tahoma", Font.BOLD, 13));
		ashigDun.setForeground(new Color(0, 255, 255));
		ashigDun.setBounds(102, 62, 139, 14);
		contentPane.add(ashigDun);
	}
}

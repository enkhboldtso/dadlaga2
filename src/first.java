import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class first extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField orlogoTextFeild;
	private JTextField zarlagaTextFeild;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					first frame = new first();
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
		orlogoButton.setBounds(373, 7, 89, 23);
		panel.add(orlogoButton);
		
		orlogoTextFeild = new JTextField();
		orlogoTextFeild.setBounds(89, 8, 275, 20);
		panel.add(orlogoTextFeild);
		orlogoTextFeild.setColumns(10);
		
		table = new JTable();
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
		zarlagaButton.setBounds(373, 7, 89, 23);
		panel_1.add(zarlagaButton);
		
		zarlagaTextFeild = new JTextField();
		zarlagaTextFeild.setBounds(88, 8, 275, 20);
		panel_1.add(zarlagaTextFeild);
		zarlagaTextFeild.setColumns(10);
		
		JButton hasahButton = new JButton("\u041C\u044D\u0434\u044D\u044D\u043B\u043B\u0438\u0439\u0433 \u0445\u0430\u0441\u0430\u0445");
		hasahButton.setBounds(368, 324, 150, 23);
		contentPane.add(hasahButton);
		
		JLabel tentsel = new JLabel("\u0422\u044D\u043D\u0446\u044D\u043B:");
		tentsel.setForeground(Color.WHITE);
		tentsel.setBounds(46, 63, 46, 14);
		contentPane.add(tentsel);
		
		JLabel ashigDun = new JLabel("1,000,000");
		ashigDun.setFont(new Font("Tahoma", Font.BOLD, 13));
		ashigDun.setForeground(new Color(0, 255, 255));
		ashigDun.setBounds(102, 62, 139, 14);
		contentPane.add(ashigDun);
	}
}

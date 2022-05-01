import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(111, 65, 59, 20);
		contentPane.add(lblName);
		
		JTextPane name = new JTextPane();
		name.setBounds(189, 65, 107, 20);
		contentPane.add(name);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(111, 117, 68, 20);
		contentPane.add(lblPassword);
		
		JTextPane password = new JTextPane();
		password.setBounds(189, 117, 107, 20);
		contentPane.add(password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConnection database = new DatabaseConnection();
				try {
					ResultSet rs=database.getStatement().executeQuery("Select *from admin where name='"+name.getText()+"' AND password='"+password.getText()+"'");
					if(rs.next()){
						JOptionPane.showMessageDialog(new AdminLogin(),"Login succesful.");
						setVisible(false);
						new AdminPanel().setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(new AdminLogin(),"Username or password incorrect.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(175, 184, 91, 23);
		contentPane.add(btnLogin);
	}
}

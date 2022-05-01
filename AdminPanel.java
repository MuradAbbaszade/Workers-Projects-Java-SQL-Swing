import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminPanel extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField id;
	private JTextField salary;
	private JButton btnSearch;
	private JTextField searchID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	public AdminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add worker :");
		lblNewLabel.setBounds(29, 26, 82, 14);
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(230, 23, 98, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		id = new JTextField();
		id.setBounds(121, 23, 84, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(119, 54, 86, 20);
		contentPane.add(surname);
		surname.setColumns(10);
		
		salary = new JTextField();
		salary.setBounds(230, 54, 98, 20);
		contentPane.add(salary);
		salary.setColumns(10);
		
		JLabel lblWarning = new JLabel("");
		lblWarning.setBounds(29, 84, 221, 14);
		contentPane.add(lblWarning);
		
		JButton btnAddWorker = new JButton("Add Worker");
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConnection database = new DatabaseConnection();
				if(id.getText().trim().equals("")|name.getText().trim().equals("")|surname.getText().trim().equals("")|salary.getText().trim().equals("")){
					lblWarning.setBackground(Color.RED);;
					lblWarning.setText("All the field must be felling");
				}
				else{
					String query ="Insert into workers VALUES("+id.getText()+",'"+name.getText()+"','"+surname.getText()+"',"+salary.getText()+")";
					try {
						database.getStatement().executeUpdate(query);
						lblWarning.setBackground(Color.GREEN);
					lblWarning.setText("Worker added succesfuly.");
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
		});
		btnAddWorker.setBackground(Color.WHITE);
		btnAddWorker.setBounds(173, 107, 91, 23);
		contentPane.add(btnAddWorker);
		
		searchID = new JTextField();
		searchID.setBounds(130, 172, 86, 20);
		contentPane.add(searchID);
		searchID.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConnection database = new DatabaseConnection();
				if(searchID.getText().trim().equals("")){
					JOptionPane.showMessageDialog(new AdminPanel(),"Fill searchID field.");
				}
				else{
					String query = "Select *from workers Where id = "+searchID.getText();
					try {
						ResultSet rs = database.getStatement().executeQuery(query);
						if(rs.next()){
							JOptionPane.showMessageDialog(new AdminPanel(),"Name="+rs.getString("name")+",Surname="+rs.getString("surname")+",Salary="+rs.getInt("salary"));
						}
						else{
							JOptionPane.showMessageDialog(new AdminPanel(),"User not found.");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(29, 171, 91, 23);
		contentPane.add(btnSearch);
		

		

	}
}

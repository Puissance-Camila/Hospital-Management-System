import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class Edit 
{
	String name;
	String address;
	String phone;
	String age;
	String sex;
	String illness;
        
        JFrame editframe;
        JPanel formpanel;
        JPanel editpane;
                
	Edit()
	{
		JFrame editframe = new JFrame("Edit");
		editframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        editframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editframe.setLayout(null);
		
		editpane = new JPanel();
		editpane.setLayout(null);
		editpane.setBounds(5,5,900,200);
		
		final JTextField idfield = new JTextField("Enter ID");
		idfield.setBounds(500,50,150,30);
		JButton editbutton = new JButton("Edit");
		editbutton.setBounds(650,50,150,30);
		
		editpane.add(idfield);
		editpane.add(editbutton);
		
		editframe.add(editpane);
                
                
                    formpanel = new JPanel();
                    formpanel.setBounds(400,250,600,900);		
                    formpanel.setLayout(null);
                
		
		editbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                PreparedStatement pstmt = null;
                try {
                   Connection conn=DriverManager.getConnection(
                             "jdbc:ucanaccess://C:\\Users\\diabolicfeak\\Documents\\NetBeansProjects\\hms\\src\\Database\\Hospital.accdb");     
					
//		Connection conn=DriverManager.getConnection("jdbc:odbc:hospital");		
                   int a = Integer.parseInt(idfield.getText());
                  String query = "select * FROM patients WHERE id = ?";
                  pstmt = conn.prepareStatement(query); // create a statement
                  pstmt.setInt(1, a); // set input parameter 1                  
                  ResultSet rs = pstmt.executeQuery(); // execute insert statement
                    rs.next();
                    name = rs.getString("PName");
                    address = rs.getString("Address");
                    phone = rs.getString("PNumber");
                    age = rs.getString("Age");
                    sex = rs.getString("Sex");
                    illness = rs.getString("Illness");

                    editform(name, address, phone, age, sex, illness);
					
                } catch (Exception e) {
                  e.printStackTrace();
                } 
            }
        });
                
           editframe.add(formpanel);     
                
                    
		
		
		
		editframe.setVisible(true);
	}
        
        public void editform(String name, String address, String phone, String age, String sex, String illness)
        {
             // EDITFORM
                    

                    JTextField namefield = new JTextField(name);
                    namefield.setBounds(200,10,250,40);
                    formpanel.add(namefield);

                    JTextField addressfield = new JTextField(address);
                    addressfield.setBounds(200,70,250,40);
                    formpanel.add(addressfield);

                    JTextField phonefield = new JTextField(phone);
                    phonefield.setBounds(200,120,250,40);
                    formpanel.add(phonefield);

                    JTextField agefield = new JTextField(age);
                    agefield.setBounds(200,170,250,40);
                    formpanel.add(agefield);

                    JTextField sexfield = new JTextField(sex);
                    sexfield.setBounds(200,220,250,40);
                    formpanel.add(sexfield);

                    JTextField illnessfield = new JTextField(illness);
                    illnessfield.setBounds(200,270,250,40);
                    formpanel.add(illnessfield);

                    JButton formeditbutton = new JButton("Submit");
                    formeditbutton.setBounds(225,320,200,40);
                    formeditbutton.setVisible(true);
                    formpanel.add(formeditbutton);
                    
                    
                    editpane.setVisible(false);
                    formpanel.repaint();
        }
	
	public static void main(String ar[])
	{
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Edit();
                
            }});
		
	}
}
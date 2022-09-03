package dumpBackground2.gui.components.detailspanel;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import dumpBackground2.model.base.entity.Token;

public class LoginDetailPanel extends GenericDetailPanel<Token> {
	 public LoginDetailPanel() {
		    initComponents();

		 
		  }
	 
	 private void initComponents() {
		 usernameLabel = new JLabel();
		    username = new JTextField();
		    passwordLabel = new JLabel();
		    password = new JTextField();
		    username.setText("ectAJlZCfeb1AvhGgozgj7dkYl7gDVLa");
		    password.setText("m34XrqOjqDjz9HSx");
		    usernameLabel = new JLabel();
		    usernameLabel.setText("Login:");
		    passwordLabel.setText("Password:");
		    GroupLayout layout = new GroupLayout(this);
		    this.setLayout(layout);
		    layout.setHorizontalGroup(
		      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		      .addGroup(layout.createSequentialGroup()
		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		          .addComponent(usernameLabel)
		          .addComponent(passwordLabel))
		        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		          .addGroup(layout.createSequentialGroup()
		            .addComponent(username, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap())
		          .addComponent(password, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
		    );
		    layout.setVerticalGroup(
		      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		      .addGroup(layout.createSequentialGroup()
		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		          .addComponent(usernameLabel)
		          .addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		          .addComponent(passwordLabel)
		          .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
		    );

		   
	 }
	 
	 public String getUserName() {
		 return this.username.getText();
	 }
	 public String getPassword() {
		 return this.password.getText();
	 }
	 
	@Override
	public void refreshItem(Token item) {
		
		
	}
	  
	  private JLabel passwordLabel;
	  private JTextField password;
	  private JLabel usernameLabel;
	  private JTextField username;
	  
	  

}

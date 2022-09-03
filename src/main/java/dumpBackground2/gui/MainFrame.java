package dumpBackground2.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dumpBackground2.gui.commands.task.ShowManagerTask;
import dumpBackground2.gui.components.StateObserverMenuItem;
import dumpBackground2.gui.components.globalpanel.LoginPanel;
import dumpBackground2.gui.components.manager.AffiliationManager;


public class MainFrame extends JFrame {
	  private final AffiliationManager affiliationManger = new AffiliationManager();
	 
private final LoginPanel loginPanel = new LoginPanel();
	  
	  private JPanel mainPanel;
	private JMenu fileMenu;
	private JMenuBar mainMenuBar;
	private JMenuItem exitMenuItem;
	//private StateObserverMenuItem affiliationsManagerMenuItem;
	private JMenuItem affiliationsManagerMenuItem;
	private JMenuItem loginMenuItem;
	public MainFrame() {
		initComponents();
	}
	
	private void initComponents() {
		 mainPanel = new JPanel();
		fileMenu = new JMenu();
		mainMenuBar = new JMenuBar();
	    exitMenuItem = new JMenuItem();
	    loginMenuItem = new JMenuItem(); 
	  //  affiliationsManagerMenuItem = new StateObserverMenuItem();
	    
	    affiliationsManagerMenuItem = new JMenuItem();
		setTitle("Hello World Java Swing");

        // set frame site
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // center the JLabel
        JLabel lblText = new JLabel("Hello World!", SwingConstants.CENTER);

        // add JLabel to JFrame
        getContentPane().add(lblText);
        affiliationsManagerMenuItem.setText("Affiliations manager");
       // affiliationsManagerMenuItem.setEnabled(false);
        affiliationsManagerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              affiliationsManagerMenuItemActionPerformed(evt);
            }
          }); 
        
        loginMenuItem.setText("Login");
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitMenuItemActionPerformed(evt);
          }
        });
        
        loginMenuItem.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent evt) {
                 loginMenuItemActionPerformed(evt);
               }

        	
        });
        
        fileMenu.add(loginMenuItem);
        fileMenu.add(affiliationsManagerMenuItem);
        fileMenu.add(exitMenuItem);
        
        fileMenu.setText("File");
        mainMenuBar.add(fileMenu);

        setJMenuBar(mainMenuBar);

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         // .addComponent(toolBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            //.addComponent(toolBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
        );
        
        // display it
        pack();
        setVisible(true);
	}
	
	 private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
		    finish();
		  }//GEN-LAST:event_exitMenuItemActionPerformed

	 
	 private void affiliationsManagerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_affiliationsManagerMenuItemActionPerformed
		   (new ShowManagerTask(this.mainPanel, this.affiliationManger)).execute();
		  }//GEN-L
	 
	 
	 private void loginMenuItemActionPerformed(ActionEvent evt) {
		  (new ShowManagerTask(this.mainPanel, this.loginPanel)).execute();
	 }
	 private void finish() {

		    


		      dispose();
		      System.out.println("Finish.");
		      System.exit(0);
		    
		  }
}

package dumpBackground2.gui.components.globalpanel;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpBackground2.App;
import dumpBackground2.gui.commands.edit.update.UpdateAffiliationEdit;
import dumpBackground2.gui.commands.task.PerformKnowledgeBaseEditTask;
import dumpBackground2.gui.components.detailspanel.LoginDetailPanel;

public class LoginPanel extends JPanel {
	private static final Logger logger = LoggerFactory.getLogger(LoginPanel.class);
	  public LoginPanel() {
		    initComponents();
		  }
	  private void initComponents() {

			 //   hideAndShowAffiliationPanel = new scimat.gui.components.HideAndShowPanel();
			    affiliationInfoPanel = new JPanel();
			    affiliationDetailPanel = new LoginDetailPanel();
			    updateAffiliationButton = new javax.swing.JButton();

			 //   updateAffiliationButton = new JButton();
			  //  hideAndShowAuthorPanel = new scimat.gui.components.HideAndShowPanel();
			    authorInfoPanel = new JPanel();
			  //  affiliationSlaveAuthorsPanel = new scimat.gui.components.slavepanel.AffiliationSlaveAuthorsPanel();
			   // hideAndShowDocumentPanel = new scimat.gui.components.HideAndShowPanel();
			    documentInfoPanel = new JPanel();
			   // affiliationSlaveDocumentsPanel = new scimat.gui.components.slavepanel.AffiliationSlaveDocumentsPanel();

			 //   this.hideAndShowAffiliationPanel.setDescription("Affiliation info");
			 //   this.hideAndShowAffiliationPanel.setPanel(this.affiliationInfoPanel);

			    updateAffiliationButton.setText("Login");
			    updateAffiliationButton.addActionListener(new java.awt.event.ActionListener() {
			      public void actionPerformed(java.awt.event.ActionEvent evt) {
			        updateAffiliationButtonActionPerformed(evt);
			      }
			    });
		
			    GroupLayout affiliationInfoPanelLayout = new GroupLayout(affiliationInfoPanel);
			    affiliationInfoPanel.setLayout(affiliationInfoPanelLayout);
			    affiliationInfoPanelLayout.setHorizontalGroup(
			      affiliationInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			      .addComponent(affiliationDetailPanel, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
			      .addGroup(GroupLayout.Alignment.LEADING, affiliationInfoPanelLayout.createSequentialGroup()
			        .addContainerGap().addContainerGap().addComponent(updateAffiliationButton)
			        )
			    );
			    affiliationInfoPanelLayout.setVerticalGroup(
			      affiliationInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			      .addGroup(affiliationInfoPanelLayout.createSequentialGroup()
			        .addContainerGap()
			        .addComponent(affiliationDetailPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap().addComponent(updateAffiliationButton)
			       )
			    );

			    //this.hideAndShowAuthorPanel.setDescription("Authors info");
			    //this.hideAndShowAuthorPanel.setPanel(this.authorInfoPanel);

			    GroupLayout authorInfoPanelLayout = new GroupLayout(authorInfoPanel);
			    authorInfoPanel.setLayout(authorInfoPanelLayout);
			    authorInfoPanelLayout.setHorizontalGroup(
			      authorInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			      //.addComponent(affiliationSlaveAuthorsPanel, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
			    );
			    authorInfoPanelLayout.setVerticalGroup(
			      authorInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			     // .addComponent(affiliationSlaveAuthorsPanel, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
			    );

			 //   this.hideAndShowDocumentPanel.setDescription("Documents info");
			 //   this.hideAndShowDocumentPanel.setPanel(this.documentInfoPanel);

			    GroupLayout documentInfoPanelLayout = new GroupLayout(documentInfoPanel);
			    documentInfoPanel.setLayout(documentInfoPanelLayout);
			    documentInfoPanelLayout.setHorizontalGroup(
			      documentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			     // .addComponent(affiliationSlaveDocumentsPanel, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
			    );
			    documentInfoPanelLayout.setVerticalGroup(
			      documentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			     // .addComponent(affiliationSlaveDocumentsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			    );

			    GroupLayout layout = new GroupLayout(this);
			    this.setLayout(layout);
			    layout.setHorizontalGroup(
			      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
			        .addContainerGap()
			        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			          .addComponent(documentInfoPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			          .addComponent(authorInfoPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			          .addComponent(affiliationInfoPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			         // .addComponent(hideAndShowAffiliationPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
			         // .addComponent(hideAndShowAuthorPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
			         // .addComponent(hideAndShowDocumentPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
			          )
			        .addContainerGap())
			    );
			    layout.setVerticalGroup(
			      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			      .addGroup(layout.createSequentialGroup()
			      //  .addComponent(hideAndShowAffiliationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addComponent(affiliationInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			      //  .addComponent(hideAndShowAuthorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addComponent(authorInfoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			       // .addComponent(hideAndShowDocumentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addComponent(documentInfoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			        .addContainerGap())
			    );
			  }// </editor-fold>//GEN-END:initComponents
	  
	  private void updateAffiliationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAffiliationButtonActionPerformed

		    PerformKnowledgeBaseEditTask task;

		    // If the name is empty
		  
		  logger.info("hello");
		  String username = this.affiliationDetailPanel.getUserName();
		  String password = this.affiliationDetailPanel.getPassword();

		      task = new PerformKnowledgeBaseEditTask(new UpdateAffiliationEdit(username,
		              password), this);

		      task.execute();
		  
		  }//GEN-LAST:event_updateAffiliationButtonActionPerformed

	  private LoginDetailPanel affiliationDetailPanel;
	  private JPanel affiliationInfoPanel;
	  //private scimat.gui.components.slavepanel.AffiliationSlaveAuthorsPanel affiliationSlaveAuthorsPanel;
	  //private scimat.gui.components.slavepanel.AffiliationSlaveDocumentsPanel affiliationSlaveDocumentsPanel;
	  private JPanel authorInfoPanel;
	  private JPanel documentInfoPanel;
	  private JButton updateAffiliationButton;

}

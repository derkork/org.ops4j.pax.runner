package org.ops4j.osgidea.runner.forms;/*
 * Copyright 2006 Niclas Hedhman.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import org.ops4j.osgidea.runner.config.ConfigBean;
import org.ops4j.osgidea.runner.repositories.RepositoryInfo;
import org.ops4j.osgidea.runner.repositories.RepositoryType;

public class AddRepositoryForm extends DialogWrapper
{

    private static final Logger m_logger = Logger.getLogger( AddRepositoryForm.class );

    private JPanel m_mainPanel;

    private JTextField m_url;
    private JRadioButton m_oscar;
    private JRadioButton m_eclipse;
    private JTextField m_name;
    private ConfigBean m_config;

    /**
     * @param parent parent component whicg is used to canculate heavy weight window ancestor.
     */
    public AddRepositoryForm( Component parent, ConfigBean config )
    {
        super( parent, true );
        m_config = config;
        if( m_logger.isDebugEnabled() )
        {
            m_logger.debug( "AddRepositoryForm(" + parent + ")" );
        }
        m_oscar.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
                m_eclipse.setSelected( false );
            }
        }
        );
        m_eclipse.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
                m_oscar.setSelected( false );
            }
        }
        );
    }

    /**
     * This method is invoked by default implementation of "OK" action. It just closes dialog
     * with <code>OK_EXIT_CODE</code>. This is convenient place to override functionality of "OK" action.
     * Note that the method does nothing if "OK" action isn't enabled.
     */
    protected void doOKAction()
    {
        if( m_logger.isDebugEnabled() )
        {
            m_logger.debug( "doOKAction()" );
        }
        String name = m_name.getText();
        RepositoryType type;
        if( m_eclipse.isSelected() )
        {
            type = RepositoryType.eclipse;
        }
        if( m_oscar.isSelected() )
        {
            type = RepositoryType.oscar;
        }
        else
        {
            throw new IllegalStateException( "None of the RadioButtons were selected." );
        }
        String url = m_url.getText();
        RepositoryInfo repoInfo = new RepositoryInfo( name, url, type );
        m_config.addRepository( repoInfo );
        super.doOKAction();
    }

    /**
     * Factory method. It creates panel with dialog options. Options panel is located at the
     * center of the dialog's content pane. The implementation can return <code>null</code>
     * value. In this case there will be no options panel.
     */
    protected JComponent createCenterPanel()
    {
        if( m_logger.isDebugEnabled() )
        {
            m_logger.debug( "createCenterPanel()" );
        }
        return m_mainPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection HardCodedStringLiteral
     */
    private void $$$setupUI$$$()
    {
        m_mainPanel = new JPanel();
        m_mainPanel.setLayout( new GridLayoutManager( 3, 2, new Insets( 0, 0, 0, 0 ), -1, -1 ) );
        final JPanel panel1 = new JPanel();
        panel1.setLayout( new GridLayoutManager( 2, 1, new Insets( 0, 0, 0, 0 ), -1, -1 ) );
        m_mainPanel.add( panel1, new GridConstraints( 2, 1, 1, 1, GridConstraints.ANCHOR_CENTER,
                                                      GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK
                                                                                 | GridConstraints.SIZEPOLICY_CAN_GROW,
                                                      GridConstraints.SIZEPOLICY_CAN_SHRINK
                                                      | GridConstraints.SIZEPOLICY_CAN_GROW,
                                                      null, null, null, 0, false
        )
        );
        panel1.setBorder( BorderFactory.createTitledBorder( "Repository Type" ) );
        m_oscar = new JRadioButton();
        m_oscar.setText( "Oscar Bundle Repository" );
        panel1.add( m_oscar, new GridConstraints( 0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                                  GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
                                                      .SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                                                                            null, null, 0, false
        )
        );
        m_eclipse = new JRadioButton();
        m_eclipse.setText( "Eclipse Update Center" );
        panel1.add( m_eclipse, new GridConstraints( 1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
                                                        .SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                                                                              null, null, 0, false
        )
        );
        m_url = new JTextField();
        m_mainPanel.add( m_url, new GridConstraints( 1, 1, 1, 1, GridConstraints.ANCHOR_WEST,
                                                     GridConstraints.FILL_HORIZONTAL,
                                                     GridConstraints.SIZEPOLICY_WANT_GROW,
                                                     GridConstraints.SIZEPOLICY_FIXED, null, new Dimension( 150, -1 ),
                                                     null, 0, false
        )
        );
        final JLabel label1 = new JLabel();
        label1.setText( "URL:" );
        m_mainPanel.add( label1, new GridConstraints( 1, 0, 1, 1, GridConstraints.ANCHOR_WEST,
                                                      GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
                                                      GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false
        )
        );
        final JLabel label2 = new JLabel();
        label2.setText( "Name:" );
        m_mainPanel.add( label2, new GridConstraints( 0, 0, 1, 1, GridConstraints.ANCHOR_WEST,
                                                      GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
                                                      GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false
        )
        );
        m_name = new JTextField();
        m_mainPanel.add( m_name, new GridConstraints( 0, 1, 1, 1, GridConstraints.ANCHOR_WEST,
                                                      GridConstraints.FILL_HORIZONTAL,
                                                      GridConstraints.SIZEPOLICY_WANT_GROW,
                                                      GridConstraints.SIZEPOLICY_FIXED, null, new Dimension( 150, -1 ),
                                                      null, 0, false
        )
        );
    }

    public JComponent $$$getRootComponent$$$()
    {
        return m_mainPanel;
    }
}
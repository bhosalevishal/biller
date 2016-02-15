package poc;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GridBagWindow extends JFrame {
	boolean inAnApplet = true;
	final boolean shouldFill = true;
	final boolean shouldWeightX = true;

	public GridBagWindow() {
		JButton button;
		JFrame frame = new JFrame("Title Bar Text");
		JPanel panel = new JPanel();
		frame.setSize(256, 256);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		JTextField fname, lname, jtadd, jtpla, jtdist, jtfam;
		JTextArea jtxta;
		JScrollPane js;
		JLabel jl1, jl2, jadd2, jplace, jdist, jfam, jsex, jstat, jadd1;
		JComboBox jcmb, jcmb1;
		Container contentPane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		// JPanel jp=new JPanel(new GridBagLayout());
		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		jl1 = new JLabel("First Name");
		c.ipadx = 2;
		c.ipady = 2;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);  // this statement added.
		gridbag.setConstraints(jl1, c);
		contentPane.add(jl1);

		fname = new JTextField(10);
		if (shouldWeightX) {
			c.weightx = 2;
		}
		c.gridx = 1;
		c.gridy = 0;
		gridbag.setConstraints(fname, c);
		contentPane.add(fname);

		jl2 = new JLabel("Last Name");
		c.gridx = 0;
		c.gridy = 1;
		gridbag.setConstraints(jl2, c);
		contentPane.add(jl2);

		lname = new JTextField(10);
		if (shouldWeightX) {
			c.weighty = 2;
		}
		c.gridx = 1;
		c.gridy = 1;
		gridbag.setConstraints(lname, c);
		contentPane.add(lname);

		jadd1 = new JLabel("add1"); // add1 label,tetxfield
		c.gridx = 0;
		c.gridy = 2;
		gridbag.setConstraints(jadd1, c);
		contentPane.add(jadd1);

		// jtxta = new JLabel(10,20);
		c.gridx = 1;
		c.gridy = 2;
		jtxta = new JTextArea(250, 450);
		jtxta.setEditable(true);
		/*
		 * js=new JScrollPane(jtxta); gridbag.setConstraints(js,c);
		 * contentPane.add(js);
		 */
		js = new JScrollPane(jtxta);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		js.setPreferredSize(new Dimension(150, 100));
		/*
		 * js.setBorder( BorderFactory.createCompoundBorder(
		 * BorderFactory.createCompoundBorder(
		 * BorderFactory.createTitledBorder(""),
		 * BorderFactory.createEmptyBorder(5,5,5,5)), js.getBorder()));
		 */
		gridbag.setConstraints(js, c);
		contentPane.add(js);

		jplace = new JLabel("Place"); // add2 label,tetxfield
		c.gridx = 0;
		c.gridy = 3;
		gridbag.setConstraints(jplace, c);
		contentPane.add(jplace);

		jtpla = new JTextField(10);
		if (shouldWeightX) {
			c.weighty = 2;
		}
		c.gridx = 1;
		c.gridy = 3;
		gridbag.setConstraints(jtpla, c);
		contentPane.add(jtpla);

		jdist = new JLabel("District"); // district label,tetxfield
		c.gridx = 0;
		c.gridy = 4;
		gridbag.setConstraints(jdist, c);
		contentPane.add(jdist);

		jtdist = new JTextField(10);
		if (shouldWeightX) {
			c.weighty = 2;
		}
		c.gridx = 1;
		c.gridy = 4;
		gridbag.setConstraints(jtdist, c);
		contentPane.add(jtdist);

		jfam = new JLabel("Family members"); // district label,tetxfield
		c.gridx = 0;
		c.gridy = 5;
		gridbag.setConstraints(jfam, c);
		contentPane.add(jfam);

		jtfam = new JTextField(10);
		if (shouldWeightX) {
			c.weighty = 2;
		}
		c.gridx = 1;
		c.gridy = 5;
		gridbag.setConstraints(jtfam, c);
		contentPane.add(jtfam);

		jsex = new JLabel("sex"); // sex label,tetxfield
		c.gridx = 0;
		c.gridy = 6;
		gridbag.setConstraints(jsex, c);
		contentPane.add(jsex);

		jcmb = new JComboBox();
		String[] itemStr = { "male", "female" };
		JMenu[] menus = new JMenu[2];
		menus[0] = new JMenu(itemStr[0]);
		menus[1] = new JMenu(itemStr[1]);
		jcmb.addItem(itemStr[0]);
		jcmb.addItem(itemStr[1]);
		if (shouldWeightX) {
			c.weighty = 2;
		}
		c.gridx = 1;
		c.gridy = 6;
		gridbag.setConstraints(jcmb, c);
		contentPane.add(jcmb);

		jstat = new JLabel("marital status"); // status label,tetxfield
		c.gridx = 0;
		c.gridy = 7;
		gridbag.setConstraints(jstat, c);
		contentPane.add(jstat);

		jcmb1 = new JComboBox();
		String[] itemStr1 = { "single", "married" };
		JMenu[] menu1 = new JMenu[2];
		menu1[0] = new JMenu(itemStr1[0]);
		menu1[1] = new JMenu(itemStr1[1]);
		jcmb1.addItem(itemStr1[0]);
		jcmb1.addItem(itemStr1[1]);
		if (shouldWeightX) {
			c.weighty = 2;
		}
		c.gridx = 1;
		c.gridy = 7;
		gridbag.setConstraints(jcmb1, c);
		contentPane.add(jcmb1);

		button = new JButton("Button 5");
		c.ipady = 0; // reset to default
		c.weighty = 2; // request any extra vertical space
		c.anchor = GridBagConstraints.SOUTH; // bottom of space
		c.insets = new Insets(5, 5, 5, 5); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 8; // third row
		gridbag.setConstraints(button, c);
		contentPane.add(button);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (inAnApplet) {
					dispose();
				} else {
					System.exit(0);
				}
			}
		});
		this.setVisible(true);
	}

	public static void main(String args[]) {
		GridBagWindow window = new GridBagWindow();
		window.inAnApplet = false;

		window.setTitle("GridBagLayout");
		window.pack();
		window.setVisible(true);
	}
}
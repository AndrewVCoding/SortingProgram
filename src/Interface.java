import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Now to see how changes show up
//But what about this?

public class Interface extends JFrame implements ChangeListener, ActionListener
{
	//need a button panel
	JPanel PANEL_BTNS = new JPanel();
	//need a list generator panel
	JPanel PANEL_LIST_GEN = new JPanel();
	//need a report panel
	JPanel PANEL_REPORT = new JPanel();

	//The six buttons to start each sorting algorithm
	JButton BTN_INSERTION = new JButton();
	JButton BTN_SELECTION = new JButton();
	JButton BTN_QUICK = new JButton();
	JButton BTN_MERGE = new JButton();
	JButton BTN_HEAP = new JButton();
	JButton BTN_RADIX = new JButton();

	//The radio buttons for generating each type of list
	JRadioButton RDO_IN = new JRadioButton();
	JRadioButton RDO_REVERSE = new JRadioButton();
	JRadioButton RDO_ALMOST = new JRadioButton();
	JRadioButton RDO_RANDOM = new JRadioButton();

	//Button to create the list
	JButton BTN_GENERATE_LIST = new JButton();

	//The slider to set the number of elements in the list
	JSlider SLIDER_LIST = new JSlider(JSlider.HORIZONTAL, 0, 10000, 100);

	//The text field showing the number of elements
	JTextField TXT_FLD_LIST_ELEMENTS = new JTextField();

	//The table displaying the results
	JTable TBL_RESULTS = new JTable();

	public Interface() {
		//Create the layout for the buttons and set their sizes
		createBtnPanel();
		createListPanel();

		//Add all of the panels to the window
		JPanel all = new JPanel();
		GroupLayout frameLayout = new GroupLayout(all);
		frameLayout.setAutoCreateGaps(true);
		frameLayout.setHorizontalGroup(frameLayout.createSequentialGroup().addComponent(PANEL_BTNS).addGroup(
				frameLayout.createParallelGroup().addComponent(PANEL_LIST_GEN)));
		frameLayout.setVerticalGroup(frameLayout.createSequentialGroup().addGroup(
				frameLayout.createParallelGroup().addComponent(PANEL_BTNS).addComponent(PANEL_LIST_GEN)));
		all.setLayout(frameLayout);

		add(all);
	}

	public void createBtnPanel() {
		BTN_INSERTION.setText("Insertion Sort");
		BTN_SELECTION.setText("Selection Sort");
		BTN_QUICK.setText("Quick Sort");
		BTN_MERGE.setText("Merge Sort");
		BTN_HEAP.setText("Heap Sort");
		BTN_RADIX.setText("Radix Sort");

		BTN_INSERTION.setVisible(true);
		BTN_SELECTION.setVisible(true);
		BTN_QUICK.setVisible(true);
		BTN_MERGE.setVisible(true);
		BTN_HEAP.setVisible(true);
		BTN_RADIX.setVisible(true);

		BTN_INSERTION.setPreferredSize(new Dimension(200, 80));

		GroupLayout buttonLayout = new GroupLayout(PANEL_BTNS);

		buttonLayout.linkSize(SwingConstants.VERTICAL, BTN_INSERTION, BTN_SELECTION, BTN_QUICK, BTN_MERGE, BTN_HEAP,
		                      BTN_RADIX);
		buttonLayout.linkSize(SwingConstants.HORIZONTAL, BTN_INSERTION, BTN_SELECTION, BTN_QUICK, BTN_MERGE, BTN_HEAP,
		                      BTN_RADIX);
		//buttonLayout.setAutoCreateContainerGaps(true);

		buttonLayout.setVerticalGroup(
				buttonLayout.createSequentialGroup().addComponent(BTN_INSERTION).addComponent(BTN_SELECTION)
						.addComponent(BTN_QUICK).addComponent(BTN_MERGE).addComponent(BTN_HEAP)
						.addComponent(BTN_RADIX));
		buttonLayout.setHorizontalGroup(
				buttonLayout.createParallelGroup().addComponent(BTN_INSERTION).addComponent(BTN_SELECTION)
						.addComponent(BTN_QUICK).addComponent(BTN_MERGE).addComponent(BTN_HEAP)
						.addComponent(BTN_RADIX));

		PANEL_BTNS.setLayout(buttonLayout);
		//PANEL_BTNS.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void createListPanel() {
		RDO_IN.setText("In Order");
		RDO_REVERSE.setText("Reverse Order");
		RDO_ALMOST.setText("Almost Order");
		RDO_RANDOM.setText("Random Order");
		TXT_FLD_LIST_ELEMENTS.setText("" + SLIDER_LIST.getValue());
		TXT_FLD_LIST_ELEMENTS.addActionListener(this);

		ButtonGroup rdoButtons = new ButtonGroup();
		rdoButtons.add(RDO_IN);
		rdoButtons.add(RDO_REVERSE);
		rdoButtons.add(RDO_ALMOST);
		rdoButtons.add(RDO_RANDOM);

		RDO_IN.setVisible(true);
		RDO_REVERSE.setVisible(true);
		RDO_ALMOST.setVisible(true);
		RDO_RANDOM.setVisible(true);
		SLIDER_LIST.setVisible(true);
		BTN_GENERATE_LIST.setVisible(true);
		TXT_FLD_LIST_ELEMENTS.setVisible(true);

		SLIDER_LIST.addChangeListener(this);

		//Panel for the slider and txt field
		JPanel sliderPanel = new JPanel();
		GroupLayout sliderLayout = new GroupLayout(sliderPanel);
		sliderLayout.setHorizontalGroup(
				sliderLayout.createSequentialGroup().addComponent(SLIDER_LIST).addComponent(TXT_FLD_LIST_ELEMENTS));
		sliderLayout.setVerticalGroup(
				sliderLayout.createParallelGroup().addComponent(SLIDER_LIST).addComponent(TXT_FLD_LIST_ELEMENTS));
		sliderPanel.setLayout(sliderLayout);

		JPanel rdoButtonPanel = new JPanel();

		/*Set up the radio button panel and its layout
        O in order     O reverse order
		O almost order O random order
		 */
		GroupLayout rdoLayout = new GroupLayout(rdoButtonPanel);
		rdoLayout.setHorizontalGroup(rdoLayout.createSequentialGroup().addGroup(
				rdoLayout.createParallelGroup().addComponent(RDO_IN).addComponent(RDO_ALMOST)).addGap(20, 200, 200)
				                             .addGroup(rdoLayout.createParallelGroup().addComponent(RDO_REVERSE)
						                                       .addComponent(RDO_RANDOM)));
		rdoLayout.setVerticalGroup(rdoLayout.createSequentialGroup().addGroup(
				rdoLayout.createParallelGroup().addComponent(RDO_IN).addComponent(RDO_REVERSE)).addGroup(
				rdoLayout.createParallelGroup().addComponent(RDO_ALMOST).addComponent(RDO_RANDOM)));

		rdoLayout.setAutoCreateGaps(true);

		rdoButtonPanel.setLayout(rdoLayout);

		GroupLayout listLayout = new GroupLayout(PANEL_LIST_GEN);

		//Set sizes of the objects
		listLayout.linkSize(SwingConstants.HORIZONTAL, rdoButtonPanel, sliderPanel, BTN_GENERATE_LIST);
		BTN_GENERATE_LIST.setText("Generate List");
		BTN_GENERATE_LIST.setMinimumSize(new Dimension(400, 40));
		BTN_GENERATE_LIST.setPreferredSize(new Dimension(400, 100));
		TXT_FLD_LIST_ELEMENTS.setMaximumSize(new Dimension(40, 20));

		listLayout.setAutoCreateContainerGaps(true);
		listLayout.setAutoCreateGaps(true);

		listLayout.setHorizontalGroup(
				listLayout.createParallelGroup().addComponent(rdoButtonPanel).addComponent(sliderPanel)
						.addComponent(BTN_GENERATE_LIST));
		listLayout.setVerticalGroup(
				listLayout.createSequentialGroup().addComponent(rdoButtonPanel).addComponent(sliderPanel)
						.addComponent(BTN_GENERATE_LIST));

		PANEL_LIST_GEN.setLayout(listLayout);

		PANEL_LIST_GEN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == SLIDER_LIST)
			TXT_FLD_LIST_ELEMENTS.setText("" + SLIDER_LIST.getValue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == TXT_FLD_LIST_ELEMENTS)
			SLIDER_LIST.setValue(Integer.parseInt(TXT_FLD_LIST_ELEMENTS.getText()));
	}
}

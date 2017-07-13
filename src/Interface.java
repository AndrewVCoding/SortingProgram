import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Now to see how changes show up
//But what about this?

class Interface extends JFrame implements ChangeListener, ActionListener
{
	private static final boolean debug = false;

	//need a button panel
	private final JPanel PANEL_BTNS = new JPanel();
	//need a list generator panel
	private final JPanel PANEL_LIST_GEN = new JPanel();
	//need a report panel
	JPanel PANEL_REPORT = new JPanel();

	//The six buttons to start each sorting algorithm
	private final JButton BTN_INSERTION = new JButton();
	private final JButton BTN_SELECTION = new JButton();
	private final JButton BTN_QUICK = new JButton();
	private final JButton BTN_MERGE = new JButton();
	private final JButton BTN_HEAP = new JButton();
	private final JButton BTN_RADIX = new JButton();

	//The radio buttons for generating each type of list
	private final JRadioButton RDO_IN = new JRadioButton();
	private final JRadioButton RDO_REVERSE = new JRadioButton();
	private final JRadioButton RDO_ALMOST = new JRadioButton();
	private final JRadioButton RDO_RANDOM = new JRadioButton();

	//Button to create the list
	private final JButton BTN_GENERATE_LIST = new JButton();

	//The slider to set the number of elements in the list
	private final JSlider SLIDER_LIST = new JSlider(JSlider.HORIZONTAL, 0, 10000, 5000);

	//The text field showing the number of elements
	private final JTextField TXT_FLD_LIST_ELEMENTS = new JTextField();

	//The labels and text fields displaying the results
	private JLabel LBL_LIST_SIZE = new JLabel("N:");
	private JLabel LBL_DATA_TYPE = new JLabel("DataType:");
	private JLabel LBL_SORT_ALG = new JLabel("Sort:");
	private JLabel LBL_COMPARISONS = new JLabel("Comparisons:");
	private JLabel LBL_MOVEMENTS = new JLabel("Movements:");
	private JLabel LBL_TOTAL_TIME = new JLabel("Total time:");
	private JLabel LBL_CURRENT_WINNER = new JLabel("Current Winner");
	private JTextField TXT_FLD_LIST_SIZE = new JTextField();
	private JTextField TXT_FLD_DATA_TYPE = new JTextField();
	private JTextField TXT_FLD_SORT_ALG = new JTextField();
	private JTextField TXT_FLD_COMPARISONS = new JTextField();
	private JTextField TXT_FLD_MOVEMENTS = new JTextField();
	private JTextField TXT_FLD_TOTAL_TIME = new JTextField();
	private JTextField TXT_FLD_CURRENT_WINNER = new JTextField();

	//To determine the winning algorithm for the current list, multiply the (movements*comparisons)/(number of
	// elements)
	private int WINNING_RATIO = 0;

	private int[] testList = Sort.randomOrder.clone();
	private String dataType = "Random Order";

	public Interface()
	{
		//Create the layout for the buttons and set their sizes
		createBtnPanel();
		createListPanel();
		createResultPanel();

		//Add all of the panels to the window
		JPanel all = new JPanel();
		GroupLayout frameLayout = new GroupLayout(all);
		frameLayout.setAutoCreateGaps(true);
		frameLayout.setAutoCreateContainerGaps(true);

		//Make a seperate panel for the list generation and report panels to link size with the button pannel
		JPanel rightPanel = new JPanel();
		GroupLayout rightGroup = new GroupLayout(rightPanel);
		rightGroup.setAutoCreateGaps(true);
		rightGroup.linkSize(SwingConstants.HORIZONTAL, PANEL_LIST_GEN, PANEL_REPORT);
		rightGroup.setVerticalGroup(
				rightGroup.createSequentialGroup().addComponent(PANEL_LIST_GEN).addComponent(PANEL_REPORT));
		rightGroup.setHorizontalGroup(
				rightGroup.createParallelGroup().addComponent(PANEL_LIST_GEN).addComponent(PANEL_REPORT));
		rightPanel.setLayout(rightGroup);


		frameLayout.linkSize(SwingConstants.VERTICAL, PANEL_BTNS, rightPanel);

		frameLayout.setHorizontalGroup(
				frameLayout.createSequentialGroup().addComponent(PANEL_BTNS).addComponent(rightPanel));
		frameLayout
				.setVerticalGroup(frameLayout.createParallelGroup().addComponent(PANEL_BTNS).addComponent(rightPanel));
		all.setLayout(frameLayout);

		add(all);
	}

	private void createBtnPanel()
	{
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

		BTN_INSERTION.addActionListener(this);
		BTN_SELECTION.addActionListener(this);
		BTN_QUICK.addActionListener(this);
		BTN_MERGE.addActionListener(this);
		BTN_HEAP.addActionListener(this);
		BTN_RADIX.addActionListener(this);

		BTN_INSERTION.setPreferredSize(new Dimension(200, 80));

		GroupLayout buttonLayout = new GroupLayout(PANEL_BTNS);

		buttonLayout.linkSize(SwingConstants.VERTICAL, BTN_INSERTION, BTN_SELECTION, BTN_QUICK, BTN_MERGE, BTN_HEAP,
		                      BTN_RADIX);
		buttonLayout.linkSize(SwingConstants.HORIZONTAL, BTN_INSERTION, BTN_SELECTION, BTN_QUICK, BTN_MERGE, BTN_HEAP,
		                      BTN_RADIX);

		buttonLayout.setVerticalGroup(
				buttonLayout.createSequentialGroup().addComponent(BTN_INSERTION).addComponent(BTN_SELECTION)
						.addComponent(BTN_QUICK).addComponent(BTN_MERGE).addComponent(BTN_HEAP)
						.addComponent(BTN_RADIX));
		buttonLayout.setHorizontalGroup(
				buttonLayout.createParallelGroup().addComponent(BTN_INSERTION).addComponent(BTN_SELECTION)
						.addComponent(BTN_QUICK).addComponent(BTN_MERGE).addComponent(BTN_HEAP)
						.addComponent(BTN_RADIX));

		buttonLayout.setAutoCreateContainerGaps(true);

		PANEL_BTNS.setLayout(buttonLayout);

		TitledBorder border = new TitledBorder("SORTING ALGORITHMS");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		PANEL_BTNS.setBorder(border);
	}

	private void createListPanel()
	{
		RDO_IN.setText("In Order");
		RDO_REVERSE.setText("Reverse Order");
		RDO_ALMOST.setText("Almost Order");
		RDO_RANDOM.setText("Random Order");
		TXT_FLD_LIST_ELEMENTS.setText("" + SLIDER_LIST.getValue());
		TXT_FLD_LIST_ELEMENTS.addActionListener(this);

		RDO_IN.addActionListener(this);
		RDO_REVERSE.addActionListener(this);
		RDO_ALMOST.addActionListener(this);
		RDO_RANDOM.addActionListener(this);

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
		O almost order O randomOrder order
		 */
		GroupLayout rdoLayout = new GroupLayout(rdoButtonPanel);
		rdoLayout.setHorizontalGroup(rdoLayout.createSequentialGroup().addGroup(
				rdoLayout.createParallelGroup().addComponent(RDO_IN).addComponent(RDO_ALMOST)).addGap(20, 100, 200)
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

		TitledBorder border = new TitledBorder("LIST GENERATOR");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		PANEL_LIST_GEN.setBorder(border);

		RDO_RANDOM.setSelected(true);
	}

	public void createResultPanel()
	{
		TXT_FLD_LIST_SIZE.setEditable(false);
		TXT_FLD_DATA_TYPE.setEditable(false);
		TXT_FLD_SORT_ALG.setEditable(false);
		TXT_FLD_COMPARISONS.setEditable(false);
		TXT_FLD_MOVEMENTS.setEditable(false);
		TXT_FLD_TOTAL_TIME.setEditable(false);
		TXT_FLD_CURRENT_WINNER.setEditable(false);

		TXT_FLD_LIST_SIZE.setVisible(true);
		TXT_FLD_DATA_TYPE.setVisible(true);
		TXT_FLD_SORT_ALG.setVisible(true);
		TXT_FLD_COMPARISONS.setVisible(true);
		TXT_FLD_MOVEMENTS.setVisible(true);
		TXT_FLD_TOTAL_TIME.setVisible(true);
		TXT_FLD_CURRENT_WINNER.setVisible(true);

		TXT_FLD_LIST_SIZE.setText("0");
		TXT_FLD_DATA_TYPE.setText("0");
		TXT_FLD_SORT_ALG.setText("0");
		TXT_FLD_COMPARISONS.setText("0");
		TXT_FLD_MOVEMENTS.setText("0");
		TXT_FLD_TOTAL_TIME.setText("0");
		TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");

		LBL_LIST_SIZE.setVisible(true);
		LBL_DATA_TYPE.setVisible(true);
		LBL_SORT_ALG.setVisible(true);
		LBL_COMPARISONS.setVisible(true);
		LBL_MOVEMENTS.setVisible(true);
		LBL_TOTAL_TIME.setVisible(true);
		LBL_CURRENT_WINNER.setVisible(true);

		//Resize components
		TXT_FLD_CURRENT_WINNER.setMinimumSize(new Dimension(400, 20));
		TXT_FLD_CURRENT_WINNER.setPreferredSize(new Dimension(400, 20));
		TXT_FLD_LIST_SIZE.setMaximumSize(new Dimension(100, 10));
		TXT_FLD_LIST_SIZE.setPreferredSize(new Dimension(100, 10));
		TXT_FLD_LIST_SIZE.setMinimumSize(new Dimension(95, 5));

		//Center the text for the current winner
		LBL_CURRENT_WINNER.setHorizontalAlignment(SwingConstants.CENTER);
		TXT_FLD_CURRENT_WINNER.setHorizontalAlignment(SwingConstants.CENTER);

		//An outer panel to hold the internal and report panel so that they can then be centered in PANEL_REPORT
		JPanel outerPanel = new JPanel();

		//Create the internal panel that holds the report
		// statistics****************************************************
		JPanel internalPanel = new JPanel();

		GroupLayout internalLayout = new GroupLayout(internalPanel);
		internalLayout.setAutoCreateContainerGaps(true);
		internalLayout.setAutoCreateGaps(true);

		internalLayout.linkSize(SwingConstants.HORIZONTAL, TXT_FLD_LIST_SIZE, TXT_FLD_DATA_TYPE, TXT_FLD_SORT_ALG,
		                        TXT_FLD_COMPARISONS, TXT_FLD_MOVEMENTS, TXT_FLD_TOTAL_TIME);
		internalLayout.linkSize(SwingConstants.VERTICAL, TXT_FLD_LIST_SIZE, TXT_FLD_DATA_TYPE, TXT_FLD_SORT_ALG,
		                        TXT_FLD_COMPARISONS, TXT_FLD_MOVEMENTS, TXT_FLD_TOTAL_TIME, LBL_LIST_SIZE,
		                        LBL_DATA_TYPE, LBL_SORT_ALG, LBL_COMPARISONS, LBL_MOVEMENTS, LBL_TOTAL_TIME);

		GroupLayout.ParallelGroup labelGroup = internalLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(LBL_LIST_SIZE).addComponent(LBL_DATA_TYPE).addComponent(LBL_SORT_ALG)
				.addComponent(LBL_COMPARISONS).addComponent(LBL_MOVEMENTS).addComponent(LBL_TOTAL_TIME);
		GroupLayout.ParallelGroup fieldGroup = internalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(TXT_FLD_LIST_SIZE).addComponent(TXT_FLD_DATA_TYPE).addComponent(TXT_FLD_SORT_ALG)
				.addComponent(TXT_FLD_COMPARISONS).addComponent(TXT_FLD_MOVEMENTS).addComponent(TXT_FLD_TOTAL_TIME);

		GroupLayout.ParallelGroup listSizeGroup = internalLayout.createParallelGroup().addComponent(LBL_LIST_SIZE)
				.addComponent(TXT_FLD_LIST_SIZE);
		GroupLayout.ParallelGroup dataTypeGroup = internalLayout.createParallelGroup().addComponent(LBL_DATA_TYPE)
				.addComponent(TXT_FLD_DATA_TYPE);
		GroupLayout.ParallelGroup sortAlgGroup = internalLayout.createParallelGroup().addComponent(LBL_SORT_ALG)
				.addComponent(TXT_FLD_SORT_ALG);
		GroupLayout.ParallelGroup comparisonsGroup = internalLayout.createParallelGroup().addComponent(LBL_COMPARISONS)
				.addComponent(TXT_FLD_COMPARISONS);
		GroupLayout.ParallelGroup movementsGroup = internalLayout.createParallelGroup().addComponent(LBL_MOVEMENTS)
				.addComponent(TXT_FLD_MOVEMENTS);
		GroupLayout.ParallelGroup totalTimeGroup = internalLayout.createParallelGroup().addComponent(LBL_TOTAL_TIME)
				.addComponent(TXT_FLD_TOTAL_TIME);

		internalLayout
				.setHorizontalGroup(internalLayout.createSequentialGroup().addGroup(labelGroup).addGroup(fieldGroup));
		internalLayout.setVerticalGroup(
				internalLayout.createSequentialGroup().addGroup(listSizeGroup).addGroup(dataTypeGroup)
						.addGroup(sortAlgGroup).addGroup(comparisonsGroup).addGroup(movementsGroup)
						.addGroup(totalTimeGroup));

		internalPanel.setLayout(internalLayout);
		//**************************************************************************************************************

		GroupLayout reportLayout = new GroupLayout(outerPanel);
		reportLayout.linkSize(SwingConstants.HORIZONTAL, internalPanel, LBL_CURRENT_WINNER, TXT_FLD_CURRENT_WINNER);
		reportLayout.setVerticalGroup(
				reportLayout.createSequentialGroup().addComponent(internalPanel).addComponent(LBL_CURRENT_WINNER)
						.addComponent(TXT_FLD_CURRENT_WINNER));
		reportLayout.setHorizontalGroup(
				reportLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(internalPanel)
						.addComponent(LBL_CURRENT_WINNER).addComponent(TXT_FLD_CURRENT_WINNER));
		outerPanel.setLayout(reportLayout);

		PANEL_REPORT.add(outerPanel);
		TitledBorder border = new TitledBorder("REPORT");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		PANEL_REPORT.setBorder(border);
	}

	@Override public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == SLIDER_LIST)
		{
			TXT_FLD_LIST_ELEMENTS.setText("" + SLIDER_LIST.getValue());
			WINNING_RATIO = 0;
			TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");
		}
	}

	@Override public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == TXT_FLD_LIST_ELEMENTS)
		{
			SLIDER_LIST.setValue(Integer.parseInt(TXT_FLD_LIST_ELEMENTS.getText()));
			WINNING_RATIO = 0;
			TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");
		}

		if(e.getSource() == RDO_RANDOM)
		{
			dataType = "Random Order";
			testList = Sort.randomOrder.clone();
			WINNING_RATIO = 0;
			TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");
		}
		if(e.getSource() == RDO_IN)
		{
			dataType = "In Order";
			testList = Sort.inOrder.clone();
			WINNING_RATIO = 0;
			TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");
		}
		if(e.getSource() == RDO_REVERSE)
		{
			dataType = "Reverse Order";
			testList = Sort.reverseOrder.clone();
			WINNING_RATIO = 0;
			TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");
		}
		if(e.getSource() == RDO_ALMOST)
		{
			dataType = "Almost Order";
			testList = Sort.almostOrder.clone();
			WINNING_RATIO = 0;
			TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");
		}

		if(e.getSource() == BTN_SELECTION)
			displayResults(Sort.selectionSort(testList, dataType));
		if(e.getSource() == BTN_INSERTION)
			displayResults(Sort.insertionSort(testList, dataType));
		if(e.getSource() == BTN_QUICK)
			displayResults(Sort.quickSort(testList, dataType));
		if(e.getSource() == BTN_MERGE)
			displayResults(Sort.mergeSort(testList, dataType));
		if(e.getSource() == BTN_HEAP)
			displayResults(Sort.heapSort(testList, dataType));
		if(e.getSource() == BTN_RADIX)
			displayResults(Sort.radixSort(testList, dataType));
	}

	public void displayResults(String[] results)
	{
		TXT_FLD_LIST_SIZE.setText(results[0]);
		TXT_FLD_DATA_TYPE.setText(results[1]);
		TXT_FLD_SORT_ALG.setText(results[2]);
		TXT_FLD_COMPARISONS.setText(results[3]);
		TXT_FLD_MOVEMENTS.setText(results[4]);
		TXT_FLD_TOTAL_TIME.setText(results[5]);

		//If the ratio is better than the current winning ratio, or if there is no winning ratio yet, set this
		// algorithm as the winning one.
		int tempRatio = (Integer.parseInt(results[3]) * Integer.parseInt(results[4])) / Integer.parseInt(results[0]);

		if(debug)
			System.out.println("Winning Ratio: " + WINNING_RATIO + "\nCurrent Ratio: " + tempRatio + " = (" +
			                   Integer.parseInt(results[3]) + " * " + Integer.parseInt(results[4]) + ") / " +
			                   Integer.parseInt(results[0]));

		if(tempRatio < WINNING_RATIO || WINNING_RATIO == 0)
		{
			WINNING_RATIO = tempRatio;
			TXT_FLD_CURRENT_WINNER.setText(results[2]);
		}

		//Reset testList to avoid sorting an inorder list on new sorts
		if(RDO_IN.isSelected())
		{
			testList = Sort.inOrder.clone();
			if(debug)
			{
				System.out.println("Resetting testList to inOrder: ");
				for(int i : Sort.inOrder)
					System.out.print(" " + i);
			}
		}
		if(RDO_REVERSE.isSelected())
		{
			testList = Sort.reverseOrder.clone();
			if(debug)
			{
				System.out.println("Resetting testList to reverseOrder: ");
				for(int i : Sort.reverseOrder)
					System.out.print(" " + i);
			}
		}
		if(RDO_ALMOST.isSelected())
		{
			testList = Sort.almostOrder.clone();
			if(debug)
			{
				System.out.println("Resetting testList to almostOrder: ");
				for(int i : Sort.almostOrder)
					System.out.print(" " + i);
			}
		}
		if(RDO_RANDOM.isSelected())
		{
			testList = Sort.randomOrder.clone();
			if(debug)
			{
				System.out.println("Resetting testList to randomOrder: ");
				for(int i : Sort.randomOrder)
					System.out.print(" " + i);
			}
		}
	}
}

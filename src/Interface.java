import com.sun.javafx.binding.StringFormatter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Now to see how changes show up
//But what about this?

/**
 * The interface for comparing six sorting algorithms: Insertion Sort, Selection Sort, Quick Sort, Merge Sort, Heap
 * Sort, and Radix Sort.
 * <p>
 * The comparison involves the number of movements(or array accesses) and comparisons made by each algorithm per
 * element sorted as well as the time to sort. Since movements are more computationally expensive then comparisons
 * they have a higher weight when computing ratio.
 */
class Interface extends JFrame implements ChangeListener, ActionListener
{
	private static final boolean debug = true;

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
	private final JSlider SLIDER_LIST = new JSlider(JSlider.HORIZONTAL, 2, 100000, 5000);

	//The text field showing the number of elements
	private final JTextField TXT_FLD_LIST_ELEMENTS = new JTextField();

	//The labels and text fields displaying the results
	private JLabel LBL_LIST_SIZE = new JLabel("N:");
	private JLabel LBL_DATA_TYPE = new JLabel("DataType:");
	private JLabel LBL_SORT_ALG = new JLabel("Sort:");
	private JLabel LBL_COMPARISONS = new JLabel("Comparisons:");
	private JLabel LBL_MOVEMENTS = new JLabel("Movements:");
	private JLabel LBL_TOTAL_TIME = new JLabel("Total time:");
	private JLabel LBL_RATIO = new JLabel("Ratio");
	private JLabel LBL_CURRENT_WINNER = new JLabel("Current Winner");
	private JTextField TXT_FLD_LIST_SIZE = new JTextField();
	private JTextField TXT_FLD_DATA_TYPE = new JTextField();
	private JTextField TXT_FLD_SORT_ALG = new JTextField();
	private JTextField TXT_FLD_COMPARISONS = new JTextField();
	private JTextField TXT_FLD_MOVEMENTS = new JTextField();
	private JTextField TXT_FLD_TOTAL_TIME = new JTextField();
	private JTextField TXT_FLD_RATIO = new JTextField();
	private JTextField TXT_FLD_CURRENT_WINNER = new JTextField();

	//To determine the winning algorithm for the current list, multiply the (movements*comparisons)/(number of
	// elements)
	private double IN_ORDER_RATIO = 0;
	private double RE_ORDER_RATIO = 0;
	private double AL_ORDER_RATIO = 0;
	private double RA_ORDER_RATIO = 0;

	private String IN_ORDER_WINNER = "No algorithms have been run on this list";
	private String RE_ORDER_WINNER = "No algorithms have been run on this list";
	private String AL_ORDER_WINNER = "No algorithms have been run on this list";
	private String RA_ORDER_WINNER = "No algorithms have been run on this list";

	private int[] IN_ORDER_LIST;
	private int[] RE_ORDER_LIST;
	private int[] AL_ORDER_LIST;
	private int[] RA_ORDER_LIST;

	private String DATA_TYPE;
	private int[] WORKING_LIST;
	private double WORKING_RATIO;

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

		//Generate an initial list of numbers of each data type
		IN_ORDER_LIST = GenerateList.inOrder(SLIDER_LIST.getValue());
		RE_ORDER_LIST = GenerateList.reverseOrder(SLIDER_LIST.getValue());
		AL_ORDER_LIST = GenerateList.almostOrder(SLIDER_LIST.getValue());
		RA_ORDER_LIST = GenerateList.randomOrder(SLIDER_LIST.getValue());

		WORKING_LIST = RA_ORDER_LIST.clone();
		WORKING_RATIO = RA_ORDER_RATIO;
		DATA_TYPE = "Random Order";
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
		BTN_GENERATE_LIST.addActionListener(this);

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
		TXT_FLD_LIST_ELEMENTS.setMaximumSize(new Dimension(60, 20));

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
		TXT_FLD_RATIO.setEditable(false);
		TXT_FLD_CURRENT_WINNER.setEditable(false);

		TXT_FLD_LIST_SIZE.setVisible(true);
		TXT_FLD_DATA_TYPE.setVisible(true);
		TXT_FLD_SORT_ALG.setVisible(true);
		TXT_FLD_COMPARISONS.setVisible(true);
		TXT_FLD_MOVEMENTS.setVisible(true);
		TXT_FLD_TOTAL_TIME.setVisible(true);
		TXT_FLD_RATIO.setVisible(true);
		TXT_FLD_CURRENT_WINNER.setVisible(true);

		TXT_FLD_LIST_SIZE.setText("0");
		TXT_FLD_DATA_TYPE.setText("0");
		TXT_FLD_SORT_ALG.setText("0");
		TXT_FLD_COMPARISONS.setText("0");
		TXT_FLD_MOVEMENTS.setText("0");
		TXT_FLD_TOTAL_TIME.setText("0");
		TXT_FLD_RATIO.setText("0");
		TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");

		LBL_LIST_SIZE.setVisible(true);
		LBL_DATA_TYPE.setVisible(true);
		LBL_SORT_ALG.setVisible(true);
		LBL_COMPARISONS.setVisible(true);
		LBL_MOVEMENTS.setVisible(true);
		LBL_TOTAL_TIME.setVisible(true);
		LBL_RATIO.setVisible(true);
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
		                        TXT_FLD_COMPARISONS, TXT_FLD_MOVEMENTS, TXT_FLD_TOTAL_TIME, TXT_FLD_RATIO);
		internalLayout.linkSize(SwingConstants.VERTICAL, TXT_FLD_LIST_SIZE, TXT_FLD_DATA_TYPE, TXT_FLD_SORT_ALG,
		                        TXT_FLD_COMPARISONS, TXT_FLD_MOVEMENTS, TXT_FLD_TOTAL_TIME, LBL_LIST_SIZE,
		                        LBL_DATA_TYPE, LBL_SORT_ALG, LBL_COMPARISONS, LBL_MOVEMENTS, LBL_TOTAL_TIME,
		                        LBL_RATIO);

		GroupLayout.ParallelGroup labelGroup = internalLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(LBL_LIST_SIZE).addComponent(LBL_DATA_TYPE).addComponent(LBL_SORT_ALG)
				.addComponent(LBL_COMPARISONS).addComponent(LBL_MOVEMENTS).addComponent(LBL_TOTAL_TIME)
				.addComponent(LBL_RATIO);
		GroupLayout.ParallelGroup fieldGroup = internalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(TXT_FLD_LIST_SIZE).addComponent(TXT_FLD_DATA_TYPE).addComponent(TXT_FLD_SORT_ALG)
				.addComponent(TXT_FLD_COMPARISONS).addComponent(TXT_FLD_MOVEMENTS).addComponent(TXT_FLD_TOTAL_TIME)
				.addComponent(TXT_FLD_RATIO);

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
		GroupLayout.ParallelGroup ratioGroup = internalLayout.createParallelGroup().addComponent(LBL_RATIO)
				.addComponent(TXT_FLD_RATIO);

		internalLayout
				.setHorizontalGroup(internalLayout.createSequentialGroup().addGroup(labelGroup).addGroup(fieldGroup));
		internalLayout.setVerticalGroup(
				internalLayout.createSequentialGroup().addGroup(listSizeGroup).addGroup(dataTypeGroup)
						.addGroup(sortAlgGroup).addGroup(comparisonsGroup).addGroup(movementsGroup)
						.addGroup(totalTimeGroup).addGroup(ratioGroup));

		internalPanel.setLayout(internalLayout);
		//*************************************************************************************************************


		//Create the winner panel**************************************************************************************
		JPanel winner = new JPanel();
		winner.add(TXT_FLD_CURRENT_WINNER);
		TitledBorder winnerBorder = new TitledBorder("WINNER");
		winnerBorder.setTitleJustification(TitledBorder.CENTER);
		winnerBorder.setTitlePosition(TitledBorder.TOP);
		winner.setBorder(winnerBorder);
		//*************************************************************************************************************

		GroupLayout reportLayout = new GroupLayout(outerPanel);
		reportLayout.linkSize(SwingConstants.HORIZONTAL, internalPanel, winner);
		reportLayout.setVerticalGroup(
				reportLayout.createSequentialGroup().addComponent(internalPanel).addComponent(winner));
		reportLayout.setHorizontalGroup(
				reportLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(internalPanel)
						.addComponent(winner));
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
			TXT_FLD_LIST_ELEMENTS.setText("" + SLIDER_LIST.getValue());
	}

	@Override public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == TXT_FLD_LIST_ELEMENTS)
			SLIDER_LIST.setValue(Integer.parseInt(TXT_FLD_LIST_ELEMENTS.getText()));

		//When a radio button is selected, show the current winning algorithm for that data type and the current list
		if(e.getSource() == RDO_IN)
		{
			DATA_TYPE = "In Order";
			TXT_FLD_CURRENT_WINNER.setText(IN_ORDER_WINNER);
			WORKING_LIST = IN_ORDER_LIST;
			WORKING_RATIO = IN_ORDER_RATIO;
		}
		if(e.getSource() == RDO_REVERSE)
		{
			DATA_TYPE = "Reverse Order";
			TXT_FLD_CURRENT_WINNER.setText(RE_ORDER_WINNER);
			WORKING_LIST = RE_ORDER_LIST;
			WORKING_RATIO = RE_ORDER_RATIO;
		}
		if(e.getSource() == RDO_ALMOST)
		{
			DATA_TYPE = "Almost Order";
			TXT_FLD_CURRENT_WINNER.setText(AL_ORDER_WINNER);
			WORKING_LIST = AL_ORDER_LIST;
			WORKING_RATIO = AL_ORDER_RATIO;
		}
		if(e.getSource() == RDO_RANDOM)
		{
			DATA_TYPE = "Random Order";
			TXT_FLD_CURRENT_WINNER.setText(RA_ORDER_WINNER);
			WORKING_LIST = RA_ORDER_LIST;
			WORKING_RATIO = RA_ORDER_RATIO;
		}

		//When the corresponding button is pressed, display the results of sorting the list with that algorithm
		if(e.getSource() == BTN_SELECTION)
			displayResults(Sort.selectionSort(WORKING_LIST.clone(), DATA_TYPE));
		if(e.getSource() == BTN_INSERTION)
			displayResults(Sort.insertionSort(WORKING_LIST.clone(), DATA_TYPE));
		if(e.getSource() == BTN_QUICK)
			displayResults(Sort.quickSort(WORKING_LIST.clone(), DATA_TYPE));
		if(e.getSource() == BTN_MERGE)
			displayResults(Sort.mergeSort(WORKING_LIST.clone(), DATA_TYPE));
		if(e.getSource() == BTN_HEAP)
			displayResults(Sort.heapSort(WORKING_LIST.clone(), DATA_TYPE));
		if(e.getSource() == BTN_RADIX)
			displayResults(Sort.betterRadixSort(WORKING_LIST.clone(), DATA_TYPE));

		//When a new list is generated, clear the results
		if(e.getSource() == BTN_GENERATE_LIST)
		{
			switch(DATA_TYPE)
			{
				case ("In Order"):
					IN_ORDER_LIST = GenerateList.inOrder(SLIDER_LIST.getValue());
					WORKING_LIST = IN_ORDER_LIST;
					IN_ORDER_RATIO = 0;
					IN_ORDER_WINNER = "No algorithm has been run on this list yet";
					TXT_FLD_CURRENT_WINNER.setText(IN_ORDER_WINNER);
					break;
				case ("Reverse Order"):
					RE_ORDER_LIST = GenerateList.reverseOrder(SLIDER_LIST.getValue());
					WORKING_LIST = RE_ORDER_LIST;
					RE_ORDER_RATIO = 0;
					RE_ORDER_WINNER = "No algorithm has been run on this list yet";
					TXT_FLD_CURRENT_WINNER.setText(RE_ORDER_WINNER);
					break;
				case ("Almost Order"):
					AL_ORDER_LIST = GenerateList.almostOrder(SLIDER_LIST.getValue());
					WORKING_LIST = AL_ORDER_LIST;
					AL_ORDER_RATIO = 0;
					AL_ORDER_WINNER = "No algorithm has been run on this list yet";
					TXT_FLD_CURRENT_WINNER.setText(AL_ORDER_WINNER);
					break;
				case ("Random Order"):
					RA_ORDER_LIST = GenerateList.randomOrder(SLIDER_LIST.getValue());
					WORKING_LIST = RA_ORDER_LIST;
					RA_ORDER_RATIO = 0;
					RA_ORDER_WINNER = "No algorithm has been run on this list yet";
					TXT_FLD_CURRENT_WINNER.setText(RA_ORDER_WINNER);
					break;
			}
			WORKING_RATIO = 0;
			TXT_FLD_CURRENT_WINNER.setText("No algorithm has been run on this list yet");
		}
	}

	/**
	 * Display the results returned from a sorting algorithm
	 *
	 * @param results
	 */
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
		double tempRatio = ratio(Double.parseDouble(results[4]), Double.parseDouble(results[3]),
		                         Double.parseDouble(results[5]), Double.parseDouble(results[0]), results[2]);

		TXT_FLD_RATIO.setText(String.format("%1$,.2f", tempRatio));

		if(debug)
			System.out.println("Working Ratio: " + WORKING_RATIO + "\nCurrent Ratio: " + tempRatio);

		if(tempRatio < WORKING_RATIO || WORKING_RATIO == 0)
		{
			if(debug)
				System.out.println("Ratio changed from " + WORKING_RATIO + " to " + tempRatio);
			WORKING_RATIO = tempRatio;
			switch(results[1])
			{
				case ("In Order"):
					IN_ORDER_WINNER = results[2];
					IN_ORDER_RATIO = tempRatio;
					break;
				case ("Reverse Order"):
					RE_ORDER_WINNER = results[2];
					RE_ORDER_RATIO = tempRatio;
					break;
				case ("Almost Order"):
					AL_ORDER_WINNER = results[2];
					AL_ORDER_RATIO = tempRatio;
					break;
				case ("Random Order"):
					RA_ORDER_WINNER = results[2];
					RA_ORDER_RATIO = tempRatio;
			}
			TXT_FLD_CURRENT_WINNER.setText(results[2] + " with ratio: " + String.format("%1$,.2f", WORKING_RATIO));
		}
	}

	/**
	 * Rate the performance of an algorithm based on number of movements and comparisons per element and the total time
	 *
	 * @param m
	 * @param c
	 * @param t
	 * @return
	 */
	private double ratio(Double m, Double c, Double t, Double n, String alg)
	{
		//First bring all values lower to be more manageable
		//Movements are given a higher weight than comparisons since they are more expensive
		double mNormal = (m) / (Math.abs(m) + (1 * n));
		double cNormal = (c) / (Math.abs(c) + (1 * n));
		//Time should also be an important factor with high weight
		double tNormal = (t) / (Math.abs(t) + (0.0001 * n));
		if(debug)
			System.out.println(
					"\nData Type: " + DATA_TYPE + "\nSorting Algorithm: " + alg + "\nm: " + m + " => " + mNormal +
					"\nc: " + c + " => " + "" + cNormal + "\nt: " + t + " =>" + " " + tNormal);

		return (2 * mNormal + cNormal + 3 * tNormal) / 6;
	}
}

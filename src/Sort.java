class Sort
{
	private static final boolean debug = true;

	//Test lists to use
	static final int[] inOrder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	static final int[] reverseOrder = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	static final int[] almostOrder = {1, 2, 3, 5, 4, 6, 7, 9, 10, 8, 11, 12, 13, 14, 15, 17, 16, 18, 19, 20};
	static final int[] randomOrder = {4, 5, 17, 12, 7, 15, 18, 10, 2, 9, 8, 1, 16, 14, 20, 19, 3, 13, 6, 11};

	static private int comparisons;
	static private int movements;
	static private long time;
	static private long start;
	static private long end;

	public static String[] selectionSort(int[] array, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			for(int i: array)
				System.out.print(i + " ");
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		for(int i = 0; i < array.length; i++)
		{
			comparisons++;
			int index = i;
			for(int j = i + 1; j < array.length; j++)
			{
				comparisons++;

				if(array[j] < array[index])
				{
					index = j;
					movements++;
				}
			}
			int smallerNumber = array[index];
			if(array[index] == array[i])
				movements++;
			array[index] = array[i];
			if(array[i] == smallerNumber)
				movements++;
			array[i] = smallerNumber;
		}
		end = System.currentTimeMillis();

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			for(int i : array)
				System.out.print(i + ", ");
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Selection Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}
		return new String[]{"" + array.length, dataType, "Selection Sort", "" + comparisons, "" + movements, "" + time};
	}

	public static String[] insertionSort(int[] array, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			for(int i: array)
				System.out.print(i + " ");
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		for(int i = 1; i < array.length; i++)
		{
			comparisons++;
			int temp = array[i];
			int j;
			for(j = i - 1; j >= 0 && temp < array[j]; j--)
			{
				comparisons++;
				movements++;
				array[j + 1] = array[j];
			}

			//Check if the two numbers are different to know whether it is making a movement
			if(array[j + 1] == temp)
				movements++;
			array[j + 1] = temp;
		}
		end = System.currentTimeMillis();

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			for(int i : array)
				System.out.print(i + ", ");
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Insertion Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + array.length, dataType, "Insertion Sort", "" + comparisons, "" + movements, "" + time};
	}

	public static String[] quickSort(int[] array, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			for(int i: array)
				System.out.print(i + " ");
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();

		//Start Algorithm here
		quickSortRecursive(0, array.length - 1, array);

		end = System.currentTimeMillis();

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			for(int i : array)
				System.out.print(i + ", ");
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Insertion Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + array.length, dataType, "Quick Sort", "" + comparisons, "" + movements, "" + time};
	}

	private static void quickSortRecursive(int lowerIndex, int higherIndex, int[] array)
	{
		int i = lowerIndex;
		int j = higherIndex;

		//calculate pivot number
		int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];

		//Split into two arrays
		while (i <= j)
		{
			//comparisons will increment once before the first while loop and once every time it iterates. this would
			// result in an extra measure of comparisons except that the last comparison, when the condition fails,
			// wouldn't get counted. So in the end it balances out.
			comparisons++;
			while (array[i] < pivot)
			{
				comparisons++;
				i++;
			}

			comparisons++;
			while(array[j] > pivot)
			{
				comparisons++;
				j--;
			}

			comparisons++;
			if(i <= j)
			{
				movements += 2;
				exchangeNumbers(i, j, array);
				i++;
				j--;
			}
		}
		if(lowerIndex < j)
			quickSortRecursive(lowerIndex, j, array);
		if(i < higherIndex)
			quickSortRecursive(i, higherIndex, array);
	}

	private static void exchangeNumbers(int i, int j, int[] array)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static String[] mergeSort(int[] array, String dataType)
	{
		int comparisons = 0;
		int movements = 0;
		long time;

		long start = System.currentTimeMillis();

		//Start Algorithm here

		long end = System.currentTimeMillis();

		time = end - start;

		return new String[]{"" + array.length, dataType, "Merge Sort", "" + comparisons, "" + movements, "" + time};
	}

	public static String[] heapSort(int[] array, String dataType)
	{
		int comparisons = 0;
		int movements = 0;
		long time;

		long start = System.currentTimeMillis();

		//Start Algorithm here

		long end = System.currentTimeMillis();

		time = end - start;

		return new String[]{"" + array.length, dataType, "Heap Sort", "" + comparisons, "" + movements, "" + time};
	}

	public static String[] radixSort(int[] array, String dataType)
	{
		int comparisons = 0;
		int movements = 0;
		long time;

		long start = System.currentTimeMillis();

		//Start Algorithm here

		long end = System.currentTimeMillis();

		time = end - start;

		return new String[]{"" + array.length, dataType, "Radix Sort", "" + comparisons, "" + movements, "" + time};
	}
}

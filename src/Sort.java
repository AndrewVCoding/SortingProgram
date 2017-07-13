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

	static private int[] array = randomOrder.clone();
	static private int length;
	static private int[] tempMergArr;

	/**
	 * Performs Selection Sort on the given array of integers
	 *
	 * @param array
	 * @param dataType
	 * @return
	 */
	public static String[] selectionSort(int[] array, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			printArray();
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		//Start the algorithm******************************************************************************************
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
			movements++;
			int smallerNumber = array[index];
			//Comparison doesn't count towards results because it is analytic rather than part of the algorithm itself
			if(array[index] == array[i])
				movements++;
			array[index] = array[i];
			if(array[i] == smallerNumber)
				movements++;
			array[i] = smallerNumber;
		}
		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			System.out.println("Sorted Array:");
			printArray();
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Selection Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}
		return new String[]{"" + array.length, dataType, "Selection Sort", "" + comparisons, "" + movements, "" +
		                                                                                                     time};
	}

	/**
	 * Performs Insertion Sort on the given array of integers
	 *
	 * @param array
	 * @param dataType
	 * @return
	 */
	public static String[] insertionSort(int[] array, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			printArray();
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		//Start the algorithm******************************************************************************************
		for(int i = 1; i < array.length; i++)
		{
			comparisons++;
			movements++;
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
		//Algorithm is finished****************************************************************************************

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			System.out.println("Sorted Array:");
			printArray();
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Insertion Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + array.length, dataType, "Insertion Sort", "" + comparisons, "" + movements, "" +
		                                                                                                     time};
	}

	/**
	 * Performs Quick Sort on the given array of integers
	 *
	 * @param arrayIn
	 * @param dataType
	 * @return
	 */
	public static String[] quickSort(int[] arrayIn, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			printArray();
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		//Start Algorithm here*****************************************************************************************
		array = arrayIn;
		quickSortRecursive(0, array.length - 1);

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			System.out.println("Sorted Array:");
			printArray();
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Quick Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + array.length, dataType, "Quick Sort", "" + comparisons, "" + movements, "" + time};
	}

	/**
	 * The recursive function of the Quick Sort Algorithm, separated to allow the report information to be reset
	 * before sorting.
	 *
	 * @param lowerIndex
	 * @param higherIndex
	 */
	private static void quickSortRecursive(int lowerIndex, int higherIndex)
	{
		int i = lowerIndex;
		int j = higherIndex;

		//calculate pivot number
		int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];

		//Split into two arrays
		while(i <= j)
		{
			//comparisons will increment once before the first while loop and once every time it iterates. this would
			// result in an extra measure of comparisons except that the last comparison, when the condition fails,
			// wouldn't get counted. So in the end it balances out.
			comparisons++;
			while(array[i] < pivot)
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
				exchangeNumbers(i, j);
				i++;
				j--;
			}
		}
		comparisons++;
		if(lowerIndex < j)
			quickSortRecursive(lowerIndex, j);
		comparisons++;
		if(i < higherIndex)
			quickSortRecursive(i, higherIndex);
	}

	/**
	 * Swaps the integers at the given indices in the array
	 *
	 * @param i
	 * @param j
	 */
	private static void exchangeNumbers(int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Performs Merge Sort on the given array
	 *
	 * @param arrayIn
	 * @param dataType
	 * @return
	 */
	public static String[] mergeSort(int[] arrayIn, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			printArray();
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();

		//Start Algorithm here*****************************************************************************************
		array = arrayIn;
		length = arrayIn.length;
		tempMergArr = new int[length];
		mergeSortRecursive(0, length - 1);

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			System.out.println("Sorted Array:");
			printArray();
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Merge Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + array.length, dataType, "Merge Sort", "" + comparisons, "" + movements, "" + time};
	}

	/**
	 * The recursive portion of the Merge Sort algorithm
	 *
	 * @param lowerIndex
	 * @param higherIndex
	 */
	private static void mergeSortRecursive(int lowerIndex, int higherIndex)
	{
		comparisons++;
		if(lowerIndex < higherIndex)
		{
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			mergeSortRecursive(lowerIndex, middle);
			mergeSortRecursive(middle + 1, higherIndex);
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	/**
	 * Merges the split arrays from mergeSortRecursive
	 *
	 * @param lowerIndex
	 * @param middle
	 * @param higherIndex
	 */
	private static void mergeParts(int lowerIndex, int middle, int higherIndex)
	{
		comparisons++;
		for(int i = lowerIndex; i <= higherIndex; i++)
		{
			comparisons++;
			movements++;
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		comparisons++;
		while(i <= middle && j <= higherIndex)
		{
			comparisons += 2;
			if(tempMergArr[i] <= tempMergArr[j])
			{
				movements++;
				array[k] = tempMergArr[i];
				i++;
			}
			else
			{
				movements++;
				array[k] = tempMergArr[j];
				j++;
			}
			k++;
		}
		comparisons++;
		while(i <= middle)
		{
			comparisons++;
			movements++;
			array[k] = tempMergArr[i];
			k++;
			i++;
		}
	}

	/**
	 * Performs Heap sort on the given array of integers
	 *
	 * @param arrayIn
	 * @param dataType
	 * @return
	 */
	public static String[] heapSort(int[] arrayIn, String dataType)
	{
		array = arrayIn;
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			printArray();
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();

		//Start Algorithm here*****************************************************************************************
		int n = array.length;

		//Build the heap
		for(int i = n / 2 - 1; i >= 0; i--)
		{
			if(debug)
				System.out.println("\nHeapifying\n     n: " + n + "\n     i: "+ i);
			heapify(n, i);
		}

		//Extract each element from the heap
		for (int i=n-1; i>=0; i--)
		{
			if(debug)
				System.out.println("Extracting Element: " + array[i]);
			// Move current root to end
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;

			// call max heapify on the reduced heap
			heapify(i, 0);
		}

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			System.out.println("Sorted Array:");
			printArray();
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Heap Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + array.length, dataType, "Heap Sort", "" + comparisons, "" + movements, "" + time};
	}

	/**
	 * Heapifys an array of integers
	 *
	 * @param n
	 * @param i
	 */
	private static void heapify(int n, int i)
	{
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if(debug)
		{
			printArray();
			System.out.println("\nlargest = " + i + "\nl = " + l + "\nr = " + r);
		}

		//Left child is larger than root
		comparisons++;
		if(l < n && array[l] > array[largest])
		{
			if(debug)
				System.out.println("left child is larger than root: " + array[1] + " > " + array[largest]);
			largest = l;
		}

		//Right child is larger than the largest so far
		comparisons++;
		if(r < n && array[r] > array[largest])
		{
			if(debug)
				System.out.println("right child is larger than root: " + array[r] + " > " + array[largest]);
			largest = r;
		}

		//Largest is not the root
		comparisons++;
		if(largest != i)
		{
			if(debug)
				System.out.println("Swapping " + array[i] + " with " + array[largest]);
			int swap = array[i];
			array[i] = array[largest];
			array[largest] = swap;
			movements += 2;

			heapify(n, largest);
		}
	}

	public static String[] radixSort(int[] arrayIn, String dataType)
	{
		if(debug)
		{
			System.out.println("\n\nInput Array: ");
			printArray();
		}

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		//Start Algorithm here*****************************************************************************************
		array = arrayIn;
		Bucket[] buckets = {new Bucket(),
		                    new Bucket(),
		                    new Bucket(),
		                    new Bucket(),
		                    new Bucket(),
		                    new Bucket(),
		                    new Bucket(),
		                    new Bucket(),
		                    new Bucket(),
		                    new Bucket()};

		//While any numbers in the array are not returning 0 from %10
		int iteration = 1;
		boolean keepSorting = true;
		while(keepSorting)
		{
			if(debug)
				System.out.println("Keep Sorting is still true");
			//Assume the array is sorted unless encountering an item that does not go into the first bucket
			keepSorting = false;

			//push each element in the array into the corresponding bucket
			for(int i : array)
			{
				movements++;
				int temp = i;
				int index = 0;

				//for whichever iteration this is, divide the temporary number by 10
				for(int j = 1; j <= iteration; j++)
				{
					comparisons++;
					index = temp % 10;
					temp = temp / 10;
				}

				/*If any index is encountered that is not 0 after this iteration, keep sorting. Otherwise it is
				already sorted.
				EXAMPLE: i = 354
				      1: 354%10 = 4    354/10 = 35
				      2:  35%10 = 5     35/10 = 3
				      3:   3%10 = 3      3/10 = 0
				      4:   0%10 = 0      0/10 = 0
				*/
				if(index != 0)
					keepSorting = true;

				//Create a new bucket with the value
				Bucket push = new Bucket();
				push.value = i;

				//push it into the corresponding index
				movements++;
				if(buckets[index].next != null)
					push.next = buckets[index].next;
				buckets[index].next = push;
			}

			//For each bucket, pop values into the array
			int index = 0;
			for(Bucket b : buckets)
			{
				while(b.next != null)
				{
					movements++;
					array[index] = b.next.value;
					if(b.next.next != null)
						b.next = b.next.next;
					else
						b.next = null;
					index++;
				}
			}

			if(debug)
			{
				System.out.println("Iteration " + iteration + ": ");
				printArray();
			}

			//Move on to the next iteration
			iteration++;
		}

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************

		time = end - start;

		if(debug)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			System.out.println("Sorted Array:");
			printArray();
			System.out.println();
			System.out.println("Length: " + array.length + "\nData Type: " + dataType + "\nAlgorithm: Radix Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + array.length, dataType, "Radix Sort", "" + comparisons, "" + movements, "" + time};
	}

	/**
	 * Prints the current working array for debugging purposes
	 */
	private static void printArray()
	{
		if(array != null)
			for(int i : array)
				System.out.print(" " + i);
	}

	private static class Bucket
	{
		Bucket next = null;
		int value;
	}
}

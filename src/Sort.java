public class Sort
{
	static private long comparisons;
	static private long movements;
	static private long time;
	static private long start;
	static private long end;

	static private int[] array;
	static private int length;
	static private int[] tempMergArr;

	static private boolean debug = false;

	/**
	 * Performs Insertion Sort on the given array of integers
	 *
	 * @param arrayIn
	 * @param dataType
	 * @return
	 */
	public static String[] insertionSort(int[] arrayIn, String dataType)
	{
		array = arrayIn;
		if(debug)
			printArray();

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
		if(debug)
			printArray();

		time = end - start;

		return new String[]{"" + array.length, dataType, "Insertion Sort", "" + comparisons, "" + movements, "" +
		                                                                                                     time};
	}

	/**
	 * Performs Selection Sort on the given array of integers
	 *
	 * @param arrayIn
	 * @param dataType
	 * @return
	 */
	public static String[] selectionSort(int[] arrayIn, String dataType)
	{
		array = arrayIn;
		if(debug)
			printArray();

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
		if(debug)
			printArray();

		time = end - start;

		return new String[]{"" + array.length, dataType, "Selection Sort", "" + comparisons, "" + movements, "" +
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
		array = arrayIn;
		if(debug)
			printArray();

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		//Start Algorithm here*****************************************************************************************
		quickSortRecursive(0, array.length - 1);

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************
		if(debug)
			printArray();

		time = end - start;

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
		array = arrayIn;
		if(debug)
			printArray();

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();

		//Start Algorithm here*****************************************************************************************
		length = array.length;
		tempMergArr = new int[length];
		mergeSortRecursive(0, length - 1);

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************
		if(debug)
			printArray();

		time = end - start;

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
			printArray();

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();

		//Start Algorithm here*****************************************************************************************
		int n = array.length;

		//Build the heap
		for(int i = n / 2 - 1; i >= 0; i--)
		{
			heapify(n, i);
		}

		//Extract each element from the heap
		for(int i = n - 1; i >= 0; i--)
		{
			// Move current root to end
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;

			// call max heapify on the reduced heap
			heapify(i, 0);
		}

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************
		if(debug)
			printArray();

		time = end - start;

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

		//Left child is larger than root
		comparisons++;
		if(l < n && array[l] > array[largest])
		{
			largest = l;
		}

		//Right child is larger than the largest so far
		comparisons++;
		if(r < n && array[r] > array[largest])
		{
			largest = r;
		}

		//Largest is not the root
		comparisons++;
		if(largest != i)
		{
			int swap = array[i];
			array[i] = array[largest];
			array[largest] = swap;
			movements += 2;

			heapify(n, largest);
		}
	}

	/**
	 * Performs Radix Sort on an array of integers
	 * @param arrayIn
	 * @param dataType
	 * @return
	 */
	public static String[] betterRadixSort(int[] arrayIn, String dataType)
	{
		array = arrayIn;
		if(debug)
			printArray();

		comparisons = 0;
		movements = 0;

		start = System.currentTimeMillis();
		//Start Algorithm here*****************************************************************************************

		int i;
		int m = array[0];
		int exp = 1;
		int n = array.length;
		int[] b = new int[n];

		//Find the largest value in the array
		for(i = 1; i < n; i++)
		{
			comparisons++;
			if(array[i] > m)
			{
				movements++;
				m = array[i];
			}
		}

		//While the exp does not exceed the number of digits in the largest number
		comparisons++;
		while(m / exp > 0)
		{
			comparisons++;
			int[] bucket = new int[10];

			//for every element in the array, add 1 to the number at the corresponding index of the bucket.
			for(i = 0; i < n; i++)
			{
				movements++;
				bucket[(array[i] / exp) % 10]++;
			}
			//for indices 1-9, set bucket element + the previous bucket element
			for(i = 1; i < 10; i++)
			{
				movements++;
				bucket[i] += bucket[i - 1];
			}
			//Starting at the last element of the array, take the comparison digit as the corresponding index of the
			// bucket. Use the element in the bucket as the index of the new array to put the original element in.
			for(i = n - 1; i >= 0; i--)
			{
				movements++;
				b[--bucket[(array[i] / exp) % 10]] = array[i];
			}
			for(i = 0; i < n; i++)
			{
				movements++;
				array[i] = b[i];
			}

			//move to the next digit
			exp *= 10;
		}

		end = System.currentTimeMillis();
		//Algorithm is finished****************************************************************************************
		if(debug)
			printArray();

		time = end - start;

		return new String[]{"" + array.length, dataType, "Radix Sort", "" + comparisons, "" + movements, "" + time};
	}

	/**
	 * Prints the current working array for debugging purposes
	 */
	private static void printArray()
	{
		System.out.println();
		if(array != null)
			for(int i : array)
				System.out.print(" " + i);
		System.out.println();
	}

	private static class Bucket
	{
		Bucket next = null;
		int value;
	}
}

/**
 * It was becoming clear that the amount of debug checks was slowing down some of the more complex sorting
 * algorithms, namely Heap and Radix. So in order to keep the time measurements accurate, the final version of the
 * program will use a class that has no debug conditions. Instead I will match the code here and use this class for
 * debugging the algorithms if any new bugs come up.
 */
public class SortDebugMode
{
	private static final boolean debug = true;

	//Test lists to use
	static final int[] inOrder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	static final int[] reverseOrder = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	static final int[] almostOrder = {1, 2, 3, 5, 4, 6, 7, 9, 10, 8, 11, 12, 13, 14, 15, 17, 16, 18, 19, 20};
	static final int[] randomOrder = {149, 310, 163, 68, 73, 213, 101, 461, 316, 64, 206, 434, 48, 302, 256, 381, 107, 202, 360, 303, 223, 442, 187, 318, 411, 407, 377, 500, 396, 293, 111, 313, 465, 322, 25, 54, 295, 47, 127, 471, 386, 334, 492, 104, 44, 220, 109, 4, 143, 122, 100, 210, 217, 215, 424, 325, 118, 5, 247, 172, 495, 355, 414, 479, 194, 117, 180, 493, 248, 139, 353, 145, 249, 380, 486, 132, 77, 412, 11, 474, 453, 472, 203, 95, 226, 193, 130, 483, 240, 32, 363, 272, 436, 390, 476, 99, 275, 238, 70, 332, 299, 191, 253, 478, 133, 348, 260, 229, 2, 131, 342, 447, 274, 323, 81, 167, 13, 234, 289, 282, 467, 320, 30, 311, 56, 239, 354, 468, 12, 271, 55, 212, 79, 112, 114, 288, 336, 16, 373, 214, 449, 83, 425, 9, 444, 42, 119, 359, 36, 285, 170, 231, 152, 267, 137, 135, 188, 259, 15, 341, 263, 156, 88, 388, 209, 452, 92, 230, 466, 418, 18, 186, 74, 241, 177, 39, 460, 35, 269, 350, 331, 376, 60, 190, 150, 23, 265, 268, 329, 17, 142, 484, 179, 498, 175, 138, 340, 480, 402, 400, 40, 395, 321, 371, 499, 445, 124, 227, 126, 158, 351, 168, 113, 1, 235, 14, 482, 270, 375, 361, 31, 364, 72, 422, 6, 401, 255, 196, 146, 409, 384, 315, 222, 103, 345, 21, 50, 200, 346, 184, 387, 356, 403, 284, 51, 236, 280, 205, 338, 421, 216, 134, 94, 29, 189, 458, 404, 427, 448, 419, 432, 41, 426, 252, 106, 438, 96, 281, 7, 357, 10, 305, 246, 463, 199, 451, 165, 431, 173, 147, 108, 382, 333, 86, 405, 49, 435, 470, 397, 53, 211, 89, 264, 429, 160, 24, 52, 261, 262, 369, 304, 294, 286, 312, 335, 123, 366, 85, 66, 413, 326, 464, 91, 347, 78, 159, 182, 153, 291, 87, 415, 58, 488, 245, 28, 317, 292, 258, 362, 385, 420, 290, 121, 367, 224, 251, 473, 455, 75, 378, 300, 22, 176, 192, 287, 446, 115, 277, 349, 257, 105, 37, 97, 164, 496, 242, 314, 485, 219, 254, 266, 352, 198, 344, 171, 120, 178, 197, 228, 383, 399, 475, 19, 433, 298, 46, 410, 45, 225, 392, 319, 61, 62, 339, 166, 450, 26, 497, 125, 372, 207, 477, 490, 221, 181, 379, 110, 481, 306, 443, 365, 439, 65, 273, 67, 307, 195, 327, 84, 129, 3, 487, 370, 93, 494, 391, 155, 469, 20, 324, 59, 8, 337, 76, 98, 43, 406, 162, 283, 309, 90, 208, 457, 244, 423, 343, 417, 441, 454, 185, 279, 416, 489, 440, 34, 398, 141, 183, 330, 27, 82, 233, 393, 408, 157, 151, 201, 232, 71, 296, 428, 243, 297, 389, 276, 136, 102, 218, 394, 368, 250, 462, 116, 148, 459, 57, 308, 374, 161, 80, 430, 358, 38, 301, 144, 174, 169, 278, 328, 33, 237, 128, 140, 204, 154, 456, 437, 491, 69, 63};

	static private int comparisons;
	static private int movements;
	static private long time;
	static private long start;
	static private long end;

	static private int[] array;
	static private int length;
	static private int[] tempMergArr;

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

	/**
	 * Performs Radix Sort on an array of integers
	 * @param arrayIn
	 * @param dataType
	 * @return
	 */
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
				System.out.println("\nKeep Sorting is still true");
			//Assume the array is sorted unless encountering an item that does not go into the first bucket
			keepSorting = false;

			//push each element in the array to the end of the corresponding bucket
			for(int i : array)
			{
				int temp = i;
				int index = 0;

				//for whichever iteration this is, divide the temporary number by 10
				for(int j = 1; j <= iteration; j++)
				{
					comparisons++;
					index = temp % 10;
					temp = temp / 10;
				}

				if(debug)
					System.out.println("Pushing " + i + " to index " + index);

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
				Bucket target = buckets[index];
				while(target.next != null)
				{
					if(debug)
						System.out.println(i + " is below " + target.value);
					target = target.next;
				}
				if(debug)
					System.out.println("reached end of bucket, inserting " + push.value);
				target.next = push;
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
		System.out.println();
	}

	private static class Bucket
	{
		Bucket next = null;
		int value;
	}
}


class Sort
{
	private static final boolean debugMode = true;

	//Test lists to use
	static final int[] inOrder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	static final int[] reverseOrder = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	static final int[] almostOrder = {1, 2, 3, 5, 4, 6, 7, 9, 10, 8, 11, 12, 13, 14, 15, 17, 16, 18, 19, 20};
	static final int[] random = {4, 5, 17, 12, 7, 15, 18, 10, 2, 9, 8, 1, 16, 14, 20, 19, 3, 13, 6, 11};

	public static String[] selectionSort(int[] list, String dataType)
	{
		int comparisons = 0;
		int movements = 0;
		long time;

		long start = System.currentTimeMillis();
		for(int i = 0; i < list.length; i++)
		{
			comparisons++;
			int index = i;
			for(int j = i + 1; j < list.length; j++)
			{
				comparisons++;

				if(list[j] < list[index])
				{
					index = j;
					movements++;
				}
			}
			int smallerNumber = list[index];
			if(list[index] == list[i])
				movements++;
			list[index] = list[i];
			if(list[i] == smallerNumber)
				movements++;
			list[i] = smallerNumber;
		}
		long end = System.currentTimeMillis();

		time = end - start;

		if(debugMode)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			for(int i : list)
				System.out.print(i + ", ");
			System.out.println();
			System.out.println("Length: " + list.length + "\nData Type: " + dataType + "\nAlgorithm: Selection Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}
		return new String[]{"" + list.length, dataType, "Selection Sort", "" + comparisons, "" + movements, "" + time};
	}

	public static String[] insertionSort(int[] list, String dataType)
	{
		int comparisons = 0;
		int movements = 0;
		long time;

		long start = System.currentTimeMillis();
		for(int i = 1; i < list.length; i++)
		{
			comparisons++;
			int temp = list[i];
			int j;
			for(j = i - 1; j >= 0 && temp < list[j]; j--)
			{
				comparisons++;
				movements++;
				list[j + 1] = list[j];
			}

			//Check if the two numbers are different to know whether it is making a movement
			if(list[j + 1] == temp)
				movements++;
			list[j + 1] = temp;
		}

		long end = System.currentTimeMillis();

		time = end - start;

		if(debugMode)
		{
			System.out.println("Start Time: " + start + "\nEnd Time: " + end);
			for(int i : list)
				System.out.print(i + ", ");
			System.out.println();
			System.out.println("Length: " + list.length + "\nData Type: " + dataType + "\nAlgorithm: Insertion Sort" +
			                   "\nComparisons: " + comparisons + "\nMovements: " + movements + "\nTime: " + time);
		}

		return new String[]{"" + list.length, dataType, "Selection Sort", "" + comparisons, "" + movements, "" + time};
	}
}

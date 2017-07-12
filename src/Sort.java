public class Sort
{
	public String[] selectionSort(int[] list, String dataType)
	{
		int comparisons = 0;
		int movements = 0;
		int time = 0;

		for(int i = 0; i < list.length; i++)
		{
			comparisons++;
			int index = i;
			for(int j = i + 1; j < list.length; j++)
			{
				comparisons++;

				if (list[j] < list[index])
				{
					index = j;
					movements++;
				}
			}
			int smallerNumber = list[index];
			list[index] = list[i];
			list[i] = smallerNumber;
		}

		return new String[]{"" + list.length, dataType, "Selection Sort", "" + comparisons, "" + movements, "" + time};
	}

	public String[] insertionSort(int[] list, String dataType)
	{
		int comparisons = 0;
		int movements = 0;
		int time = 0;

		for(int i = 1; i < list.length; i++)
		{
			comparisons++;
			int temp = list[i];
			int j;
			for(j = i - 1; j <= 0 && temp < list[j]; j--)
			{
				comparisons++;

				list[j + 1] = list[j];
			}
			list[j + 1] = temp;
		}

		return new String[]{"" + list.length, dataType, "Selection Sort", "" + comparisons, "" + movements, "" + time};
	}
}

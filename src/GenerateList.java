/**
 * Generates arrays of integers
 */
public class GenerateList
{
	private static boolean debug = false;
	/**
	 * used to increase the size of the numbers generated within the arrays
	 */
	private static int multiplier = 1000;

	/**
	 * Generates an ordered array of unique integers ranging from 0-length
	 *
	 * @param length
	 * @return
	 */
	public static int[] inOrder(int length)
	{
		int[] output = new int[length];
		for(int i = 0; i < length; i++)
			output[i] = i * multiplier;
		if(debug)
			display(output);
		return output;
	}

	/**
	 * Generates a reverse ordered array of unique integers ranging from 0-length
	 *
	 * @param length
	 * @return
	 */
	public static int[] reverseOrder(int length)
	{
		int[] output = new int[length];
		for(int i = 0; i < length; i++)
			output[length - i - 1] = i * multiplier;
		if(debug)
			display(output);
		return output;
	}

	/**
	 * Generates an almost ordered array of unique integers ranging from 0-length
	 *
	 * @param length
	 * @return
	 */
	public static int[] almostOrder(int length)
	{
		//First generate an ordered list
		int[] output = new int[length];
		for(int i = 0; i < length; i++)
			output[i] = i * multiplier;

		//Then swap some of the elements around, the number of them depending on the length of the list
		int numberOfSwaps = (int) (length / (Math.floor(Math.random() * 13)));

		for(int i = 0; i < numberOfSwaps; i++)
		{
			//Get two random indices
			int indexOne = (int) Math.floor(Math.random() * length);
			int indexTwo = (int) Math.floor(Math.random() * length);

			//swap their values
			int swap = output[indexOne];
			output[indexOne] = output[indexTwo];
			output[indexTwo] = swap;
		}
		if(debug)
			display(output);
		return output;
	}

	/**
	 * Generates a randomized array of unique integers ranging from 0-length
	 *
	 * @param length
	 * @return
	 */
	public static int[] randomOrder(int length)
	{
		//Generate an ordered list
		int[] output = new int[length];
		for(int i = 0; i < length; i++)
			output[i] = i * multiplier;

		//For each element, swap it with a random element, running through the table twice
		for(int i = 0; i < length * 2; i++)
		{
			//Pick a random destination index
			int index = (int) Math.floor(Math.random() * length);

			//swap their values
			int swap = output[i % length];
			output[i % length] = output[index];
			output[index] = swap;
		}
		if(debug)
			display(output);
		return output;
	}

	/**
	 * Displays an array of integers
	 * @param array
	 */
	private static void display(int[] array)
	{
		System.out.println();
		for(int i : array)
		{
			System.out.print(i + " ");
		}
	}
}

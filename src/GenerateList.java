/**
 * Generates arrays of integers
 */
public class GenerateList
{
	private static boolean debug = false;

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
			output[i] = i;
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
			output[length - i - 1] = i;
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
			output[i] = i;

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
			output[i] = i;

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

	private static void display(int[] array)
	{
		int lineLengthLimit = 200;
		int c = 0;
		for(int i : array)
		{
			System.out.print(i + " ");
			c++;
			if(c > lineLengthLimit)
			{
				System.out.println();
				c = 0;
			}
		}
	}
}

package imageprocessing;

/**
 * Class that can be used to measure time taken by a piece of code to execute
 * @author Constantin Silvian
 *
 */
public class RegisterTime {
	private long startTime;													// timp initial
	private long endTime;													// timp final

	/**
	 * Method that will take the curent timestamp and save it
	 * Must be called before stopTimer
	 */
	public void startTimer() {
		this.startTime = System.nanoTime();									// salvare timp curent
	}
	
	/**
	 * Method that will take the curent timestamp and save it
	 * Must be called after startTimer
	 */
	public void stopTimer() {
		this.endTime = System.nanoTime();									// salvare timp curent
	}
	
	/**
	 * Method that will compute the difference betwen start and finish time and
	 * @return a string which represents time taken to run in milliseconds with 2 decimals
	 */
	public String getDuration() {
		double duration = (double)(endTime - startTime) / 1_000_000; 		// endTime si startTime sunt in ns, deci impartim la 1000000 pt a le transforma in ms
		String[] arr = String.valueOf(duration).split("\\.");				// luam partea intraga, si partea zecimala ( "." este un caracter special si trebuie sa facem escap_)
		if(arr[1].length() >= 2) {
			return arr[0] + "." + arr[1].substring(0, 2);					// afisam numarul cu doua zecimale
		} else {
			return arr[0] + "." + arr[1].substring(0, 1);					// afisam numarul cu o zecimala
		}
	}
}

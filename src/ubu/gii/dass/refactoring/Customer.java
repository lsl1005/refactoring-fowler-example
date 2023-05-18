package ubu.gii.dass.refactoring;

/**
* Tema  Refactorizaciones 
*
* Ejemplo de aplicaci�n de refactorizaciones. Actualizado para colecciones gen�ricas de java 1.5
*
* @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos L�pez</A>
* @version 1.1
* @see java.io.File
*
*/
import java.util.*;

public class Customer {
	private String _name;
	private List<Rental> _rentals;
	private int frequentRenterPoints;

	public Customer(String name) {
		_name = name;
		_rentals = new ArrayList<Rental>();
		frequentRenterPoints = 0;

	};

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement(boolean html) {
		double totalAmount = 0;
		String result=""; 
		Iterator<Rental> rentals = _rentals.iterator();
		if (html) { result += "<h1>";}
		result += "Rental Record for " + getName();
		if (html) { result += "</h1>";}else{ result +="\n";}
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental aRental = rentals.next();
			// determine amounts for each line
			thisAmount = aRental.getCharge();			
			// add frequent renter points
			frequentRenterPoints = aRental.getFrecuentPoints(frequentRenterPoints);
			// show figures for this rental
			if (html) {
				result += "<H2>" + aRental.getMovie().getTitle() + " "
						+ String.valueOf(thisAmount) + "</H2>";
			}
			else {
				result += "\t" + aRental.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			}
			totalAmount += thisAmount;
		}
		// add footer lines
		if (html) {
			result += "<p>Amount owed is " + String.valueOf(totalAmount) + "</p>";
			result += "<p>You earned " + String.valueOf(frequentRenterPoints)
					+ " frequent renter points </p>";
		}else {
			result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
			result += "You earned " + String.valueOf(frequentRenterPoints)
					+ " frequent renter points";
		}
		return result;
	}
}

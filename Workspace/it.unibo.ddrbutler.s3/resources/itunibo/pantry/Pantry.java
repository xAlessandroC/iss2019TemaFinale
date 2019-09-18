package itunibo.pantry;

public class Pantry {
	
	private int dishes;
	
	public Pantry(int initialValue) {
		this.dishes = initialValue;
	}
	
	public int getDishes() {
		return dishes;
	}

	public void setDishes(int dishes) {
		this.dishes = dishes;
	}

	public void putDishes(int quantity) {
		this.dishes = this.dishes + quantity;
	}

	public void takeDishes(int quantity) {
		if(quantity < this.dishes)
			this.dishes = this.dishes - quantity;
	}
	
	
	public String toString() {
		String message="";
		
		message+="=============================\n";
		message+="PANTRY\n";
		message+="Dishes = " + this.dishes + "\n";
		message+="=============================\n";
		
		return message;
	}
}

package itunibo.dishwasher;

public class Dishwasher {
	
	private int dishes = 0;

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
		message+="DISHWASHER\n";
		message+="Dishes = " + this.dishes + "\n";
		message+="=============================\n";
		
		return message;
	}
}

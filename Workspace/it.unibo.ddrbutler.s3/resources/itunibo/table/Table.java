package itunibo.table;

import java.util.HashMap;
import java.util.Map;

public class Table {

	private int dishes;
	private Map<String, Integer> mapFood;

	public Table() {
		this.dishes = 0;
		this.mapFood = new HashMap<>();
	}

	public int getDishes() {
		return dishes;
	}

	public int getQuantity(String foodcode) {
		Integer res = this.mapFood.get(foodcode);
		if (res != null) {
			return res;
		}

		return -1;
	}

	public void putDishes(int quantity) {
		this.dishes += quantity;
	}

	public void takeDishes(int quantity) {
		this.dishes -= quantity;
	}

	public void putFood(String foodcode, int quantity) {
		Integer oldValue = this.mapFood.get(foodcode);

		if (oldValue == null) {
			oldValue = 0;
		}

		if (quantity >= 0) {
			int newValue = oldValue + quantity;
			this.mapFood.put(foodcode, newValue);
		}
	}

	public void takeFood(String foodcode, int quantity) {
		Integer oldValue = this.mapFood.get(foodcode);

		if (oldValue == null) {
			oldValue = 0;
		}

		if (quantity >= 0) {
			int newValue = oldValue - quantity;
			if (newValue >= 0) {
				this.mapFood.put(foodcode, newValue);
			}
		}

	}

	public String toString() {
		String message = "";

		message += "=============================\n";
		message += "TABLE\n";
		message += "Dishes = " + this.dishes + "\n";
		message += "Food\n";
		for (String s : this.mapFood.keySet()) {
			message += s + "\t" + this.mapFood.get(s) + "\n";
		}
		message += "=============================\n";

		return message;
	}
}

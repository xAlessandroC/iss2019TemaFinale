package itunibo.fridge;

import java.util.HashMap;
import java.util.Map;

public class Fridge {

	private Map<String, Integer> mapFood;
	
	public Fridge() {
		this.mapFood=new HashMap<>();
		this.mapFood.put("taralli", 20);
		this.mapFood.put("brasciole", 20);
		this.mapFood.put("polpette", 20);
		this.mapFood.put("cicorie", 20);
	}
	
	public Map<String,Integer> getAll(){
		return this.mapFood;
	}
	
	public boolean isAvailable(String fc, int qnt) {
		boolean res = false;
		
		if(mapFood.containsKey(fc) && mapFood.get(fc)>=qnt) {
			res = true;
		}
		
		return res;
	}
	
	public int getQuantity(String foodcode) {
		Integer res = this.mapFood.get(foodcode);
		if (res != null) {
			return res;
		}

		return -1;
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
		message += "FRIDGE\n";
		for (String s : this.mapFood.keySet()) {
			message += s + "\t\t" + this.mapFood.get(s) + "\n";
		}
		message += "=============================\n";

		return message;
	}
	
}

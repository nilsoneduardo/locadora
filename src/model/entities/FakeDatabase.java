package model.entities;

import java.util.ArrayList;

public class FakeDatabase {
	
	private ArrayList<Carros> db = new ArrayList();
	
	public FakeDatabase() {
		db.add(new Carros(1,"Civic"));
		db.add(new Carros(2,"Corolla"));
		db.add(new Carros(3,"CRV"));
		db.add(new Carros(4,"Onyx"));
		
	}
	
	public ArrayList<Carros> all() {
		return db;
	}

}

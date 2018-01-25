package info.stochastic.simpleShop.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class ApplicationService {

	@EJB
	private Repository<Clothes> shop;
	
	@EJB
	private Repository<Clothes> warehouse;
	
	public void addToShop(Clothes clothes) {
		add(shop, clothes);
	}
	
	public void addToWarehouse(Clothes clothes) {
		add(warehouse, clothes);
	}
	
	private void add(Repository<Clothes> repository, Clothes clothes) {
		repository.add(clothes);
	}
	
	public void removeFromShop(Clothes clothes) {
		remove(shop, clothes);
	}
	
	public void removeFromWarehouse(Clothes clothes) {
		remove(warehouse, clothes);
	}
	
	private void remove(Repository<Clothes> repository, Clothes clothes) {
		repository.remove(clothes);
	}
	
	public Clothes getFromShopById(int id) {
		return getById(shop, id);
	}
	
	public Clothes getFromWarehouseById(int id) {
		return getById(warehouse, id);
	}
	
	private Clothes getById(Repository<Clothes> repository, int id) {
		return repository.getById(id);
	}
	
	public List<Clothes> getAllFromShop() {
		return getAll(shop);
	}
	
	public List<Clothes> getAllFromWarehouse() {
		return getAll(warehouse);
	}
	
	private List<Clothes> getAll(Repository<Clothes> repository) {
		return repository.getItems();
	}
}

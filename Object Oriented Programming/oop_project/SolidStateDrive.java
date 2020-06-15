 /* Osman Utku Özbudak S012136 Department of Computer Science */
package Project2nd;

public class SolidStateDrive {

	private String name;
	private double price;
	private int storageSize;
	private int bandwith;
	
	public SolidStateDrive(String name, double price, 
							int storageSize, int bandwith) {
		this.name = name;
		this.price = price;
		this.storageSize = storageSize;
		this.bandwith = bandwith;
		
	}

	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getStorageSize() {
		return storageSize;
	}
	public int getBandwith() {
		return bandwith;
	}
	
	public String toString() {
		return this.name +"\n" + this.price +"\n" + "TL" + "\n" + this.storageSize + "\n" +this.bandwith;
	}
	
}

 /* Osman Utku Özbudak S012136 Department of Computer Science */
package Project2nd;



public class Motherboard {
	
	private String name;
	private double price;
	private String socketType;
	private String busVersion;
	private String memoryType;
	
	
	public Motherboard(String name, double price, String socketType,
						String memoryType,String busVersion) {
		this.name = name;
		this.price = price;
		this.socketType = socketType;
		this.memoryType = memoryType;
		this.busVersion = busVersion;
		
		
	}
	public Motherboard() {
		
	}
	
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public String getSocketType() {
		return socketType;
	}
	public String getBusVersion() {
		return busVersion;
	}
	public String getMemoryType() {
		return memoryType;
	}
	
	public String toString() {
		return (this.name +"\n" + this.price + "\n"+"TL" +"\n"  +this.socketType +"\n" + this.busVersion + "\n" +this.memoryType);
	}
	
}

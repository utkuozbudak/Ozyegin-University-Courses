 /* Osman Utku Özbudak S012136 Department of Computer Science */
package Project2nd;

public class CPU {

	private String name;
	private double price;
	private int coreCount;
	private int clockSpeed;
	private String socketType;
	
	public CPU(String name, double price, int coreCount,
				int clockSpeed, String socketType) {
		this.name = name;
		this.price = price;
		this.coreCount = coreCount;
		this.clockSpeed = clockSpeed;
		this.socketType = socketType;
	
	}
	
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getCoreCount() {
		return coreCount;
	}
	public int getClockSpeed() {
		return clockSpeed;
	}
	public String getSocketType() {
		return socketType;
	}
	
	public String toString() {
		return (this.name +"\n" + this.price +"\n" + "TL" +"\n" +this.coreCount +"\n" + this.clockSpeed +"\n" + this.socketType);
	}
	
}

 /* Osman Utku Özbudak S012136 Department of Computer Science */
package Project2nd;

public class GraphicsCard {
	
	private String name;
	private double price;
	private int coreCount;
	private int clockSpeed;
	private int memorySize;
	private int memorySpeed;
	private String memoryType;
	private String busVersion;
	
	public GraphicsCard(String name, double price,int coreCount,
						int clockSpeed, int memorySize, int memorySpeed,
						String memoryType,String busVersion) {
		this.name = name;
		this.price = price;
		this.coreCount = coreCount;
		this.clockSpeed = clockSpeed;
		this.memorySize = memorySize;
		this.memorySpeed = memorySpeed;
		this.memoryType = memoryType;
		this.busVersion = busVersion;
		
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
	public int getMemorySize() {
		return memorySize;
	}
	public int getMemorySpeed() {
		return memorySpeed;
	}
	public String getMemoryType() {
		return memoryType;
	}
	public String getBusVersion() {
		return busVersion;
	}
	public String toString() {
		return (this.name +"\n" + this.price +"\n" + "TL" +"\n"  + this.coreCount +"\n" + this.clockSpeed +"\n" + this.memorySize + "\n" +this.memorySpeed+"\n" + this.memoryType +"\n" + this.busVersion);
	}


}

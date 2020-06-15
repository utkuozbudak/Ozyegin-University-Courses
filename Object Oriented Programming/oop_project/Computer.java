 /* Osman Utku Özbudak S012136 Department of Computer Science */
package Project2nd;

public class Computer extends Main {
	public static Motherboard item_motherboard;
	public static double item_motherboard_price;
	
	public static CPU item_cpu;
	public static double item_cpu_price;
	
	public static Memory item_memory;
	public static double item_memory_price;
	
	public static GraphicsCard item_graphicscard;
	public static double item_graphicscard_price;
	
	public static HardDiskDrive item_hdd;
	public static double item_hdd_price;
	
	public static SolidStateDrive item_ssd;
	public static double item_ssd_price;
	
	public static double finalPrice;
	
	
	
	
	public Computer() {
		
	}
	
	
	public double getItemMbPrice() {
		return item_motherboard_price;
	}
	public double getItemCPUPrice() {
		return item_cpu_price;
	}
	public double getItemMemoryPrice() {
		return item_memory_price;
	}
	public double getItemGraphicsPrice() {
		return item_graphicscard_price;
	}
	public double getItemHDDPrice() {
		return item_hdd_price;
	}
	public double getItemSSDPrice() {
		return item_ssd_price;
	}
	
	

	
	
	
	
}

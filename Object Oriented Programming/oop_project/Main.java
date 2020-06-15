 /* Osman Utku Özbudak S012136 Department of Computer Science */

package Project2nd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main {
	
	
	public static void main(String[] args) throws Exception {
		
		Double price = 0.0;
		String comp = "";
		FileReader myfile = new FileReader(getFileName()); 
		@SuppressWarnings("resource")
		BufferedReader myreader = new BufferedReader(myfile);
		
	
		String text = "";
		String line = myreader.readLine();
		while(line != null) {
			text += line + "\n";
			line = myreader.readLine();
		}
		StringTokenizer tokenizer = new StringTokenizer(text, "\n");
		while(tokenizer.hasMoreTokens()) {
			String tokenString = tokenizer.nextToken(":");
			comp += tokenString + "\n";
			
		}
		
		MotherboardFrame mbFrame = new MotherboardFrame();
		CPUFrame cpuFrame = new CPUFrame();
		MemoryFrame memoryFrame = new MemoryFrame();
		GraphicsCardFrame graphicsFrame = new GraphicsCardFrame();
		StorageFrame storageFrame = new StorageFrame();
		
		
		
		
		Computer[] items = new Computer[5];
		Double priceList[] = new Double[5];
		
		JButton[] button_mbArray = new JButton[5];
		JButton[] button_cpuArray = new JButton[3];
		JButton[] button_memoryArray = new JButton[5];
		JButton[] button_graphicsArray = new JButton[5];
		JButton[] button_hddArray = new JButton[3];
		JButton[] button_ssdArray = new JButton[3];
		
		
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("PC Builder");
		mainFrame.setSize(750, 500);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(6,1,1,1));
		
		Color color = new Color(128,0,0);
		
		JPanel panel_motherboard = new JPanel();
		mainFrame.add(panel_motherboard);
		panel_motherboard.setLayout(null);
		panel_motherboard.setBackground(Color.WHITE);
		
		JPanel panel_cpu = new JPanel();
		mainFrame.add(panel_cpu);
		panel_cpu.setLayout(null);
		panel_cpu.setBackground(Color.WHITE);
		
		JPanel panel_memory = new JPanel();
		mainFrame.add(panel_memory);
		panel_memory.setLayout(null);
		panel_memory.setBackground(Color.WHITE);
		
		JPanel panel_graphicscard = new JPanel();
		mainFrame.add(panel_graphicscard);
		panel_graphicscard.setLayout(null);
		panel_graphicscard.setBackground(Color.WHITE);
		
		JPanel panel_storage = new JPanel();
		mainFrame.add(panel_storage);
		panel_storage.setLayout(null);
		panel_storage.setBackground(Color.WHITE);
		
	
		JLabel label_motherboard = new JLabel("Motherboard");
		panel_motherboard.add(label_motherboard);
		label_motherboard.setBounds(10, 17, 100, 50);
		
		JLabel label_cpu = new JLabel("CPU");
		panel_cpu.add(label_cpu);
		label_cpu.setBounds(10, 18, 100, 50);
		
		JLabel label_memory = new JLabel("Memory");
		panel_memory.add(label_memory);
		label_memory.setBounds(10, 19, 100, 50);
		
		JLabel label_graphicscard = new JLabel("Graphics Card");
		panel_graphicscard.add(label_graphicscard);
		label_graphicscard.setBounds(10, 20, 100, 50);
		
		JLabel label_storage = new JLabel("Storage");
		panel_storage.add(label_storage);
		label_storage.setBounds(10, 21, 100, 50);
		
		JButton button_motherboard = new JButton("+");
		panel_motherboard.add(button_motherboard);
		button_motherboard.setBounds(690, 30, 60, 50);
		button_motherboard.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				mbFrame.setVisible(true);
				}
			});
		
		JButton button_cpu = new JButton("+");
		panel_cpu.add(button_cpu);
		button_cpu.setBounds(690, 31, 60, 50);
		button_cpu.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				cpuFrame.setVisible(true);
			}
		});
		
		JButton button_memory = new JButton("+");
		panel_memory.add(button_memory);
		button_memory.setBounds(690, 32, 60, 50);
		button_memory.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				memoryFrame.setVisible(true);
			}
		});
		
		JButton button_graphicscard = new JButton("+");
		panel_graphicscard.add(button_graphicscard);
		button_graphicscard.setBounds(690, 33, 60, 50);
		button_graphicscard.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				graphicsFrame.setVisible(true);
			}
		});
		
		JButton button_storage = new JButton("+");
		panel_storage.add(button_storage);
		button_storage.setBounds(690, 34, 60, 50);
		button_storage.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				storageFrame.setVisible(true);
			}
		});
		JPanel panel6 = new JPanel();
		mainFrame.add(panel6);
		panel6.setLayout(new FlowLayout());
		panel6.setBackground(Color.GRAY);
		
		JButton button_reset = new JButton("Reset");
		panel6.add(button_reset);
		
		JButton button_buildscreen = new JButton("Build Screen");
		panel6.add(button_buildscreen);
		button_buildscreen.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				mbFrame.dispose();
				cpuFrame.dispose();
				memoryFrame.dispose();
				graphicsFrame.dispose();
				storageFrame.dispose();
				mainFrame.setVisible(true);
			}
		});
		mainFrame.add(panel6);
		JButton button_check = new JButton("Check Compability");
		panel6.add(button_check);
		button_check.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				if(Computer.item_motherboard != null && Computer.item_cpu != null && Computer.item_memory != null &&
					 Computer.item_graphicscard != null && (Computer.item_hdd != null || Computer.item_ssd != null )) {
					if(Computer.item_motherboard.getSocketType().equals(Computer.item_cpu.getSocketType())) {
						if(Computer.item_motherboard.getBusVersion().equals(Computer.item_graphicscard.getBusVersion())) {
							if(Computer.item_motherboard.getMemoryType().equals(Computer.item_memory.getType())) {
								JOptionPane.showMessageDialog(null, "Computer is compatible.");
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Computer is not compatible.");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Computer is not compatible.");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Computer is not compatible.");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "5 component need to be chosen.");
				}
				
				
			}
			
			
		});
		
		button_reset.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				panel_motherboard.removeAll();
				panel_motherboard.add(button_motherboard);
				panel_motherboard.add(label_motherboard);
				
				panel_cpu.removeAll();
				panel_cpu.add(button_cpu);
				panel_cpu.add(label_cpu);
				
				panel_memory.removeAll();
				panel_memory.add(button_memory);
				panel_memory.add(label_memory);
				
				panel_graphicscard.removeAll();
				panel_graphicscard.add(button_graphicscard);
				panel_graphicscard.add(label_graphicscard);
				
				panel_storage.removeAll();
				panel_storage.add(button_storage);
				panel_storage.add(label_storage);
				
				panel6.removeAll();
				panel6.add(button_reset);
				panel6.add(button_buildscreen);
				panel6.add(button_check);
				
				
				
				mainFrame.setVisible(true);
			}
		});
		
		
		
		
		Motherboard[] motherboardArray = createMotherboard(comp);
		for(int i=0; i<motherboardArray.length; i++) {
			button_mbArray[i] = new JButton(motherboardArray[i].getName() + "\n" + motherboardArray[i].getPrice() + "\n" + motherboardArray[i].getSocketType()+  "\n" +
					motherboardArray[i].getBusVersion() +  "\n" + motherboardArray[i].getMemoryType());
			mbFrame.add(button_mbArray[i]);
			int k = i;
			button_mbArray[i].addActionListener(new Action12() {
				public void actionPerformed(ActionEvent e) {
					
					if(Computer.item_motherboard_price != 0.0) {
						Computer.finalPrice -= Computer.item_motherboard_price;
						
					}
					
					Computer.item_motherboard = motherboardArray[k];
					Computer.item_motherboard_price = Computer.item_motherboard.getPrice();
					
					JLabel item_label = new JLabel(Computer.item_motherboard.toString());
					
					
					Computer.finalPrice = Computer.item_motherboard_price;	
					JLabel pricelabel = new JLabel("Price:" + (Computer.finalPrice) + " TL");
					
					panel6.removeAll();
					panel6.add(button_reset);
					panel6.add(button_buildscreen);
					panel6.add(button_check);
					
					
					
					panel6.add(pricelabel);				
					
					priceList[0] = Computer.item_motherboard.getPrice();
					
					panel_motherboard.removeAll();
					panel_motherboard.add(label_motherboard);
					panel_motherboard.add(button_motherboard);
					panel_motherboard.add(item_label);
					item_label.setBounds(11, 17, 690, 100);
					mbFrame.dispose();
					mainFrame.setVisible(true);
					
					}
			});
		}
		JPanel panel7 = new JPanel();
		
		panel7.setLayout(new FlowLayout());
		panel7.setBackground(Color.GRAY);
		
		JButton button_reset2 = new JButton("Reset");
		panel7.add(button_reset2);
		button_reset2.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mbFrame.dispose();
				panel_motherboard.removeAll();
				panel_motherboard.add(button_motherboard);
				panel_motherboard.add(label_motherboard);
				
				panel_cpu.removeAll();
				panel_cpu.add(button_cpu);
				panel_cpu.add(label_cpu);
				
				panel_memory.removeAll();
				panel_memory.add(button_memory);
				panel_memory.add(label_memory);
				
				panel_graphicscard.removeAll();
				panel_graphicscard.add(button_graphicscard);
				panel_graphicscard.add(label_graphicscard);
				
				panel_storage.removeAll();
				panel_storage.add(button_storage);
				panel_storage.add(label_storage);
				
				mainFrame.setVisible(true);
			}
		});
		JButton button_buildscreen2 = new JButton("Build Screen");
		panel7.add(button_buildscreen2);
		button_buildscreen2.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				mbFrame.dispose();
				cpuFrame.dispose();
				memoryFrame.dispose();
				graphicsFrame.dispose();
				storageFrame.dispose();
				mainFrame.setVisible(true);
			}
		});
		JButton button_check2= new JButton("Check Compability");
		panel7.add(button_check2);
		button_check2.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				if(Computer.item_motherboard != null && Computer.item_cpu != null && Computer.item_memory != null &&
					 Computer.item_graphicscard != null && (Computer.item_hdd != null || Computer.item_ssd != null )) {
					if(Computer.item_motherboard.getSocketType().equals(Computer.item_cpu.getSocketType())) {
						if(Computer.item_motherboard.getBusVersion().equals(Computer.item_graphicscard.getBusVersion())) {
							if(Computer.item_motherboard.getMemoryType().equals(Computer.item_memory.getType())) {
								JOptionPane.showMessageDialog(null, "Computer is compatible.");
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Computer is not compatible.");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Computer is not compatible.");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Computer is not compatible.");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "5 component need to be chosen.");
				}
				
				
			}
			
			
		});
		
		mbFrame.add(panel7);
		
		CPU[] cpuArray = createCpu(comp);
		for(int i=0; i<cpuArray.length; i++) {
			button_cpuArray[i] = new JButton(cpuArray[i].getName() + "\n" + cpuArray[i].getPrice() + "\n" + cpuArray[i].getCoreCount() + "\n" +
						cpuArray[i].getClockSpeed() + "\n" + cpuArray[i].getSocketType());
			cpuFrame.add(button_cpuArray[i]);
			int k = i;
			button_cpuArray[i].addActionListener(new Action12() {
				public void actionPerformed(ActionEvent e) {
					if(Computer.item_cpu_price != 0.0) {
						Computer.finalPrice -= Computer.item_cpu_price;
						
					}
					
					
					
					Computer.item_cpu = cpuArray[k];
					Computer.item_cpu_price = Computer.item_cpu.getPrice();
					JLabel item2_label = new JLabel(Computer.item_cpu.toString());
					
					Computer.finalPrice += Computer.item_cpu_price;	
					JLabel pricelabel = new JLabel("Price:" + (Computer.finalPrice) + " TL");
					
					panel6.removeAll();
					panel6.add(button_reset);
					panel6.add(button_buildscreen);
					panel6.add(button_check);
					
					
					
					panel6.add(pricelabel);	
					
					priceList[1] = Computer.item_cpu.getPrice();
					
					panel_cpu.removeAll();
					panel_cpu.add(label_cpu);
					panel_cpu.add(button_cpu);
					panel_cpu.add(item2_label);
					item2_label.setBounds(11,18,690,100);
					cpuFrame.dispose();
					mainFrame.setVisible(true);
					
					
				}
			});
		}
		JPanel panel8 = new JPanel();
		
		panel8.setLayout(new FlowLayout());
		panel8.setBackground(Color.GRAY);
		
		JButton button_reset3 = new JButton("Reset");
		panel8.add(button_reset3);
		button_reset3.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				cpuFrame.dispose();
				panel_motherboard.removeAll();
				panel_motherboard.add(button_motherboard);
				panel_motherboard.add(label_motherboard);
				
				panel_cpu.removeAll();
				panel_cpu.add(button_cpu);
				panel_cpu.add(label_cpu);
				
				panel_memory.removeAll();
				panel_memory.add(button_memory);
				panel_memory.add(label_memory);
				
				panel_graphicscard.removeAll();
				panel_graphicscard.add(button_graphicscard);
				panel_graphicscard.add(label_graphicscard);
				
				panel_storage.removeAll();
				panel_storage.add(button_storage);
				panel_storage.add(label_storage);
				
				mainFrame.setVisible(true);
			}
		});
		JButton button_buildscreen3 = new JButton("Build Screen");
		panel8.add(button_buildscreen3);
		button_buildscreen3.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				mbFrame.dispose();
				cpuFrame.dispose();
				memoryFrame.dispose();
				graphicsFrame.dispose();
				storageFrame.dispose();
				mainFrame.setVisible(true);
			}
		});
		JButton button_check3 = new JButton("Check Compability");
		panel8.add(button_check3);
		button_check3.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				if(Computer.item_motherboard != null && Computer.item_cpu != null && Computer.item_memory != null &&
					 Computer.item_graphicscard != null && (Computer.item_hdd != null || Computer.item_ssd != null )) {
					if(Computer.item_motherboard.getSocketType().equals(Computer.item_cpu.getSocketType())) {
						if(Computer.item_motherboard.getBusVersion().equals(Computer.item_graphicscard.getBusVersion())) {
							if(Computer.item_motherboard.getMemoryType().equals(Computer.item_memory.getType())) {
								JOptionPane.showMessageDialog(null, "Computer is compatible.");
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Computer is not compatible.");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Computer is not compatible.");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Computer is not compatible.");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "5 component need to be chosen.");
				}
				
				
			}
			
			
		});
		
		cpuFrame.add(panel8);
		
		Memory[] memoryArray = createMemory(comp);
		for(int i=0; i<memoryArray.length; i++) {
			button_memoryArray[i] = new JButton(memoryArray[i].getName() + "\n" + memoryArray[i].getPrice() + "\n" + memoryArray[i].getSize() + "\n" +
						memoryArray[i].getSpeed() + "\n" + memoryArray[i].getType());
			memoryFrame.add(button_memoryArray[i]);
			int k = i;
			button_memoryArray[i].addActionListener(new Action12() {
				public void actionPerformed(ActionEvent e) {
					if(Computer.item_memory_price != 0.0) {
						Computer.finalPrice -= Computer.item_memory_price;
						
					}
					
					
					
					Computer.item_memory = memoryArray[k];
					Computer.item_memory_price = Computer.item_memory.getPrice();
					JLabel item3_label = new JLabel(Computer.item_memory.toString());
					
					Computer.finalPrice += Computer.item_memory_price;	
					JLabel pricelabel = new JLabel("Price:" + (Computer.finalPrice) + " TL");
					
					panel6.removeAll();
					panel6.add(button_reset);
					panel6.add(button_buildscreen);
					panel6.add(button_check);
					panel6.add(pricelabel);
					
					
					priceList[2] = Computer.item_memory.getPrice();
					
					panel_memory.removeAll();
					panel_memory.add(label_memory);
					panel_memory.add(button_memory);
					panel_memory.add(item3_label);
					item3_label.setBounds(11, 19, 690, 100);
					memoryFrame.dispose();
					mainFrame.setVisible(true);
					}
			});
		}
		JPanel panel9 = new JPanel();
		
		panel9.setLayout(new FlowLayout());
		panel9.setBackground(Color.GRAY);
		
		JButton button_reset4 = new JButton("Reset");
		panel9.add(button_reset4);
		button_reset4.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				cpuFrame.dispose();
				panel_motherboard.removeAll();
				panel_motherboard.add(button_motherboard);
				panel_motherboard.add(label_motherboard);
				
				panel_cpu.removeAll();
				panel_cpu.add(button_cpu);
				panel_cpu.add(label_cpu);
				
				panel_memory.removeAll();
				panel_memory.add(button_memory);
				panel_memory.add(label_memory);
				
				panel_graphicscard.removeAll();
				panel_graphicscard.add(button_graphicscard);
				panel_graphicscard.add(label_graphicscard);
				
				panel_storage.removeAll();
				panel_storage.add(button_storage);
				panel_storage.add(label_storage);
				
				mainFrame.setVisible(true);
			}
		});
		JButton button_buildscreen4 = new JButton("Build Screen");
		panel9.add(button_buildscreen4);
		button_buildscreen4.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				memoryFrame.dispose();
				mbFrame.dispose();
				cpuFrame.dispose();
				memoryFrame.dispose();
				graphicsFrame.dispose();
				storageFrame.dispose();
				mainFrame.setVisible(true);
			}
		});
		JButton button_check4 = new JButton("Check Compability");
		panel9.add(button_check4);
		button_check4.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				if(Computer.item_motherboard != null && Computer.item_cpu != null && Computer.item_memory != null &&
					 Computer.item_graphicscard != null && (Computer.item_hdd != null || Computer.item_ssd != null )) {
					if(Computer.item_motherboard.getSocketType().equals(Computer.item_cpu.getSocketType())) {
						if(Computer.item_motherboard.getBusVersion().equals(Computer.item_graphicscard.getBusVersion())) {
							if(Computer.item_motherboard.getMemoryType().equals(Computer.item_memory.getType())) {
								JOptionPane.showMessageDialog(null, "Computer is compatible.");
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Computer is not compatible.");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Computer is not compatible.");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Computer is not compatible.");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "5 component need to be chosen.");
				}
				
				
			}
			
			
		});
		
		memoryFrame.add(panel9);
		
		GraphicsCard[] graphicsArray = createGC(comp);
		for(int i=0; i<graphicsArray.length; i++) {
			button_graphicsArray[i] = new JButton(graphicsArray[i].getName() + "\n" + graphicsArray[i].getPrice() + "\n" + graphicsArray[i].getCoreCount() + "\n" + graphicsArray[i].getClockSpeed()+
					"\n" +	graphicsArray[i].getMemorySize() + "\n" + graphicsArray[i].getMemorySpeed() + "\n" + graphicsArray[i].getMemoryType() + "\n" + graphicsArray[i].getBusVersion());
			graphicsFrame.add(button_graphicsArray[i]);
			int k=i;
			button_graphicsArray[i].addActionListener(new Action12() {
				public void actionPerformed(ActionEvent e) {
					if(Computer.item_graphicscard_price != 0.0) {
						Computer.finalPrice -= Computer.item_graphicscard_price;
						
					}
					
					
					
					Computer.item_graphicscard = graphicsArray[k];
					Computer.item_graphicscard_price = Computer.item_graphicscard.getPrice();
					JLabel item4_label = new JLabel(Computer.item_graphicscard.toString());
					
					Computer.finalPrice += Computer.item_graphicscard_price;	
					JLabel pricelabel = new JLabel("Price:" + (Computer.finalPrice) + " TL");
					
					panel6.removeAll();
					panel6.add(button_reset);
					panel6.add(button_buildscreen);
					panel6.add(button_check);
					panel6.add(pricelabel);
					priceList[3] = Computer.item_graphicscard.getPrice();
					
					panel_graphicscard.removeAll();
					panel_graphicscard.add(label_graphicscard);
					panel_graphicscard.add(button_graphicscard);
					panel_graphicscard.add(item4_label);
					item4_label.setBounds(11, 20, 690, 100);
					graphicsFrame.dispose();
					mainFrame.setVisible(true);
					
				}
			});
		}
		JPanel panel10 = new JPanel();
		
		panel10.setLayout(new FlowLayout());
		panel10.setBackground(Color.GRAY);
		
		JButton button_reset5 = new JButton("Reset");
		panel10.add(button_reset5);
		button_reset5.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				graphicsFrame.dispose();
				panel_motherboard.removeAll();
				panel_motherboard.add(button_motherboard);
				panel_motherboard.add(label_motherboard);
				
				panel_cpu.removeAll();
				panel_cpu.add(button_cpu);
				panel_cpu.add(label_cpu);
				
				panel_memory.removeAll();
				panel_memory.add(button_memory);
				panel_memory.add(label_memory);
				
				panel_graphicscard.removeAll();
				panel_graphicscard.add(button_graphicscard);
				panel_graphicscard.add(label_graphicscard);
				
				panel_storage.removeAll();
				panel_storage.add(button_storage);
				panel_storage.add(label_storage);
				
				mainFrame.setVisible(true);
			}
		});
		JButton button_buildscreen5 = new JButton("Build Screen");
		panel10.add(button_buildscreen5);
		button_buildscreen5.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mbFrame.dispose();
				mbFrame.dispose();
				cpuFrame.dispose();
				memoryFrame.dispose();
				graphicsFrame.dispose();
				storageFrame.dispose();
				mainFrame.setVisible(true);
			}
		});
		JButton button_check5 = new JButton("Check Compability");
		panel10.add(button_check5);
		button_check5.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				if(Computer.item_motherboard != null && Computer.item_cpu != null && Computer.item_memory != null &&
					 Computer.item_graphicscard != null && (Computer.item_hdd != null || Computer.item_ssd != null )) {
					if(Computer.item_motherboard.getSocketType().equals(Computer.item_cpu.getSocketType())) {
						if(Computer.item_motherboard.getBusVersion().equals(Computer.item_graphicscard.getBusVersion())) {
							if(Computer.item_motherboard.getMemoryType().equals(Computer.item_memory.getType())) {
								JOptionPane.showMessageDialog(null, "Computer is compatible.");
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Computer is not compatible.");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Computer is not compatible.");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Computer is not compatible.");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "5 component need to be chosen.");
				}
				
				
			}
			
			
		});
		
		graphicsFrame.add(panel10);
		
		HardDiskDrive[] hddArray = createHardDisk(comp);
		for(int i=0; i<hddArray.length; i++) {
			button_hddArray[i] = new JButton(hddArray[i].getName() + "\n" + hddArray[i].getPrice() + "\n" + hddArray[i].getStorageSize() + "\n" +hddArray[i].getBandwith());
			storageFrame.add(button_hddArray[i]);
			int k=i;
			button_hddArray[i].addActionListener(new Action12() {
				public void actionPerformed(ActionEvent e) {
					if(Computer.item_hdd_price != 0.0) {
						Computer.finalPrice -= Computer.item_hdd_price;
						
					}
					
					
					
					Computer.item_hdd = hddArray[k];
					Computer.item_hdd_price = Computer.item_hdd.getPrice();
					JLabel item5_label = new JLabel(Computer.item_hdd.toString());
					Computer.finalPrice += Computer.item_hdd_price;	
					JLabel pricelabel = new JLabel("Price:" + (Computer.finalPrice) + " TL");
					
					panel6.removeAll();
					panel6.add(button_reset);
					panel6.add(button_buildscreen);
					panel6.add(button_check);
					panel6.add(pricelabel);
					priceList[4] = Computer.item_hdd.getPrice();
					
					panel_storage.removeAll();
					panel_storage.add(label_storage);
					panel_storage.add(button_storage);
					panel_storage.add(item5_label);
					item5_label.setBounds(11,21,690,100);
					storageFrame.dispose();
					mainFrame.setVisible(true);
				}
			});
			
		}
		SolidStateDrive[] ssdArray = createSSD(comp);
		for(int i=0; i<ssdArray.length; i++) {
			button_ssdArray[i] = new JButton(ssdArray[i].getName() + "\n" + ssdArray[i].getPrice() + "\n" + ssdArray[i].getStorageSize() + "\n" + ssdArray[i].getBandwith());
		storageFrame.add(button_ssdArray[i]);
		int k=i;
		button_ssdArray[i].addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				if(Computer.item_ssd_price != 0.0) {
					Computer.finalPrice -= Computer.item_ssd_price;
					
				}
				
				
				
				
				Computer.item_ssd = ssdArray[k];
				Computer.item_ssd_price = Computer.item_ssd.getPrice();
				JLabel item5_label = new JLabel(Computer.item_ssd.toString());
				Computer.finalPrice += Computer.item_ssd_price;	
				JLabel pricelabel = new JLabel("Price:" + (Computer.finalPrice) + " TL");
				
				panel6.removeAll();
				panel6.add(button_reset);
				panel6.add(button_buildscreen);
				panel6.add(button_check);
				panel6.add(pricelabel);
				priceList[4] = Computer.item_ssd.getPrice();
				
				panel_storage.removeAll();
				panel_storage.add(label_storage);
				panel_storage.add(button_storage);
				panel_storage.add(item5_label);
				item5_label.setBounds(11,21,690,100);
				storageFrame.dispose();
				mainFrame.setVisible(true);
				
			}
		});
	}
		JPanel panel11 = new JPanel();
		
		panel11.setLayout(new FlowLayout());
		panel11.setBackground(Color.GRAY);
		
		JButton button_reset6 = new JButton("Reset");
		panel11.add(button_reset6);
		button_reset6.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				storageFrame.dispose();
				panel_motherboard.removeAll();
				panel_motherboard.add(button_motherboard);
				panel_motherboard.add(label_motherboard);
				
				panel_cpu.removeAll();
				panel_cpu.add(button_cpu);
				panel_cpu.add(label_cpu);
				
				panel_memory.removeAll();
				panel_memory.add(button_memory);
				panel_memory.add(label_memory);
				
				panel_graphicscard.removeAll();
				panel_graphicscard.add(button_graphicscard);
				panel_graphicscard.add(label_graphicscard);
				
				panel_storage.removeAll();
				panel_storage.add(button_storage);
				panel_storage.add(label_storage);
				
				mainFrame.setVisible(true);
			}
		});
		JButton button_buildscreen6 = new JButton("Build Screen");
		panel11.add(button_buildscreen6);
		button_buildscreen6.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				mbFrame.dispose();
				mbFrame.dispose();
				cpuFrame.dispose();
				memoryFrame.dispose();
				graphicsFrame.dispose();
				storageFrame.dispose();
				mainFrame.setVisible(true);
			}
		});
		JButton button_check6 = new JButton("Check Compability");
		panel11.add(button_check6);
		button_check6.addActionListener(new Action12() {
			public void actionPerformed(ActionEvent e) {
				if(Computer.item_motherboard != null && Computer.item_cpu != null && Computer.item_memory != null &&
					 Computer.item_graphicscard != null && (Computer.item_hdd != null || Computer.item_ssd != null )) {
					if(Computer.item_motherboard.getSocketType().equals(Computer.item_cpu.getSocketType())) {
						if(Computer.item_motherboard.getBusVersion().equals(Computer.item_graphicscard.getBusVersion())) {
							if(Computer.item_motherboard.getMemoryType().equals(Computer.item_memory.getType())) {
								JOptionPane.showMessageDialog(null, "Computer is compatible.");
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Computer is not compatible");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Computer is not compatible");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Computer is not compatible");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "5 component need to be chosen.");
				}
				
				
			}
			
			
		});
		
		storageFrame.add(panel11);
		
		mainFrame.setVisible(true);
		
		
}
	
	
	public static String getFileName() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt file", "txt");
		chooser.setFileFilter(filter);
		int returnVal= chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			throw new RuntimeException("Failed to choose file");
			
		}
		return chooser.getSelectedFile().getAbsolutePath();
}

	public static Motherboard[] createMotherboard(String text) {
		ArrayList <String> motherboardName = new ArrayList<String>();
		ArrayList <Double> motherboardPrice = new ArrayList<Double>();
		ArrayList <String> motherboardSocketType = new ArrayList<String>();
		ArrayList <String> motherboardMemoryType = new ArrayList<String>();
		ArrayList <String> motherboardBusVersiyon = new ArrayList<String>();
		int counter=0;
		
		StringTokenizer tokenizer = new StringTokenizer(text, "\n");
		while(tokenizer.hasMoreTokens()) {
			String mytoken = tokenizer.nextToken();
			if(mytoken.equals("Motherboard")) {
				tokenizer.nextToken();
				String nameMotherboard = tokenizer.nextToken();
				motherboardName.add(nameMotherboard);
				tokenizer.nextToken();
				String priceMotherboard = tokenizer.nextToken();
				motherboardPrice.add(Double.parseDouble(priceMotherboard));
				tokenizer.nextToken();
				String sockettype= tokenizer.nextToken();
				motherboardSocketType.add(sockettype);
				tokenizer.nextToken();
				String memorytype = tokenizer.nextToken();
				motherboardMemoryType.add(memorytype);
				tokenizer.nextToken();
				String busversiyon = tokenizer.nextToken();
				motherboardBusVersiyon.add(busversiyon);
				counter++;
				
			}
		}
		Motherboard[] motherboardArray = new Motherboard[counter];
		
		for (int i=0; i<motherboardName.size();i++) {
			motherboardArray[i] = new Motherboard(motherboardName.get(i),motherboardPrice.get(i),motherboardSocketType.get(i),motherboardMemoryType.get(i),motherboardBusVersiyon.get(i));
		}
		return motherboardArray;
}


	
	public static CPU[] createCpu(String text) {
		ArrayList <String>  cpuName = new ArrayList<String>();
		ArrayList <Double>  cpuPrice = new ArrayList<Double>();
		ArrayList <Integer> cpuCoreCount = new ArrayList<Integer>();
		ArrayList <Integer> cpuClockSpeed = new ArrayList<Integer>();
		ArrayList <String>  cpuSocketType = new ArrayList<String>();
	int count=0;
	
	StringTokenizer tokenizer = new StringTokenizer(text, "\n");
	while(tokenizer.hasMoreTokens()) {
		String mytoken = tokenizer.nextToken();
		if(mytoken.equals("CPU")) {
			tokenizer.nextToken();
			String nameCpu = tokenizer.nextToken();
			cpuName.add(nameCpu);
			tokenizer.nextToken();
			String priceCpu = tokenizer.nextToken();
			cpuPrice.add(Double.parseDouble(priceCpu));
			tokenizer.nextToken();
			String coreCount= tokenizer.nextToken();
			cpuCoreCount.add(Integer.parseInt(coreCount));
			tokenizer.nextToken();
			String clockSpeed = tokenizer.nextToken();
			cpuClockSpeed.add(Integer.parseInt(clockSpeed));
			tokenizer.nextToken();
			String socketType = tokenizer.nextToken();
			cpuSocketType.add(socketType);
			count++;
			
		}
	}
	CPU[] cpuArray = new CPU[count];
	for (int i=0; i<cpuArray.length;i++) {
		cpuArray[i] = new CPU(cpuName.get(i),cpuPrice.get(i),cpuCoreCount.get(i),cpuClockSpeed.get(i),cpuSocketType.get(i));
	}
	return cpuArray;
}


	public static Memory[] createMemory(String text) {
		ArrayList <String> memoryName = new ArrayList<String>();
		ArrayList <Double> memoryPrice = new ArrayList<Double>();
		ArrayList <Double> memorySize = new ArrayList<Double>();
		ArrayList <Integer> memorySpeed = new ArrayList<Integer>();
		ArrayList <String> memorySocketType = new ArrayList<String>();
		int count=0;
	
		StringTokenizer tokenizer = new StringTokenizer(text, "\n");
		while(tokenizer.hasMoreTokens()) {
		String mytoken = tokenizer.nextToken();
		if(mytoken.equals("Memory")) {
			tokenizer.nextToken();
			String nameMemory = tokenizer.nextToken();
			memoryName.add(nameMemory);
			tokenizer.nextToken();
			String priceMemory = tokenizer.nextToken();
			memoryPrice.add(Double.parseDouble(priceMemory));
			tokenizer.nextToken();
			String size= tokenizer.nextToken();
			memorySize.add(Double.parseDouble(size));
			tokenizer.nextToken();
			String speed = tokenizer.nextToken();
			memorySpeed.add(Integer.parseInt(speed));
			tokenizer.nextToken();
			String socketType = tokenizer.nextToken();
			memorySocketType.add(socketType);
			count++;
			
		}
	}
	Memory[] memoryArray = new Memory[count];
	for (int i=0; i<memoryArray.length;i++) {
		memoryArray[i] = new Memory(memoryName.get(i),memoryPrice.get(i),memorySize.get(i),memorySpeed.get(i),memorySocketType.get(i));
	}
	return memoryArray;
}
	
	public static GraphicsCard[] createGC(String text) {
		ArrayList <String> gcName = new ArrayList<String>();
		ArrayList <Double> gcPrice = new ArrayList<Double>();
		ArrayList <Integer> gcCoreCount = new ArrayList<Integer>();
		ArrayList <Integer> gcClockSpeed = new ArrayList<Integer>();
		ArrayList <Integer> gcMemorySize = new ArrayList<Integer>();
		ArrayList<Integer> gcMemorySpeed = new ArrayList<Integer>();
		ArrayList <String> gcMemoryType = new ArrayList<String>();
		ArrayList <String> gcBusVersion = new ArrayList<String>();
		int count = 0;
		

		StringTokenizer tokenizer = new StringTokenizer(text, "\n");
		while(tokenizer.hasMoreTokens()) {
			String mytoken = tokenizer.nextToken();
			if(mytoken.equals("GraphicsCard")) {
				tokenizer.nextToken();
				String nameGC = tokenizer.nextToken();
				gcName.add(nameGC);
				tokenizer.nextToken();
				String priceGC = tokenizer.nextToken();
				gcPrice.add(Double.parseDouble(priceGC));
				tokenizer.nextToken();
				String corecountGC = tokenizer.nextToken();
				gcCoreCount.add(Integer.parseInt(corecountGC));
				tokenizer.nextToken();
				String clockspeedGC = tokenizer.nextToken();
				gcClockSpeed.add(Integer.parseInt(clockspeedGC));
				tokenizer.nextToken();
				String memorySizeGC = tokenizer.nextToken();
				gcMemorySize.add(Integer.parseInt(memorySizeGC));
				tokenizer.nextToken();
				String memorySpeedGC = tokenizer.nextToken();
				gcMemorySpeed.add(Integer.parseInt(memorySpeedGC));
				tokenizer.nextToken();
				String memoryTypeGC = tokenizer.nextToken();
				gcMemoryType.add(memoryTypeGC);
				tokenizer.nextToken();
				String busVersGC = tokenizer.nextToken();
				gcBusVersion.add(busVersGC);
				count++;
				}
		}
		GraphicsCard[] graphicsArray = new GraphicsCard[count];
		for(int i=0; i<graphicsArray.length; i++) {
			graphicsArray[i] = new GraphicsCard(gcName.get(i), gcPrice.get(i), gcCoreCount.get(i), gcClockSpeed.get(i), gcMemorySize.get(i),
							gcMemorySpeed.get(i), gcMemoryType.get(i), gcBusVersion.get(i));
		}
		return graphicsArray;
}
	
	
	public static HardDiskDrive[] createHardDisk(String text) {
		ArrayList<String> harddiskName = new ArrayList<String>();
		ArrayList<Double> harddiskPrice = new ArrayList<Double>();
		ArrayList<Integer> harddiskStorage = new ArrayList<Integer>();
		ArrayList<Integer> harddiskBandwith = new ArrayList<Integer>();
		int count=0;
		
		StringTokenizer tokenizer = new StringTokenizer(text , "\n");
		while(tokenizer.hasMoreTokens()) {
			String mytoken = tokenizer.nextToken();
			if(mytoken.equals("HardDiskDrive")) {
				tokenizer.nextToken();
				String hdName = tokenizer.nextToken();
				harddiskName.add(hdName);
				tokenizer.nextToken();
				String hdPrice = tokenizer.nextToken();
				harddiskPrice.add(Double.parseDouble(hdPrice));
				tokenizer.nextToken();
				String hdStorage = tokenizer.nextToken();
				harddiskStorage.add(Integer.parseInt(hdStorage));
				tokenizer.nextToken();
				String hdBandwith = tokenizer.nextToken();
				harddiskBandwith.add(Integer.parseInt(hdBandwith));
				count++;
			}
		}
		HardDiskDrive[] hddArray = new HardDiskDrive[count];
		for(int i=0; i<hddArray.length; i++) {
			hddArray[i] = new HardDiskDrive(harddiskName.get(i), harddiskPrice.get(i), harddiskStorage.get(i), harddiskBandwith.get(i));
		}
		return hddArray;
}

	public static SolidStateDrive[] createSSD(String text) {
		ArrayList<String> ssdName = new ArrayList<String>();
		ArrayList<Double> ssdPrice = new ArrayList<Double>();
		ArrayList<Integer> ssdStorage = new ArrayList<Integer>();
		ArrayList<Integer> ssdBandwith = new ArrayList<Integer>();
		int count=0;
		
		StringTokenizer tokenizer = new StringTokenizer(text , "\n");
		while(tokenizer.hasMoreTokens()) {
			String mytoken = tokenizer.nextToken();
			if(mytoken.equals("SolidStateDrive")) {
				tokenizer.nextToken();
				String namessd = tokenizer.nextToken();
				ssdName.add(namessd);
				tokenizer.nextToken();
				String pricessd = tokenizer.nextToken();
				ssdPrice.add(Double.parseDouble(pricessd));
				tokenizer.nextToken();
				String storagessd = tokenizer.nextToken();
				ssdStorage.add(Integer.parseInt(storagessd));
				tokenizer.nextToken();
				String bandwithssd = tokenizer.nextToken();
				ssdBandwith.add(Integer.parseInt(bandwithssd));
				count++;
			}	
	}
		SolidStateDrive[] ssdArray = new SolidStateDrive[count];
		for(int i=0; i<ssdArray.length; i++) {
			ssdArray[i] = new SolidStateDrive(ssdName.get(i), ssdPrice.get(i), ssdStorage.get(i), ssdBandwith.get(i));
			
		}
		return ssdArray;
}
public static Double calculate() {
	double total=0.0;
	if(Computer.item_motherboard != null) {
		total += Computer.item_motherboard_price;
		if(Computer.item_cpu != null) {
			total += Computer.item_cpu_price;
			if(Computer.item_memory != null) {
				total += Computer.item_memory_price;
				if(Computer.item_graphicscard != null) {
					total += Computer.item_graphicscard_price;
					if(Computer.item_hdd != null || Computer.item_ssd != null) {
						total += Computer.item_hdd_price;
						total += Computer.item_ssd_price;
					}
					return total;
				}
				return total;
			}
			return total;
			
		}
		return total;
	}
	return total;
}
	
	
	
	
} //main


class Action12 implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		JFrame MotherboardFrame = new MotherboardFrame();
		
	}
}




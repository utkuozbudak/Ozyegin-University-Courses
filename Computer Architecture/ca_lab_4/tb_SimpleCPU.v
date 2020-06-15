`timescale 1ns / 1ps

module tb;
parameter SIZE = 10, DEPTH = 1024;

reg clk;
initial begin
clk = 1;
forever
#5 clk = ~clk;
end

reg rst;
initial begin
// $dumpvars;
rst = 1;
repeat (10) @(posedge clk);
rst <= #1 0;
repeat (250) @(posedge clk);
repeat (100) begin
	@(posedge clk);
	if (testCount - 1 == 32) begin
			//pCounterCheck(32,"BZJi");
			memCheck(139,140,"CPIi");
			//$display("Total Errors %d\n", errorCount);
			$fwrite(f,"Total Errors %d", errorCount);
			$display("Total Errors %d", errorCount);
			$fclose(f); 
			$finish;
	end
end
$finish;
end

wire [31:0] data_toRAM;
wire [SIZE-1:0] pCounter;
integer f;
 

// For the other module instances, it's up to you
wire [9:0] addr_toRAM;
wire [31:0] data_fromRAM;
SimpleCPU VerySimpleCPU(
	.clk(clk), 
	.rst(rst), 
	.data_fromRAM(data_fromRAM), 
	.wrEn(wrEn), 
	.addr_toRAM(addr_toRAM), 
	.data_toRAM(data_toRAM), 
	.pCounter(pCounter)
);

blram #(SIZE, DEPTH) blramI(
	.clk(clk),
	.rst(rst),
	.i_we(wrEn), 
	.i_addr(addr_toRAM), 
	.i_ram_data_in(data_toRAM), 
	.o_ram_data_out(data_fromRAM)
);
blram #(SIZE, DEPTH) blramB(
	.clk(clk),
	.rst(rst),
	.i_we(wrEn), 
	.i_addr(addr_toRAM), 
	.i_ram_data_in(data_toRAM), 
	.o_ram_data_out()
);

initial begin
  f = $fopen("output.txt","w");
end

reg [7:0] testCount = 0;
reg [7:0] errorCount = 0;
always@(pCounter) begin
	if(!rst)begin
	case(testCount - 1)
		0: memCheck(101,2,"CP");
		1: memCheck(103,5,"CPi");
		2: memCheck(104,8,"SRL");
		3: memCheck(106,40,"SRL");
		4: memCheck(108,1,"SRLi");
		5: memCheck(109,144,"SRLi");
		6: memCheck(110,4294967295,"NAND");
		7: memCheck(110,0,"NAND");
		8: memCheck(112,4294967293,"NAND");
		9: memCheck(112,2,"NAND");
		10: memCheck(114,4294967295,"NANDi");
		11: memCheck(114,0,"NAND");
		12: memCheck(115,4294967293,"NANDi");
		13: memCheck(115,2,"NAND");
		14: memCheck(116,1,"LT");
		15: memCheck(117,0,"LT");
		16: memCheck(118,0,"LT");
		17: memCheck(120,1,"LTi");
		18: memCheck(121,0,"LTi");
		19: memCheck(122,0,"LTi");
		20: memCheck(123,0,"ADD");
		21: memCheck(124,0,"ADDi");
		22: memCheck(125,63,"MUL");
		23: memCheck(127,27,"MULi");
		24: pCounterCheck(26,"BZJ");
		//25: memCheck(130,2,"CPi");
		26: pCounterCheck(27,"BZJ");
		27: memCheck(133,3,"CPi");
		28: pCounterCheck(30,"BZJi");
		30: memCheck(136,5,"CPI"); 
		31: memCheck(140,5," CPI");
		32: memCheck(139,140,"CPIi"); 
	endcase
	testCount = pCounter + 1;
	end
end

task memCheck;
    input [31:0] memLocation, expectedValue;
	input [47:0] instCode; 
    begin
		#5;
      if(blramB.memory[memLocation] != expectedValue) begin
			$display("Error %d, Instruction  %s, %d ns, RAM Addr %d,  expected %d, received %d", testCount - 1, instCode, $time, memLocation, expectedValue, blramB.memory[memLocation]);
			$fwrite(f,"Error  %d, Instruction  %s, %d ns, RAM Addr %d,  expected %d, received %d\n", testCount - 1, instCode, $time, memLocation, expectedValue, blramB.memory[memLocation]);
			errorCount = errorCount + 1;
		end
		if(blramB.memory[memLocation] == expectedValue) begin
			$display("Correct %d, Instruction code %s, %d ns, RAM Addr %d,  expected %d, received %d", testCount - 1, instCode, $time, memLocation, expectedValue, blramB.memory[memLocation]);
			$fwrite(f,"Correct  %d, Instruction code %s, %d ns, RAM Addr %d,  expected %d, received %d\n", testCount - 1, instCode, $time, memLocation, expectedValue, blramB.memory[memLocation]);
		end
    end
endtask

task pCounterCheck;
    input [31:0] pCounterExpected, instCode; 
    begin
      if(pCounter != pCounterExpected) begin
			$display("Error  %d, Instruction code %s, %d ns expected %d, received %d", testCount - 1, instCode, $time, pCounterExpected, pCounter);
			$fwrite(f,"Error  %d, Instruction code %s, %d ns expected %d, received %d\n", testCount - 1, instCode, $time, pCounterExpected, pCounter);
			errorCount = errorCount + 1;
		end
		else begin
			$display("Correct %d, Instruction code %s, %d ns expected %d, received %d", testCount - 1, instCode, $time, pCounterExpected, pCounter);
			$fwrite(f,"Correct %d, Instruction code %s, %d ns expected %d, received %d\n", testCount - 1, instCode, $time, pCounterExpected, pCounter);
		end
    end
endtask

endmodule
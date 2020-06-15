`timescale 1ns / 1ps
module UDC_tb;

reg clk, rst, upDown; 
wire [7:0] dataOut;
wire [3:0] anodeOut;
reg [7:0] data [0:9];
reg [7:0] datasize;

integer errorFlag, jj;

initial $readmemb("patterns", data);

// Instantiate the Unit Under Test (UUT)
UDC DUT(.upDown(upDown), .clk(clk), .rst(rst), .seg(dataOut), .an(anodeOut));

initial begin
	clk = 1;
	forever
		#5 clk = ~clk;
end

initial begin
	datasize  = 10;
	errorFlag = 0;
	rst       = 0;	
	repeat(5) @(posedge clk);
	rst       <= #1 1;
	
	@(posedge clk);
	rst       <= #1 0;	
	
	upDown = 1;
	jj = 0;
	repeat(datasize*2) begin
		@(posedge clk);
		if(dataOut !== data[jj]) begin
			$display("Output Error at time %d, expected %d, received %d", $time, data[jj], dataOut);
			errorFlag = errorFlag +1;
		end
		#1;
		jj = jj +1;
		if(jj == 10)
			jj = 0;
	end
	upDown = 0;
	repeat(datasize*2) begin
		@(posedge clk);
		#1;
		if(dataOut !== data[jj]) begin
			$display("Output Error at time %d, expected %d, received %d", $time, data[jj], dataOut);
			errorFlag = errorFlag +1;
		end
		if(jj == 0)
			jj = 10;	
		jj = jj - 1;
	end	

	if(errorFlag == 0)
		$display("No Errors Found!");
	else
		$display("%d Errors Found!", errorFlag);
	$finish;
end
endmodule

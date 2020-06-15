`timescale 1ns / 1ps
module led_tb;

reg        clk, rst;
wire [7:0] dataOut;
reg  [7:0] data [0:10];
reg  [7:0] datasize;

parameter COUNT = 1'b1; 
integer errorFlag, jj;

initial $readmemb("patterns", data);

// Instantiate the Unit Under Test (UUT)
led #(.COUNT(COUNT)) led0(.clk(clk), .rst(rst), .dataOut(dataOut));

initial begin
	clk = 1;
	forever
		#5 clk = ~clk;
end

initial begin
	datasize  = 11;
	errorFlag = 0;
	rst       = 0;
	
	repeat(5) @(posedge clk);
	rst       <= #1 1;
	
	@(posedge clk);
	rst       <= #1 0;

	jj = 0;
	repeat(datasize) @(dataOut) begin
		@(posedge clk);
		if(dataOut !== data[jj]) begin
			$display("Output Error at time %d, expected %d, received %d", $time, data[jj], dataOut);
			errorFlag = errorFlag +1;
		end
		#1;
		jj = jj +1;
	end

	if(errorFlag == 0)
		$display("No Errors Found!");
	else
		$display("%d Errors Found!", errorFlag);
	$finish;
end
endmodule

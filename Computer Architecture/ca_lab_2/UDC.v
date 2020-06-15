`timescale 1ns / 1ps
module UDC(upDown, rst, clk, seg, an);

	input upDown;
	input rst;
	input clk;
	output reg [7:0] seg;
	output reg [3:0] an;
	
	reg [3:0] cnt, cntNext;
///////////////////////////////////////////////////////////////////////////
//////// You need to change code until simulation errors gone /////////////	
///////////////////////////////////////////////////////////////////////////
	always @(posedge clk) begin
		cnt <= #1 cntNext;
	end

	always @(*) begin
		cntNext = cnt;
		if(rst)
			cntNext = 0; 
		else begin
			// Fill here...
			
			repeat(upDown == 1) begin
			
				cntNext = cntNext +1;
				if(cntNext == 10) 
					cntNext = 0;
			end
			
			repeat(upDown == 0) begin
			
				if(cntNext == 0)
					cntNext = 10;
				cntNext = cntNext -1;
			end
			
			
		end
	end
///////////////////////////////////////////////////////////////////////////
//////////// Just change the place that says "Fill here..." ///////////////	
///////////////////////////////////////////////////////////////////////////
	always @(*) begin
		an  = 4'b0111;
		seg = 8'b1111_1111;
		if(rst) begin 
			an  = 4'b0111;
			seg = 8'b1111_1111;
		end else begin
			case(cnt)
			0: seg = 8'b11000000; 
			1: seg = 8'b11111001;
			2: seg = 8'b10100100;
			3: seg = 8'b10110000;
			4: seg = 8'b10011001;
			5: seg = 8'b10010010;
			6: seg = 8'b10000010;
			7: seg = 8'b11111000;
			8: seg = 8'b10000000;
			9: seg = 8'b10010000;
			default: seg = 8'b11000000;
			endcase
		end
	end
endmodule

`timescale 1ns / 1ps
module calc(clk, rst, validIn, dataIn, dataOut);
input clk, rst, validIn;
input  [7:0] dataIn;
output reg [7:0] dataOut;

reg [1:0] state, stateNext;
reg [7:0] number, numberNext, dataOutNext;
reg [2:0] operation, operationNext;

always @(posedge clk) begin
	state         <= #1 stateNext;
	number        <= #1 numberNext;
	operation     <= #1 operationNext;
	dataOut       <= #1 dataOutNext;
end

always @(*) begin
	stateNext     = state;
	numberNext    = number;
	operationNext = operation;
	dataOutNext   = dataOut;
	if(rst) begin
		// ---> FILL IN HERE!!!
		stateNext = 0;
		numberNext = 0;
		operationNext = 0;
		dataOutNext = 0;
		
	end else begin
		case(state)
			0: // WAITING FIRST OPERAND
			begin
				// ---> FILL IN HERE!!!
				
				if(validIn == 0) begin
				end else if(validIn==1) begin
				
				numberNext = dataIn;
				dataOutNext = numberNext;
				stateNext = 1;
				end;
				
			end
			1: // WAITING OPERATION
			begin
				// ---> FILL IN HERE!!!	
				if(validIn==0) begin
				end else if (validIn == 1) begin
				
				operationNext = dataIn;
				dataOutNext = operationNext;
				
				if(operationNext==0) begin
				stateNext = 2;
				end else if(operationNext == 1) begin
				stateNext = 2;
				end else if(operationNext==2) begin
				stateNext = 2; 
				
				end else if(operationNext == 3) begin
				dataOutNext = number * number;
				stateNext=0;
				end else if(operationNext == 4) begin
				dataOutNext = number + 2;
				stateNext=0;
				end else if(operationNext == 5) begin
				dataOutNext = number - 2;
				stateNext=0;
				end
				end
				
			end
			2: // WAITING SECOND OPERAND (IF NEEDED)
			begin 
				// ---> FILL IN HERE!!!
				if(validIn==0) begin
				end else if(validIn==1) begin
				
				if(operationNext == 0) begin
				dataOutNext = dataIn * number;
				end else if(operationNext == 1) begin
				dataOutNext = dataIn + number;
				end else if(operationNext == 2) begin
				dataOutNext = number - dataIn;
				end
				stateNext = 0;
				end
				
			end	
		endcase
	end
end
endmodule

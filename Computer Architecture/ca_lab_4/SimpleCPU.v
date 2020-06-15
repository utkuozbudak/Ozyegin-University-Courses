`timescale 1ns / 1ps
module SimpleCPU(clk, rst, data_fromRAM, wrEn, addr_toRAM, data_toRAM, pCounter);
 
parameter SIZE = 10;

input clk, rst;
input wire [31:0] data_fromRAM;
output reg wrEn;
output reg [SIZE-1:0] addr_toRAM;
output reg [31:0] data_toRAM;
output reg [SIZE-1:0] pCounter;

// internal signals
reg [SIZE-1:0] pCounterNext;
reg [ 3:0] opcode, opcodeNext;
reg [13:0] operand1, operand2, operand1Next, operand2Next;
reg [31:0] num1, num2, num1Next, num2Next;
reg [ 4:0] state, stateNext;


always @(posedge clk)begin
	state    <= #1 stateNext;
	pCounter <= #1 pCounterNext;
	opcode   <= #1 opcodeNext;
	operand1 <= #1 operand1Next;
	operand2 <= #1 operand2Next;
	num1     <= #1 num1Next;
	num2     <= #1 num2Next;
end

always @*begin
	stateNext    = state;
	pCounterNext = pCounter;
	opcodeNext   = opcode;
	operand1Next = operand1;
	operand2Next = operand2;
	num1Next     = num1;
	num2Next     = num2;
	addr_toRAM   = 0;
	wrEn         = 0;
	data_toRAM   = 0;
if(rst)
	begin
	stateNext    = 0;
	pCounterNext = 0;
	opcodeNext   = 0;
	operand1Next = 0;
	operand2Next = 0;
	num1Next     = 0;
	num2Next     = 0;
	addr_toRAM   = 0;
	wrEn         = 0;
	data_toRAM   = 0;
	end
else 
	case(state)                       
		0: begin         // "addr_toRAM = pCounter" => read memory location of pCounter
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = 0;
			operand2Next = 0;
			addr_toRAM   = pCounter;
			num1Next     = 0;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 1;
		end 
		1:begin          // take opcode and request *A
			pCounterNext = pCounter;
			opcodeNext   = data_fromRAM[31:28];
			operand1Next = data_fromRAM[27:14];
			operand2Next = data_fromRAM[13: 0];
			addr_toRAM   = data_fromRAM[27:14];
			num1Next     = 0;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			if(opcodeNext == 4'b1101) // BZJi
				stateNext = 4;
			if(opcodeNext == 4'b0000) // ADD INSTRUCTION
				stateNext = 2;
			if(opcodeNext == 4'b0001) // ADDi
				stateNext = 5;
			if(opcodeNext == 4'b1100) // BZJ
				stateNext = 6;
			if(opcodeNext == 4'b0110) // LT
				stateNext = 8;
			if(opcodeNext == 4'b0111) //LTi
				stateNext = 10;
			if(opcodeNext == 4'b1000) //CP
				stateNext = 11;
			if(opcodeNext == 4'b1001) //CPi
				stateNext = 13;
			if(opcodeNext == 4'b1110) //MUL
				stateNext = 14;
			if(opcodeNext == 4'b1111) //MULi
				stateNext = 16;
			if(opcodeNext == 4'b0010) //NAND
				stateNext = 17;
			if(opcodeNext == 4'b0011) //NANDi
				stateNext = 19;
			if(opcodeNext == 4'b0100) //SRL
				stateNext = 20;
			if(opcodeNext == 4'b0101) //SRLi
				stateNext = 22;
			if(opcodeNext == 4'b1010)//CPI
				stateNext = 23;
			if(opcodeNext == 4'b1011) //CPIi
				stateNext = 26;


		end
		2: begin         // (ADD) request *B and take *A 
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = data_fromRAM;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 3;
		end
		3: begin         // (ADD) take *B 
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
			num1Next     = num1;
			num2Next     = data_fromRAM;
			wrEn = 1;
			if(opcode == 4'b0000)
				data_toRAM = num1 + data_fromRAM;
			stateNext = 0;
		end
		4: begin //(BZJi)
			pCounterNext = data_fromRAM + operand2;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;	
			num1Next     = data_fromRAM;
			num2Next     = operand2;
			wrEn         = 0;
			data_toRAM   = 32'hFFFF_FFFF;
			stateNext = 0;
		end
		
		5: begin //ADDi
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1; // A
			operand2Next = operand2; // B value
			addr_toRAM   = operand1;
			num1Next     = data_fromRAM; // Inside *A
			num2Next     = operand2; // B value
			wrEn         = 1;
			if(opcode == 4'b0001)
				data_toRAM = data_fromRAM + operand2; // *A + B
			stateNext = 0;
		end
		
		6: begin //BZJ
			pCounterNext = pCounter;
			opcodeNext = opcode;
			operand1Next = operand1; // A
			operand2Next = operand2; // B
			addr_toRAM = operand2; // To get *B in next cycle
			num1Next = data_fromRAM; // Inside *A
			num2Next = 0;
			wrEn = 0;
			data_toRAM = 0;
			stateNext = 7;
		end
		
		7: begin //BZJ contd
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
		   num1Next     = num1;
			num2Next     = data_fromRAM; // Inside *B
			wrEn = 0;
			data_toRAM = 32'hFFFF_FFFF;
			if(data_fromRAM == 0) // Check *B
				pCounterNext = num1;
			if(data_fromRAM != 0) // Check *B
				pCounterNext = pCounter + 1;
			stateNext = 0;
		end
		
		8: begin //LT
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = data_fromRAM;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 9;
		end
		
		9: begin //LT contd
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
			num1Next     = num1;
			num2Next     = data_fromRAM;
			wrEn         = 1;
			if(num1 < data_fromRAM)
				data_toRAM = 1;
			else if (num1 >= data_fromRAM)
				data_toRAM = 0;
			stateNext = 0;
		end
		
		10: begin //LTi
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1; // A
			operand2Next = operand2; // B value
			addr_toRAM   = operand1;
			num1Next     = data_fromRAM; // Inside *A
			num2Next     = operand2; // B value
			wrEn = 1;
			if(data_fromRAM < operand2)
				data_toRAM = 1;
			else if (data_fromRAM >= operand2)
				data_toRAM = 0;
			stateNext = 0;
		end
		
		11: begin //CP
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = data_fromRAM;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 12;
		end
		
		12: begin //CP contd
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
			num1Next     = num1;
			num2Next     = data_fromRAM;
			wrEn         = 1;
			data_toRAM = data_fromRAM;
			stateNext = 0;
		end
		
		13: begin //CPi
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1; // A
			operand2Next = operand2; // B value
			addr_toRAM   = operand1;
			num1Next     = data_fromRAM; // Inside *A
			num2Next     = operand2; // B value
			wrEn         = 1;
			data_toRAM = operand2;
			stateNext = 0;
		end
		
		14: begin //MUL
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = data_fromRAM;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 15;
		
		end
		
		15: begin //MUL contd
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
			num1Next     = num1;
			num2Next     = data_fromRAM;
			wrEn         = 1;
			data_toRAM = data_fromRAM * num1;
			stateNext = 0;
		end
		
		16: begin //MULi
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1; // A
			operand2Next = operand2; // B value
			addr_toRAM   = operand1;
			num1Next     = data_fromRAM; // Inside *A
			num2Next     = operand2; // B value
			wrEn         = 1;
			data_toRAM = data_fromRAM * operand2;
			stateNext = 0;
		end
		
		17: begin // NAND
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = data_fromRAM;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 18;
		end
		
		18: begin //NAND condt
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
			num1Next     = num1;
			num2Next     = data_fromRAM;
			wrEn         = 1;
			data_toRAM = ~(num1 & data_fromRAM);
			stateNext = 0;
		end
		
		19: begin //NANDi
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1; // A
			operand2Next = operand2; // B value
			addr_toRAM   = operand1;
			num1Next     = data_fromRAM; // Inside *A
			num2Next     = operand2; // B value
			wrEn         = 1;
			data_toRAM = ~(data_fromRAM & operand2) ;
			stateNext = 0;
		end
		
		20: begin //SRL
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = data_fromRAM;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 21;
		end
		
		21: begin //SRL contd
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
			num1Next     = num1;
			num2Next     = data_fromRAM;
			wrEn         = 1;
			if(data_fromRAM < 32)
				data_toRAM = num1 >> data_fromRAM;
			else if(data_fromRAM >=32)
				data_toRAM = num1 << (data_fromRAM - 32);
			stateNext = 0;
		end
		
		22: begin //SRLi
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1; // A
			operand2Next = operand2; // B value
			addr_toRAM   = operand1;
			num1Next     = data_fromRAM; // Inside *A
			num2Next     = operand2; // B value
			wrEn         = 1;
			if(operand2 < 32)
				data_toRAM = data_fromRAM >> operand2;
			else if(operand2 >= 32)
				data_toRAM = data_fromRAM << (operand2 - 32);
			stateNext = 0;
		end
		
		23: begin //CPI
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = 0;
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 24;
		end
		
		24: begin //CPI contd
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = data_fromRAM; 
			num1Next     = 0; 
			num2Next     = 0;
			wrEn         = 0;
			stateNext    = 25;
		end
		
		25: begin // CPI contd
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand1;
			num1Next     = 0;
			num2Next     = data_fromRAM; // or 0
			wrEn         = 1;
			data_toRAM   = data_fromRAM;
			stateNext    = 0;
		end
		
		26: begin //CPIi
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = operand2;
			num1Next     = data_fromRAM; // *A
			num2Next     = 0;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 27;
		end
		
		27: begin //CPIi contd
			pCounterNext = pCounter;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = num1;
			num1Next     = num1;
			num2Next     = data_fromRAM;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 28;
		end
		
		28: begin //CPIi contd
			pCounterNext = pCounter + 1;
			opcodeNext   = opcode;
			operand1Next = operand1;
			operand2Next = operand2;
			addr_toRAM   = num1;
			num1Next     = 0;
			num2Next     = num2;
			wrEn         = 1;
			data_toRAM   = num2;
			stateNext    = 0;
		end 
		
	
		default: begin
			stateNext    = 0;
			pCounterNext = 0;
			opcodeNext   = 0;
			operand1Next = 0;
			operand2Next = 0;
			num1Next     = 0;
			num2Next     = 0;
			addr_toRAM   = 0;
			wrEn         = 0;
			data_toRAM   = 0;
		end
	endcase

end

endmodule

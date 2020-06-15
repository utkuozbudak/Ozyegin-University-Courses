module projectCPU2020(
  clk,
  rst,
  wrEn,
  data_fromRAM,
  addr_toRAM,
  data_toRAM,
  PC,
  W
);

input clk, rst;
input [15:0] data_fromRAM;
output reg[15:0] data_toRAM;
output reg wrEn;

// 12 can be made smaller so that it fits in the FPGA
output reg [12:0] addr_toRAM;
output reg [12:0] PC; // This has been added as an output for TB purposes
output reg [15:0] W; // This has been added as an output for TB purposes

reg [2:0] opcode, opcodeNext;
reg [12:0] operand1, operand1Next , pcNext;
reg [15:0] wNext;
reg [3:0] state, stateNext;

always @(posedge clk)begin
    state     <= #1 stateNext;
	 W         <= #1 wNext;
	 PC        <= #1 pcNext;
    opcode    <= #1 opcodeNext;
    operand1  <= #1 operand1Next;
end

always @*begin
    stateNext    = state;
    opcodeNext   = opcode;
    operand1Next = operand1;
	 pcNext       = PC;
	 wNext        = W;
	 addr_toRAM   = 0;
	 wrEn         = 0;
	 data_toRAM   = 0;
if(rst)
    begin
    stateNext    = 0;
    pcNext       = 0;
    opcodeNext   = 0;
    operand1Next = 0;
    wNext        = 0;
	 wrEn         = 0;
	 addr_toRAM   = 0;
	 data_toRAM   = 0;
    end
else
	case(state)
		0:begin
			pcNext       = PC;
			wNext        = W;
			opcodeNext   = opcode;
			operand1Next = 0;
			addr_toRAM   = PC;
			wrEn         = 0;
			data_toRAM   = 0;
			stateNext    = 1;
		end   
			1:begin
			pcNext       = PC;
			opcodeNext   = data_fromRAM[15:13];
			operand1Next = data_fromRAM[12:0];	
			addr_toRAM   = data_fromRAM[12:0];
			wrEn         = 0;
			data_toRAM   = 0;
			
			if(opcodeNext == 3'b111) //JMP
				stateNext = 2;
			if(opcodeNext == 3'b101) //CP2W
				stateNext = 3;
			if(opcodeNext == 3'b000) //ADD
				stateNext = 4;
			if(opcodeNext == 3'b001) //NOR
				stateNext = 5;
			if(opcodeNext == 3'b010) //SRRL
				stateNext = 6;
			if(opcodeNext == 3'b011) //GT
				stateNext = 7;
			if(opcodeNext == 3'b100) //SZ
				stateNext = 8;
			if(opcodeNext == 3'b110) // CPfW
				stateNext = 9;
      end
			2:begin // JMP
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				pcNext       = data_fromRAM[12:0];
				opcodeNext   = opcode;
				operand1Next = operand1;
				addr_toRAM   = operand1;
				wrEn         = 0;
				data_toRAM   = 0;
				stateNext    = 0;
				end
			end
			3:begin // CP2W
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				pcNext       = PC + 1;
				opcodeNext   = opcode;
				operand1Next = operand1;
				addr_toRAM   = operand1;
				wNext        = data_fromRAM;
				wrEn         = 0;
				data_toRAM   = 0;
				stateNext    = 0;
				end
			end
			4:begin // ADD
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				pcNext       = PC + 1;
				opcodeNext   = opcode;
				operand1Next = operand1;
				addr_toRAM   = operand1;
				wNext        = W + data_fromRAM;
				wrEn         = 0;
				data_toRAM   = 0;
				stateNext    = 0;
				end
			end
			5:begin //NOR
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				pcNext       = PC + 1;
				opcodeNext   = opcode;
				operand1Next = operand1;
				addr_toRAM   = operand1;
				wNext        = ~(W | (data_fromRAM));
				wrEn         = 0;
				data_toRAM   = 0;
				stateNext    = 0;
				end
			end
			6:begin //SRRL
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				pcNext       = PC + 1;
				opcodeNext   = opcode;
				operand1Next = operand1;
				addr_toRAM   = operand1;
				if(data_fromRAM < 16)
					wNext = W >> data_fromRAM;
				else if(data_fromRAM < 31)
					wNext = W << data_fromRAM[3:0];
				else if(data_fromRAM < 47)
					wNext = {W >> data_fromRAM[3:0] | W <<(16-data_fromRAM[3:0])};
				else
					wNext = {W << data_fromRAM[3:0] | W >>(16-data_fromRAM[3:0])};
				wrEn         = 0;
				data_toRAM   = 0;
				stateNext    = 0;
				end
			end
			7:begin //GT
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				pcNext        = PC + 1;
				opcodeNext    = opcode;
				operand1Next  = operand1;
				addr_toRAM    = operand1;
				if(W > data_fromRAM)
					wNext = 1;
				else if(W <= data_fromRAM)
					wNext = 0;
				wrEn          = 0;
				data_toRAM    = 0;
				stateNext     = 0;
				end
			end
			8:begin //SZ
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				opcodeNext     = opcode;
				operand1Next   = operand1;
				addr_toRAM     = operand1;
				wrEn           = 0;
				data_toRAM     = 0;
				if(data_fromRAM == 0)
					pcNext = PC + 2;
				else if(data_fromRAM != 0)
					pcNext  = PC + 1;
				stateNext      = 0;
				end
			end
			9:begin // CPfW
				if(operand1Next == 0)begin
					stateNext = 10;
					addr_toRAM = 2;
				end
				else begin
				pcNext        = PC + 1;
				opcodeNext    = opcode;
				operand1Next  = operand1;
				addr_toRAM    = operand1;
				wrEn          = 1;
				data_toRAM    = W;
				stateNext     = 0;
				end
			end
			10:begin //transition state for instructions except CPfW, request *2
				if(opcodeNext == 3'b110) begin
					pcNext        = PC + 1;
					opcodeNext    = opcode;
					operand1Next  = operand1;
					addr_toRAM    = data_fromRAM;
					wrEn          = 1;
					data_toRAM    = W;
					stateNext     = 0;
				end
				else begin
				pcNext         = PC;
				opcodeNext     = opcode;
				operand1Next   = operand1;
				addr_toRAM     = data_fromRAM;
				wrEn           = 0;
				stateNext      = 11;
				end
			end
			11:begin
				if(opcodeNext == 3'b100) begin
					opcodeNext     = opcode;
					operand1Next   = operand1;
					addr_toRAM     = operand1;
					wrEn           = 0;
					data_toRAM     = 0;
					if(data_fromRAM == 0)
						pcNext = PC + 2;
					else if(data_fromRAM != 0)
						pcNext  = PC + 1;
					stateNext      = 0;
					end
				else if(opcodeNext == 3'b011) begin
					pcNext        = PC + 1;
					opcodeNext    = opcode;
					operand1Next  = operand1;
					addr_toRAM    = operand1;
					if(W > data_fromRAM)
						wNext = 1;
					else if(W <= data_fromRAM)
						wNext = 0;
					wrEn          = 0;
					data_toRAM    = 0;
					stateNext     = 0;
				end
				else if(opcodeNext == 3'b111) begin
					pcNext       = data_fromRAM[12:0];
					opcodeNext   = opcode;
					operand1Next = operand1;
					addr_toRAM   = operand1;
					wrEn         = 0;
					data_toRAM   = 0;
					stateNext    = 0;
				end
				else if(opcodeNext == 3'b000) begin
					pcNext       = PC + 1;
					opcodeNext   = opcode;
					operand1Next = operand1;
					addr_toRAM   = operand1;
					wNext        = W + data_fromRAM;
					wrEn         = 0;
					data_toRAM   = 0;
					stateNext    = 0;
				end
				else if(opcodeNext == 3'b001) begin
					pcNext       = PC + 1;
					opcodeNext   = opcode;
					operand1Next = operand1;
					addr_toRAM   = operand1;
					wNext        = ~(W | (data_fromRAM));
					wrEn         = 0;
					data_toRAM   = 0;
					stateNext    = 0;
				end
				else if(opcodeNext == 3'b101) begin
					pcNext       = PC + 1;
					opcodeNext   = opcode;
					operand1Next = operand1;
					addr_toRAM   = operand1;
					wNext        = data_fromRAM;
					wrEn         = 0;
					data_toRAM   = 0;
					stateNext    = 0;
				end
				else if(opcodeNext == 3'b010) begin	
					pcNext       = PC + 1;
					opcodeNext   = opcode;
					operand1Next = operand1;
					addr_toRAM   = operand1;
					if(data_fromRAM < 16)
						wNext = W >> data_fromRAM;
					else if(data_fromRAM < 31)
						wNext = W << data_fromRAM[3:0];
					else if(data_fromRAM < 47)
						wNext = {W >> data_fromRAM[3:0] | W <<(16-data_fromRAM[3:0])};
					else
						wNext = {W << data_fromRAM[3:0] | W >>(16-data_fromRAM[3:0])};
					wrEn         = 0;
					data_toRAM   = 0;
					stateNext    = 0;
					end
				end
		endcase
	end
endmodule

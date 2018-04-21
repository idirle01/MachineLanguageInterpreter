# SDP-2018-Coursework01 - Simple Machine Language
# Author
Iulia Dirleci (idirle01)
# Approach
In the first instance, the exisiting code has been amended to allow Machine to handle the other types of instructions. 
For this purpose, the following classes have been added:

* OutInstruction to handle `out` instructions
* SubInstruction to handle `sub` instructions
* MulInstruction to handle `mul` instructions
* DivInstruction to handle `div` instructions
* BnzInstruction to handle `bnz` instructions
	
The `getInstruction` method in the Machine.kt class was then amended to make use of reflection when creating the instructions.

# Testing
test*.sml files (see repo) were used to test the functionality of this program. 
Several situations where errors could occur have been tested:
* If the user runs the program with unknown opcode instructions, the program treats these as null/NoOpInstructions, which are just placeholders that do not do anything (test5.sml)
```
Here is the program; it has 6 instructions.
f0: noop 1
f1: lin register 1 value of 5
f2: lin register 2 value of 5
f3: add 2 + 3 to 1
f4: noop 3
f5: out register 1

Beginning program execution...
The value of register 1 is 5
Ending program execution.
Values of registers at program termination: sml.sml.Registers(registers=[0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]).

Process finished with exit code 0
```




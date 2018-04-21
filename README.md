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
If the user runs the program with unknown instructions, these will be treated as null/NoOpInstructions, which are just placeholders that do not do anything. 
test*.sml files (see repo) were used to test the functionality of this program. 


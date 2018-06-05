# SDP-2018-CW01 - Simple Machine Language
# Author
Iulia Dirleci (idirle01)
# Overview
In the first instance, the existing code has been amended to allow Machine to handle the other types of instructions. 
For this purpose, the following classes have been added:

* OutInstruction to handle `out` instructions
* SubInstruction to handle `sub` instructions
* MulInstruction to handle `mul` instructions
* DivInstruction to handle `div` instructions
* BnzInstruction to handle `bnz` instructions
	
The `getInstruction` method in the Machine.kt class was then amended to make use of reflection when creating the instructions.

# Approach
test*.sml files (see repo) were used to test the functionality of this program. 

Several situations have been tested:
* If the user runs the program with unknown opcode instructions, the program treats it as a NoOpInstructions, which is just a placeholder that does not do anything (`test5.sml` output reproduced below)
```
Here is the program; it has 5 instructions.
f0: noop 1
f1: lin register 1 value of 5
f2: lin register 2 value of 5
f3: add 1 + 2 to 1
f4: out register 1

Beginning program execution...
The value of register 1 is 10
Ending program execution.
Values of registers at program termination: sml.sml.Registers(registers=[0, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]).

Process finished with exit code 0
```

* Similarly, if a branch instruction references an instruction that does not exist, the program ignores it and continues execution (`test11.sml`):
```
Here is the program; it has 7 instructions.
f0: lin register 20 value of 6
f1: lin register 21 value of 1
f2: lin register 22 value of 1
f3: mul 21 * 20 to 21
f4: sub 20 - 22 to 20
f5: bnz 20
f6: out register 21

Beginning program execution...
The value of register 21 is 6
Ending program execution.
Values of registers at program termination: sml.sml.Registers(registers=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]).

Process finished with exit code 0
```
* If a given instruction contains more arguments than necessary, the extra arguments willl just be ignored. This is the case with instructions 3-6 in `test7.sml`:
```
Here is the program; it has 9 instructions.
f0: noop 1
f1: lin register 1 value of 5
f2: lin register 2 value of 5
f3: add 2 + 1 to 1
f4: div 1 / 2 to 1
f5: mul 1 * 2 to 3
f6: sub 3 - 1 to 3
f7: noop 3
f8: out register 1

Beginning program execution...
The value of register 1 is 2
Ending program execution.
Values of registers at program termination: sml.sml.Registers(registers=[0, 2, 5, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]).

Process finished with exit code 0
```

# Known issues
* Division by zero is an obvious case where errors can occur. The approach chosen was to let an ArithmeticException to happen so as to prevent next instructions from using erroneous data. Indeed, when `test5_2.sml` is run, the following output is obtained:
```
Here is the program; it has 9 instructions.
Exception in thread "main" java.lang.ArithmeticException: / by zero
f0: noop 1
f1: lin register 1 value of 5
	at sml.instructions.DivInstruction.execute(DivInstruction.kt:11)
f2: lin register 2 value of 5
f3: add 1 + 2 to 1
	at sml.Machine.execute(Machine.kt:52)
f4: div 1 / 2 to 1
	at sml.MainKt.main(main.kt:21)
f5: div 1 / 2 to 1
f6: div 2 / 1 to 2
f7: noop 3
f8: out register 1

Beginning program execution...
Process finished with exit code 1
```
* An IndexOutOfBoundsException will be thrown if the number of instruction parameters is less than the number required for that specific instruction. Below is the output obtained after running `test6.sml`

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 2147483647
	at sml.Registers.getRegister(Registers.kt:31)
	at sml.instructions.AddInstruction.execute(AddInstruction.kt:14)
	at sml.Machine.execute(Machine.kt:52)
	at sml.MainKt.main(main.kt:21)
Here is the program; it has 3 instructions.
f0: lin register 1 value of 10
f1: lin register 2 value of 10
f2: add 2 + 2147483647 to 1

Beginning program execution...
Process finished with exit code 1
```
* An IndexOutOfBoundsException will also be thrown if the register referred to in the instruction is not in an integer, like in the output obtained after running `test9.sml`:

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 2147483647
	at sml.Registers.getRegister(Registers.kt:31)
	at sml.instructions.AddInstruction.execute(AddInstruction.kt:14)
	at sml.Machine.execute(Machine.kt:52)
	at sml.MainKt.main(main.kt:21)
Here is the program; it has 3 instructions.
f0: lin register 1 value of 10
f1: lin register 2 value of 10
f2: add 1 + 2147483647 to 1

Beginning program execution...
Process finished with exit code 1
```

* An IndexOutOfBoundsException will also be thrown if an instruction references a register number smaller than zero or bigger than 31 (`test10.sml`) 
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 32Here is the program; it has 1 instructions.
f0: lin register 32 value of 6

Beginning program execution...
	at sml.Registers.setRegister(Registers.kt:27)
	at sml.instructions.LinInstruction.execute(LinInstruction.kt:9)
	at sml.Machine.execute(Machine.kt:52)
	at sml.MainKt.main(main.kt:21)

Process finished with exit code 1
```





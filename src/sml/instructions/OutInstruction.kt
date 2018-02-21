package sml.instructions

import sml.Instruction
import sml.Machine

class OutInstruction(label: String, val register: Int) : Instruction(label, "out") {

    override fun execute(m: Machine) {
        println()
        println("The value of register " + register + " is " + m.registers.getRegister(register))
    }

    override fun toString(): String {
        return super.toString() + " register " + register
    }

}
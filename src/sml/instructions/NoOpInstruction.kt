package sml.instructions

import sml.Instruction
import sml.Machine

// NoOpInstruction is used for representing a null instruction
class NoOpInstruction(label: String, val line: String) : Instruction(label, "noop") {
    override fun execute(m: Machine) {
    }

    override fun toString(): String {
        return super.toString() + line
    }
}
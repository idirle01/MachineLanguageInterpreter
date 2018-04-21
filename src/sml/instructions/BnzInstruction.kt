package sml.instructions

import sml.Instruction
import sml.Machine


class BnzInstruction(label: String, val registerToCheck: Int, val secondLabel: String) : Instruction(label, "bnz") {

    override fun execute(m: Machine) {

        if (m.registers.getRegister(registerToCheck) != 0) {
            m.jumpToInstruction(secondLabel)
        }

    }

    override fun toString(): String {
        return super.toString() + " " + registerToCheck
    }

}
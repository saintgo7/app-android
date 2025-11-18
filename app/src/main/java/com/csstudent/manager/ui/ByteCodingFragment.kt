package com.csstudent.manager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.csstudent.manager.R
import com.csstudent.manager.databinding.FragmentByteCodingBinding

class ByteCodingFragment : Fragment() {

    private var _binding: FragmentByteCodingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentByteCodingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnConvert.setOnClickListener {
            convertNumber()
        }

        binding.btnAnd.setOnClickListener {
            performBitOperation("AND")
        }

        binding.btnOr.setOnClickListener {
            performBitOperation("OR")
        }

        binding.btnXor.setOnClickListener {
            performBitOperation("XOR")
        }

        binding.btnNot.setOnClickListener {
            performBitOperation("NOT")
        }

        binding.btnLeftShift.setOnClickListener {
            performBitOperation("LEFT_SHIFT")
        }

        binding.btnRightShift.setOnClickListener {
            performBitOperation("RIGHT_SHIFT")
        }

        binding.btnUnsignedRightShift.setOnClickListener {
            performBitOperation("UNSIGNED_RIGHT_SHIFT")
        }

        binding.btnCountOnes.setOnClickListener {
            performBitOperation("COUNT_ONES")
        }
    }

    private fun convertNumber() {
        val input = binding.inputDecimal.text.toString()
        if (input.isEmpty()) {
            Toast.makeText(context, R.string.enter_number, Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val decimal = input.toInt()
            val binary = Integer.toBinaryString(decimal)
            val hex = Integer.toHexString(decimal).uppercase()
            val octal = Integer.toOctalString(decimal)

            binding.resultBinary.text = "2진수: $binary"
            binding.resultHex.text = "16진수: 0x$hex"
            binding.resultOctal.text = "8진수: $octal"
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "올바른 숫자를 입력하세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performBitOperation(operation: String) {
        val num1Str = binding.inputNum1.text.toString()
        val num2Str = binding.inputNum2.text.toString()

        if (num1Str.isEmpty()) {
            Toast.makeText(context, R.string.enter_number, Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val num1 = num1Str.toInt()
            val result = when (operation) {
                "AND" -> {
                    if (num2Str.isEmpty()) {
                        Toast.makeText(context, R.string.enter_second_number, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val num2 = num2Str.toInt()
                    val res = num1 and num2
                    "$num1 AND $num2 = $res\n" +
                    "${Integer.toBinaryString(num1)} AND ${Integer.toBinaryString(num2)} = ${Integer.toBinaryString(res)}"
                }
                "OR" -> {
                    if (num2Str.isEmpty()) {
                        Toast.makeText(context, R.string.enter_second_number, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val num2 = num2Str.toInt()
                    val res = num1 or num2
                    "$num1 OR $num2 = $res\n" +
                    "${Integer.toBinaryString(num1)} OR ${Integer.toBinaryString(num2)} = ${Integer.toBinaryString(res)}"
                }
                "XOR" -> {
                    if (num2Str.isEmpty()) {
                        Toast.makeText(context, R.string.enter_second_number, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val num2 = num2Str.toInt()
                    val res = num1 xor num2
                    "$num1 XOR $num2 = $res\n" +
                    "${Integer.toBinaryString(num1)} XOR ${Integer.toBinaryString(num2)} = ${Integer.toBinaryString(res)}"
                }
                "NOT" -> {
                    val res = num1.inv()
                    "NOT $num1 = $res\n" +
                    "NOT ${Integer.toBinaryString(num1)} = ${Integer.toBinaryString(res)}"
                }
                "LEFT_SHIFT" -> {
                    if (num2Str.isEmpty()) {
                        Toast.makeText(context, R.string.enter_second_number, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val num2 = num2Str.toInt()
                    val res = num1 shl num2
                    "$num1 << $num2 = $res\n" +
                    "${Integer.toBinaryString(num1)} << $num2 = ${Integer.toBinaryString(res)}"
                }
                "RIGHT_SHIFT" -> {
                    if (num2Str.isEmpty()) {
                        Toast.makeText(context, R.string.enter_second_number, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val num2 = num2Str.toInt()
                    val res = num1 shr num2
                    "$num1 >> $num2 = $res\n" +
                    "${Integer.toBinaryString(num1)} >> $num2 = ${Integer.toBinaryString(res)}"
                }
                "UNSIGNED_RIGHT_SHIFT" -> {
                    if (num2Str.isEmpty()) {
                        Toast.makeText(context, R.string.enter_second_number, Toast.LENGTH_SHORT).show()
                        return
                    }
                    val num2 = num2Str.toInt()
                    val res = num1 ushr num2
                    "$num1 >>> $num2 = $res\n" +
                    "${Integer.toBinaryString(num1)} >>> $num2 = ${Integer.toBinaryString(res)}\n" +
                    "(부호 비트를 포함하여 오른쪽으로 시프트)"
                }
                "COUNT_ONES" -> {
                    val ones = Integer.bitCount(num1)
                    val binary = Integer.toBinaryString(num1)
                    "Number: $num1\n" +
                    "Binary: $binary\n" +
                    "1의 개수: $ones\n" +
                    "0의 개수: ${32 - binary.length + binary.count { it == '0' }}"
                }
                else -> ""
            }

            binding.bitOperationResult.text = result
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "올바른 숫자를 입력하세요", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

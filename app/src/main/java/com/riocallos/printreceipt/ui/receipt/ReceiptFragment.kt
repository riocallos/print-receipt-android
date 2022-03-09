package com.riocallos.printreceipt.ui.receipt

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.print.PrintHelper
import com.riocallos.printreceipt.databinding.FragmentReceiptBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReceiptFragment : Fragment() {

    private lateinit var binding: FragmentReceiptBinding
    private val viewModel: ReceiptViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReceiptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addDiscount.setOnClickListener {
            binding.addDiscount.visibility = View.GONE
            binding.discount.visibility = View.VISIBLE
            binding.removeDiscount.visibility = View.VISIBLE
            binding.total.text = "₱11.60"
        }

        binding.removeDiscount.setOnClickListener {
            binding.addDiscount.visibility = View.VISIBLE
            binding.discount.visibility = View.GONE
            binding.removeDiscount.visibility = View.GONE
            binding.total.text = "₱14.50"
        }

        binding.print.setOnClickListener {
            if(binding.addDiscount.visibility == View.VISIBLE) {
                binding.addDiscount.visibility = View.INVISIBLE
            }
            if(binding.removeDiscount.visibility == View.VISIBLE) {
                binding.removeDiscount.visibility = View.INVISIBLE
            }
            val dialog = ReceiptDialogFragment()
            dialog.isCancelable = false
            dialog.show(requireActivity().supportFragmentManager, ReceiptDialogFragment::class.java.simpleName)

            val printHelper = PrintHelper(requireActivity())
            printHelper.scaleMode = PrintHelper.SCALE_MODE_FIT
            printHelper.colorMode = PrintHelper.COLOR_MODE_MONOCHROME
            screenShot(binding.container)?.let {
                printHelper.printBitmap("Receipt Summary", it) {
                    dialog.done()
                    binding.addDiscount.visibility = View.VISIBLE
                    binding.discount.visibility = View.GONE
                    binding.removeDiscount.visibility = View.GONE
                }
            }
        }

    }

    private fun screenShot(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }


}
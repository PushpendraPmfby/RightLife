package com.example.rlapp.ai_package.ui.eatright.fragment.tab.frequentlylogged

import android.R.color.transparent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.rlapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LoggedBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logged_bottom_sheet, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialog = BottomSheetDialog(requireContext(), R.style.LoggedBottomSheetDialogTheme)
        dialog.setContentView(R.layout.fragment_frequently_logged)
        dialog.window?.setBackgroundDrawableResource(transparent)
//        val closeIcon = view.findViewById<ImageView>(R.id.close_icon)

//        closeIcon.setOnClickListener {
//            dismiss()
//        }
    }

    companion object {
        const val TAG = "LoggedBottomSheet"

        @JvmStatic
        fun newInstance() = LoggedBottomSheet().apply {
            arguments = Bundle().apply {
                // Add any required arguments here
            }
        }
    }
}


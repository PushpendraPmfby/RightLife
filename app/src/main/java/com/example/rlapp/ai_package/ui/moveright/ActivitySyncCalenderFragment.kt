package com.example.rlapp.ai_package.ui.moveright

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rlapp.R
import com.example.rlapp.ai_package.base.BaseFragment
import com.example.rlapp.ai_package.ui.eatright.fragment.YourMealLogsFragment
import com.example.rlapp.ai_package.ui.eatright.model.CalendarDateModel
import com.example.rlapp.ai_package.ui.eatright.model.CalendarSummaryModel
import com.example.rlapp.databinding.FragmentActivitySyncCalenderBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Locale


class ActivitySyncCalenderFragment : BaseFragment<FragmentActivitySyncCalenderBinding>() {
    private lateinit var icLeftArrow: ImageView
    private lateinit var txtDate: TextView
    private lateinit var icRightArrow: ImageView
    private lateinit var btnClose: ImageView
    private lateinit var recyclerCalendar: RecyclerView
    private lateinit var recyclerSummary: RecyclerView

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentActivitySyncCalenderBinding
        get() = FragmentActivitySyncCalenderBinding::inflate
    var snackbar: Snackbar? = null

    private val calendarAdapter by lazy {
        ActivityAsyncCalenderAdapter(
            requireContext(), arrayListOf(), -1, null,
            false, ::onMealLogCalenderItem
        )
    }

    private val calendarSummaryAdapter by lazy {
        ActivityAsyncClaendarSummaryAdapter(
            requireContext(), arrayListOf(), -1, null,
            false, ::onMealLogCalenderSummaryItem
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCalendar = view.findViewById(R.id.recyclerCalendar)
        recyclerSummary = view.findViewById(R.id.recyclerSummary)
        icLeftArrow = view.findViewById(R.id.icLeftArrow)
        txtDate = view.findViewById(R.id.txtDate)
        icRightArrow = view.findViewById(R.id.icRightArrow)
        btnClose = view.findViewById(R.id.btnClose)

        recyclerCalendar.layoutManager = GridLayoutManager(context, 7)
        recyclerCalendar.adapter = calendarAdapter

        recyclerSummary.layoutManager = LinearLayoutManager(context)
        recyclerSummary.adapter = calendarSummaryAdapter

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val fragment = YourMealLogsFragment()
                    val args = Bundle()
                    fragment.arguments = args
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flFragment, fragment, "landing")
                        addToBackStack("landing")
                        commit()
                    }
                }
            })

        btnClose.setOnClickListener {
            val fragment = YourActivityFragment()
            val args = Bundle()
            fragment.arguments = args
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragment, "landing")
                addToBackStack("landing")
                commit()
            }
        }

        icLeftArrow.setOnClickListener {
            txtDate.text = "Thur, 5 Feb 2025"
        }

        icRightArrow.setOnClickListener {
            txtDate.text = "Thur, 7 Feb 2025"
        }

        onMealLogCalenderItemRefresh()
        onMealLogCalenderSummaryRefresh()
    }

    private fun onMealLogCalenderItemRefresh() {

        val yearDays = generateYearCalendar()
        // val calendarDays = List(60) { CalendarDateModel(it + 1, it % 5 == 0) }

//        val calendarDays = listOf(
//            MealLogDateModel("01", "M", true),
//            MealLogDateModel("02", "T", false),
//            MealLogDateModel("03", "W", true),
//            MealLogDateModel("04", "T", false),
//            MealLogDateModel("05", "F", true),
//            MealLogDateModel("06", "S", true),
//            MealLogDateModel("07", "S", true)
//        )

        val valueLists: ArrayList<CalendarDateModel> = ArrayList()
        valueLists.addAll(yearDays as Collection<CalendarDateModel>)
        val mealLogDateData: CalendarDateModel? = null
        calendarAdapter.addAll(valueLists, -1, mealLogDateData, false)
    }

    private fun onMealLogCalenderSummaryRefresh() {

        val summaryList = listOf(
            CalendarSummaryModel("Over", "2140"),
            CalendarSummaryModel("Under", "12.3 kCal"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0")
        )

        val valueLists: ArrayList<CalendarSummaryModel> = ArrayList()
        valueLists.addAll(summaryList as Collection<CalendarSummaryModel>)
        val mealLogDateData: CalendarSummaryModel? = null
        calendarSummaryAdapter.addAll(valueLists, -1, mealLogDateData, false)
    }

    private fun onMealLogCalenderItem(
        mealLogDateModel: CalendarDateModel,
        position: Int,
        isRefresh: Boolean
    ) {

        val yearDays = generateYearCalendar()
        //   val calendarDays = List(60) { CalendarDateModel(it + 1, it % 5 == 0) }

        val valueLists: ArrayList<CalendarDateModel> = ArrayList()
        valueLists.addAll(yearDays as Collection<CalendarDateModel>)
        calendarAdapter.addAll(valueLists, position, mealLogDateModel, isRefresh)
    }

    private fun onMealLogCalenderSummaryItem(
        mealLogDateModel: CalendarSummaryModel,
        position: Int,
        isRefresh: Boolean
    ) {

        val summaryList = listOf(
            CalendarSummaryModel("Over", "2140"),
            CalendarSummaryModel("Under", "12.3 kCal"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0"),
            CalendarSummaryModel("Under", "0")
        )
        val valueLists: ArrayList<CalendarSummaryModel> = ArrayList()
        valueLists.addAll(summaryList as Collection<CalendarSummaryModel>)
        calendarSummaryAdapter.addAll(valueLists, position, mealLogDateModel, isRefresh)
    }

//    private fun generateYearDays(year: Int): List<CalendarDateModel> {
//        val calendar = Calendar.getInstance()
//        val daysList = mutableListOf<CalendarDateModel>()
//
//        for (month in 0..11) { // 0 = January, 11 = December
//            calendar.set(year, month, 1)
//            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//
//            for (day in 1..daysInMonth) {
//                daysList.add(
//                    CalendarDateModel(
//                        date = day,
//                        month = month,
//                        year = year,
//                        surplus = (day * 50) % 500 // Random surplus example
//                    )
//                )
//            }
//        }
//        return daysList
//    }

    private fun generateYearCalendar(): List<CalendarDateModel> {
        val calendar = java.util.Calendar.getInstance()
        val year = calendar.get(java.util.Calendar.YEAR)
        val daysList = mutableListOf<CalendarDateModel>()
        val currentMonth = getMonthAbbreviation(calendar.get(java.util.Calendar.MONTH))
        val dateFormat = java.text.SimpleDateFormat("EEE, dd MMM yyyy")
        val currentDate = dateFormat.format(calendar.time)
        txtDate.text = currentDate

        // Set calendar to January 1st of the given year
        calendar.set(year, java.util.Calendar.JANUARY, 1)
        val firstDayOfWeek =
            calendar.get(java.util.Calendar.DAY_OF_WEEK) // Sunday = 1, Monday = 2, etc.

        // Calculate how many previous year days we need to add to start from Monday
        val daysToFill =
            if (firstDayOfWeek == java.util.Calendar.MONDAY) 0 else (firstDayOfWeek - 2 + 7) % 7

        // Add previous year days
        calendar.add(java.util.Calendar.DAY_OF_YEAR, -daysToFill)
        for (i in 0 until daysToFill) {
            daysList.add(
                CalendarDateModel(
                    date = calendar.get(java.util.Calendar.DAY_OF_MONTH),
                    month = getMonthAbbreviation(calendar.get(java.util.Calendar.MONTH)),
                    year = calendar.get(java.util.Calendar.YEAR),
                    dayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK),
                    currentDate = currentDate,
                    currentMonth = currentMonth,
                    surplus = (i * 50) % 500 // Random surplus example
                )
            )
            calendar.add(java.util.Calendar.DAY_OF_YEAR, 1) // Move forward
        }
        // Now reset to actual year and start adding days
        calendar.set(year, java.util.Calendar.JANUARY, 1)
        while (calendar.get(java.util.Calendar.YEAR) == year) {
            daysList.add(
                CalendarDateModel(
                    date = calendar.get(java.util.Calendar.DAY_OF_MONTH),
                    month = getMonthAbbreviation(calendar.get(java.util.Calendar.MONTH)),
                    year = calendar.get(java.util.Calendar.YEAR),
                    dayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK),
                    currentDate = currentDate,
                    currentMonth = currentMonth,
                    surplus = (1 * 50) % 500 // Random surplus example
                )
            )
            calendar.add(java.util.Calendar.DAY_OF_YEAR, 1) // Move forward
        }
        return daysList
    }

    private fun getMonthAbbreviation(monthNumber: Int): String {
        val calendar = java.util.Calendar.getInstance()
        calendar.set(java.util.Calendar.MONTH, monthNumber) // Months are 0-based
        val dateFormat =
            java.text.SimpleDateFormat("MMM", Locale.ENGLISH) // "MMM" for 3-letter month
        return dateFormat.format(calendar.time)
    }
}
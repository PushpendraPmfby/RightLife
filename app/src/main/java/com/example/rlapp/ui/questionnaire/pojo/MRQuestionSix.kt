package com.example.rlapp.ui.questionnaire.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MRQuestionSix {
    @SerializedName("module")
    @Expose
    var module: String? = null

    @SerializedName("answer")
    @Expose
    var answer: EnergyAnswer? = null
}
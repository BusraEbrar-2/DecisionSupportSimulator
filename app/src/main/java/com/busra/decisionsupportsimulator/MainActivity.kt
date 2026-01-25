package com.busra.decisionsupport.ui.input

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.busra.decisionsupport.data.model.PriorityLevel
import com.busra.decisionsupport.viewmodel.DecisionViewModel
import com.busra.decisionsupportsimulator.data.model.*
import com.busra.decisionsupportsimulator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DecisionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinners()
        observeResult()

        binding.btnEvaluate.setOnClickListener {
            viewModel.evaluateDecision(
                RiskLevel.values()[binding.spinnerRisk.selectedItemPosition],
                ResourceStatus.values()[binding.spinnerResource.selectedItemPosition],
                TimeConstraint.values()[binding.spinnerTime.selectedItemPosition]
            )
        }
    }

    private fun setupSpinners() {
        binding.spinnerRisk.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, RiskLevel.values())

        binding.spinnerResource.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, ResourceStatus.values())

        binding.spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, TimeConstraint.values())
    }

    private fun observeResult() {
        viewModel.decisionResult.observe(this) { result ->

            binding.cardResult.visibility = View.VISIBLE

            binding.tvPriority.text = "Priority: ${result.priority}"
            binding.tvAction.text = "Action: ${result.recommendedAction}"
            binding.tvExplanation.text = result.explanation

            val color = when (result.priority) {
                PriorityLevel.CRITICAL -> getColor(android.R.color.holo_red_dark)
                PriorityLevel.HIGH -> getColor(android.R.color.holo_orange_dark)
                PriorityLevel.MEDIUM -> getColor(android.R.color.holo_blue_dark)
                PriorityLevel.LOW -> getColor(android.R.color.holo_green_dark)
            }

            binding.cardResult.setCardBackgroundColor(color)
        }
    }

}

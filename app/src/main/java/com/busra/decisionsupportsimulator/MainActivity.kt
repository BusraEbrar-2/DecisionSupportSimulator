package com.busra.decisionsupport.ui.input

import android.graphics.Color
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
            ArrayAdapter(this, android.R.layout.simple_spinner_item, RiskLevel.values()).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        binding.spinnerResource.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, ResourceStatus.values()).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        binding.spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, TimeConstraint.values()).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
    }

    private fun observeResult() {
        viewModel.decisionResult.observe(this) { result ->

            binding.cardResult.visibility = View.VISIBLE

            binding.tvPriority.text = "Priority: ${result.priority}"
            binding.tvAction.text = "Action: ${result.recommendedAction}"
            binding.tvExplanation.text = result.explanation

            // Accent bar rengi (🔥 olay burada)
            val accentColor = when (result.priority) {
                PriorityLevel.CRITICAL -> Color.parseColor("#EF4444") // kırmızı
                PriorityLevel.HIGH -> Color.parseColor("#F97316")     // turuncu
                PriorityLevel.MEDIUM -> Color.parseColor("#3B82F6")   // mavi
                PriorityLevel.LOW -> Color.parseColor("#10B981")      // yeşil
            }

            binding.viewAccent.setBackgroundColor(accentColor)

            // Kart arka planı hep soft kalsın
            binding.cardResult.setCardBackgroundColor(
                Color.parseColor("#F0F7FF")
            )
        }
    }
}
// ui değişikliği yapıldı
package com.busra.decisionsupport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busra.decisionsupport.data.model.*
import com.busra.decisionsupportsimulator.data.model.DecisionInput
import com.busra.decisionsupportsimulator.data.model.ResourceStatus
import com.busra.decisionsupportsimulator.data.model.RiskLevel
import com.busra.decisionsupportsimulator.data.model.TimeConstraint
import com.busra.decisionsupportsimulator.domain.engine.DecisionEngine

class DecisionViewModel : ViewModel() {

    private val decisionEngine = DecisionEngine()

    private val _decisionResult = MutableLiveData<DecisionResult>()
    val decisionResult: LiveData<DecisionResult> = _decisionResult

    fun evaluateDecision(
        riskLevel: RiskLevel,
        resourceStatus: ResourceStatus,
        timeConstraint: TimeConstraint
    ) {
        val input = DecisionInput(
            riskLevel = riskLevel,
            resourceStatus = resourceStatus,
            timeConstraint = timeConstraint
        )

        _decisionResult.value = decisionEngine.evaluate(input)
    }
}

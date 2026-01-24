package com.busra.decisionsupportsimulator.domain.engine

import com.busra.decisionsupport.data.model.DecisionResult
import com.busra.decisionsupportsimulator.data.model.DecisionInput
import com.busra.decisionsupport.data.model.*
import com.busra.decisionsupportsimulator.data.model.ResourceStatus
import com.busra.decisionsupportsimulator.data.model.RiskLevel
import com.busra.decisionsupportsimulator.data.model.TimeConstraint


class DecisionEngine  {

    fun evaluate(input : DecisionInput) : DecisionResult {


        return when {
            input.riskLevel == RiskLevel.HIGH &&
                    input.resourceStatus == ResourceStatus.CRITICAL &&
                    input.timeConstraint == TimeConstraint.CRITICAL -> {

                DecisionResult(
                    priority = PriorityLevel.CRITICAL,
                    recommendedAction = "Stabilizasyonu sağla ve süreci durdur",
                    explanation = "Yüksek risk, yetersiz kaynak ve kritik zaman kısıtı tespit edildi."
                )
            }
input.riskLevel== RiskLevel.MEDIUM && input.resourceStatus==ResourceStatus.LIMITED -> {

DecisionResult(
    priority = PriorityLevel.HIGH,
    recommendedAction = "Kaynakları optimize et ve süreci yakından izle",
    explanation = "Orta risk ve sınırlı kaynak durumu mevcut."

)


    }
            else -> {
                DecisionResult(
                    priority = PriorityLevel.MEDIUM,
                    recommendedAction = "Süreci planlandığı şekilde devam ettir",
                    explanation = "Kritik bir durum tespit edilmedi."
                )
            }





}  }}
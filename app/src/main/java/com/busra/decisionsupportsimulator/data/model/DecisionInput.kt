package com.busra.decisionsupportsimulator.data.model

data class DecisionInput (
    val riskLevel : RiskLevel,
    val resourceStatus: ResourceStatus,
    val timeConstraint: TimeConstraint
    )

    enum class RiskLevel {
        LOW, MEDIUM, HIGH
    }

    enum class ResourceStatus {
        SUFFICIENT, LIMITED, CRITICAL
    }

    enum class TimeConstraint {
        FLEXIBLE, LIMITED, CRITICAL
    }
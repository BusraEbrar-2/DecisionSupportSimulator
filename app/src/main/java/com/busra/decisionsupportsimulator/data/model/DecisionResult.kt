package com.busra.decisionsupport.data.model

data class DecisionResult(
    val priority: PriorityLevel,
    val recommendedAction: String,
    val explanation: String
)

enum class PriorityLevel {
    LOW, MEDIUM, HIGH, CRITICAL
}

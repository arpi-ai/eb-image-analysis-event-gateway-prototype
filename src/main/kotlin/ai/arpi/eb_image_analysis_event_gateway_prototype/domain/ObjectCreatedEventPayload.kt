package ai.arpi.eb_image_analysis_event_gateway_prototype.domain

data class ObjectCreatedEventPayload(
    val containerName: String,
    val outputType: String,
    val outputFilename: String,
)

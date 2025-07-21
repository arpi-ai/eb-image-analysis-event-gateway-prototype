package ai.arpi.eb_image_analysis_event_gateway_prototype.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NcpObjectStorageWebhookDto(
    @JsonProperty("container_name")
    val containerName: String,

    @JsonProperty("event_name")
    val eventName: String?,

    @JsonProperty("event_type")
    val eventType: String?,

    @JsonProperty("object_name")
    val objectName: String,

    @JsonProperty("object_length")
    val objectLength: String?,

    val region: String?,

    @JsonProperty("__ow_headers")
    val headers: Map<String, String>? = null,

    @JsonProperty("timestamp_start")
    val timestampStart: String?,

    @JsonProperty("timestamp_finish")
    val timestampFinish: String?,
)

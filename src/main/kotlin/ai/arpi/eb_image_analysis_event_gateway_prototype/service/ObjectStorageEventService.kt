package ai.arpi.eb_image_analysis_event_gateway_prototype.service

import ai.arpi.eb_image_analysis_event_gateway_prototype.domain.ObjectCreatedEventPayload

interface ObjectStorageEventService {
    fun processCreated(payload: ObjectCreatedEventPayload)
}
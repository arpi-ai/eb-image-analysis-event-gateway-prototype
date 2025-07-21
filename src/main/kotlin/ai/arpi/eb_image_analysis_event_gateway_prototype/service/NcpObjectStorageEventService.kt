package ai.arpi.eb_image_analysis_event_gateway_prototype.service

import ai.arpi.eb_image_analysis_event_gateway_prototype.domain.ObjectCreatedEventPayload
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class NcpObjectStorageEventService: ObjectStorageEventService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun processCreated(payload: ObjectCreatedEventPayload) {
        log.info(payload.toString())

    }
}
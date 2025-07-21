package ai.arpi.eb_image_analysis_event_gateway_prototype.controller

import ai.arpi.eb_image_analysis_event_gateway_prototype.controller.dto.NcpObjectStorageWebhookDto
import ai.arpi.eb_image_analysis_event_gateway_prototype.domain.ObjectCreatedEventPayload
import ai.arpi.eb_image_analysis_event_gateway_prototype.service.NcpObjectStorageEventService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Paths

@RestController
@RequestMapping("/api/v1/webhooks/object-storage")
class NcpObjectStorageWebhookController(
    private val objectStorageEventService: NcpObjectStorageEventService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/created")
    fun onObjectCreated(
        @RequestBody dto: NcpObjectStorageWebhookDto
    ) {
        log.info(dto.toString())

        val path          = Paths.get(dto.objectName)        // ex) out_graphical/transfer.png
        val outputFilename = path.fileName.toString()        // → "transfer.png"
        val outputType     = path.parent?.fileName
            ?.toString() ?: ""           // → "out_graphical" (슬래시 없으면 "")

        objectStorageEventService.processCreated(
            payload = ObjectCreatedEventPayload(
                containerName  = dto.containerName,
                outputType     = outputType,
                outputFilename = outputFilename,
            )
        )
    }
}
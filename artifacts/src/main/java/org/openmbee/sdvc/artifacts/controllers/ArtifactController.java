package org.openmbee.sdvc.artifacts.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.openmbee.sdvc.artifacts.ArtifactConstants;
import org.openmbee.sdvc.artifacts.objects.ArtifactResponse;
import org.openmbee.sdvc.core.exceptions.BadRequestException;
import org.openmbee.sdvc.core.objects.ElementsResponse;
import org.openmbee.sdvc.artifacts.service.ArtifactService;
import org.openmbee.sdvc.core.services.NodeService;
import org.openmbee.sdvc.crud.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.Map;

@RestController
@RequestMapping("/projects/{projectId}/refs/{refId}/elements")
@Tag(name = "Artifacts")
public class ArtifactController extends BaseController {

    private ArtifactService artifactService;

    @Autowired
    public void setArtifactService(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @PostMapping(value = "{elementId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("@mss.hasBranchPrivilege(authentication, #projectId, #refId, 'BRANCH_EDIT_CONTENT', false)")
    public ElementsResponse createOrUpdateArtifacts(
        @PathVariable String projectId,
        @PathVariable String refId,
        @PathVariable String elementId,
        @RequestParam("file") MultipartFile file,
        @RequestParam(required = false) Map<String, String> params,
        Authentication auth) {

        ElementsResponse response = new ElementsResponse();
        if (! file.isEmpty()) {
            return artifactService.createOrUpdate(projectId, refId, elementId, file, auth.getName(), params);
        }
        throw new BadRequestException(response.addMessage("Empty"));
    }


    @GetMapping(value = "{elementId}/{extension}")
    @PreAuthorize("@mss.hasBranchPrivilege(authentication, #projectId, #refId, 'BRANCH_READ', false)")
    public ResponseEntity getArtifactByExtension(
        @PathVariable String projectId,
        @PathVariable String refId,
        @PathVariable String elementId,
        @PathVariable String extension,
        @RequestParam(required = false) Map<String, String> params,
        Authentication auth) {

        params.put(ArtifactConstants.EXTENSION_PARAM, extension);
        ArtifactResponse artifact = artifactService.get(projectId, refId, elementId, params);

        return ResponseEntity.ok()
            .contentType(MediaType.valueOf(artifact.getMimeType()))
            .body(artifact.getData());
    }

    @GetMapping(value = "{elementId}")
    @PreAuthorize("@mss.hasBranchPrivilege(authentication, #projectId, #refId, 'BRANCH_READ', false)")
    public ResponseEntity getArtifactForElement(
        @PathVariable String projectId,
        @PathVariable String refId,
        @PathVariable String elementId,
        @RequestHeader(value = "Accept") String acceptHeader,
        @RequestParam(required = false) Map<String, String> params,
        Authentication auth) {

        params.put(ArtifactConstants.MIMETYPE_PARAM, acceptHeader);
        ArtifactResponse artifact = artifactService.get(projectId, refId, elementId, params);

        return ResponseEntity.ok()
            .contentType(MediaType.valueOf(artifact.getMimeType()))
            .body(artifact.getData());
    }

    @DeleteMapping(value = "{elementId}/{extension}")
    @PreAuthorize("@mss.hasBranchPrivilege(authentication, #projectId, #refId, 'BRANCH_EDIT_CONTENT', false)")
    public ElementsResponse deleteArtifactByExtension(
        @PathVariable String projectId,
        @PathVariable String refId,
        @PathVariable String elementId,
        @PathVariable String extension,
        @RequestParam(required = false) Map<String, String> params,
        Authentication auth) {

        params.put(ArtifactConstants.EXTENSION_PARAM, extension);
        return artifactService.disassociate(projectId, refId, elementId, auth.getName(), params);
    }
}

package org.openmbee.sdvc.crud.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openmbee.sdvc.crud.controllers.BaseController;
import org.openmbee.sdvc.crud.domains.Node;
import org.openmbee.sdvc.json.ElementJson;
import org.springframework.stereotype.Service;

@Service
public class NodeGetHelper extends NodeOperation {

    public NodeGetInfo processGetJson(List<ElementJson> elements) {
        NodeGetInfo info = initInfo(elements, null);

        for (String nodeId : info.getReqElementMap().keySet()) {
            if (!existingNodeContainsNodeId(info, nodeId)) {
                continue;
            }
            Node node = info.getExistingNodeMap().get(nodeId);
            Map<String, Object> indexElement = info.getExistingElementMap().get(nodeId);
            if (node.isDeleted()) {
                Map<String, Object> reject = new HashMap<>();
                reject.put("code", 410);
                reject.put("message", "Element deleted");
                reject.put("id", nodeId);
                reject.put("element", indexElement);
                info.getRejected().add(reject);
                continue;
            }
        }
        return info;
    }

    public Map<String, Map<String, Object>> processGetAll() {
        Set<String> indexIds = new HashSet<>();
        List<Node> existingNodes = nodeRepository.findAll();
        Map<String, Node> existingNodeMap = new HashMap<>();
        for (Node node : existingNodes) {
            indexIds.add(node.getIndexId());
            existingNodeMap.put(node.getNodeId(), node);
        }
        // bulk read existing elements in elastic
        try {

            List<Map<String, Object>> existingElements = nodeIndex.findAllById(indexIds);
            Map<String, Map<String, Object>> existingElementMap = convertToMap(existingElements, ElementJson.ID);
            return existingElementMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Remove all this
    public static Map<String, Map<String, Object>> convertToMap(List<Map<String, Object>> elements, String key) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        for (Map<String, Object> elem : elements) {
            if (elem == null) {
                continue;
            }
            String id = (String) elem.get(key);
            if (id != null && !id.equals("")) {
                result.put(id, elem);
            }
        }
        return result;
    }
}

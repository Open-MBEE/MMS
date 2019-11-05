package org.openmbee.sdvc.rdb.repositories;

import org.openmbee.sdvc.core.dao.WebhookDAO;
import org.openmbee.sdvc.data.domains.global.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebhookDAOImpl implements WebhookDAO {
    private WebhookRepository webhookRepository;

    @Autowired
    public void setWebhookRepository(WebhookRepository webhookRepository) {
        this.webhookRepository = webhookRepository;
    }

    @Override
    public List<Webhook> findAllByProject_ProjectId(String id) {
        return webhookRepository.findAllByProject_ProjectId(id);
    }

    @Override
    public Webhook save(Webhook webhook) {
        return webhookRepository.save(webhook);
    }

    @Override
    public void delete(Webhook org) {
        webhookRepository.delete(org);
    }

    @Override
    public List<Webhook> findAll() {
        return webhookRepository.findAll();
    }
}

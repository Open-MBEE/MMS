package org.openmbee.sdvc.crud.domains;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "commits")
public class Commit implements Serializable {

    private static final long serialVersionUID = -1904568893497416476L;

    @Id
    @GeneratedValue(
        strategy= GenerationType.IDENTITY
    )
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false)
    @CreatedDate
    private Instant timestamp;

    private String elasticId;
    private String branchId;
    private String creator;

    @Column(columnDefinition = "smallint")
    private CommitType commitType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getElasticId() {
        return elasticId;
    }

    public void setElasticId(String elasticId) {
        this.elasticId = elasticId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public CommitType getCommitType() {
        return commitType;
    }

    public void setCommitType(CommitType commitType) {
        this.commitType = commitType;
    }

}

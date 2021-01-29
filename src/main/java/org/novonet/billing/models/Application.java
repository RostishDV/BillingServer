package org.novonet.billing.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private StatusType status;

    private String title;

    private String description;

    @Column(name = "publication_date")
    private Date publicationDate;

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}

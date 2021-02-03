package org.novonet.billing.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(length = 10)
    private String status;

    @Column(length = 50)
    private String title;

    private String description;

    @Column(name = "publication_date")
    private Date publicationDate;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", referencedColumnName = "id",
//            insertable = false, updatable = false)
//    private Subscriber subscriber;

    public Application() {
    }

    public Application(long subscriberId, String status, String title, String description) {
        this.subscriberId = subscriberId;
        this.status = status;
        this.title = title;
        this.description = description;
        publicationDate = new Date();
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

//    public Subscriber getSubscriber() {
//        return subscriber;
//    }
//
//    public void setSubscriber(Subscriber subscriber) {
//        this.subscriber = subscriber;
//    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Application)){
            return false;
        }
        Application other = (Application) obj;
        return this.id.equals(other.id);
    }
}

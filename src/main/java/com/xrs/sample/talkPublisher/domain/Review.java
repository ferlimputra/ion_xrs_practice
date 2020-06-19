package com.xrs.sample.talkpublisher.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.iontrading.isf.committer.spi.AbstractPersistablePublishableEntity;
import com.iontrading.isf.committer.spi.DomainEntity;
import com.iontrading.isf.committer.spi.RevisionIdentifier;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

@Entity
@Table(name = "REVIEW")
@TalkType(name = "Review")
public class Review extends AbstractPersistablePublishableEntity implements DomainEntity {

  private static final long serialVersionUID = 3444707528957288172L;

  @Transient
  private transient RevisionIdentifier revId;

  @TalkProperty
  @Column(name = "REVIEW_ID")
  private String reviewId;

  @TalkProperty
  @Column(name = "REVIEWER")
  private String reviewer;

  @TalkProperty
  @Column(name = "RATING")
  private double rating;

  public Review() {
  }

  public Review(String reviewId, String reviewer, double rating) {
    this.reviewId = reviewId;
    this.reviewer = reviewer;
    this.rating = rating;
  }

  @Override
  @Transient
  public RevisionIdentifier getIdentifier() {
    return revId;
  }

  @Override
  public void setIdentifier(RevisionIdentifier newId) {
    revId = newId;
  }

  @Override
  @Transient
  public RevisionIdentifier getPreviousIdentifier() {
    return revId;
  }

  public String getReviewId() {
    return reviewId;
  }

  public void setReviewId(String reviewId) {
    this.reviewId = reviewId;
  }

  public String getReviewer() {
    return reviewer;
  }

  public void setReviewer(String reviewer) {
    this.reviewer = reviewer;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

}

package me.nlighten.backend.db.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWER")
public class Answer {

  @Id
  private String id = UUID.randomUUID().toString();

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "TEXT")
  private String text;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "QUESTION")
  private Question question;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

}

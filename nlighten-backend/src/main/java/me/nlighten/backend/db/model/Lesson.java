package me.nlighten.backend.db.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LESSON")
public class Lesson {

  @Id
  private String id = UUID.randomUUID().toString();

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "LESSON_ORDER")
  private int order;

  @Column(name = "DURATION")
  private int duration;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "LESSON_ID")
  private Set<Comment> comments;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "LESSON_ID")
  private Set<Question> questions;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "COURSE")
  private Course course;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Set<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}

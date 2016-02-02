package me.nlighten.backend.db.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
public class Question {

  @Id
  private String id = UUID.randomUUID().toString();

  @Column(name = "COURSE_ID")
  private String courseId;

  @Column(name = "LESSON_ID")
  private String lessonId;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "TEXT")
  private String text;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Answer> answers;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getLessonId() {
    return lessonId;
  }

  public void setLessonId(String lessonId) {
    this.lessonId = lessonId;
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

  public Set<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(Set<Answer> answers) {
    this.answers = answers;
  }

}

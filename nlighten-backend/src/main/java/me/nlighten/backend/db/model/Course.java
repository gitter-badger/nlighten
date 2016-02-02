package me.nlighten.backend.db.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import me.nlighten.backend.db.enums.CourseType;
import me.nlighten.backend.db.enums.Difficult;

@Entity
@Table(name = "COURSE")
public class Course {

  @Id
  private String id = UUID.randomUUID().toString();

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "TAGS")
  private String tags;

  @Column(name = "AUTHOR")
  private String author;

  @Column(name = "TYPE")
  @Enumerated(EnumType.STRING)
  private CourseType CourseType;

  @Column(name = "LANGUAGE")
  private String language;

  @Column(name = "DURATION")
  private String duration;

  @Column(name = "DIFFICULTY")
  private Difficult difficulty;

  @Column(name = "RELEASED")
  private Date released;

  @Column(name = "RATING")
  private int rating;

  @Column(name = "RESOURCES")
  private String resources;

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Lesson> lessons;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "COURSE_ID")
  private Set<Comment> comments;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "COURSE_ID")
  private Set<Question> questions;

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

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public CourseType getCourseType() {
    return CourseType;
  }

  public void setCourseType(CourseType CourseType) {
    this.CourseType = CourseType;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public Difficult getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficult difficulty) {
    this.difficulty = difficulty;
  }

  public Date getReleased() {
    return released;
  }

  public void setReleased(Date released) {
    this.released = released;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getResources() {
    return resources;
  }

  public void setResources(String resources) {
    this.resources = resources;
  }

  public Set<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(Set<Lesson> lessons) {
    this.lessons = lessons;
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
}

package me.nlighten.backend.test.db.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import me.nlighten.backend.db.enums.CourseType;
import me.nlighten.backend.db.enums.Difficult;
import me.nlighten.backend.db.model.Answer;
import me.nlighten.backend.db.model.Comment;
import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.db.model.Lesson;
import me.nlighten.backend.db.model.Question;

public class EntityCreatorUtility {

  private static Random random = new Random();

  private EntityCreatorUtility() {}

  public static Course createCourse() {
    Course course = new Course();
    course.setName("name" + random.nextInt());
    course.setDescription("description" + random.nextInt());
    course.setTags("tags" + random.nextInt());
    course.setAuthor("author" + random.nextInt());
    course.setCourseType(CourseType.values()[random.nextInt(CourseType.values().length)]);
    course.setLanguage("language" + random.nextInt());
    course.setDuration("duration" + random.nextInt());
    course.setDifficulty(Difficult.values()[random.nextInt(Difficult.values().length)]);
    course.setReleased(new Date());
    course.setRating(random.nextInt());
    course.setResources("resources" + random.nextInt());

    Set<Lesson> lessons = new HashSet<>();
    Set<Comment> comments = new HashSet<>();
    Set<Question> questions = new HashSet<>();

    for (int i = 0; i < random.nextInt(3); i++) {
      lessons.add(createLesson(course));
      comments.add(createComment());
      questions.add(createQuestion());
    }

    course.setLessons(lessons);
    course.setComments(comments);
    course.setQuestions(questions);
    return course;
  }

  public static Lesson createLesson(Course course) {
    Lesson lesson = new Lesson();
    lesson.setName("name" + random.nextInt());
    lesson.setDescription("description" + random.nextInt());
    lesson.setOrder(random.nextInt());
    lesson.setDuration(random.nextInt());

    Set<Comment> comments = new HashSet<>();
    Set<Question> questions = new HashSet<>();
    for (int i = 0; i < random.nextInt(3); i++) {
      comments.add(createComment());
      questions.add(createQuestion());
    }

    lesson.setComments(comments);
    lesson.setQuestions(questions);
    lesson.setCourse(course);
    return lesson;
  }

  public static Comment createComment() {
    Comment comment = new Comment();
    comment.setUserName("userName" + random.nextInt());
    comment.setText("text" + random.nextInt());
    return comment;
  }

  public static Question createQuestion() {
    Question question = new Question();
    question.setUserName("userName" + random.nextInt());
    question.setText("text" + random.nextInt());

    Set<Answer> answers = new HashSet<>();
    for (int i = 0; i < random.nextInt(3); i++) {
      answers.add(createAnswer(question));
    }

    question.setAnswers(answers);
    return question;
  }

  public static Answer createAnswer(Question question) {
    Answer answer = new Answer();
    answer.setUserName("userName" + random.nextInt());
    answer.setText("text" + random.nextInt());
    answer.setQuestion(question);
    return answer;
  }

}

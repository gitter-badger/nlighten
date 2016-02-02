package me.nlighten.backend.db.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.db.model.Course_;
import me.nlighten.backend.db.model.Lesson;
import me.nlighten.backend.db.model.Lesson_;
import me.nlighten.backend.db.model.Question;
import me.nlighten.backend.db.model.Question_;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CourseDAO {

  @Inject
  private EntityManager em;

  @Inject
  private Logger logger;

  public Course findById(String id) throws Exception {
    Course result = null;
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Course> cq = cb.createQuery(Course.class);
      Root<Course> root = cq.from(Course.class);

      cq.where(cb.equal(root.get(Course_.id), id));
      TypedQuery<Course> typedQuery = em.createQuery(cq);
      result = typedQuery.getSingleResult();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return result;
  }

  public Course loadById(String id) throws Exception {
    Course result = null;
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Course> cq = cb.createQuery(Course.class);
      Root<Course> root = cq.from(Course.class);
      Fetch<Course, Lesson> lessons = root.fetch(Course_.lessons, JoinType.LEFT);
      lessons.fetch(Lesson_.comments, JoinType.LEFT);
      Fetch<Lesson, Question> lessonQuestions = lessons.fetch(Lesson_.questions, JoinType.LEFT);
      lessonQuestions.fetch(Question_.answers, JoinType.LEFT);
      root.fetch(Course_.comments, JoinType.LEFT);
      Fetch<Course, Question> courseQuestions = root.fetch(Course_.questions, JoinType.LEFT);
      courseQuestions.fetch(Question_.answers, JoinType.LEFT);

      cq.where(cb.equal(root.get(Course_.id), id));
      TypedQuery<Course> typedQuery = em.createQuery(cq);
      result = typedQuery.getSingleResult();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return result;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Course save(Course course) throws Exception {
    try {
      Course foundCourse = em.find(Course.class, course.getId());
      if (foundCourse != null) {
        course = em.merge(course);
      } else {
        em.persist(course);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return course;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public boolean delete(Course course) throws Exception {
    boolean deleted = false;
    try {
      if (!em.contains(course)) {
        course = em.merge(course);
      }
      em.remove(course);
      deleted = true;
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return deleted;
  }

}

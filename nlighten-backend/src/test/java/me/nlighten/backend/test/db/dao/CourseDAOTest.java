package me.nlighten.backend.test.db.dao;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import me.nlighten.backend.db.dao.CourseDAO;
import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.test.AbstractTest;

@RunWith(Arquillian.class)
public class CourseDAOTest extends AbstractTest {

  @Inject
  private Logger logger;

  @Inject
  private CourseDAO courseDAO;

  @Inject
  UserTransaction utx;

  @Test
  public void saveTest() throws Exception {
    try {
      Course course = EntityCreatorUtility.createCourse();
      Course savedCourse = courseDAO.save(course);
      Assert.assertNotNull(savedCourse);
      Assert.assertEquals(course.getId(), savedCourse.getId());
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }

  @Test
  public void deleteTest() throws Exception {
    try {
      Course course = EntityCreatorUtility.createCourse();
      course = courseDAO.save(course);

      boolean result = courseDAO.delete(course);
      Assert.assertTrue(result);

      Course foundCourse = courseDAO.findById(course.getId());
      Assert.assertNull(foundCourse);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }

  @Test
  public void findByIdTest() {
    try {
      Course course = EntityCreatorUtility.createCourse();
      course = courseDAO.save(course);

      Course foundCourse = courseDAO.findById(course.getId());
      Assert.assertNotNull(foundCourse);
      Assert.assertEquals(course.getId(), course.getId());
      try {
        foundCourse.getLessons().size();
        Assert.assertTrue("Lessons should not be loaded!", false);
      } catch (Exception e) {
        Assert.assertTrue(true);
      }
      try {
        foundCourse.getComments().size();
        Assert.assertTrue("Comments should not be loaded!", false);
      } catch (Exception e) {
        Assert.assertTrue(true);
      }
      try {
        foundCourse.getQuestions().size();
        Assert.assertTrue("Questions should not be loaded!", false);
      } catch (Exception e) {
        Assert.assertTrue(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }

  @Test
  public void loadByIdTest() {
    try {
      Course course = EntityCreatorUtility.createCourse();
      course = courseDAO.save(course);

      Course foundCourse = courseDAO.loadById(course.getId());
      Assert.assertNotNull(foundCourse);
      Assert.assertEquals(course.getId(), course.getId());
      try {
        Assert.assertEquals(course.getLessons().size(), foundCourse.getLessons().size());
        Assert.assertTrue(true);
      } catch (Exception e) {
        Assert.assertTrue("Lessons should be loaded!", false);
        e.printStackTrace();
      }
      try {
        Assert.assertEquals(course.getComments().size(), foundCourse.getComments().size());
        Assert.assertTrue(true);
      } catch (Exception e) {
        Assert.assertTrue("Comments should be loaded!", false);
        e.printStackTrace();
      }
      try {
        Assert.assertEquals(course.getQuestions().size(), foundCourse.getQuestions().size());
        Assert.assertTrue(true);
      } catch (Exception e) {
        Assert.assertTrue("Questions should be loaded!", false);
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }



}

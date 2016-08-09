package ucsf.dao;

import java.util.Date;
import java.util.List;
import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ucsf.models.AccessionNumberBlock;
import ucsf.models.Profile;
import ucsf.models.Trial;

/**
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class ProfileDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(Profile jd) {
    entityManager.persist(jd);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(Profile jd) {
    if (entityManager.contains(jd))
      entityManager.remove(jd);
    else
      entityManager.remove(entityManager.merge(jd));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<Profile> getAll() {
    return entityManager.createQuery("from Profile").getResultList();
  }
  
  /**
   * Return the user having the passed email.
   */
  @SuppressWarnings("unchecked")
  public List<Profile> getByAccessionBlockId(long id) {
    return entityManager.createQuery(
        "from Profile where anbId = :id")
        .setParameter("id", id)
        .getResultList();
  }
  
  public void deleteByAnbId(Long id) {
	  String hql = "delete from Profile where anbId= :id";
	  entityManager.createQuery(hql).setParameter("id", id).executeUpdate();
	  return;
  }
  
  public void deleteAll() {
	  String hql = "delete from Profile";
	  entityManager.createQuery(hql).executeUpdate();
	  return;
  }

  public Profile getProfileById(long id) {
	    return entityManager.find(Profile.class, id);
	  }
  /**
   * Return the user having the passed id.
   */
  public Profile getByAccessionId(long id) {
    return entityManager.find(Profile.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(Profile profile) {
    entityManager.merge(profile);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
} // class UserDao

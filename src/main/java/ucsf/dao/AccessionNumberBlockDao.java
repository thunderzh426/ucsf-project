package ucsf.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ucsf.models.AccessionNumberBlock;

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
public class AccessionNumberBlockDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(AccessionNumberBlock anb) {
    entityManager.persist(anb);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(AccessionNumberBlock anb) {
    if (entityManager.contains(anb))
      entityManager.remove(anb);
    else
      entityManager.remove(entityManager.merge(anb));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<AccessionNumberBlock> getAll() {
    return entityManager.createQuery("from AccessionNumberBlock").getResultList();
  }
  
  public void deleteAll() {
	  String hql = "delete from AccessionNumberBlock";
	  entityManager.createQuery(hql).executeUpdate();
	  return;
  }
  
  /**
   * Return the user having the passed email.
   */
  public AccessionNumberBlock getByDoB(Date dob) {
    return (AccessionNumberBlock) entityManager.createQuery(
        "from User where dob = :dob")
        .setParameter("dob", dob)
        .getSingleResult();
  }

  /**
   * Return the user having the passed id.
   */
  public AccessionNumberBlock getByAccessionId(long id) {
    return entityManager.find(AccessionNumberBlock.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(AccessionNumberBlock anb) {
    entityManager.merge(anb);
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

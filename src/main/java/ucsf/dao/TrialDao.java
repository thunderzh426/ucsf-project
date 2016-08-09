package ucsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ucsf.models.Profile;
import ucsf.models.Trial;

@Repository
@Transactional
public class TrialDao {
	// ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  public void create(Trial trial) {
	    entityManager.persist(trial);
	    return;
	  }
	  
	  /**
	   * Delete the user from the database.
	   */
	  public void delete(Trial trial) {
	    if (entityManager.contains(trial))
	      entityManager.remove(trial);
	    else
	      entityManager.remove(entityManager.merge(trial));
	    return;
	  }
	  
	  public void deleteByProfileId(Long id) {
		  String hql = "delete from Trial where profileId= :id";
		  entityManager.createQuery(hql).setParameter("id", id).executeUpdate();
		  return;
	  }
	  
	  public void deleteAll() {
		  String hql = "delete from Trial";
		  entityManager.createQuery(hql).executeUpdate();
		  return;
	  }
	  
	  
	  /**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Trial> getAll() {
	    return entityManager.createQuery("from Trial").getResultList();
	  }
	  
	  /**
	   * Return the user having the passed email.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Trial> getByProfileId(long id) {
	    return entityManager.createQuery(
	        "from Trial where profileId = :id")
	        .setParameter("id", id)
	        .getResultList();
	  }

	  /**
	   * Return the user having the passed id.
	   */
	  public Trial getBytrialId(long id) {
	    return entityManager.find(Trial.class, id);
	  }

	  /**
	   * Update the passed user in the database.
	   */
	  public void update(Trial trial) {
	    entityManager.merge(trial);
	    return;
	  }

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
}

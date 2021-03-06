/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Marciano
 */
public class GenericDAO<T> {
    
	private final Class<T> classe;
        
        public GenericDAO(Class<T> classe){
            this.classe = classe;
        }
        
        public void adiciona(T t) throws SQLException{
            EntityManager em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();
        }
        
        public List<T> getTodos() throws SQLException {
            EntityManager em = new JPAUtil().getEntityManager();
            CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
            query.select(query.from(classe));            
            List<T> lista = em.createQuery(query).getResultList();
            em.close();
            return lista;
	}
        
        public void remove(T t, Long id) throws SQLException {
            EntityManager em = new JPAUtil().getEntityManager();
            t = em.getReference(classe, id);           
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            em.close();
        }       
        
        public T getById(Long id){
            EntityManager em = new JPAUtil().getEntityManager();
            T t = em.find(classe, id);
            return t;
        }
}

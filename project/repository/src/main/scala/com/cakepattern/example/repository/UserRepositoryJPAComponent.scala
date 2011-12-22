package com.cakepattern.example.repository

import api.UserRepositoryComponent
import javax.persistence.EntityManager
import com.cakepattern.example.domain.User

/**
 * @author markh
 */
trait UserRepositoryJPAComponent extends UserRepositoryComponent {
  val em: EntityManager

  def userLocator = new UserLocatorJPA(em)

  def userUpdater = new UserUpdaterJPA(em)

  class UserLocatorJPA(val em: EntityManager) extends UserLocator {
    def findAll = em.createQuery("from User", classOf[User]).getResultList
  }

  class UserUpdaterJPA(val em: EntityManager) extends UserUpdater {
    def save(user: User) {
      //we use this function to wrap the command in a transaction. JPA requires this.
      transactionally {
        em.persist(user)
      }
    }
  }

  def transactionally[T](f: => T) = {
    val tx = em.getTransaction
    tx.begin()
    val ret = f //invoke the function
    tx.commit()

    ret
  }
}
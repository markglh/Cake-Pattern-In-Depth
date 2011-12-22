package com.cakepattern.example

import domain.User
import javax.persistence.{TypedQuery, EntityManager}
import java.util.ArrayList
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import com.cakepattern.services.DefaultUserServiceComponent
import repository.UserRepositoryJPAComponent

/**
 * @author markh
 */

class UserServicesIntegrationTestSpecification extends Specification with Mockito {

  trait MockEntitManager {
    val em = mock[EntityManager]

    def expect(f: (EntityManager) => Any) {
      f(em)
    }
  }

  "findAll should use the EntityManager's typed queries" in {
    val query = mock[TypedQuery[User]]
    val users: java.util.List[User] = new ArrayList[User]()
    users.add(new User)

    val userService = new DefaultUserServiceComponent with UserRepositoryJPAComponent with MockEntitManager
    userService.expect {
      em =>
        em.createQuery("from User", classOf[User]) returns query
        query.getResultList returns users
    }

    userService.userService.findAll must_== users
  }


  //This test does the same thing but doesn't use the MockEntityManager trait
  "findAll should use the EntityManager's typed queries simple" in {
    val mem = mock[EntityManager]
    val query = mock[TypedQuery[User]]
    val users: java.util.List[User] = new ArrayList[User]()

    val userService = new DefaultUserServiceComponent with UserRepositoryJPAComponent {
      val em = mem
    }

    mem.createQuery("from User", classOf[User]) returns query
    query.getResultList returns users

    userService.userService.findAll must_== users
  }

}
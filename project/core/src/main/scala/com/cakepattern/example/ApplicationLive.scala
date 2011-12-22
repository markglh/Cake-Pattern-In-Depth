package com.cakepattern.example

import javax.persistence.Persistence
import com.cakepattern.services.DefaultUserServiceComponent
import repository.UserRepositoryJPAComponent

/**
 * @author markh
 */
object ApplicationLive {
  val userServiceComponent = new DefaultUserServiceComponent with UserRepositoryJPAComponent {
    val em = Persistence.createEntityManagerFactory("com.cakepattern.example").createEntityManager()
  }

  val userService = userServiceComponent.userService
}
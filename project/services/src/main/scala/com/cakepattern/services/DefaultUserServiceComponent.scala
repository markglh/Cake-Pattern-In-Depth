package com.cakepattern.services

import api.UserServiceComponent
import com.cakepattern.example.domain.User
import com.cakepattern.example.repository.api.UserRepositoryComponent

/**
 * @author markh
 */
trait DefaultUserServiceComponent extends UserServiceComponent {
  this: UserRepositoryComponent =>

  def userService = new DefaultUserService

  class DefaultUserService extends UserService {
    def findAll = userLocator.findAll

    def save(user: User) {
      userUpdater.save(user: User)
    }
  }

}
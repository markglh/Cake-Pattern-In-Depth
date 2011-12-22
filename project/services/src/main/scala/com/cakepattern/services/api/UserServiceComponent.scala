package com.cakepattern.services.api

import com.cakepattern.example.domain.User

/**
 * @author markh
 */
trait UserServiceComponent {
  def userService: UserService

  trait UserService {
    def findAll: java.util.List[User]

    def save(user: User)
  }

}
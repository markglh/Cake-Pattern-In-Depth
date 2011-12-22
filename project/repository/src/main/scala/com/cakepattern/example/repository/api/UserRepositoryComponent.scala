package com.cakepattern.example.repository.api

import com.cakepattern.example.domain.User

/**
 * @author markh
 */

trait UserRepositoryComponent {
  def userLocator : UserLocator
  def userUpdater : UserUpdater
  trait UserLocator {
    def findAll: java.util.List[User]
  }
  trait UserUpdater {
    def save(user: User)
  }
}
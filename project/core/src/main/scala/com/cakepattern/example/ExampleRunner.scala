package com.cakepattern.example

import com.cakepattern.example.domain.User
import scala.collection.JavaConversions._

/**
 * @author markh
 */
object ExampleRunner extends App {
  val userService = ApplicationLive.userService

  val user: User = new User
  user.setFirstName("Mark")
  user.setLastName("Harrison")

  userService.save(user)

  val users: java.util.List[User] = userService.findAll

  if (!users.isEmpty) {
    println()
    users.toList.foreach(arg => println("successfully loaded " + arg.firstName + " " + arg.lastName))
  } else {
    println("argh something went wrong!")
  }
}
package com.epam.spark.exam.model

import com.epam.spark.exam.model.User
import com.epam.spark.exam.services.UserService

import scala.collection.JavaConverters._
import scala.language.implicitConversions

/**
 * @author Dekel Levitan
 */
case class UserScala(id: Int, firstname: String, lastName: String, countryOfOrigin: String, email: String)

object UserAdapter {
  implicit def toUserScala(user: User): UserScala = {
    UserScala(user.getId, user.getFirstname, user.getLastName, user.getCountryOfOrigin, user.getEmail)
  }

  implicit def toUserScalaList(drivers: java.util.List[User]): List[UserScala] = {
    drivers.asScala.toList.map(user => toUserScala(user))
  }

}

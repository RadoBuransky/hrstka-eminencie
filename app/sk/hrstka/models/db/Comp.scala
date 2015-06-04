package sk.hrstka.models.db

import sk.hrstka.models.db.Identifiable.{Handle, Id}

case class Comp(_id: Id,
                authorId: Id,
                name: String,
                website: String,
                city: Handle,
                employeeCount: Option[Int],
                codersCount: Option[Int],
                femaleCodersCount: Option[Int],
                note: String,
                products: Boolean,
                services: Boolean,
                internal: Boolean,
                techs: Set[Handle],
                joel: Set[Int]) extends Identifiable with Authorable
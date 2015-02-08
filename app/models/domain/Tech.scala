package models.domain

import models.domain.Identifiable.Id

case class Tech(id: Id,
                authorId: Id,
                name: String,
                rating: Option[TechRating]) extends Identifiable with Authorable
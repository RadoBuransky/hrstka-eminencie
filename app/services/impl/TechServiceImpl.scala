package services.impl

import models._
import models.domain.Identifiable.{Id, _}
import models.domain.{Tech, TechRating, TechVote}
import repositories.{TechRepository, VoteLogRepository, VoteRepository}
import services.TechService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TechServiceImpl(techRepository: TechRepository,
                      techVoteRepository: VoteRepository,
                      techVoteLogRepository: VoteLogRepository) extends TechService {

  override def insert(name: String, userId: domain.Identifiable.Id) =
    techRepository.insert(
      name      = name.toLowerCase,
      authorId  = userId
    ).map(_.stringify)

  override def all() =
    techRepository.all().map(_.map(Tech(_)).sortBy(-1 * _.rating.value))

  override def voteUp(id: Id, userId: Id) = voteDelta(id, userId, 1)
  override def voteDown(id: Id, userId: Id) = voteDelta(id, userId, -1)
  
  private def voteDelta(id: Id, userId: Id, delta: Int): Future[Unit] = {
    techVoteRepository.getValue(id, userId).map { latestVoteOption =>
      val newVoteValue = latestVoteOption.getOrElse(0) + delta
      if ((newVoteValue <= TechRating.maxVoteValue) &&
        (newVoteValue >= TechRating.minVoteValue))
        vote(id, userId, delta, newVoteValue)
    }
  }

  private def vote(id: Id, userId: Id, delta: Int, value: Int): Future[Unit] = {
    techRepository.updateRating(id, delta, value).map { Unit =>
      techVoteRepository.vote(id, userId, value).map { changed =>
        if (changed)
          techVoteLogRepository.logVote(id, userId, value)
      }
    }
  }

  override def votesFor(userId: Id): Future[Seq[TechVote]] =
    techVoteRepository.getAll(userId).map(_.map(TechVote(_)))
}
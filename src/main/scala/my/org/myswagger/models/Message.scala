package my.org.myswagger.models

import java.util.{Date, UUID}

final case class Message(
    body: String,
    id: UUID = UUID.randomUUID(),
    datetime: Date = new Date(),
)


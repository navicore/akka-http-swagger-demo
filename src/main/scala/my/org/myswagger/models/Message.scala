package my.org.myswagger.models

import java.util.{Date, UUID}

final case class Message(
    body: String,
    id: UUID = UUID.randomUUID(),
    datetime: Date = new Date(),
    items: Option[List[Item]]
)

final case class Item(
    name: String,
    id: UUID = UUID.randomUUID()
)


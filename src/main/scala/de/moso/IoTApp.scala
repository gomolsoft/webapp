package de.moso

import com.mongodb.MongoClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.web.bind.annotation.RestController

/**
 * Created by sandro on 17.05.15.
 */

@SpringBootApplication
@RestController
class IoTApp

object IoTApp extends App {
  SpringApplication.run(classOf[IoTApp])

}


import com.mongodb.Mongo

@Configuration
class MongoConfig extends AbstractMongoConfiguration {

  def getDatabaseName:String = "iot"

  def mongo:Mongo = new MongoClient()

}

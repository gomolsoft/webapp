package de.moso

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by sandro on 17.05.15.
 */

@SpringBootApplication
class IoTApp

object IoTApp extends App {
  SpringApplication.run(classOf[IoTApp]);
}
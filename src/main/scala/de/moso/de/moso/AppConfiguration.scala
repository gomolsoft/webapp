import akka.actor.ActorSystem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@ComponentScan(Array("de.moso.de.moso.ActorPersistence"))
class AppConfiguration {

  @Autowired
  implicit var ctx: ApplicationContext = _

  /**
   * Actor system singleton for this application.
   */
  @Bean
  def actorSystem() = {
    val system = ActorSystem("PersistenceSystem")
    // initialize the application context in the Akka Spring Extension
    //SpringExt(system)
    system
  }
}
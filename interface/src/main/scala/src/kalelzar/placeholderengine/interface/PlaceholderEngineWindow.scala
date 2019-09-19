package src.kalelzar.placeholderengine.interface

import scala.collection.immutable.HashMap

trait PlaceholderEngineWindow extends ICallback {

  private var scenes = HashMap[String, Scene]()
  private var activeScene: Scene = _

  /**
    * Register the window features to be used by this window
    * such as starting dimensions, decorations, name, etc.
    * Should be called before ready()
    *
    * @param features the features to register.
    */
  def registerFeatures(features: Seq[WindowFeatures]): Unit

  /**
    * Initializes the window and all its WindowFeatures
    * Should be called after all features, callbacks and so on are registered
    */
  def ready(): Unit

  /**
    * Add scene to the window scene list
    *
    * @param scene the scene to add to the window
    */
  def addScene(scene: Scene): Unit = {
    scenes = scenes ++ Seq(scene.name, scene)
  }

  /**
    * Change the currently displayed scene to the one provided
    *
    * @param sceneName the name of the scene to change to
    */
  def show(sceneName: String): Unit = {
    if (!scenes.contains(sceneName))
      throw PlaceholderEngineError(s"Scene $sceneName is not registered.")
    activeScene = scenes(sceneName)
  }

}
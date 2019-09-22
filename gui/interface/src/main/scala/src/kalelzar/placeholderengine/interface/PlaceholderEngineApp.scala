package src.kalelzar.placeholderengine.interface

import src.kalelzar.placeholderengine.common.math.Vector2
import src.kalelzar.placeholderengine.interface.graphics.{Scene, WindowFeature}
import src.kalelzar.placeholderengine.interface.util.PlaceholderEngineRuntime

trait PlaceholderEngineApp {

  /**
    * Window as created by the runtime
    * size and other features must be initialized by a call
    * to initWindow otherwise the window won't be displayed.
    */
  protected val window: PlaceholderEngineWindow = PlaceholderEngineRuntime.createWindow()

  /**
    * Called once when the trait is instantiated.
    */
  def start(): Unit

  /**
    * Called every update.
    *
    * @param delta time since previous update in seconds
    */
  def update(delta: Float): Unit

  /**
    * Called when app should suspend updates.
    * Pausing while already paused does nothing.
    */
  def pause(): Unit

  /**
    * Called when app should resume updates after being paused.
    * Attempting to resume without pausing yields no result.
    */
  def resume(): Unit

  /**
    * Called once when the app is shutting down.
    */
  def stop(): Unit

  /**
    * Called every time the window is rendered to the screen.
    *
    * @param delta time elapsed since last render in seconds
    */
  def onWindowRender(delta: Float): Unit = null

  /**
    * Called every time the window is resized
    *
    * @param oldSize the old size of the screen as a two-dimensional vector
    * @param newSize the new size of the screen as a two-dimensional vector
    */
  def onWindowResize(oldSize: Vector2, newSize: Vector2): Unit

  /**
    * Called every time the scene of the window is changed
    *
    * @param oldScene the old scene
    * @param newScene the new scene
    */
  def onWindowSceneChanged(oldScene: Scene, newScene: Scene): Unit

  /**
    * Add scene to the window scene list
    *
    * @param scene - the scene to add to the window
    */
  def addSceneToWindow(scene: Scene): Unit = {
    window.addScene(scene)
  }

  /**
    * Change the currently displayed scene to the one provided
    *
    * @param scene - the name of the scene to change to
    */
  def changeScene(scene: String): Unit = {
    window.show(scene)
  }

  /**
    * Initializes Window with the provided features
    * also creates callbacks to trait methods
    * must be called for the window to be displayed.
    */
  protected def initWindow(f: WindowFeature*): Unit = {
    window.registerFeatures(f)
    import src.kalelzar.placeholderengine.interface.util.Callback._
    window.registerCallback("onWindowCreate", (onWindowCreate _).asCallback)
    window.registerCallback("onWindowUpdate", (onWindowRender _).asCallback)
    window.registerCallback("onWindowDestroyed", (onWindowDestroyed _).asCallback)
    window.registerCallback("onWindowShow", (onWindowShow _).asCallback)
    window.registerCallback("onWindowHide", (onWindowHide _).asCallback)
    window.registerCallback("onWindowResize", (onWindowResize _).asCallback)
    window.registerCallback("onWindowSceneChanged", (onWindowSceneChanged _).asCallback)

    window.ready()
  }

  /**
    * Callback for the moment the window used by the app is created.
    * Called once immediately after initWindow resolves
    */
  def onWindowCreate(window: PlaceholderEngineWindow): Unit = null

  /**
    * Called once just before the window is destroyed.
    */
  def onWindowDestroyed(): Unit = null

  /**
    * Called every time the window is unhidden (is that a word?).
    * Does nothing if the window is not hidden
    */
  def onWindowShow(): Unit = null

  /**
    * Called every time the window is hidden (is that a word?).
    * Does nothing if the window is already hidden
    */
  def onWindowHide(): Unit = null

}

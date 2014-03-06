package org.unsane.spirit.news.lib

import java.nio.file._
import net.liftweb.common.Loggable
import java.nio.file.WatchEvent.{Modifier, Kind}
import java.nio.file.StandardWatchEventKinds._
import java.io.{File, IOException}
import org.unsane.spirit.news.lib.Config._

object UploadWatcher extends Loggable {

  def run() = {
    val schedulePath = loadProps("timetable2db.schedulePath")
    val watcher = FileSystems.getDefault().newWatchService()
    val dir = FileSystems.getDefault().getPath(schedulePath)

    try {
      val key = dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
    } catch {
      case s: Exception => logger warn s
    }

    (new UploadWatchActor(watcher)).start()
  }
}

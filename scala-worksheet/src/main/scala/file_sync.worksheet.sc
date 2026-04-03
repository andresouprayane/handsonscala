import $ivy.`com.lihaoyi::os-lib::0.11.6`


def sync(src: os.Path, dest: os.Path) = {
  for (srcSubPath <- os.walk(src)) {
    val subPath = srcSubPath.subRelativeTo(src)
    val destSubPath = dest / subPath
    (os.isDir(srcSubPath), os.isDir(destSubPath)) match {
      case (false, true) | (true, false) =>
        os.copy.over(srcSubPath, destSubPath, createFolders = true)
      case (false, false)
        if !os.exists(destSubPath)
        || !os.read.bytes(srcSubPath).sameElements(os.read.bytes(destSubPath)) =>
        os.copy.over(srcSubPath, destSubPath, createFolders = true)
      case _ => // do nothing
    }
  for (destSubPath <- os.walk(dest)) {
    val subPath = destSubPath.subRelativeTo(dest)
    val srcSubPath = src / subPath
    (os.isDir(srcSubPath), os.isDir(destSubPath)) match {
      case (false, false) if !os.exists(srcSubPath) => 
            {os.remove(destSubPath)
            print("pass")
            }
      case _ => // do nothing
    }
  }

  }
}

os.pwd

sync(os.pwd / "test" / "folder_a", os.pwd / "test" / "folder_b")



















